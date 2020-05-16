package com.e.p92practicalexam2satvirsingh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class orderItemActivity extends AppCompatActivity {
    ListView listItems;
    ArrayList<SetOrderItems> orderItems =new ArrayList<>();
String name;
    int count;
    int price,quantity,totalPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtocart);

        SharedPreferences prefss = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences p1 = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences p2 = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences p3 = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences p4 = PreferenceManager.getDefaultSharedPreferences(this);

        count = prefss.getInt("count", 0);
        Log.e("count: ", String.valueOf(count));
//        price = p1.getInt("itemPrice" + count, 0);
//        Log.e("name",String.valueOf(price));


        for (int i = 1; i <= count; i++) {
            name = p1.getString("itemName" + i, " ");
            Log.e("name",name);
            //String encoded = p2.getString("itemImage"+i,null);
          //  byte[] imageAsBytes = Base64.decode(encoded.getBytes(), Base64.DEFAULT);
          //  image = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
            quantity= p3.getInt("item"+name+"Quantity"+count,0);
            price = p4.getInt("itemPrice" + i, 0);
            orderItems.add(new SetOrderItems(name,price,quantity,totalPrice));
        }
        listItems = (ListView) findViewById(R.id.Prodlist1);

        cartAdapter a =new cartAdapter(orderItemActivity.this,R.id.gone,orderItems);

        listItems.setAdapter(a);


        Button cart = (Button) findViewById(R.id.chechout);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);

                SharedPreferences presssfs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                presssfs.edit().clear().commit();
                startActivity(intent);
            }

        });


    }
}
