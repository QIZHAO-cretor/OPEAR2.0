package com.example.opera20;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.io.File;

public class Lassociation extends AppCompatActivity {

    private WebView webview;
    ProgressBar progressBar;
    AlertDialog.Builder builder=null;
    private static final String APP_CACHE_DIRNAME = "/webcache"; // web 缓存目录
    private static final String SITE_URL = "http://www.lafabriqueopera-valdeloire.com/lassociation/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lassociation);

        webview=findViewById(R.id.webview);
        webview.loadUrl(SITE_URL);
        webview.setWebViewClient(new WebViewClient(){
            //设置在 WebView 点击打开的新网页在当前界面显示,而不跳转到新的浏览器中
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings webSettings=webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //设置缓存模式
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        // 开启DOM storage API 功能
        webSettings.setDomStorageEnabled(true);

        // 开启database storage API功能
        webSettings.setDatabaseEnabled(true);

        String cacheDirPath = getCacheDir().getAbsolutePath() + APP_CACHE_DIRNAME;
        Log.d("webcache",cacheDirPath);
        File f = new File(cacheDirPath);
        if (!f.exists())
        {
            f.mkdirs();
        }
        // 设置数据库缓存路径
        webSettings.setAppCachePath(cacheDirPath);
        webSettings.setAppCacheEnabled(true);
//        webview.setWebViewClient(new Lassociation.MyBrowser());
//        WebSettings webSettings=webview.getSettings();
//        webview.requestFocus();
//        webSettings.setLightTouchEnabled(true);
//        webSettings.setJavaScriptEnabled(true);
//        webSettings.setGeolocationEnabled(true);
//        webview.setSoundEffectsEnabled(true);
//        webSettings.setSupportZoom(true);
//        webSettings.setBuiltInZoomControls(true);
//        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        webview.loadUrl("http://www.lafabriqueopera-valdeloire.com/lassociation/");
//
//
//        progressBar=findViewById(R.id.myProgressBar);
//
//
//
//        webview.setWebChromeClient(new WebChromeClient()
//        {
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                if(newProgress==100){
//                    progressBar.setVisibility(View.GONE);//加载完网页进度条消失
//                }
//                else{
//                    progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
//                    progressBar.setProgress(newProgress);//设置进度值
//                }
//            }
//        });
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//    }
//
//
//    private class MyBrowser extends WebViewClient
//    {
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl(url);
//            return true;
//        }
//    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if(keyCode==KeyEvent.KEYCODE_BACK) {
//            if(webview.canGoBack()) {// when webview is not the url page，back to the last page.
//                webview.goBack();
//                return true;
//            }
//            else {//when webview is the url page, quit the activity
//                Intent HomePage2 = new Intent(Lassociation.this, HomePage.class);
//                startActivity(HomePage2);
//                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//                Lassociation.this.finish();
//            }
//        }
//        return false;
//    }
//
//    @Override
//    protected void onDestroy() {
//        builder=null;
//        super.onDestroy();
//    }
//
//    @Override
//    public void finish() {
//        super.finish();
//        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
