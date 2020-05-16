package com.e.p92practicalexam2satvirsingh;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class dbHelperClass extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "finalexam.db";
    private static final String DB_PATH_SUFFIX = "/databases/";
    static Context ctx;

    public dbHelperClass(Context context) {
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

    public ArrayList<SetDataClass> getDetails() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<SetDataClass> contList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from recipes", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String str_name = cursor.getString(cursor.getColumnIndex("name"));
                String str_des = cursor.getString(cursor.getColumnIndex("des"));
                int price = cursor.getInt(cursor.getColumnIndex("price"));
                byte[] img = cursor.getBlob(cursor.getColumnIndex("image"));

                ByteArrayInputStream imageStream = new ByteArrayInputStream(img);
                Bitmap theImage = BitmapFactory.decodeStream(imageStream);

                contList.add(new SetDataClass( theImage, str_name, str_des, price));
            }
            cursor.close();
            db.close();
        }
        return contList;
    }


}
