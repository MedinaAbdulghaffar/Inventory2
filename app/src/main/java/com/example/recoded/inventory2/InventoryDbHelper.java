package com.example.recoded.inventory2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import static com.example.recoded.inventory2.InventoryContract.InventoryEntry.*;

public class InventoryDbHelper extends SQLiteOpenHelper {
    public static final int DATA_BASE_VERSION = 1;
    public static final String DATA_BASE_NAME = "Inventory.db";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY," + PRODUCT_NAME + " TEXT NOT NULL," + PRICE + " INTEGER NOT NULL," +
            QUANTITY + " INTEGER NOT NULL," + SUPPLIER + " TEXT NOT NULL," + SUPPLIER_PHONE_NUMBER + " INTEGER NOT NULL)";

    public InventoryDbHelper(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
