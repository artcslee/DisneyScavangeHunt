package com.catchoom.test.activities;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.catchoom.test.R;
import com.catchoom.test.controllers.RealmController;

@SuppressLint("SetJavaScriptEnabled")
public class DisneyMapActivity extends AppCompatActivity {

    private WebView browser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disney_map);
        browser = (WebView)findViewById(R.id.webview);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setLoadsImagesAutomatically(true);
        browser.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        browser.setWebViewClient(new MyBrowser());
        browser.setWebChromeClient(new MyWebChromeClient());
        browser.loadUrl("file:///android_asset/index.html");
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            view.addJavascriptInterface(new CustomObject(view), "map");
            return true;
        }

        private class CustomObject extends Object {
            WebView mWebView;

            CustomObject(WebView view) {
                mWebView = view;
            }

            @JavascriptInterface
            public void selectArea(String area) throws Exception
            {
                Log.d("LOGIN::", "Clicked: " + area);
                mWebView.loadUrl("javascript:confirm('Do you want to scavange in " + RealmController.AREA_STRINGS.get(area) + "?')");
            }
        }
    }

    private class MyWebChromeClient extends WebChromeClient {

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result)
        {
            final JsResult finalRes = result;
            new AlertDialog.Builder(view.getContext())
                    .setMessage(message)
                    .setPositiveButton(android.R.string.ok,
                            new AlertDialog.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finalRes.confirm();
                                }
                            })
                    .setCancelable(false)
                    .create()
                    .show();
            return true;
        }
    }
}
