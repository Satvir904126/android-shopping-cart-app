package com.e.onlineshopingcart;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;


public class ConnectingAdapter extends ArrayAdapter<String> {

      Context context;
       ArrayList<SetImagesClass> imagesArray= new ArrayList<>();
    public ConnectingAdapter(@NonNull Context context, int resource,ArrayList<SetImagesClass> imagesArray) {
        super(context, resource);
        this.context =context;
        this.imagesArray= imagesArray;
    }

    @Override
    public int getCount() {
        return imagesArray.size();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_all_images,parent, false);

        TextView tv_name = (TextView)view.findViewById(R.id.productName);
        ImageView img =(ImageView)view.findViewById(R.id.productImage);

        tv_name.setText(imagesArray.get(position).getImg_name());
        //img.setImageResource(Integer.parseInt(imagesArray.get(position).getImage()));
        img.setImageBitmap(imagesArray.get(position).getImage());
        //img.setImageDrawable(ContextCompat.getDrawable(context, imagesArray.get(position).getImage()));

        return view;
    }

}
