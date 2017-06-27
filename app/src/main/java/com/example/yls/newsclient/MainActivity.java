package com.example.yls.newsclient;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import com.example.yls.newsclient.fragment.MainFragment1;
import com.example.yls.newsclient.fragment.MainFragment2;
import com.example.yls.newsclient.fragment.MainFragment3;
import com.example.yls.newsclient.fragment.MainFragment4;
import com.example.yls.newsclient.fragment.MainFragment5;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private RadioGroup radioGroup;
    private ViewPager viewPager;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    /*  当点击RadioButton时，ViewPager显示的子界面工切换*/
    @Override
    public void initListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_01:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.rb_02:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.rb_03:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.rb_04:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.rb_05:
                        viewPager.setCurrentItem(4);
                        break;
                }
            }
        });
    /*    当ViewPager子界面发生了改变时，要选中并高亮不同的RadioButton选项卡*/
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        radioGroup.check(R.id.rb_01);
                        break;
                    case 1:
                        radioGroup.check(R.id.rb_02);
                        break;
                    case 2:
                        radioGroup.check(R.id.rb_03);
                        break;
                    case 3:
                        radioGroup.check(R.id.rb_04);
                        break;
                    case 4:
                        radioGroup.check(R.id.rb_05);
                        break;
                }
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MainFragment1());
        fragments.add(new MainFragment2());
        fragments.add(new MainFragment3());
        fragments.add(new MainFragment4());
        fragments.add(new MainFragment5());

        viewPager.setAdapter(new FragmentPagerAdapter(
                getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
    }
    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.rg_01);
        initViewPager();
        initNavigationView();
        initToolbar();
        initDrawerLayout();
    }

    private void initDrawerLayout() {
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();     // 同步drawerLayout和toolbar的状态
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        toolbar.setLogo(R.drawable.logo);
        toolbar.setTitle("Toolbar");
        toolbar.setSubtitle("这是子标题");
        toolbar.setTitleTextColor(Color.RED);
        toolbar.setSubtitleTextColor(Color.YELLOW);
// 导航栏图标显示
        toolbar.setNavigationIcon(R.drawable.btn_back);
// 点击toolbar导航栏左上角的图标后，退出当前界面
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    //================Toolbar右上角弹出菜单(begin)=======================
public  boolean onCreateOptionsaMenu(Menu menu){
    getMenuInflater().inflate(R.menu.main_option,menu);
    return  true;
}
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.item_01) {
            showToast("item 01");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //================Toolbar右上角弹出菜单(end)=========================

    private void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        // 侧滑菜单点击监听
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        // 点击侧滑菜单item时，通过DrawerLayout关闭侧滑菜单
                        showToast("" + item.getTitle());
                        drawerLayout.closeDrawers();

                        return false;
                    }
                });
    }
    }
