package com.wuchangi.mvplogindemo.presenter;

import android.os.Handler;
import com.wuchangi.mvplogindemo.model.bean.User;
import com.wuchangi.mvplogindemo.model.biz.IUserBiz;
import com.wuchangi.mvplogindemo.model.biz.OnLoginListener;
import com.wuchangi.mvplogindemo.model.biz.UserBiz;
import com.wuchangi.mvplogindemo.view.IUserLoginView;



/**
 * Created by WuchangI on 2018/11/7.
 */

public class UserLoginPresenter
{
    private IUserBiz mUserBiz;
    private IUserLoginView mUserLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView)
    {
        this.mUserLoginView = userLoginView;
        this.mUserBiz = new UserBiz();
    }

    public void login()
    {
        mUserLoginView.showLoading();

        mUserBiz.login(mUserLoginView.getUserName(), mUserLoginView.getPassword(), new OnLoginListener()
        {
            @Override
            public void loginSuccess(final User user)
            {
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        mUserLoginView.toMainActivity(user);
                        mUserLoginView.hideLoading();
                    }
                });
            }

            @Override
            public void loginFailed()
            {
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        mUserLoginView.showFailedError();
                        mUserLoginView.hideLoading();
                    }
                });
            }
        });
    }

    public void clear()
    {
        mUserLoginView.clearUserName();
        mUserLoginView.clearPassword();
    }


}
