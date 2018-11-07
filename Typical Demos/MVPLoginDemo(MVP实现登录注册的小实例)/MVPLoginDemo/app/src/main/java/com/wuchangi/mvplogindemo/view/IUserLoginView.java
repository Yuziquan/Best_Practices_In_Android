package com.wuchangi.mvplogindemo.view;

import com.wuchangi.mvplogindemo.model.bean.User;

/**
 * Created by WuchangI on 2018/11/7.
 */

public interface IUserLoginView
{
    String getUserName();

    String getPassword();

    void clearUserName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(User user);

    void showFailedError();
}
