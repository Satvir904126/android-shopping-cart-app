package com.e.onlineshopingcart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends MenuOptionClass {

    ListView listImage;
    DBHelper dbSqlitHelper;
//    ImageButton clickImage;
    ArrayList<SetImagesClass> arrayList =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dbSqlitHelper = new DBHelper(this);
        dbSqlitHelper.openDataBase();
        arrayList = dbSqlitHelper.getDetails();

        listImage = (ListView) findViewById(R.id.listOfImages);

          ConnectingAdapter adapter = new ConnectingAdapter(MainActivity.this, R.id.gone,arrayList);
         listImage.setAdapter(adapter);

        listImage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String clickedName = arrayList.get(position).img_name;
                int clickedPrice = arrayList.get(position).price;
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                Bitmap clickedImage = arrayList.get(position).image;
                clickedImage.compress(Bitmap.CompressFormat.PNG, 50, bs);
Log.e("hjhkh", String.valueOf(clickedPrice));
                Intent intent = new Intent(getApplicationContext(),AddToCart.class);
                //Intent intent = new Intent(view.getContext(),AddToCart.class);
               //intent.putExtra("ClickedImage", clickedImage);
                intent.putExtra("ClickedImage", bs.toByteArray());
                intent.putExtra("ClickedName", clickedName);
                intent.putExtra("ClickedPrice", clickedPrice);
                startActivity(intent);
            }
        });

    }



}
