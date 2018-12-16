package com.wuchangi.mvplogindemo.model.biz;

import com.wuchangi.mvplogindemo.model.bean.User;

/**
 * Created by WuchangI on 2018/11/7.
 */

public interface OnLoginListener
{
    void loginSuccess(User user);

    void loginFailed();
}
