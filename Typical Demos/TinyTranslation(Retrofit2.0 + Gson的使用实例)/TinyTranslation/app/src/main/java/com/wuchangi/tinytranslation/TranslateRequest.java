package com.wuchangi.tinytranslation;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by WuchangI on 2018/10/4.
 */

// 描述发送的翻译请求的接口
public interface TranslateRequest
{
    // 采用@FormUrlEncoded注解: 因为API规定采用请求格式x-www-form-urlencoded,即表单形式
    @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    @FormUrlEncoded
    Call<Translation> getTranslateResult(@Field("i") String sourceText);
}
