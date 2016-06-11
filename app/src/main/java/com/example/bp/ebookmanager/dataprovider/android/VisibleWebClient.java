package com.example.bp.ebookmanager.dataprovider.android;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.bp.ebookmanager.R;
import com.example.bp.ebookmanager.dataprovider.WebClient;

/**
 * Created by bp on 11.06.16.
 */
public class VisibleWebClient implements WebClient {
    private AlertDialog dialog;
    private Callbacks callbacks;
    public static final String GET_HTML_JS_FUNCTION = "(function() { return ('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>'); })();";
    private WebView webView;

    public VisibleWebClient(Context context) {
        webView = createWebView(context);
        dialog = createDialog(context);
    }

    private WebView createWebView(Context context) {
        WebView webView = new WebView(context);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, final String url) {
                super.onPageFinished(view, url);
                Log.d("VisibleWebClient", "Finished - " + url);
                view.evaluateJavascript(GET_HTML_JS_FUNCTION, new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String html) {
                        callbacks.onPageFinished(url, html);
                    }
                });
            }
        });
        return webView;
    }

    private AlertDialog createDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.action_required);
        builder.setView(webView);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        return builder.create();
    }

    @Override
    public void setCallbacks(Callbacks callbacks) {
        this.callbacks = callbacks;
    }

    @Override
    public void loadUrl(String url) {
        webView.loadUrl(url);
        dialog.show();
    }

    @Override
    public boolean isHeadless() {
        return false;
    }

    @Override
    public void finishWork() {
        dialog.dismiss();
    }
}
