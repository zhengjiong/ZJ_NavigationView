package com.zj.example.zj_navigationview;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

/**
 * 讓NavigationView覆蓋在Statusbar上面
 *
 * create by zhengjiong
 * Date: 2015-06-07
 * Time: 10:59
 */
public class NavigationViewOnStatusBarDemo extends AppCompatActivity{
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_view_on_statusbar_demo);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mNavigationView = (NavigationView) findViewById(R.id.navigationview);

        initToolBar();
        initDrawerToggle();
        initNavigationView();

        getSupportFragmentManager().beginTransaction()
                .replace(
                        R.id.content_frame,
                        BaseFragment.newInstance("item1"),
                        String.valueOf(R.id.item1))
                .commit();
    }

    private void initNavigationView() {
        //NavigationView側滑菜單的點擊事件
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.item1:
                        Snackbar.make(mNavigationView, "item1 hasSubMenu " + menuItem.hasSubMenu(), Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.item2:
                        Snackbar.make(mNavigationView, "item2 hasSubMenu " + menuItem.hasSubMenu(), Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.item3:
                        Snackbar.make(mNavigationView, "item3 hasSubMenu " + menuItem.hasSubMenu(), Snackbar.LENGTH_SHORT).show();
                    case R.id.type2_item1:
                        Snackbar.make(mNavigationView, "type2_item1 hasSubMenu " + menuItem.hasSubMenu(), Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.type2_item2:
                        Snackbar.make(mNavigationView, "type2_item2 hasSubMenu " + menuItem.hasSubMenu(), Snackbar.LENGTH_SHORT).show();
                        break;
                }

                Log.i("zj", "getGroupId()=" + menuItem.getGroupId());
                menuItem.setChecked(true);//設置item為選中狀態
                mDrawerLayout.closeDrawers();//關閉drawer
                getSupportFragmentManager().beginTransaction()
                        .replace(
                                R.id.content_frame,
                                BaseFragment.newInstance(menuItem.getTitle().toString()),
                                String.valueOf(menuItem.getItemId()))
                        .commit();
                return true;
            }
        });

        //設置進入的時候,默認選中第一個,下面兩種方式都可以
        mNavigationView.getMenu().getItem(0).setChecked(true);
        //mNavigationView.getMenu().findItem(R.id.item1).setChecked(true);
    }

    private void initDrawerToggle() {
        //設置ActionBarDrawerToggle,和打開關閉listener
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.openRes, R.string.closeRes){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //drawer關閉
                Log.i("zj", "onDrawerClosed");
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //drawer打開
                Log.i("zj", "onDrawerOpened");
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void initToolBar() {
        mToolbar.setTitle("NavigationViewOnStatusBarDemo");
        //這裡不用設置toolbar的titleColor,在style中用textColorPrimary
        //mToolbar.setTitleTextColor(Color.WHITE);

        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);//设置返回键可用
    }

    /**
     * onPostCreate是activity創建完成以後執行的
     *
     * 設置這個才可以顯示出左上角3條橫線的圖標
     * @param savedInstanceState
     */
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
}
