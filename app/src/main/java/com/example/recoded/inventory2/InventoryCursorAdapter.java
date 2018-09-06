package com.example.recoded.inventory2;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import static com.example.recoded.inventory2.InventoryContract.InventoryEntry.*;

public class InventoryCursorAdapter extends CursorAdapter {
    public InventoryCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_view_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView productNameTv = view.findViewById(R.id.product_name_tv);
        final TextView priceTv = view.findViewById(R.id.price_tv);
        final TextView quantityTv = view.findViewById(R.id.quantity_tv);
        Button editB = view.findViewById(R.id.edit_b);
        Button saleB = view.findViewById(R.id.sale_b);

        int productNameIndex = cursor.getColumnIndexOrThrow(PRODUCT_NAME);
        int priceIndex = cursor.getColumnIndexOrThrow(PRICE);
        int quantityIndex = cursor.getColumnIndexOrThrow(QUANTITY);
        int id = cursor.getColumnIndexOrThrow(ID);
        final String idStr = String.valueOf(cursor.getInt(id));

        productNameTv.setText(cursor.getString(productNameIndex));
        priceTv.setText(cursor.getInt(priceIndex) + "");
        quantityTv.setText(cursor.getInt(quantityIndex) + "");

        saleB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                int quantity = Integer.parseInt(quantityTv.getText().toString().trim());
                if (quantity > 0) {
                    quantity--;
                    values.put(QUANTITY, quantity);
                    Uri uri = Uri.withAppendedPath(CONTENT_URI, idStr);
                    v.getContext().getContentResolver().update(uri, values, null, null);
                    quantityTv.setText(quantity + "");
                }

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra(ID, idStr);
                v.getContext().startActivity(intent);
            }
        });
        editB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditerActivity.class);
                intent.putExtra(ID, idStr);
                v.getContext().startActivity(intent);
            }
        });


    }
}
