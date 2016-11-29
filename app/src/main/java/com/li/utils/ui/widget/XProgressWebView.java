package com.li.utils.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


/**
 * Created by Mingwei Li on 2016/11/23 :)
 */

public class XProgressWebView extends WebView {
    private ProgressBar progressBar;
    private WebViewCallback callback;
    private Subscription subscribe;
    private String url;

    public XProgressWebView(Context context) {
        super(context);
    }

    public XProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XProgressWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public XProgressWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public XProgressWebView(Context context, AttributeSet attrs, int defStyleAttr, boolean privateBrowsing) {
        super(context, attrs, defStyleAttr, privateBrowsing);
    }

    public XProgressWebView withProgress(ProgressBar progressBar) {
        this.progressBar = progressBar;
        return this;
    }

    public XProgressWebView init() {

        setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.toString());
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
                if (callback != null) {
                    callback.onError();
                }
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                if (callback != null) {
                    callback.onError();
                }
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
                if (callback != null)
                    callback.onError();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                subscribe.unsubscribe();
                progressBar.setProgress(100);
                progressBar.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(INVISIBLE);
                        progressBar.setProgress(0);
                    }
                }, 200);
            }
        });

        setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (progressBar.getVisibility() == INVISIBLE)
                    progressBar.setVisibility(VISIBLE);
                if (newProgress != 100) {
                    progressBar.setProgress(newProgress);
                }
            }
        });

        //setting
        WebSettings settings = getSettings();
        settings.setUseWideViewPort(true);
        settings.setAppCacheEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        return this;
    }

    public String getUrl() {
        return url;
    }

    public void setCallback(WebViewCallback callback) {
        this.callback = callback;
    }

    public void loadUrl(String url) {
        this.url = url;
        super.loadUrl(url);
        progressBar.setVisibility(VISIBLE);
        if (subscribe != null)
            subscribe.unsubscribe();
        subscribe = Observable
                .interval(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Log.d("Progress", "call() called with: progress = [" + progressBar.getProgress() + "]");
                        if (progressBar.getProgress() >= 90)
                            return;
                        progressBar.setProgress(progressBar.getProgress() + 5);
                    }
                });

    }

    public abstract static class WebViewCallback {
        void onPageStarted() {
        }

        void onPageFinished() {
        }

        public abstract void onError();
    }

    public boolean goBackPage() {
        if (canGoBack()) {
            goBack();
            return true;
        }
        return false;
    }

    public void detach() {
        if (subscribe != null)
            subscribe.unsubscribe();
        callback = null;
    }
}
