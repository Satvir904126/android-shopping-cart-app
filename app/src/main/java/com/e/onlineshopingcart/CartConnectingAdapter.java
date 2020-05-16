package com.e.onlineshopingcart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CartConnectingAdapter extends ArrayAdapter<String> {

    Context context;
    ArrayList<SetCartItems> cartItems= new ArrayList<>();
    public CartConnectingAdapter(@NonNull Context context, int resource,  ArrayList<SetCartItems> cartItems) {
        super(context, resource);
        this.context=context;
        this.cartItems=cartItems;
    }

    @Override
    public int getCount() {
        return cartItems.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.stored_item_list,parent, false);

        TextView itemName = (TextView)view.findViewById(R.id.itemName);
        TextView itemPrice = (TextView)view.findViewById(R.id.ItemTotalPrice);

        itemName.setText(cartItems.get(position).getItemName());
        itemPrice.setText( String.valueOf(cartItems.get(position).getTotalPrice()));


        return view;




    }
}
