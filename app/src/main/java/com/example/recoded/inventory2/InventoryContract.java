package com.example.recoded.inventory2;

import android.net.Uri;
import android.provider.BaseColumns;

public class InventoryContract {
    public InventoryContract(){}
    public static final String CONTENT_AUTHORITY="com.example.recoded.inventory2";
    public static final Uri CONTENT_BASE= Uri.parse("content://"+CONTENT_AUTHORITY);

    public static class InventoryEntry implements BaseColumns{
        public static final String TABLE_NAME="inventory";
        public static final String INVENTORY_ID=BaseColumns._ID;
        public static final String PRODUCT_NAME="product_name";
        public static final String PRICE="price";
        public static final String QUANTITY="quantity";
        public static final String SUPPLIER="supplier";
        public static final String SUPPLIER_PHONE_NUMBER="supplier_phone_number";
        public static final String PATH_INVENTORY="inventory";
        public static final Uri CONTENT_URI=Uri.withAppendedPath(CONTENT_BASE,PATH_INVENTORY);
        }
}
