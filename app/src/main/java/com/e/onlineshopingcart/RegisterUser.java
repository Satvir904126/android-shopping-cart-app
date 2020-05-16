package com.e.onlineshopingcart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterUser extends MenuOptionClass {
EditText userName,password,email,mobNo;
Button register;
TextView backToLogin;
    DBHelper dbSqlitHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        dbSqlitHelper = new DBHelper(this);
        userName=(EditText)findViewById(R.id.userName);
        password=(EditText)findViewById(R.id.password);
        email=(EditText)findViewById(R.id.userEmail);
        mobNo=(EditText)findViewById(R.id.mobileNo);
        register=(Button)findViewById(R.id.btnRegister);
        backToLogin=(TextView)findViewById(R.id.backToLogin);
    register.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            checkValidation();
            String str_name = userName.getText().toString();
            String str_pass = password.getText().toString();
            String str_email = email.getText().toString();
            String str_mob = mobNo.getText().toString();
            Log.e("enter name",str_name);
            Log.e("enterpassword",str_pass);
            dbSqlitHelper.insrtValue(userName.getText().toString(),password.getText().toString());
            Log.e("enter name",str_name);
            Log.e("enterpassword",str_pass);

if(checkValidation()) {
    Toast.makeText(getApplicationContext(),"Account Created",Toast.LENGTH_LONG).show();
    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
    startActivity(i);
}
        }
    });

    //redirect to login page
    backToLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
        }
    });
    }

    boolean checkValidation(){
        if (TextUtils.isEmpty(userName.getText().toString())){

            Toast.makeText(this, "Plz enter your name ", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(email.getText().toString())){
            Toast.makeText(this, "Plz enter your Email ", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(mobNo.getText().toString()) && Integer.parseInt(mobNo.getText().toString())<10){
            Toast.makeText(this, "Plz enter your Mobile No ", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(password.getText().toString())){
            Toast.makeText(this, "Plz enter your password ", Toast.LENGTH_SHORT).show();
            return false;
        }



        return true;
    }
}

