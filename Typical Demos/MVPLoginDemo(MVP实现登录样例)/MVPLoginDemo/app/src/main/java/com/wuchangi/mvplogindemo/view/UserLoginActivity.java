package com.wuchangi.mvplogindemo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.wuchangi.mvplogindemo.R;
import com.wuchangi.mvplogindemo.model.bean.User;
import com.wuchangi.mvplogindemo.presenter.UserLoginPresenter;

public class UserLoginActivity extends AppCompatActivity implements IUserLoginView
{
    @BindView(R.id.et_user_name)
    EditText mEtUserName;

    @BindView(R.id.et_password)
    EditText mEtPassword;

    @BindView(R.id.btn_login)
    Button mBtnLogin;

    @BindView(R.id.btn_clear)
    Button mBtnClear;

    @BindView(R.id.pb_loading)
    ProgressBar mPbLoading;


    private UserLoginPresenter mUserLoginPresenter = new UserLoginPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        ButterKnife.bind(this);
    }



    @OnClick({R.id.btn_login, R.id.btn_clear})
    public void handleAllClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_login:
                mUserLoginPresenter.login();
                break;

            case R.id.btn_clear:
                mUserLoginPresenter.clear();
                break;

            default:
        }
    }




    @Override
    public String getUserName()
    {
        return mEtUserName.getText().toString();
    }

    @Override
    public String getPassword()
    {
        return mEtPassword.getText().toString();
    }

    @Override
    public void clearUserName()
    {
        mEtUserName.setText("");
    }

    @Override
    public void clearPassword()
    {
       mEtPassword.setText("");
    }

    @Override
    public void showLoading()
    {
        mPbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading()
    {
        mPbLoading.setVisibility(View.GONE);
    }

    @Override
    public void toMainActivity(User user)
    {
        Toast.makeText(this, user.getUsername() + " 登录成功, 跳转至 MainActivity...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError()
    {
        Toast.makeText(this, "登录失败！", Toast.LENGTH_SHORT).show();
    }
}
