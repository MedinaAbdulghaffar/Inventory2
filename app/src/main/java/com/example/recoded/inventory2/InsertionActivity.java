package com.example.recoded.inventory2;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.recoded.inventory2.InventoryContract.InventoryEntry.*;


public class InsertionActivity extends AppCompatActivity {
    EditText mProductName;
    EditText mPrice;
    EditText mQuantity;
    EditText mSupplierName;
    EditText mSupplierPhoneNumber;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertion);
        mProductName = findViewById(R.id.product_name_et);
        mPrice = findViewById(R.id.price_et);
        mQuantity = findViewById(R.id.quantity_et);
        mSupplierName = findViewById(R.id.supplier_name_et);
        mSupplierPhoneNumber = findViewById(R.id.supplier_phone_number_et);
        saveButton = findViewById(R.id.save_b);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                insert();
//                Intent intent=new Intent(InsertionActivity.this,MainActivity.class);
//                startActivity(intent);


                ContentValues values = new ContentValues();

                String productName = mProductName.getText().toString().trim();
                if (!productName.isEmpty()) {
                    values.put(PRODUCT_NAME, productName);

                } else {
                    mProductName.setError("required");


                }
                String price = mPrice.getText().toString().trim();

                if (price.isEmpty()) {
                    mPrice.setError("required");
                } else {
                    values.put(PRICE, Integer.parseInt(price));
                }
                String quantity = mQuantity.getText().toString().trim();
                if (quantity.isEmpty()) {
                    mQuantity.setError("required");
                } else {
                    values.put(QUANTITY, Integer.parseInt(quantity));
                }
                String supplierName = mSupplierName.getText().toString().trim();
                if (supplierName.isEmpty()) {
                    mSupplierName.setError("required");
                } else {
                    values.put(SUPPLIER, supplierName);
                }
                String supplierPhoneNumber = mSupplierPhoneNumber.getText().toString().trim();
                if (supplierPhoneNumber.isEmpty()) {
                    mSupplierPhoneNumber.setError("required");
                } else {
                    values.put(SUPPLIER_PHONE_NUMBER, Integer.parseInt(supplierPhoneNumber));
                }
                Uri newUri = getContentResolver().insert(CONTENT_URI, values);
                if (newUri == null) {
                    Toast.makeText(InsertionActivity.this, R.string.insert_data_failed, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(InsertionActivity.this, getString(R.string.insert_data_successful), Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });


    }

    public void insert() {
        ContentValues values = new ContentValues();

        String productName = mProductName.getText().toString().trim();
        if (!productName.isEmpty()) {
            values.put(PRODUCT_NAME, productName);

        } else {
            mProductName.setError("required");


        }
        String price = mPrice.getText().toString().trim();

        if (price.isEmpty()) {
            mPrice.setError("required");
        } else {
            values.put(PRICE, Integer.parseInt(price));
        }
        String quantity = mQuantity.getText().toString().trim();
        if (quantity.isEmpty()) {
            mQuantity.setError("required");
        } else {
            values.put(QUANTITY, Integer.parseInt(quantity));
        }
        String supplierName = mSupplierName.getText().toString().trim();
        if (supplierName.isEmpty()) {
            mSupplierName.setError("required");
        } else {
            values.put(SUPPLIER, supplierName);
        }
        String supplierPhoneNumber = mSupplierPhoneNumber.getText().toString().trim();
        if (supplierPhoneNumber.isEmpty()) {
            mSupplierPhoneNumber.setError("required");
        } else {
            values.put(SUPPLIER_PHONE_NUMBER, Integer.parseInt(supplierPhoneNumber));
        }
        Uri newUri = getContentResolver().insert(CONTENT_URI, values);
        if (newUri == null) {
            Toast.makeText(InsertionActivity.this, R.string.insert_data_failed, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(InsertionActivity.this, getString(R.string.insert_data_successful), Toast.LENGTH_LONG).show();
        }

    }
}





