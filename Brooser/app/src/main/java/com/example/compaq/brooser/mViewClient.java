package com.example.compaq.brooser;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by COMPAQ on 09/03/2015.
 */
public class mViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView v , String url){
       v.loadUrl(url);
        return true;
    }
}
