package com.e.onlineshopingcart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class StoredCartItemsActivity extends MenuOptionClass {
    ListView listItems;

    ArrayList<SetCartItems> orderItems =new ArrayList<>();
    String name = "m";
    int count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stored_cart_items);
        Button checkout=(Button)findViewById(R.id.btnMakeOrder);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences prefss = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences pess = PreferenceManager.getDefaultSharedPreferences(this);
        count = prefs.getInt("count",0);
        //Log.e("count",String.valueOf(count));
        /*String itemName = prefs.getString("itemName","");
        Log.e("list items", itemName);*/

        /*
        int itemPrice = prefss.getInt("itemPrice",0);
        Log.e("list price", String.valueOf(itemPrice));

*/      for(int i=1;i<=count;i++) {
            orderItems.add(new SetCartItems(prefss.getString("itemName"+i, null), pess.getInt("itemPrice"+i, 0)));
        }
        listItems = (ListView) findViewById(R.id.listitem);

        CartConnectingAdapter a =new CartConnectingAdapter(StoredCartItemsActivity.this,R.id.gone,orderItems);

//                        CartConnectingAdapter adapter = new CartConnectingAdapter(AddToCart.this,R.id.gone,orderItems);
        listItems.setAdapter(a);

        listItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String name = orderItems.get(position).itemName;
                int price = orderItems.get(position).totalPrice;
            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences prefss = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences pess = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);

                for(int i=1;i<=count;i++) {

                    prefss.edit().remove("itemName"+i);
                    pess.edit().remove("itemPrice"+i);
                    prefss.edit().commit();
                    pess.edit().commit();
                    //orderItems.add(new SetCartItems(prefss.getString("itemName"+i, null), pess.getInt("itemPrice"+i, 0)));
                }
                prefs.edit().remove("count").commit();
                Toast.makeText(getApplicationContext(), "Order is successfully placed", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(intent);
            }
        });



        //prefs.edit().remove("itemName");
        //prefss.edit().remove("itemPrice");
        //prefs.edit().commit();
    }


}
