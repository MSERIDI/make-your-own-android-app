package com.example.compaq.brooser;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


public class MainActivity extends ActionBarActivity {
    EditText url;
    WebView Mbrowser;
    String urls ;
    //ContentResolver contentResolver = getContentResolver();
    String mSelectionClause = UrlContract.DATA;
    String[] mSelectionArg = {""};
    ContentValues mupdatevalues = new ContentValues();
    String urldb= "http://www.google.com";
    //String urldb ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      /*  new mCursor = getContentResolver().query(
                UrlContract.CONTENT_URI,  // The content URI of the words table
                column,                       // The columns to return for each row
                mSelectionClause,                   // Either null, or the word the user entered
                mSelectionArg,                    // Either empty, or the string the user entered
                null);*/
      /*  Bundle extrass = getIntent().getExtras();
        if(extrass !=null) {
             urldbb = extrass.getString("KEY");
        }*/








              urldb = CursLoader.Urldb;



        Mbrowser = (WebView) findViewById(R.id.wVbrowser);
        Mbrowser.getSettings().setJavaScriptEnabled(true);
        Mbrowser.getSettings().setLoadWithOverviewMode(true);
        Mbrowser.getSettings().setUseWideViewPort(true);

        Mbrowser.setWebViewClient(new mViewClient());

        if (urldb !="") {
            urls = urldb;
            Mbrowser.loadUrl(urls);

        }
        else {
            try {
                Mbrowser.loadUrl("http://www.google.com");
                url.setText("http://www.google.com");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ImageButton go = (ImageButton) findViewById(R.id.goo);
        ImageButton back = (ImageButton) findViewById(R.id.go_back);
        ImageButton forward = (ImageButton) findViewById(R.id.go_forward);
        ImageButton refresh = (ImageButton) findViewById(R.id.refresh);
        url = (EditText) findViewById(R.id.urled);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                urls = url.getText().toString();
                 if(urls.substring(0,6)=="http://") {
                     Mbrowser.loadUrl(urls);
                 }
                else if(urls.substring(0,3).equals("www.")) {
                     Mbrowser.loadUrl("http://" + urls);
                 }
                else if (urls != null) {
                     Mbrowser.loadUrl("http://www.google.com/#q=" + urls);
                 }
                else {
                     Mbrowser.loadUrl("http://www.google.com/");
                     url.setText("http://www.google.com/");
                 }




                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(url.getWindowToken(), 0);
                urls = url.toString();

                mupdatevalues.put(UrlContract.DATA,urls);
                // update db
                int mRowsDeleted =0;
               mRowsDeleted = getContentResolver().update(
                       UrlContract.CONTENT_URI,
                        mupdatevalues,
                        mSelectionClause,
                        mSelectionArg
               );


            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Mbrowser.canGoBack()) {
                    Mbrowser.goBack();
                }
                urls = url.toString();
                mupdatevalues.put(UrlContract.DATA,urls);

                int mRowsDeleted =0;
                mRowsDeleted = getContentResolver().update(
                        UrlContract.CONTENT_URI,
                        mupdatevalues,
                        mSelectionClause,
                        mSelectionArg
                );
            }
        });
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Mbrowser.canGoForward()) {
                    Mbrowser.goForward();

                    urls = url.toString();
                }
                mupdatevalues.put(UrlContract.DATA,urls);

                int mRowsDeleted =0;
                mRowsDeleted = getContentResolver().update(
                        UrlContract.CONTENT_URI,
                        mupdatevalues,
                        mSelectionClause,
                        mSelectionArg
                );
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mbrowser.reload();
            }
        });
}

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("URL", Mbrowser.getUrl());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            startActivity ( new Intent (this, AboutActivity.class)) ;
            return true;
        }
        if (id == R.id.action_clearhist) {
            Mbrowser.clearHistory();
            try {
                Mbrowser.loadUrl("http://www.google.com");
            } catch (Exception e) {
                e.printStackTrace();
            }
            urls = url.toString();
            mupdatevalues.put(UrlContract.DATA,"http://www.google.com");
            int mRowsDeleted =0;
            mRowsDeleted = getContentResolver().update(
                    UrlContract.CONTENT_URI,
                    mupdatevalues,
                    mSelectionClause,
                    mSelectionArg
            );
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}