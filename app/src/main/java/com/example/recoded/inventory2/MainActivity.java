package com.example.recoded.inventory2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import static com.example.recoded.inventory2.InventoryContract.InventoryEntry.CONTENT_URI;
import static com.example.recoded.inventory2.InventoryContract.InventoryEntry.ID;
import static com.example.recoded.inventory2.InventoryContract.InventoryEntry.PRICE;
import static com.example.recoded.inventory2.InventoryContract.InventoryEntry.PRODUCT_NAME;
import static com.example.recoded.inventory2.InventoryContract.InventoryEntry.QUANTITY;
import static com.example.recoded.inventory2.InventoryContract.InventoryEntry.SUPPLIER;
import static com.example.recoded.inventory2.InventoryContract.InventoryEntry.SUPPLIER_PHONE_NUMBER;

public class MainActivity extends AppCompatActivity {
   FloatingActionButton fab;
   InventoryDbHelper helper;
   TextView noDataTv;
   ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab=findViewById(R.id.fab);
        noDataTv=findViewById(R.id.no_data);
        listView=findViewById(R.id.lv);
        helper=new InventoryDbHelper(this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,InsertionActivity.class);
                startActivity(intent);

            }
        });

        android.support.v4.app.LoaderManager loaderManager=getSupportLoaderManager();
        loaderManager.initLoader(0,null,cursorLoader);
        }
    private Cursor getCursorData()
    {
        String[] columns = {
                ID,
                PRODUCT_NAME,
                PRICE,
                QUANTITY,
                SUPPLIER,
                SUPPLIER_PHONE_NUMBER};
        Cursor cursor=getContentResolver().query(CONTENT_URI,columns,null,null,null);
        if(cursor.getCount()>0){
            noDataTv.setVisibility(View.INVISIBLE);
        }
       return cursor;
    }

    protected void onStart() {
        LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.restartLoader(0, null, cursorLoader);
        super.onStart();
    }

    LoaderManager.LoaderCallbacks<Cursor> cursorLoader=new LoaderManager.LoaderCallbacks<Cursor>() {
        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {


            String[] columns = {ID,
                    PRODUCT_NAME,
                    PRICE,
                    QUANTITY,
                    SUPPLIER,
                    SUPPLIER_PHONE_NUMBER};

            return new CursorLoader(
                    MainActivity.this,
                    CONTENT_URI,
                    columns,
                    null,
                    null,
                    null);

        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
          InventoryCursorAdapter adapter=new InventoryCursorAdapter(MainActivity.this,getCursorData());
          listView.setAdapter(adapter);
          adapter.changeCursor(getCursorData());
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    };
}
