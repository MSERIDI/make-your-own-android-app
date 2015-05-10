package com.example.compaq.brooser;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.UriMatcher;
import android.net.Uri;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.util.SparseArray;

public class UrlProvider extends ContentProvider {

    private static final SparseArray<DataRecord> db = new SparseArray<DataRecord>();
    @SuppressWarnings("unused")
    private static final String TAG = "Link";

    @Override
    public synchronized int delete(Uri uri, String selection,
                                   String[] selectionArgs) {

        int numRecordsRemoved = 0;
        if (isTableUri(uri)) {
            numRecordsRemoved = db.size();
            db.clear();
        } else if (isItemUri(uri)) {
            Integer requestId = Integer.parseInt(uri.getLastPathSegment());
            if (null != db.get(requestId)) {
                db.remove(requestId);
                numRecordsRemoved++;
            }
        }
        return numRecordsRemoved;
    }

    @Override
    public synchronized String getType(Uri uri) {
        String contentType = UrlContract.CONTENT_ITEM_TYPE;
        if (isTableUri(uri)) {
            contentType = UrlContract.CONTENT_DIR_TYPE;
        }

        return contentType;
    }

    @Override
    public synchronized Uri insert(Uri uri, ContentValues value) {

        if (value.containsKey(UrlContract.DATA)) {

            DataRecord dataRecord = new DataRecord(value.getAsString(UrlContract.DATA));
            db.put(dataRecord.getID(), dataRecord);
             return Uri.withAppendedPath(UrlContract.CONTENT_URI,
                    String.valueOf(dataRecord.getID()));

        }
        return null;
    }
    @Override
    public synchronized Cursor query(Uri uri, String[] projection,
                                     String selection, String[] selectionArgs, String sortOrder) {
        MatrixCursor cursor = new MatrixCursor(UrlContract.ALL_COLUMNS);

        if (isTableUri(uri)) {

            for (int idx = 0; idx < db.size(); idx++) {
                DataRecord dataRecord = db.get(db.keyAt(idx));
                cursor.addRow(new Object[] { dataRecord.getID(),
                        dataRecord.getData() });
            }
        } else if (isItemUri(uri)){
            Integer requestId = Integer.parseInt(uri.getLastPathSegment());

            if (null != db.get(requestId)) {

                DataRecord dr = db.get(requestId);
                cursor.addRow(new Object[] { dr.getID(), dr.getData() });

            }
        }
        return cursor;
    }
    @Override
    public synchronized int update(Uri uri, ContentValues values,
                                   String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public boolean onCreate() {
        Uri mNewUri;
        ContentValues mNewValues = new ContentValues();
        mNewValues.put(UrlContract.DATA,"http://www.google.com");
        mNewUri = insert(UrlContract.CONTENT_URI,mNewValues );
        return true;
    }
    private boolean isItemUri(Uri uri) {
        return uri.getLastPathSegment().matches("\\d+");
    }
    private boolean isTableUri(Uri uri) {
        return uri.getLastPathSegment().equals(UrlContract.DATA_TABLE);
    }
}
