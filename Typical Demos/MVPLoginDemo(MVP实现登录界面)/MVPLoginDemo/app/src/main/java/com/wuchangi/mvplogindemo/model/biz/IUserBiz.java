package com.wuchangi.mvplogindemo.model.biz;

/**
 * Created by WuchangI on 2018/11/7.
 */

public interface IUserBiz
{
    void login(String username, String password, OnLoginListener onLoginListener);
}
