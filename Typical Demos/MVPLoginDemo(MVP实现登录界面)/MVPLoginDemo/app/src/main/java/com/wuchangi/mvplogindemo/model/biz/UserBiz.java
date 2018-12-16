package com.wuchangi.mvplogindemo.model.biz;

import com.wuchangi.mvplogindemo.model.bean.User;

/**
 * Created by WuchangI on 2018/11/7.
 */

public class UserBiz implements IUserBiz
{

    @Override
    public void login(final String username, final String password, final OnLoginListener onLoginListener)
    {
        new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(2000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

                if("admin".equals(username) && "12345".equals(password))
                {
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    onLoginListener.loginSuccess(user);
                }
                else
                {
                    onLoginListener.loginFailed();
                }
            }
        }.start();
    }
}
