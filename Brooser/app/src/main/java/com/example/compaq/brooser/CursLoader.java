package com.example.compaq.brooser;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;


public class CursLoader extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int Loader_ID = 1992 ;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    getLoaderManager().initLoader(Loader_ID,null,this);
    }
    public static String Urldb;


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        Uri contentUri = UrlContract.CONTENT_URI;
       String [] column = {UrlContract.DATA};
        return new CursorLoader(getActivity(),contentUri,column, null, null, null);
    }
    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        cursor.moveToFirst();
            Urldb = cursor.getString(cursor.getColumnIndex("data"));
    }
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }
}