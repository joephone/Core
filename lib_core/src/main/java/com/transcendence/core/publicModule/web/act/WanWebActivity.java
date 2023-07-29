package com.transcendence.core.publicModule.web.act;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.transcendence.core.R;
import com.transcendence.core.utils.StatusBarUtils;


/**
 * @author Joephone on 2019/12/17 18:24
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class WanWebActivity extends AppCompatActivity {


    private WebView mWebView;
    private ImageView ivBack;
    private ImageView ivMenu;
    private ImageView ivForward;
    private TextView tvTitle;
    private FrameLayout flBack;

    private int mArticleId = -1;
    private String mTitle = "";
    private String mAuthor = "";
    private String mUrl = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        StatusBarUtils.with(WanWebActivity.this).init();

        mWebView = findViewById(R.id.webView);

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(v -> {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
            } else {
                finish();
            }
        });
        ivMenu = findViewById(R.id.iv_menu);

        ivForward = findViewById(R.id.iv_forward);
        ivForward.setOnClickListener(v -> {
            if (mWebView.canGoForward()) {
                mWebView.goForward();
            }
        });
        tvTitle = findViewById(R.id.tv_title);


        mArticleId = getIntent().getIntExtra("articleId", -1);
        mTitle = getIntent().getStringExtra("title");
        mTitle = mTitle == null ? "" : mTitle;
        mAuthor = getIntent().getStringExtra("author");
        tvTitle.setText(mTitle = mTitle == null ? "" : mTitle);
        mUrl = getIntent().getStringExtra("url");
        mUrl = mUrl == null ? "" : mUrl;
//        boolean collected = getIntent().getBooleanExtra("collected", false);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());

        mWebView.loadUrl(mUrl);
    }

//    public static void start(Context context, BannerBean.DataBean item) {
//        int articleId = item.getOriginId() != 0 ? item.getOriginId() : item.getId();
//        start(context, articleId, item.getTitle(), item.getLink(), item.isCollect());
//    }

    public static void start(Context context, int articleId, String title, String url,String author, boolean collected) {
        Intent intent = new Intent(context, WanWebActivity.class);
        intent.putExtra("articleId", articleId);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        intent.putExtra("author", author);
        intent.putExtra("collected", collected);
        context.startActivity(intent);
    }


    public static void start(Context context, String url) {
        start(context,url,"");
    }

    public static void start(Context context, String url,String title) {
        Intent intent = new Intent(context, WanWebActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

//    public static void start(Context context, BannerBean item) {
//        Intent intent = new Intent(context, WanWebActivity.class);
//        intent.putExtra("title", item.getDesc());
//        intent.putExtra("url", item.getUrl());
//        context.startActivity(intent);
//    }



}
