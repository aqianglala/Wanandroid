package com.example.wanandroid.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.example.library.app.BaseActivity;
import com.example.library.app.BasePresenter;
import com.example.wanandroid.R;

public class DetailsActivity extends BaseActivity {

    private static final String KEY_TITLE = "title";
    private static final String KEY_URL = "url";
    private WebView myWebView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        String title = null;
        String url = null;
        if (intent != null) {
            title = intent.getStringExtra(KEY_TITLE);
            url = intent.getStringExtra(KEY_URL);
        }
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(url)) {
            return;
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(title);
        }

        // 1. 添加webview
        FrameLayout fl_container = findViewById(R.id.fl_container);
        myWebView = new WebView(this);
        FrameLayout.LayoutParams layoutParams =
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
        fl_container.addView(myWebView, layoutParams);
        // 2. 配置webview
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);// 开启JavaScript
        myWebView.setWebViewClient(new WebViewClient());// 点击网页中的链接时不跳转到浏览器
        // 安全浏览服务：即验证网址，遇到威胁时会显示继续还是返回安全页面，默认开启；可以选择关闭也可以自定义响应威胁的方式，一般不用处理。
        // 定位：看需求是否要加
        // 处理渲染器进程消失：极度优化才要处理了，一般不用
        // 视口问题，即网页在手机上显示的问题。应该由网页处理，但webView可以通过以下方法确保页面能较好地显示
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        // 3. 加载网页
        myWebView.loadUrl(url);
        // 4. 处理销毁
        // 5. 返回键处理
        // 6. AndroidManifest 中阻止配置更改 android:configChanges="orientation|keyboardHidden"
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        if (myWebView != null) {
            ((ViewGroup) myWebView.getParent()).removeView(myWebView);
            myWebView.destroy();
            myWebView = null;
        }
        super.onDestroy();
    }

    public static void start(Context context, String title, String url) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(KEY_TITLE, title);
        intent.putExtra(KEY_URL, url);
        context.startActivity(intent);
    }

}
