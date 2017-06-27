package com.example.yls.newsclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity {

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        initView();
        initListener();
          initData();
      }

    protected abstract int getLayoutRes();
    public abstract void initListener();
    public abstract void initView();
    public abstract void initData();

    private Toast mToast;

    public void showToast(String msg){
        if(mToast == null){
            mToast = Toast.makeText(this,"", Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }

}
