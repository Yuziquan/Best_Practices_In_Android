package com.wuchangi.logindemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import java.util.Locale;

public class MainActivity extends AppCompatActivity
{
    @BindView(R.id.et_user_name)
    EditText mEtUserName;

    @BindView(R.id.et_password)
    EditText mEtPassword;

    @BindView(R.id.tv_attempts_left)
    TextView mTvAttemptsLeft;

    @BindView(R.id.btn_login)
    Button mBtnLogin;

    @BindView(R.id.btn_exit)
    Button mBtnExit;

    @BindView(R.id.btn_switch_language)
    Button mBtnSwitchLanguage;

    private static int sAttemptTimes = 3;

    private String[] mItems;

    private SharedPreferences mSharedPreferences;

    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        initLocaleLanguage();

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mItems = new String[]{getResources().getString(R.string.zh), getResources().getString(R.string.en)};
    }


    private void initLocaleLanguage()
    {
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();

        configuration.locale = new Locale(mSharedPreferences.getString("language",
                Locale.getDefault().getLanguage()), "");
        resources.updateConfiguration(configuration, displayMetrics);
    }


    @OnClick({R.id.btn_login, R.id.btn_exit, R.id.btn_switch_language})
    public void handleAllClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_login:

                if (sAttemptTimes > 0)
                {
                    if ("admin".equals(mEtUserName.getText().toString()) && "12345".equals(mEtPassword.getText().toString()))
                    {
                        Toast.makeText(MainActivity.this, getResources().getString(R.string.login_success_hint), Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        sAttemptTimes--;
                        mTvAttemptsLeft.setText(sAttemptTimes + "");

                        if (sAttemptTimes == 0)
                        {
                            mBtnLogin.setEnabled(false);
                            Toast.makeText(MainActivity.this, getResources().getString(R.string.login_disabled), Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, getResources().getString(R.string.login_fail_hint), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                break;

            case R.id.btn_exit:
                finish();
                break;

            case R.id.btn_switch_language:

                final int selectedIndex;

                if (mSharedPreferences.getString("language", Locale.getDefault().getLanguage()).equals("zh"))
                {
                    selectedIndex = 0;
                }
                else
                {
                    selectedIndex = 1;
                }

                AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.select_language_title)).setSingleChoiceItems(mItems, selectedIndex, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        if (which == selectedIndex)
                        {
                            return;
                        }

                        mEditor = mSharedPreferences.edit();

                        if (which == 0)
                        {
                            mEditor.putString("language", "zh");
                            switchLanguage("zh");
                        }
                        else
                        {
                            mEditor.putString("language", "en");
                            switchLanguage("en");
                        }

                        mEditor.apply();
                    }
                }).create();

                alertDialog.show();

                break;

            default:
        }
    }


    /**
     * 语言切换
     *
     * @param language 切换的目标语言
     */
    private void switchLanguage(String language)
    {
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();

        if ("zh".equals(language))
        {
            configuration.locale = Locale.SIMPLIFIED_CHINESE;
        }
        else if ("en".equals(language))
        {
            configuration.locale = Locale.ENGLISH;
        }
        else
        {
            configuration.locale = Locale.getDefault();
        }
        resources.updateConfiguration(configuration, displayMetrics);

        finish();

        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
