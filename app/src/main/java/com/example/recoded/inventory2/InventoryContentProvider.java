package com.example.recoded.inventory2;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.example.recoded.inventory2.InventoryContract.CONTENT_AUTHORITY;
import static com.example.recoded.inventory2.InventoryContract.InventoryEntry.PATH_INVENTORY;

public class InventoryContentProvider extends ContentProvider {
    InventoryDbHelper helper;
   public static final  UriMatcher URI_MATCHER= new UriMatcher(UriMatcher.NO_MATCH);
   public static final int INVENTORY=100;
   public static final int INVENTORY_ID=101;
   static {
       URI_MATCHER.addURI(CONTENT_AUTHORITY, PATH_INVENTORY,INVENTORY);
       URI_MATCHER.addURI(CONTENT_AUTHORITY,PATH_INVENTORY+"/#",INVENTORY_ID);
   }
    @Override
    public boolean onCreate() {
        helper=new InventoryDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
