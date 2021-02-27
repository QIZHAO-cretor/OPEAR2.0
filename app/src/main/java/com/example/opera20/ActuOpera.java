package com.example.opera20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;

import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import ren.yale.android.cachewebviewlib.WebViewCacheInterceptor;
import ren.yale.android.cachewebviewlib.WebViewCacheInterceptorInst;

public class ActuOpera extends AppCompatActivity {

    private WebView webview;
    ProgressBar progressBar;
    AlertDialog.Builder builder=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actu_opera);
        //
        WebViewCacheInterceptorInst.getInstance().
                init(new WebViewCacheInterceptor.Builder(this));
        //
        webview=findViewById(R.id.webview);
        webview.setWebViewClient(new ActuOpera.MyBrowser());
        WebSettings webSettings=webview.getSettings();
        webview.requestFocus();
        webSettings.setLightTouchEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webview.setSoundEffectsEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webview.loadUrl("http://www.lafabriqueopera-valdeloire.com/actualites/");


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
            if(webview.canGoBack()) {//当webview不是处于第一页面时，返回上一个页面
                webview.goBack();
                return true;
            }
            else {//当webview处于第一页面时,直接退出程序
                Intent HomePage = new Intent(ActuOpera.this, HomePage.class);
                startActivity(HomePage);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                ActuOpera.this.finish();
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