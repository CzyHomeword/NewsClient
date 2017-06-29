package com.example.yls.newsclient;

import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.yls.newsclient.bean.NewsEntity;

/**
 * Created by yls on 2017/6/28.
 */

public class NewsDetailActivity extends BaseActivity {
    private WebView webView;
    private ProgressBar progressBar;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_new_detail;
    }

    @Override
    public void initView() {
        progressBar = (ProgressBar) findViewById(R.id.pb_01);
        initWebView();
    }


    @Override
    public void initData() {
        NewsEntity.ResultBean newsBean = (NewsEntity.ResultBean) getIntent().getSerializableExtra("news");
/*        webView.loadUrl(newsBean.getUrl());*/
        // 显示标题栏左上角的返回图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // 显示标题栏
        getSupportActionBar().setTitle(newsBean.getTitle());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();   // 标题栏左上角的返回按钮，退出当前界面
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void initWebView() {
        webView = (WebView) findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient());

        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
              if(newProgress == 100 ) {
                  progressBar.setVisibility(View.GONE);
              }
                else {
                  progressBar.setVisibility(View.VISIBLE);
                  progressBar.setProgress(newProgress);
                  System.out.println("--------percent:"+newProgress);
            }
        }
        });
    }

    @Override
    public void initListener() {

    }

}
