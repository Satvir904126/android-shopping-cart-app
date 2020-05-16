package com.e.p92practicalexam2satvirsingh;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listOfImages;
    dbHelperClass dbSqlitHelper;
    ArrayList<SetDataClass> arrayList =new ArrayList<>();
int price =0,val=0,finalPrice=0,count=1;
    TextView cartQuantity,totalPrice;
    Button increment,decrement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dbSqlitHelper = new dbHelperClass(this);
        dbSqlitHelper.openDataBase();
        arrayList = dbSqlitHelper.getDetails();
        Log.e("new","create");



        listOfImages = (ListView) findViewById(R.id.Prodlist);
        //listImage = (ListView) findViewById(R.id.listOfImages);

        ConnectingListMain adapter = new ConnectingListMain(MainActivity.this, R.id.gone,arrayList);
        listOfImages.setAdapter(adapter);


        listOfImages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final String name = arrayList.get(position).img_name;
        String des = arrayList.get(position).description;
        price = arrayList.get(position).price;
        final Bitmap image = arrayList.get(position).image;

                
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);



        LayoutInflater factory = LayoutInflater.from(MainActivity.this);
final View newview = factory.inflate(R.layout.dialoglist, null);
        //((ViewGroup)newview.getParent()).removeView(newview);
        alertDialogBuilder.setView(newview);
        // set title
        alertDialogBuilder.setTitle("Order Please");

        //
        // set dialog message
        alertDialogBuilder
        .setCancelable(false)
        .setPositiveButton("Add to Cart", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    if(val>0)
                    {


                        Log.e("count beofre:",String.valueOf(count));
                        SharedPreferences p7 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        if(String.valueOf(p7.getInt("count", 0)) !=null)
                        {
                            count = p7.getInt("count", 0);
                            count += 1;
                        }


                        SharedPreferences p8 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences p9 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        p8.edit().putString("itemName"+count, name).commit();
                        p9.edit().putInt("item"+name+"Quantity"+count,val).commit();

                        SharedPreferences p10 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        //SharedPreferences p11 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                        p10.edit().putInt("itemPrice"+count, price).commit();

//                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                        image.compress(Bitmap.CompressFormat.PNG, 100, baos); //bm is the bitmap object
//                        byte[] b = baos.toByteArray();
//                        String encoded = Base64.encodeToString(b, Base64.DEFAULT);
//
//                        p11.edit().putString("itemImage"+count,encoded).commit();

                        p7.edit().putInt("count",count).commit();
//                        Log.e("msg",cartitemslist.get(0).name);
                        val = 0;
                        Toast.makeText(getApplicationContext(), "Your item is added to cart", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Need atlest one item", Toast.LENGTH_SHORT).show();
                    }
                }
            })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    dialog.cancel();
                    val = 0;
                    Toast.makeText(getApplicationContext(), "You clicked on Cancel Button", Toast.LENGTH_SHORT).show();
                }
            });


        ImageView im = (ImageView) newview.findViewById(R.id.dImage);
        im.setImageBitmap(image);
TextView n =(TextView)newview.findViewById(R.id.dName);
n.setText(name);
                TextView dess =(TextView)newview.findViewById(R.id.dDes);
                dess.setText(des);
       cartQuantity = (TextView) newview.findViewById(R.id.dquantity);
        increment = (Button)newview.findViewById(R.id.btnInc);
         decrement = (Button) newview.findViewById(R.id.btnDec);
        totalPrice = (TextView) newview.findViewById(R.id.dTotalPrice);

        increment.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {

        val = val + 1;
    cartQuantity.setText(String.valueOf(val));
        displayTotal(val);
        }
        });

        decrement.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {


        if (val > 0) {
        val = val - 1;
        Log.e("quantity", String.valueOf(val));
        cartQuantity.setText(String.valueOf(val));
        displayTotal(val);
        }
        }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        }
        });

        Button checkOrder= (Button)findViewById(R.id.btnSeeOrder);
        checkOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),orderItemActivity.class);
                startActivity(intent);
            }
        });



        }

       public void displayTotal(int value) {
        finalPrice = value * price;
         totalPrice.setText("$ " + String.valueOf(finalPrice));
        }
    }




