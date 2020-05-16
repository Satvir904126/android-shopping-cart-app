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

public class ConnectingListMain extends ArrayAdapter<String> {



    Context context;
    ArrayList<SetDataClass> ListArray= new ArrayList<>();
    public ConnectingListMain(@NonNull Context context, int resource, ArrayList<SetDataClass> imagesArray) {
        super(context, resource);
        this.context =context;
        this.ListArray= imagesArray;
    }

    @Override
    public int getCount() {
        return ListArray.size();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listofiems,parent, false);

        TextView name = (TextView)view.findViewById(R.id.ProdName);
        TextView description = (TextView)view.findViewById(R.id.ProdDes);
        TextView price = (TextView)view.findViewById(R.id.ProdPrice);
        ImageView imgage =(ImageView)view.findViewById(R.id.prodImage);

        name.setText(ListArray.get(position).getImg_name());
        description.setText(ListArray.get(position).getDescription());
        price.setText("$"+String.valueOf(ListArray.get(position).getPrice()));
        imgage.setImageBitmap(ListArray.get(position).getImage());

        return view;
    }

}

