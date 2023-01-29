package com.hifly.englishexam;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback;
import com.tencent.smtt.export.external.interfaces.JsPromptResult;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class X5WebViewActivity extends AppCompatActivity {
    private WebView webView;
    public static final String TAG = "X5WebView";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_x5);
        webView = findViewById(R.id.web_view);
        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setSupportZoom(true);
        webSetting.setDatabaseEnabled(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setDomStorageEnabled(true);

        initWebViewClient();
        initWebChromeClient();
        initJavaScriptInterface();
        webView.loadUrl("http://192.168.0.105:9000");
//        webView.loadUrl("http://soft.imtt.qq.com/browser/tes/feedback.html");
    }

    private void initWebViewClient() {
        webView.setWebViewClient(new WebViewClient() {

            /**
             * 具体接口使用细节请参考文档：
             * https://x5.tencent.com/docs/webview.html
             * 或 Android WebKit 官方：
             * https://developer.android.com/reference/android/webkit/WebChromeClient
             */

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.i(TAG, "onPageStarted, view:" + view + ", url:" + url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.i(TAG, "onPageFinished, view:" + view + ", url:" + url);
            }

            @Override
            public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
                Log.e(TAG, "onReceivedError: " + errorCode
                        + ", description: " + description
                        + ", url: " + failingUrl);
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
                if (webResourceRequest.getUrl().toString().contains("debugdebug")) {
                    InputStream in = null;
                    Log.i("AterDebug", "shouldInterceptRequest");
                    try {
                        in = new FileInputStream(new File("/sdcard/1.png"));
                    } catch (Exception e) {

                    }

                    return new WebResourceResponse("image/*", "utf-8", in);
                } else {
                    return super.shouldInterceptRequest(webView, webResourceRequest);
                }

            }
        });
    }

    private void initWebChromeClient() {
        final Context context = this;
        final Activity activity = this;
        webView.setWebChromeClient(new WebChromeClient() {
            /**
             * 具体接口使用细节请参考文档：
             * https://x5.tencent.com/docs/webview.html
             * 或 Android WebKit 官方：
             * https://developer.android.com/reference/android/webkit/WebChromeClient
             */

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                Log.i(TAG, "onProgressChanged, newProgress:" + newProgress + ", view:" + view);
//                changGoForwardButton(view);
            }

            @Override
            public boolean onJsAlert(WebView webView, String url, String message, JsResult result) {
                new AlertDialog.Builder(context).setTitle("JS弹窗Override")
                        .setMessage(message)
                        .setPositiveButton("OK", (dialogInterface, i) -> result.confirm())
                        .setCancelable(false)
                        .show();
                return true;
            }

            @Override
            public boolean onJsConfirm(WebView webView, String url, String message, JsResult result) {
                new AlertDialog.Builder(context).setTitle("JS弹窗Override")
                        .setMessage(message)
                        .setPositiveButton("OK", (dialogInterface, i) -> result.confirm())
                        .setNegativeButton("Cancel", (dialogInterface, i) -> result.cancel())
                        .setCancelable(false)
                        .show();
                return true;
            }

            @Override
            public boolean onJsBeforeUnload(WebView webView, String url, String message, JsResult result) {
                new AlertDialog.Builder(context).setTitle("页面即将跳转")
                        .setMessage(message)
                        .setPositiveButton("OK", (dialogInterface, i) -> result.confirm())
                        .setNegativeButton("Cancel", (dialogInterface, i) -> result.cancel())
                        .setCancelable(false)
                        .show();
                return true;
            }

            @Override
            public boolean onJsPrompt(WebView webView, String url, String message, String defaultValue, JsPromptResult result) {
                final EditText input = new EditText(context);
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                new AlertDialog.Builder(context).setTitle("JS弹窗Override")
                        .setMessage(message)
                        .setView(input)
                        .setPositiveButton("OK", (dialogInterface, i) -> result.confirm(input.getText().toString()))
                        .setCancelable(false)
                        .show();
                return true;
            }

            @Override
            public boolean onShowFileChooser(WebView webView,
                                             ValueCallback<Uri[]> filePathCallback,
                                             WebChromeClient.FileChooserParams fileChooserParams) {
                Log.i(TAG, "openFileChooser: " + fileChooserParams.getMode());
//                mFilePathCallback = filePathCallback;
//                openFileChooseProcess(fileChooserParams.getMode() == FileChooserParams.MODE_OPEN_MULTIPLE);
                return true;
            }

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin,
                                                           GeolocationPermissionsCallback geolocationPermissionsCallback) {
//                if (verifyLocationPermissions(activity)) {
//                    geolocationPermissionsCallback.invoke(origin, true, false);
//                } else {
//                    locationPermissionUrl = origin;
//                    mGeolocationCallback = geolocationPermissionsCallback;
//                }
            }
        });
    }

    private void initJavaScriptInterface() {
        final Activity context = this;
        webView.addJavascriptInterface(new WebViewJavaScriptFunction() {
            @Override
            public void onJsFunctionCalled(String tag) {

            }

            @JavascriptInterface
            public void openQRCodeScan() {
//                new IntentIntegrator(context).initiateScan();
            }

            @JavascriptInterface
            public void openDebugX5() {
                webView.loadUrl("http://debugx5.qq.com");
            }

            @JavascriptInterface
            public void openWebkit() {
            }


        }, "Android");
    }

}
