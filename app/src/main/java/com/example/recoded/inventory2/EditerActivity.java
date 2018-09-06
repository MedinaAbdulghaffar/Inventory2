package com.example.recoded.inventory2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.recoded.inventory2.InventoryContract.InventoryEntry.CONTENT_URI;
import static com.example.recoded.inventory2.InventoryContract.InventoryEntry.ID;
import static com.example.recoded.inventory2.InventoryContract.InventoryEntry.PRICE;
import static com.example.recoded.inventory2.InventoryContract.InventoryEntry.PRODUCT_NAME;
import static com.example.recoded.inventory2.InventoryContract.InventoryEntry.QUANTITY;
import static com.example.recoded.inventory2.InventoryContract.InventoryEntry.SUPPLIER;
import static com.example.recoded.inventory2.InventoryContract.InventoryEntry.SUPPLIER_PHONE_NUMBER;

public class EditerActivity extends AppCompatActivity {

    EditText mProductNameEd;
    EditText mPriceEd;
    EditText mQuantityEd;
    EditText mSupplierNameEd;
    EditText mSupplierPhoneNumberEd;
    Uri uri;
    Button saveChangesBtn;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editer);
        mProductNameEd = findViewById(R.id.edit_product_name_et);
        mPriceEd = findViewById(R.id.edit_price_et);
        mQuantityEd = findViewById(R.id.edit_quantity_et);
        mSupplierNameEd = findViewById(R.id.edit_supplier_name_et);
        mSupplierPhoneNumberEd = findViewById(R.id.edit_supplier_phone_number_et);
        saveChangesBtn = findViewById(R.id.save_changes_b);


        Intent intent = getIntent();
        id = String.valueOf(intent.getStringExtra(ID));
        uri = Uri.withAppendedPath(CONTENT_URI, id);

        String[] columns = {
                ID,
                PRODUCT_NAME,
                PRICE,
                QUANTITY,
                SUPPLIER,
                SUPPLIER_PHONE_NUMBER};
        Cursor cursor = getContentResolver().query(uri, columns, null, null, null);
        try {
            while (cursor.moveToNext()) {
                int productNameIndex = cursor.getColumnIndexOrThrow(PRODUCT_NAME);
                int priceIndex = cursor.getColumnIndexOrThrow(PRICE);
                int quantityIndex = cursor.getColumnIndexOrThrow(QUANTITY);
                int supplierNameIndex = cursor.getColumnIndexOrThrow(SUPPLIER);
                int supplierPhoneNumberIndex = cursor.getColumnIndexOrThrow(SUPPLIER_PHONE_NUMBER);

                String productNameStr = cursor.getString(productNameIndex);
                int price = cursor.getInt(priceIndex);
                int quantity = cursor.getInt(quantityIndex);
                String supplierNameStr = cursor.getString(supplierNameIndex);
                final int supplierPhoneNumber = cursor.getInt(supplierPhoneNumberIndex);

                mProductNameEd.setText(productNameStr);
                mPriceEd.setText(price + "");
                mQuantityEd.setText(quantity + "");
                mSupplierNameEd.setText(supplierNameStr);
                mSupplierPhoneNumberEd.setText(supplierPhoneNumber + "");


            }
        } finally {
            cursor.close();
        }
        saveChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();

            }
        });
    }

    public void saveChanges() {
        ContentValues values = new ContentValues();

        String productName = mProductNameEd.getText().toString().trim();
        if (!productName.isEmpty()) {
            values.put(PRODUCT_NAME, productName);

        } else {
            mProductNameEd.setError("required");


        }
        String price = mPriceEd.getText().toString().trim();

        if (price.isEmpty()) {
            mPriceEd.setError("required");
        } else {
            values.put(PRICE, Integer.parseInt(price));
        }
        String quantity = mQuantityEd.getText().toString().trim();
        if (quantity.isEmpty()) {
            mQuantityEd.setError("required");
        } else {
            values.put(QUANTITY, Integer.parseInt(quantity));
        }
        String supplierName = mSupplierNameEd.getText().toString().trim();
        if (supplierName.isEmpty()) {
            mSupplierNameEd.setError("required");
        } else {
            values.put(SUPPLIER, supplierName);
        }
        String supplierPhoneNumber = mSupplierPhoneNumberEd.getText().toString().trim();
        if (supplierPhoneNumber.isEmpty()) {
            mSupplierPhoneNumberEd.setError("required");
        } else {
            values.put(SUPPLIER_PHONE_NUMBER, Integer.parseInt(supplierPhoneNumber));
        }
        getContentResolver().update(uri, values, null, null);


    }
}
