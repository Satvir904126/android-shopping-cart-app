package com.e.onlineshopingcart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AddToCart extends MenuOptionClass {
ImageView productImage;
TextView  productName,totalValue,productPrice;
Button addToCart;
int value=0,price=0, finalprice=0;
int count=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        productImage = (ImageView) findViewById(R.id.img_car);
        productName = (TextView) findViewById(R.id.text_line);
        productPrice=(TextView)findViewById(R.id.price);
        //int getvalue = getIntent().getIntExtra("ClickedImage", 0);
        //Bitmap getvalue = getIntent().getParcelableExtra("ClickedImage");
        Bitmap getvalue = BitmapFactory.decodeByteArray(
                getIntent().getByteArrayExtra("ClickedImage"),0,getIntent().getByteArrayExtra("ClickedImage").length);
        final String getname = getIntent().getStringExtra("ClickedName");
        price = getIntent().getIntExtra("ClickedPrice",0);


        productName.setText(getname);
        //productImage.setImageResource(Integer.parseInt(String.valueOf(getvalue)));
        productImage.setImageBitmap(getvalue);
        productPrice.setText("$ "+String.valueOf(price));


        //add Quantity


        final TextView cartValue = (TextView) findViewById(R.id.quantityValue);
        Button increment = (Button) findViewById(R.id.btnIncrement);
        Button decrement = (Button) findViewById(R.id.btnDecriment);
        totalValue = (TextView) findViewById(R.id.totalPriceValue);

        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                value = value + 1;
                cartValue.setText(String.valueOf(value));
                displayTotal(value);
            }
        });

        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (value > 0) {
                    value = value - 1;
                    Log.e("quantity", String.valueOf(value));
                    cartValue.setText(String.valueOf(value));
                    displayTotal(value);
                }

            }
//
        });

            addToCart = (Button) findViewById(R.id.btnAddCart);
            addToCart.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (value > 0) {
                        //code for add to cart here
                        /*Intent uniqueintent = new Intent(getApplicationContext(),StoredCartItemsActivity.class);
                        uniqueintent.putExtra("itemName", getname);
                        uniqueintent.putExtra("itemPrice", price);*/
                        /*
                        nameSet.add(getname);
                        priceSet.add(String.valueOf(finalprice));

                        prefs.edit().putStringSet("nameSet",nameSet);
                        prefs.edit().putStringSet("priceSet",priceSet);
                        prefs.edit().commit();*/

                        SharedPreferences pefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        if(String.valueOf(pefs.getInt("count", 0)) !=null)
                        {
                            count = pefs.getInt("count", 0);
                            count += 1;
                        }

                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        prefs.edit().putString("itemName"+count, getname).commit();
                        prefs.edit().commit();

                        SharedPreferences prefsss = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        prefsss.edit().putInt("itemPrice"+count, finalprice).commit();
                        prefsss.edit().commit();

                        pefs.edit().putInt("count",count).commit();


                        Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent1);


                        Toast.makeText(getApplicationContext(), "Your item is added to cart", Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(getApplicationContext(), "Need atlest one item", Toast.LENGTH_SHORT).show();

                    }
                }
            });

    }


    //function for count price
    public void displayTotal(int value){
        //finalprice = 0;
        finalprice =value*price;
        totalValue.setText("$ "+String.valueOf(finalprice));

    }
}
