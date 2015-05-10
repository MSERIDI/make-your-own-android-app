package com.example.compaq.brooser;

import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.ContactsContract;

/**
 * Created by COMPAQ on 26/03/2015.
 */
/*
public final class UrlContract {

    public UrlContract() {}
    public static final String AUTHORITY =
    "com.example.compaq.brooser";

    public static final Uri BASE_CONTENT_URI =
    Uri.parse("content://" +AUTHORITY);
    public static final String PATH_LINK = "links";
    public static final class LastLink implements BaseColumns {

                 public static final Uri CONTENT_URI =
                      BASE_CONTENT_URI.buildUpon().appendPath(PATH_LINK).build();
                 public static final String CONTENT_TYPE =
                      "vnd.android.cursor.dir/" + AUTHORITY + "/" + PATH_LINK;
                public static final String CONTENT_ITEM_TYPE =
                      "vnd.android.cursor.item/" + AUTHORITY + "/" + PATH_LINK;

    }
}*/
import android.content.ContentResolver;
         import android.net.Uri;


         // Contract Class for accessing ContentResolver


         public final class UrlContract {
             public static final String AUTHORITY =
                     "com.example.compaq.brooser";


             	public static final String _ID = "_id";
     	public static final String DATA = "data";
     	public static final String DATA_TABLE = "data_table";


             	private static final Uri BASE_URI =
                        Uri.parse("content://"+ AUTHORITY);


             	// The URI for this table.
             	public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_URI,DATA_TABLE);


             	// Mime type for a directory of data items
             	public static final String CONTENT_DIR_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
    			+ "/Link.data.text";


             	// Mime type for a single data item
             	public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
     			+ "/Link.data.text";


             	// All columns of this table
             	public static final String[] ALL_COLUMNS = { _ID, DATA };


         }



