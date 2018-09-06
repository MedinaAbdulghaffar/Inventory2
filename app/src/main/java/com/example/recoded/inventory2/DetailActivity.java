package com.example.recoded.inventory2;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import static com.example.recoded.inventory2.InventoryContract.InventoryEntry.*;

public class DetailActivity extends AppCompatActivity {
   TextView productNameTv;
   TextView priceTv;
   TextView quantityTv;
   TextView supplierNameTv;
   TextView supplierPhoneNumberTv;
   Button contactBtn;
   Button deletBtn;
    Uri uri;
    ImageButton increaseQuantityButton;
    ImageButton decreaseQuantityButton;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        productNameTv=findViewById(R.id.detail_product_name_tv);
        priceTv=findViewById(R.id.detail_price_tv);
        quantityTv=findViewById(R.id.detail_quantity_tv);
        supplierNameTv=findViewById(R.id.detail_supplier_tv);
        supplierPhoneNumberTv=findViewById(R.id.detail_supplier_phone_number_tv);
        contactBtn=findViewById(R.id.contact_button);
        deletBtn=findViewById(R.id.delet_button);
        increaseQuantityButton=findViewById(R.id.increase_quantity_ib);
        decreaseQuantityButton=findViewById(R.id.decrease_quantity_ib);




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

                productNameTv.setText(productNameStr);
                priceTv.setText(price+"");
                quantityTv.setText(quantity + "");
                supplierNameTv.setText(supplierNameStr);
                supplierPhoneNumberTv.setText(supplierPhoneNumber+"");

                contactBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + supplierPhoneNumber));
                        startActivity(intent);
                    }
                });
            }
        } finally {
            cursor.close();
        }

       deletBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
                View deleteView = LayoutInflater.from(DetailActivity.this).inflate(R.layout.delet_alert_dialog, null);
                builder.setView(deleteView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();

                Button yesBtn = deleteView.findViewById(R.id.yes_b);
                yesBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getContentResolver().delete(uri, null, null);
                        finish();
                        Intent intent=new Intent(DetailActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                });
                Button noBtn = deleteView.findViewById(R.id.no_b);
                noBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.hide();
                    }
                });
            }
        });
        increaseQuantityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values=new ContentValues();
                int quantity= Integer.parseInt(quantityTv.getText().toString().trim());
                if(quantity>0) {
                    quantity--;
                    values.put(QUANTITY, quantity);
                    Uri uri = Uri.withAppendedPath(CONTENT_URI, id);
                    v.getContext().getContentResolver().update(uri, values, null, null);
                    quantityTv.setText(quantity + ""); }
        }});
        decreaseQuantityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values=new ContentValues();
                int quantity= Integer.parseInt(quantityTv.getText().toString().trim());
                     quantity++;
                    values.put(QUANTITY, quantity);
                    Uri uri = Uri.withAppendedPath(CONTENT_URI, id);
                    v.getContext().getContentResolver().update(uri, values, null, null);
                    quantityTv.setText(quantity + "");
            }});


    }
}

