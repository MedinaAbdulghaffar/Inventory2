package com.example.recoded.inventory2;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import static com.example.recoded.inventory2.InventoryContract.CONTENT_AUTHORITY;
import static com.example.recoded.inventory2.InventoryContract.InventoryEntry.PATH_INVENTORY;
import static com.example.recoded.inventory2.InventoryContract.InventoryEntry.TABLE_NAME;

public class InventoryContentProvider extends ContentProvider {
    InventoryDbHelper helper;
    public static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
    public static final int INVENTORY = 100;
    public static final int INVENTORY_ID1 = 101;
    Cursor cursor;
    public static final String TAG = InventoryContentProvider.class.getSimpleName();
    SQLiteDatabase readDataBase;

    static {
        URI_MATCHER.addURI(CONTENT_AUTHORITY, PATH_INVENTORY, INVENTORY);
        URI_MATCHER.addURI(CONTENT_AUTHORITY, PATH_INVENTORY + "/#", INVENTORY_ID1);
    }

    @Override
    public boolean onCreate() {
        helper = new InventoryDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        readDataBase = helper.getReadableDatabase();
        int match = URI_MATCHER.match(uri);
        switch (match) {
            case INVENTORY:
                cursor = readDataBase.query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case INVENTORY_ID1:
                selection = InventoryContract.InventoryEntry.ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = readDataBase.query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("failed to load" + uri);
        }
        return cursor;

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase insertInToDatabase = helper.getWritableDatabase();
        long id = insertInToDatabase.insert(TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(TAG, "failed to insert row for" + uri);
            return null;
        }
        return ContentUris.withAppendedId(uri, id);
    }


    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int match = URI_MATCHER.match(uri);
        SQLiteDatabase deletDataBase = helper.getWritableDatabase();
        selection = InventoryContract.InventoryEntry.ID + "=?";
        selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
        switch (match) {
            case INVENTORY:
                break;
            case INVENTORY_ID1:
                return deletDataBase.delete(TABLE_NAME, selection, selectionArgs);

            default:
                throw new IllegalArgumentException("Failed to delete row for " + uri);
        }
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int match = URI_MATCHER.match(uri);
        SQLiteDatabase updateDataBase = helper.getWritableDatabase();
        selection = InventoryContract.InventoryEntry.ID + "=?";
        selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
        switch (match) {
            case INVENTORY:
                break;
            case INVENTORY_ID1:
                return updateDataBase.update(TABLE_NAME, values, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("failed to update" + uri);
        }

        return 0;
    }
}
