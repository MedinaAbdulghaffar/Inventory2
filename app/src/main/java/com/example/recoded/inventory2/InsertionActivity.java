package com.example.recoded.inventory2;

import android.content.ContentValues;
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
        saveButton=findViewById(R.id.save_b);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });



    }
    public void insert(){

        String productName= mProductName.getText().toString().trim();
        int price=Integer.parseInt(mPrice.getText().toString().trim());
        int quantity=Integer.parseInt(mQuantity.getText().toString().trim());
        String supplierName=mSupplierName.getText().toString().trim();
        int supplierPhoneNumber=Integer.parseInt(mSupplierPhoneNumber.toString().trim());
        ContentValues values=new ContentValues();
        values.put(PRODUCT_NAME,productName);
        values.put(PRICE,price);
        values.put(QUANTITY,quantity);
        values.put(SUPPLIER,supplierName);
        values.put(SUPPLIER_PHONE_NUMBER,supplierPhoneNumber);

        Uri newUri=getContentResolver().insert(CONTENT_URI,values);
        if(newUri==null) {
            Toast.makeText(this, R.string.insert_data_failed,Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this,getString(R.string.insert_data_successful),Toast.LENGTH_LONG).show();
        }

        }
    }





