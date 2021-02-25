package com.example.opera20;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import ren.yale.android.cachewebviewlib.CacheType;
import ren.yale.android.cachewebviewlib.WebViewCacheInterceptor;
import ren.yale.android.cachewebviewlib.WebViewCacheInterceptorInst;

public class Billetterie extends AppCompatActivity {

    private WebView webview;
    ProgressBar progressBar;
    AlertDialog.Builder builder=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billetterie);
        //
        WebViewCacheInterceptorInst.getInstance().
                init(new WebViewCacheInterceptor.Builder(this));
         WebViewCacheInterceptor.Builder builder =  new WebViewCacheInterceptor.Builder(this);

        builder//设置缓存路径，默认getCacheDir，名称CacheWebViewCache
                .setCacheSize(1024*1024*300)//设置缓存大小，默认100M
                .setConnectTimeoutSecond(20)//设置http请求链接超时，默认20秒
                .setReadTimeoutSecond(20)//设置http请求链接读取超时，默认20秒
                .setCacheType(CacheType.NORMAL);//设置缓存为正常模式，默认模式为强制缓存静态资源

        WebViewCacheInterceptorInst.getInstance().init(builder);

        //
        webview=findViewById(R.id.webview);
        //webview.setWebViewClient(new MyBrowser());
        //
                webview.setWebViewClient(new WebViewClient(){

                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Nullable
                    @Override
                    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                        return  WebViewCacheInterceptorInst.getInstance().interceptRequest(request);
                    }

                    @Nullable
                    @Override
                    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                        return  WebViewCacheInterceptorInst.getInstance().interceptRequest(url);
                    }
                });
        //
        WebSettings webSettings=webview.getSettings();

        webview.requestFocus();
        webSettings.setLightTouchEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webview.setSoundEffectsEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webview.loadUrl("http://www.lafabriqueopera-valdeloire.com/reservations/");

        progressBar=findViewById(R.id.myProgressBar);



        webview.setWebChromeClient(new WebChromeClient()
        {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress==100){
                    progressBar.setVisibility(View.GONE);//加载完网页进度条消失
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progressBar.setProgress(newProgress);//设置进度值
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    private class MyBrowser extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK) {
            if(webview.canGoBack()) {// when webview is not the url page，back to the last page.
                webview.goBack();
                return true;
            }
            else {//when webview is the url page, quit the activity
                Intent HomePage2 = new Intent(Billetterie.this, HomePage.class);
                startActivity(HomePage2);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                //Billetterie.this.finish();
            }
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        builder=null;
        super.onDestroy();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}