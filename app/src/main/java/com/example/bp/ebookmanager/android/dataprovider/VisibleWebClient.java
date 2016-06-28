package com.example.bp.ebookmanager.android.dataprovider;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.bp.ebookmanager.R;
import com.example.bp.ebookmanager.dataprovider.WebClient;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Ebook Manager
 * Created by bp on 11.06.16.
 */
public class VisibleWebClient implements WebClient {
    private Dialog dialog;
    private Callbacks callbacks;
    public static final String GET_HTML_JS_FUNCTION = "(function() { return ('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>'); })();";
    private WebView webView;

    public VisibleWebClient(Context context) {
        dialog = createDialog(context);
        webView = getWebView();
    }

    private WebView getWebView() {
        WebView webView = (WebView) dialog.findViewById(R.id.dialog_web_view);
        webView.getSettings().setUserAgentString(
                "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36");
        webView.getSettings().setJavaScriptEnabled(true);
//        CookieManager.getInstance().removeAllCookie();
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, final String url) {
                super.onPageFinished(view, url);
                Log.d("VisibleWebClient", "Finished - " + url);
                view.evaluateJavascript(GET_HTML_JS_FUNCTION, new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String html) {
                        html = StringEscapeUtils.unescapeJava(html);
                        html = html.substring(1, html.length() - 1);
                        Log.d("VisibleWebClient", url);
                        callbacks.onPageFinished(url, html);
                    }
                });
            }
        });
        return webView;
    }

    private Dialog createDialog(Context context) {
        Dialog dialog = new Dialog(context);
        dialog.setTitle(R.string.action_required);
        dialog.setContentView(R.layout.web_view_dialog);
        return dialog;
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
    public void close() {
        dialog.dismiss();
    }
}
