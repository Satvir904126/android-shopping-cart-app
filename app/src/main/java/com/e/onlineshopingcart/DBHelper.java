package com.e.onlineshopingcart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "onlineShopping.db";
    private static final String DB_PATH_SUFFIX = "/databases/";
    static Context ctx;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        ctx = context;
    }

    public void CopyDataBaseFromAsset() throws IOException {
        InputStream myInput = ctx.getAssets().open(DATABASE_NAME);
// Path to the just created empty db
        String outFileName = getDatabasePath();
// if the path doesn't exist first, create it
        File f = new File(ctx.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
        if (!f.exists())
            f.mkdir();
// Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);
// transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
// Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }
    private static String getDatabasePath() {
        return ctx.getApplicationInfo().dataDir + DB_PATH_SUFFIX
                + DATABASE_NAME;
    }
    public SQLiteDatabase openDataBase() throws SQLException {
        File dbFile = ctx.getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists()) {
            try {
                CopyDataBaseFromAsset();
                System.out.println("Copying sucess from Assets folder");
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }
        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public ArrayList<SetImagesClass> getDetails() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<SetImagesClass> contList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from items", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
//                Contact cont = new Contact(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                String str_name = cursor.getString(cursor.getColumnIndex("itemName"));
                int price = cursor.getInt(cursor.getColumnIndex("itemPrice"));
                byte[] img = cursor.getBlob(cursor.getColumnIndex("itemImage"));

                ByteArrayInputStream imageStream = new ByteArrayInputStream(img);
                Bitmap theImage = BitmapFactory.decodeStream(imageStream);
                /*contList.add(str_name);
                contList.add(price);
               contList.add(img);*/
                contList.add(new SetImagesClass( theImage, str_name, price));
            }
            cursor.close();
            db.close();
        }
        return contList;
    }


    public ArrayList<userData> getAllDetail() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<userData> userList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from user", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
//                Contact cont = new Contact(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                String name = cursor.getString(cursor.getColumnIndex("userName"));
                String pass = cursor.getString(cursor.getColumnIndex("userPassword"));



                userList.add(new userData(name,pass));
            }
            cursor.close();
            db.close();
        }
        return userList;
    }

    public void insrtValue(String str_name,String str_pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("userName",str_name);
        cv.put("userPassword",str_pass);

        long newRowId =  db.insert ("user",null,cv);
        Log.e("usereee", String.valueOf(newRowId));
    }

}


//btn_get.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View v) {
//
//        ArrayList<CustomStudent> al_students = dbSqliteHelper.getStudents();
//        for (int i=0; i<al_students.size();i++){
//        Log.e("name=", al_students.get(i).getStr_name());
//        }
//
//        }
//        });









