package com.example.yls.newsclient;

import android.content.Intent;
import android.os.SystemClock;

public class StartActivity extends BaseActivity {
    @Override
    public int getLayoutRes() {
        return R.layout.activity_start;
    }


    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }
    @Override
    public void initData() {
       new Thread(){
           @Override
           public void run() {
               SystemClock.sleep(1500);
               boolean firstRun = SharedPrefUtil.getBoolean(
                       getApplicationContext(), "firstRun", true);
               if (firstRun) {
                   SharedPrefUtil.saveBoolean(StartActivity.this,
                           "firstRun", false);
                   enterGuideActivity();
               } else {
                   enterMainActivity();
               }
           }
       }.start();
    }
private void   enterGuideActivity(){
    Intent intent = new Intent(this,GuideActivity.class);
    startActivity(intent);
    finish();
}
    private void   enterMainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
