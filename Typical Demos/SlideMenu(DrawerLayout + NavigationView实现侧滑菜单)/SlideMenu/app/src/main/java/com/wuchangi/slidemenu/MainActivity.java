package com.wuchangi.slidemenu;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;

public class
MainActivity extends AppCompatActivity
{
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        /* 用工具栏Toolbar替换原来的标题栏ActionBar。
          ( 注意要先在values/styles.xml文件中修改应用的主题为
            1. parent="Theme.AppCompat.Light.NoActionBar"
         或 2. parent="Theme.AppCompat.NoActionBar")
        */
        setSupportActionBar(mToolbar);

        // 此时获取的ActionBar的具体实现是Toolbar
        ActionBar actionBar = getSupportActionBar();

        // 为Toolbar最左边添加一个导航按钮
        if (actionBar != null)
        {
            // 让HomeAsUp按钮可见
            actionBar.setDisplayHomeAsUpEnabled(true);
            // 设置HomeAsUp按钮的图标
            actionBar.setHomeAsUpIndicator(R.drawable.navigation);
        }

        Resources resources = getBaseContext().getResources();

        // 设置侧滑菜单的菜单项图标icon颜色
        mNavigationView.setItemIconTintList(resources.getColorStateList(R.drawable.nav_menu_item_color));

        // 设置侧滑菜单的菜单项文字颜色
        mNavigationView.setItemTextColor(resources.getColorStateList(R.drawable.nav_menu_item_color));

        // 设置侧滑菜单默认选中项
        mNavigationView.setCheckedItem(R.id.nav_profile);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            // 处理HomeAsUp按钮的点击事件，HomeAsUp按钮的id一直为android.R.id.home
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;

            default:
        }
        return true;
    }
}
