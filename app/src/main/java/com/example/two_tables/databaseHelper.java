package com.example.two_tables;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class databaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "movies.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_MOVIES = "movies";
    public static final String COL_NAME = "NAME";
    public static final String COL_DESC = "DESCRIPTION";
    public static final String COL_POSTER = "POSTER";
    public static final String COL_VIDID = "VID_ID";
    public static final String COL_PRICE = "PRICE";
    public static final String COL_ID = "ID";


    public static final String TABLE_CART = "cart";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_PRICE = "PRICE";
    public static final String COLUMN_POSTER = "POSTER";


    public databaseHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //movies table
        String createMovies = "CREATE TABLE " + TABLE_MOVIES+ " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NAME + " TEXT ,"
                + COL_DESC + " TEXT ,"
                + COL_PRICE + " TEXT,"
                + COL_VIDID + " TEXT ," //we use this to load video form youtube
                + COL_POSTER + " BLOB)";

        sqLiteDatabase.execSQL(createMovies);


        //cart table
        String createCart = "CREATE TABLE " + TABLE_CART + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT ,"
                + COLUMN_PRICE + " TEXT,"
                + COLUMN_POSTER + " BLOB)";

        sqLiteDatabase.execSQL(createCart);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        onCreate(sqLiteDatabase);
    }

    public void addOne(String name, String price,String description, byte[] poster, String vid_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_NAME, name);
        cv.put(COL_PRICE, price);
        cv.put(COL_DESC, description);
        cv.put(COL_POSTER, poster);
        cv.put(COL_VIDID, vid_id);

        db.insert(TABLE_MOVIES, null, cv);
        db.close();
    }


    public ArrayList<moviesModel> getall(){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT NAME, DESCRIPTION, PRICE, VID_ID, POSTER FROM " + TABLE_MOVIES+";";
        ArrayList<moviesModel> allmovies = new ArrayList<>();

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            //loop through result >> new contacts object for each row

            do{
                String movieName = cursor.getString(0);

                String movieDescription = cursor.getString(1);

                String moviePrice = cursor.getString(2);

                String vid_id = cursor.getString(3);

                byte[] byteimage = cursor.getBlob(4);

                moviesModel _new = new moviesModel( movieName, moviePrice, movieDescription, vid_id, byteimage);


                allmovies.add(_new);

            }while(cursor.moveToNext());

        }else{

        }

        cursor.close();
        db.close();

        return allmovies;
    }

    public void addCart(String name, String price, byte[] poster){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_POSTER, poster);

        db.insert(TABLE_CART, null, cv );
        db.close();

    }


    public List<cartModel> getAllCarts(){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT NAME, PRICE,POSTER FROM " + TABLE_CART+";";
        List<cartModel> allmovies = new ArrayList<>();

        Cursor cursor = db.rawQuery(query, null);


        if(cursor.moveToFirst()){

            //loop through result >> new contacts object for each row

            do{
                String movieName = cursor.getString(0);

                String moviePrice = cursor.getString(1);

                byte[] byteimage = cursor.getBlob(2);

                cartModel _new = new cartModel( movieName, moviePrice, byteimage);


                allmovies.add(_new);


            }while(cursor.moveToNext());

        }else{

        }

        cursor.close();
        db.close();
        return allmovies;
    }

    public List<String> getprice(){
        SQLiteDatabase db = this.getReadableDatabase();

        List<String> priceArray = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT PRICE FROM "+ TABLE_CART, null);

        if(cursor.moveToFirst()){

            //loop through result >> new contacts object for each row

            do{

                String moviePrice = cursor.getString(0);

                priceArray.add(moviePrice);

            }while(cursor.moveToNext());

        }else{
        }

        cursor.close();
        db.close();
        return priceArray;
    }

    public Integer getID(int position){
        SQLiteDatabase db = this.getReadableDatabase();

        Integer ID = null;

        Cursor cursor = db.rawQuery("SELECT ID FROM "+ TABLE_CART + "WHERE ID =  "+ position , null);

        if(cursor.moveToFirst()){

            //loop through result >> new contacts object for each row

            do{

                ID = cursor.getInt(0);



            }while(cursor.moveToNext());

        }else{
        }

        cursor.close();
        db.close();
        return ID;
    }

    public List<String> get_name(){
        SQLiteDatabase db = this.getReadableDatabase();

        List<String> nameArray = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT NAME FROM "+ TABLE_CART, null);

        if(cursor.moveToFirst()){

            //loop through result >> new contacts object for each row

            do{

                String moviePrice = cursor.getString(0);

                nameArray.add(moviePrice);

            }while(cursor.moveToNext());

        }else{
        }

        cursor.close();
        db.close();
        return nameArray;
    }




    public void deletecartTable(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_CART,null, null);
        db.close();
    }

    public void deletecartItem(String name){

        SQLiteDatabase db = this.getWritableDatabase();

        Integer ID = null;

        db.delete(TABLE_CART, "NAME = " + " '" + name + "' ", null);
        db.close();
    }

}
