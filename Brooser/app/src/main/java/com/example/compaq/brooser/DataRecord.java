package com.example.compaq.brooser;

/**
 * Created by COMPAQ on 28/03/2015.
 */
class DataRecord {

    private static int id;

    // Unique ID
    private final int _id;

    // Display Name
    private final String _data;

    DataRecord(String _data) {
        this._data = _data;
        this._id = id++;
    }

    String getData() {
        return _data;
    }

    int getID() {
        return _id;
    }

}
