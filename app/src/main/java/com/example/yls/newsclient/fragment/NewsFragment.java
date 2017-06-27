package com.example.yls.newsclient.fragment;

import android.app.Activity;
import android.widget.TextView;

import com.example.yls.newsclient.BaseActivity;
import com.example.yls.newsclient.R;

public class NewsFragment extends BaseActivity{
    private String channelId;
    private Activity mRoot;

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_news;
    }


    @Override
    public void initListener() {

    }

    @Override
    public void initView() {
        TextView textView = (TextView) mRoot.findViewById(R.id.tv_01);
        textView.setText(channelId);    // 显示新闻类别id，以作区分
    }

    @Override
    public void initData() {

    }
}


