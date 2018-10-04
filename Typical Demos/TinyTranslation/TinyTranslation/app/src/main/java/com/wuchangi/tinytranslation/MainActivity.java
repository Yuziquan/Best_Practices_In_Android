package com.wuchangi.tinytranslation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "MainActivity";

    @BindView(R.id.et_source_text)
    EditText etSourceText;

    @BindView(R.id.tv_translate_result)
    TextView tvTranslateResult;

    @BindView(R.id.iv_translate)
    ImageView ivTranslate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        ButterKnife.bind(this);
    }


    // 发送翻译请求
    public void sendTranslateRequest(String sourceText)
    {
        // 创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 创建翻译请求接口的实例
        TranslateRequest translateRequest = retrofit.create(TranslateRequest.class);

        // 对待发送的请求进行封装，并传入要翻译的原文
        Call<Translation> translateCall = translateRequest.getTranslateResult(sourceText);

        // 发送请求（异步方式）
        translateCall.enqueue(new Callback<Translation>()
        {
            // 成功响应（请求成功时回调）
            @Override
            public void onResponse(Call<Translation> call, final Response<Translation> response)
            {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        tvTranslateResult.setText(response.body().getTranslateResult().get(0).get(0).getTgt());
                    }
                });
            }

            // 响应失败（请求失败时回调）
            @Override
            public void onFailure(Call<Translation> call, Throwable t)
            {
                Log.d(TAG, "请求失败！");
                Log.d(TAG, t.getMessage());
            }
        });
    }


    @OnClick({R.id.iv_translate})
    public void translate()
    {
        String sourceText = etSourceText.getText().toString();
        sendTranslateRequest(sourceText);
    }


}
