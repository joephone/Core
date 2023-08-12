package com.transcendence.core.publicModule.web.act;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

import com.alibaba.android.arouter.utils.TextUtils;
import com.just.agentweb.AgentWeb;
import com.transcendence.core.R;
import com.transcendence.core.base.mvvm.activity.BaseMvvmActivity;
import com.transcendence.core.base.mvvm.BaseViewModel;
import com.transcendence.core.databinding.ActivityAgentwebBinding;
import com.transcendence.core.utils.log.LogUtils;

/**
 * @author joephone
 * @date 2023/5/2
 * @desc
 */
public class AgentWebAc extends BaseMvvmActivity<ActivityAgentwebBinding, BaseViewModel> {

    AgentWeb agentWeb;

    private int mArticleId = -1;
    private String mTitle = "";
    private String mAuthor = "";
    private String mUrl = "";
    private String mCurUrl;

    public static void start(Context context, String url) {
        start(context,url,"");
    }

    public static void start(Context context, String url,String title) {
        Intent intent = new Intent(context, AgentWebAc.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_agentweb;
    }

    @Override
    protected void initView() {
        mArticleId = getIntent().getIntExtra("articleId", -1);
        mTitle = getIntent().getStringExtra("title");
        mTitle = mTitle == null ? "" : mTitle;
        mAuthor = getIntent().getStringExtra("author");
        super.setTitle(mTitle = mTitle == null ? "" : mTitle);
        mUrl = getIntent().getStringExtra("url");
        mUrl ="https://jpdfunit.sourceforge.net/references/jpdfunit_aShortIntroduction.pdf";   //= mUrl == null ? "" : mUrl;
    }

    @Override
    protected void loadData() {
        loadUrl(mUrl);
    }


    public void loadUrl(String url){
        if(agentWeb == null){
            agentWeb = AgentWeb.with(this)
                    .setAgentWebParent(dataBinding.ll,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
                    .useDefaultIndicator(ContextCompat.getColor(mActivity,R.color.colorPrimaryDark),2)
                    .setWebChromeClient(new WebChromeClient(){
                        @Override
                        public void onReceivedTitle(WebView view, String title) {
                            super.onReceivedTitle(view, title);
                            LogUtils.d("title:"+title);
                            if(!TextUtils.isEmpty(title)){
                                setTitle(title);
                            }
//                            if(StringUtils.isNotBlank(title)){
//                                setToolbarTitle(title);
//                            }
                        }

                    })
                    .setWebViewClient(new WebViewClient(){
                        @Override
                        public void onPageStarted(WebView view, String url, Bitmap favicon) {
                            super.onPageStarted(view, url, favicon);
                            mCurUrl = url;
                            LogUtils.d("mCurUrl:"+mCurUrl);
                        }
                    })
                    .createAgentWeb()
                    .go(url);
        }else{
            agentWeb.getWebCreator().getWebView().loadUrl(url);
        }

    }
}
