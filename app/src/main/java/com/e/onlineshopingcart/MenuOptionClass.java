package com.e.onlineshopingcart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MenuOptionClass extends AppCompatActivity {

    ///menu in file top navigation
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_items,menu);

        return true;
    }


/////selelct the particular button home contact us etc


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                break;
            case R.id.item2:
                Intent i2 = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i2);
                break;
            case R.id.item3:
                Intent i3 = new Intent(getApplicationContext(),RegisterUser.class);
                startActivity(i3);
                break;
            case R.id.item4:
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                boolean Islogin = prefs.getBoolean("Islogin", false);
                if(Islogin)
                {
                    Intent i4 = new Intent(getApplicationContext(),StoredCartItemsActivity.class);
                    startActivity(i4);
                }
                else
                {
                    Intent i6 = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(i6);
                }
                break;
            case R.id.item5:
                SharedPreferences preferes = PreferenceManager.getDefaultSharedPreferences(this);
                preferes.edit().remove("Islogin").commit();
                Intent i7 = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i7);
                break;

            default:
        }

        return super.onOptionsItemSelected(item);
    }


}
