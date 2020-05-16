package com.e.onlineshopingcart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.service.autofill.UserData;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.e.onlineshopingcart.R;

import java.util.ArrayList;
import java.util.Iterator;

public class LoginActivity extends MenuOptionClass {
EditText userName,password;
Button login;
TextView createAccount;
    boolean Islogin = false;
    DBHelper dbSqlitHelper;

    ArrayList<userData> userList =new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        dbSqlitHelper = new DBHelper(this);
        dbSqlitHelper.openDataBase();
        userList = dbSqlitHelper.getAllDetail();
       // Log.e("user",userList.get(0).getName());
        //Log.e("user",userList.get(0).getPassword());


userName=(EditText)findViewById(R.id.userName);
password=(EditText)findViewById(R.id.password);
createAccount=(TextView)findViewById(R.id.createAccount);
        Log.e("loguser", String.valueOf(userName));
        Log.e("log pass", String.valueOf(password));


        login=(Button)findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Function to ckeck the textbox has data or not
                checkValidation();
                String str_name = userName.getText().toString();
                String str_pass = password.getText().toString();
                if (checkValidation() == true) {
                    Islogin = true;
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    prefs.edit().putBoolean("Islogin", Islogin).commit();

                    Intent i = new Intent(getApplicationContext(), StoredCartItemsActivity.class);
                    i.putExtra("name", str_name);
                    i.putExtra("pass", str_pass);

                    ///directy sent the type by gloval variable

                    //i.putExtra("type", str_name);
                    startActivity(i);
                }
            }
        });

        //redirect to login page
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegisterUser.class);
                startActivity(i);
            }
        });
    }
    boolean checkValidation() {
        boolean match = true;
        //Iterator i = userList.iterator();
        if (TextUtils.isEmpty(userName.getText().toString())) {
            Log.e("userName", "empty");
            Toast.makeText(this, "Plz enter your name ", Toast.LENGTH_SHORT).show();
            match = false;
        }
        if (TextUtils.isEmpty(password.getText().toString())) {
            Log.e("userName", "empty");
            Toast.makeText(this, "Plz enter your password ", Toast.LENGTH_SHORT).show();
            match = false;
        }
        for (int i = 0; i < userList.size(); i++) {
            if (!userName.getText().toString().equals(userList.get(i).getName())) {
                match = false;
            } else {
                if (!password.getText().toString().equals(userList.get(i).getPassword())) {
                    match = false;

                } else {
                    match = true;
                    break;
                }
            }
        }

            if (match != true) {
                Toast.makeText(this, "user name or Password is wrong ", Toast.LENGTH_SHORT).show();
            }

            return match;
        }
}
