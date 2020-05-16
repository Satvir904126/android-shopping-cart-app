package com.e.p92practicalexam2satvirsingh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class cartAdapter extends ArrayAdapter<String> {




    Context context;
    ArrayList<SetOrderItems> itemArray= new ArrayList<>();
    public cartAdapter(@NonNull Context context, int resource, ArrayList<SetOrderItems> itemArray) {
        super(context, resource);
        this.context =context;
        this.itemArray= itemArray;
    }

    @Override
    public int getCount() {
        return itemArray.size();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listcart,parent, false);

        TextView name = (TextView)view.findViewById(R.id.ProdName1);
        TextView price = (TextView)view.findViewById(R.id.ProdPrice1);
        TextView totalPrice = (TextView)view.findViewById(R.id.TotalPrice1);
        TextView quantity = (TextView)view.findViewById(R.id.ProdQuantity1);


        name.setText(itemArray.get(position).ProdName);
        price.setText(String.valueOf(itemArray.get(position).Price));
        totalPrice.setText(String.valueOf(itemArray.get(position).Total));
        quantity.setText(String.valueOf(itemArray.get(position).Quantity));

       // img.setImageBitmap(imagesArray.get(position).getImage());


        return view;
    }

}