package com.example.yls.newsclient.fragment;

import android.app.AlertDialog;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yls.newsclient.R;
import com.example.yls.newsclient.adapter.NewsAdapter;
import com.example.yls.newsclient.bean.NewsEntity;
import com.example.yls.newsclient.bean.URLManager;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class NewsFragment extends BaseFragment{

    /** 新闻类别id */
    private String channelId;

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_news;
    }

    @Override
    public void initView() {
        TextView textView = (TextView) mRoot.findViewById(R.id.tv_01);
        textView.setText(channelId);    // 显示新闻类别id，以作区分
    }

    @Override
    public void initListener() {
    }

    @Override
    public void initData() {
        getDataFromServer();
    }

    private void getDataFromServer() {
        String url= URLManager.getUrl(channelId);
        HttpUtils utilts = new HttpUtils();
        utilts.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String json=  responseInfo.result;
                System.out.println("----服务器返回的json数据:" + json);
                json = json.replace(channelId,"result");
                Gson gson = new Gson();
                NewsEntity newsDatas = gson.fromJson(json,NewsEntity.class);
                System.out.println("----解析json:" + newsDatas.getResult().size());
                // 显示数据到列表中
                showDatas(newsDatas);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                error.printStackTrace();

            }
        });
    }

    private void showDatas(NewsEntity newsDatas) {
        if(newsDatas == null|| newsDatas.getResult() == null ||
                newsDatas.getResult().size() == 0){
            System.out.println("----没有获取到服务器的新闻数据");
            return;
        }
        // （1）显示轮播图


        // （2）显示新闻列表
        NewsAdapter newsAdapter = new NewsAdapter(mActivity,newsDatas.getResult());
        listView.setAdapter(newsAdapter);
    }
}
