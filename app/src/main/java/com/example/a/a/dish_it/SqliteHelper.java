package com.example.a.a.dish_it;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SqliteHelper extends SQLiteOpenHelper {

    //DATABASE NAME
    public static final String DATABASE_NAME = "Alllll.com";

    String Error = "Error";
    String tag = "tag";
    //DATABASE VERSION
    public static final int DATABASE_VERSION = 12;

    //TABLE NAME
    public static final String TABLE_USERS = "users";
    public static final String TABLE_DISH_DETAIL = "dishDetail";
    public static final String TABLE_CART = "cart";

    //TABLE USERS COLUMNS
    //ID COLUMN @primaryKey
    public static final String KEY_ID = "id";
    //COLUMN user name
    public static final String KEY_USER_NAME = "username";
    //COLUMN email
    public static final String KEY_EMAIL = "email";
    //COLUMN password
    public static final String KEY_PASSWORD = "password";

    public static final String KEY_TYPE = "accountType";

    //TABLE DishDetail COLUMNS
    //ID COLUMN @primaryKey
    public static final String KEY_DISH_ID = "dishID";
    public static final String KEY_DISH_NAME = "dishName";
    public static final String KEY_DISH_Description = "dishDescription";
    public static final String KEY_Dish_Price = "dishPrice";
    public static final String KEY_Dish_chef = "dishChef";
    public static final String KEY_Dish_USEREmail = "userEmail";
    public static final String KEY_Dish_Quantity = "qty";

    public static final String KEY_CART_ID = "cartID";
    public static final String KEY_CART_EMAIL = "cartUserEmail";
    public static final String KEY_CART_TOTALPRICE = "cartTotalPrice";
    public static final String KEY_CART_ADDRESS = "cartAddress";
    public static final String KEY_CART_CONTACT = "cartContact";
    public static final String KEY_CART_DELIVERY = "cartDeliver";




    //SQL for creating users table
    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , "
            + KEY_USER_NAME + " TEXT, "
            + KEY_EMAIL + " TEXT, "
            + KEY_PASSWORD + " TEXT, "
            + KEY_TYPE + " TEXT "
            + " ) ";


    //SQL for creating Dish table
    public static final String SQL_TABLE_DISH_DETAIL = " CREATE TABLE " + TABLE_DISH_DETAIL
            + " ( "
            + KEY_DISH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + KEY_DISH_NAME + " TEXT, "
            + KEY_DISH_Description + " TEXT, "
            + KEY_Dish_Price + " TEXT, "
            + KEY_Dish_chef + " TEXT, "
            + KEY_Dish_USEREmail + " TEXT,"
            + KEY_Dish_Quantity + " TEXT "
            + " ) ";

    //SQL for creating Dish table
    public static final String SQL_TABLE_CART = " CREATE TABLE " + TABLE_CART
            + " ( "
            + KEY_CART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + KEY_CART_EMAIL + " TEXT, "
            + KEY_CART_TOTALPRICE  + " TEXT, "
            + KEY_CART_ADDRESS + " TEXT, "
            + KEY_CART_CONTACT + " TEXT, "
            + KEY_CART_DELIVERY + " TEXT "
            + " ) ";



    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create Table when oncreate gets called
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);
        sqLiteDatabase.execSQL(SQL_TABLE_DISH_DETAIL);
        sqLiteDatabase.execSQL(SQL_TABLE_CART);

    }


    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop table to create new one if database version updated
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_DISH_DETAIL);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + SQL_TABLE_CART);
    }

    //using this method we can add users to user table
    public void addUser(User user) {
        try {


            //get writable database
            SQLiteDatabase db = this.getWritableDatabase();

            //create content values to insert
            ContentValues values = new ContentValues();

            //Put username in  @values
            values.put(KEY_USER_NAME, user.userName);

            //Put email in  @values
            values.put(KEY_EMAIL, user.email);

            //Put password in  @values
            values.put(KEY_PASSWORD, user.password);

            values.put(KEY_TYPE, user.userType);

            // insert row
            long todo_id = db.insert(TABLE_USERS, null, values);
            db.close();
            Log.i(tag,"Inserting user"+todo_id+""+values);
        }catch (Exception ex){

            Log.e(Error,"Adding User Error"+ex);

        }
    }

    public void addDish(Dish dish) {
        try {


            //get writable database
            SQLiteDatabase db = this.getWritableDatabase();

            //create content values to insert
            ContentValues values = new ContentValues();

            values.put(KEY_DISH_NAME, dish.dishName);

            values.put(KEY_DISH_Description, dish.dishDescription);

            values.put(KEY_Dish_Price, dish.dishprice);
            values.put(KEY_Dish_chef, dish.chef);

            values.put(KEY_Dish_USEREmail, dish.userEmail);

            // insert row
            long todo_id = db.insert(TABLE_DISH_DETAIL, null, values);
            Log.i(tag,"Adding Dish Error"+values);
            db.close();

        }catch (Exception ex){

            Log.e(Error,"Adding Dish Error checkTest "+ex);

        }
    }

    public void addOrder(Delivery delivery) {
        try {


            //get writable database
            SQLiteDatabase db = this.getWritableDatabase();
            //create content values to insert
            ContentValues values = new ContentValues();
            values.put(KEY_CART_EMAIL, delivery.userEmail);
            Log.i(tag,"Adding CART checkTest"+ values);
            Log.i(tag,"Adding CART checkTest"+ delivery.userEmail);
            values.put(KEY_CART_TOTALPRICE, delivery.TotalPrice);
            Log.i(tag,"Adding CART checkTest"+ values);
            Log.i(tag,"Adding CART checkTest"+ delivery.TotalPrice);
            values.put(KEY_CART_ADDRESS, delivery.Address);
            Log.i(tag,"Adding CART checkTest"+ values);
            Log.i(tag,"Adding CART checkTest"+ delivery.Address);
            values.put(KEY_CART_CONTACT, delivery.ContactNo);
            Log.i(tag,"Adding CART checkTest"+ values);
            Log.i(tag,"Adding CART checkTest"+ delivery.ContactNo);
            values.put(KEY_CART_DELIVERY, delivery.Deliver);
            Log.i(tag,"Adding CART checkTest"+ values);
            Log.i(tag,"Adding CART checkTest"+ delivery.Deliver);



            // insert row
            long todo_id = db.insert(TABLE_CART, null, values);
            Log.i(tag,"Adding CART checkTest "+values);
            db.close();

        }catch (Exception ex){

            Log.e(Error,"Adding CART Error  "+ex);

        }
    }

    public User Authenticate(User user) {
        try {

            Log.i(tag,"AuthenticationUser¼ Inside " +user);
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("Select * from users where email=? and password = ?", new
                    String[]{user.email, user.password});
            Log.i(tag,"AuthenticationUser Inside " +cursor);

            if (cursor != null &&  cursor.moveToFirst() && cursor.getCount()>0) {
                //if cursor has value then in user database there is user associated with this given email
                User user11 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));

                //Match both dishid check they are same or not
                if (user.email.equalsIgnoreCase(user11.email) && user.password.equalsIgnoreCase(user11.password)) {
                    Log.i(tag,"AuthenticationUser Inside "+" email" +user11);
                    return user11;
                }
            }

            //if user dishid does not matches or there is no record with that email then return @false

        }catch (Exception ex){
            Log.e(Error,"Athentication User Error"+ex);
        }
        return null;
    }

    public String AuthenticateUserType(String email) {
        try {
            String str = "";
            User user1 = new User();
            Log.i(tag,"AuthenticationUserType Inside " +email);
            SQLiteDatabase db = this.getReadableDatabase();
            //  String query = "SELECT KEY_TYPE FROM TABLE_USERS WHERE KEY_EMAIL='" + email;

            Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                    new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD,KEY_TYPE},//Selecting columns want to query
                    KEY_EMAIL + "=?",
                    new String[]{email},//Where clause
                    null, null, null);
            Log.i(tag,"AuthenticationUserType Inside " +cursor);


            Log.i(tag,"AuthenticationUserType Inside " +email);
            if (cursor != null &&  cursor.moveToFirst() && cursor.getCount()>0) {
                return   str = cursor.getString(cursor.getColumnIndex("accountType"));

            }



            //if user dishid does not matches or there is no record with that email then return @false

        }catch (Exception ex){
            Log.e(Error,"Athentication User Error"+ex);
        }
        return null;
    }

    public String getUserName(String email) {
        try {
            String str = "";
            User user1 = new User();
            Log.i(tag,"getUserName Inside " +email);
            SQLiteDatabase db = this.getReadableDatabase();
            //  String query = "SELECT KEY_TYPE FROM TABLE_USERS WHERE KEY_EMAIL='" + email;

            Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                    new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD,KEY_TYPE},//Selecting columns want to query
                    KEY_EMAIL + "=?",
                    new String[]{email},//Where clause
                    null, null, null);
            Log.i(tag,"getUserName Inside " +cursor);


            Log.i(tag,"getUserName Inside " +email);
            if (cursor != null &&  cursor.moveToFirst() && cursor.getCount()>0) {
                return   str = cursor.getString(cursor.getColumnIndex("username"));

            }



            //if user dishid does not matches or there is no record with that email then return @false

        }catch (Exception ex){
            Log.e(Error,"Athentication User Error"+ex);
        }
        return null;
    }

    public Dish Authenticate(Dish dish) {
        try {

            Log.i(tag,"Athentication Dish Id  "+dish);
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(TABLE_DISH_DETAIL,// Selecting Table
                    new String[]{KEY_DISH_ID, KEY_DISH_NAME, KEY_DISH_Description, KEY_Dish_Price, KEY_Dish_chef,
                            KEY_Dish_USEREmail,KEY_Dish_Quantity},//Selecting columns want to query
                    KEY_DISH_NAME + "=?",
                    new String[]{dish.dishName},//Where clause
                    null, null, null,null);
            Log.i(tag,"Athentication Dish Id  "+cursor);
            if (cursor != null &&  cursor.moveToFirst() && cursor.getCount()>0) {
                //if cursor has value then in user database there is user associated with this given email
                Dish dish1 = new Dish(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4),cursor.getString(5),cursor.getString(6));
                Log.i(tag,"Athentication Dish Id  "+dish1);
                //Match both dishid check they are same or not
                if (dish.dishid.equalsIgnoreCase(dish1.dishid)) {
                    Log.i(tag,"Athentication Dish Id  "+dish.dishid);
                    return dish1;
                }
            }

        }catch (Exception ex){

            Log.e(Error,"Athentication Dish Error"+ex);

        }

        //if user dishid does not matches or there is no record with that email then return @false
        return null;
    }

    public boolean isEmailExists(String email) {
        try {
            Log.i(tag,"isEmailExists Inside " +email);
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                    new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD,KEY_TYPE},//Selecting columns want to query
                    KEY_EMAIL + "=?",
                    new String[]{email},//Where clause
                    null, null, null);
            Log.i(tag,"isEmailExists Inside " +cursor);
            if (cursor != null && cursor.moveToFirst()  && cursor.getCount()>0) {
                //if cursor has value then in user database there is user associated with this given email so return true
                return true;
            }
            Log.i(tag,"isEmailExists Inside " +cursor);
        }catch (Exception ex){

            Log.e(Error,"Check Email Exist Error"+ex);

        }
        //if email does not exist return false
        return false;
    }

    public boolean isDishExists(String dishid) {
        try {
            Log.i(tag,"isDishExists Inside");
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(TABLE_DISH_DETAIL,// Selecting Table
                    new String[]{KEY_DISH_ID, KEY_DISH_NAME, KEY_DISH_Description, KEY_Dish_Price, KEY_Dish_chef,
                            KEY_Dish_USEREmail,KEY_Dish_Quantity},//Selecting columns want to query
                    KEY_DISH_ID + "=?",
                    new String[]{dishid},//Where clause
                    null, null, null);

            if (cursor != null && cursor.moveToFirst()  && cursor.getCount()>0) {
                //if cursor has value then in user database there is user associated with this given email so return true
                return true;
            }
        }catch (Exception ex){

            Log.e(Error,"Check Dish Exist Error"+ex);

        }
        //if email does not exist return false
        return false;
    }

    //Getting all the record of dish
    public List<Dish> getAllData() {

        ArrayList<Dish> arrayList = new ArrayList<>();

        // select all query
        String select_query= "SELECT * FROM " + TABLE_DISH_DETAIL;

        SQLiteDatabase db = this .getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Dish dish = new Dish();
                dish.setDishId(cursor.getString(0));
                dish.setDishName(cursor.getString(1));
                dish.setDishDescription(cursor.getString(2));
                dish.setDishPrice(cursor.getString(3));
                dish.setChef(cursor.getString(4));
                arrayList.add(dish);
            }while (cursor.moveToNext());
        }
        return arrayList;
    }

    public List<User> getAlluser() {

        ArrayList<User> arrayList = new ArrayList<>();

        // select all query
        String select_query= "SELECT * FROM " + TABLE_USERS;

        SQLiteDatabase db = this .getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getString(0));
                user.setUserName(cursor.getString(1));
                user.setEmail(cursor.getString(2));
                user.setPassword(cursor.getString(3));
                user.setUserType(cursor.getString(4));
                
                arrayList.add(user);
            }while (cursor.moveToNext());
        }
        return arrayList;
    }

    public List<Delivery> getAllOrder() {

        ArrayList<Delivery> arrayList = new ArrayList<>();

        // select all query
        String select_query= "SELECT * FROM " + TABLE_CART;

        SQLiteDatabase db = this .getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Delivery delivery = new Delivery();
                delivery.setId(cursor.getString(0));
                delivery.setUserEmail(cursor.getString(1));
                delivery.setTotalPrice(cursor.getString(2));
                delivery.setAddress(cursor.getString(3));
                delivery.setContactNo(cursor.getString(4));
                delivery.setDeliver(cursor.getString(5));
                arrayList.add(delivery);
            }while (cursor.moveToNext());
        }
        return arrayList;
    }

    public List<Delivery> getAllOrderNotDeliverd(String delivered) {

        ArrayList<Delivery> arrayList = new ArrayList<>();



        SQLiteDatabase db = this .getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_CART + " WHERE " + KEY_CART_DELIVERY + " = ?";
        Log.i(tag, query);
        Cursor cursor = db.rawQuery(query, new String[] {  delivered });
//        Cursor cursor =  db.query(TABLE_CART,// Selecting Table
//                new String[]{KEY_ID, KEY_CART_EMAIL, KEY_CART_TOTALPRICE, KEY_CART_ADDRESS,
//                        KEY_CART_CONTACT,KEY_CART_DELIVERY},//Selecting columns want to query
//                KEY_CART_DELIVERY + "=?",
//                new String[]{delivery.Deliver},//Where clause
//                null, null, null);
        // looping through all rows and adding to list Log.i(tag, query);

        Log.i(tag, delivered);
        if (cursor.moveToFirst()) {
            do {
                Delivery delivery = new Delivery();
                Log.i(tag,  "delivery.Deliver");
                delivery.setId(cursor.getString(0));
                Log.i(tag, "cursor.getString(0) ");
                delivery.setUserEmail(cursor.getString(1));
                Log.i(tag, "cursor.getString(1)" );
                delivery.setTotalPrice(cursor.getString(2));
                Log.i(tag, "cursor.getString(2)" );
                delivery.setAddress(cursor.getString(3));
                Log.i(tag, "cursor.getString(3)" );
                delivery.setContactNo(cursor.getString(4));
                Log.i(tag, "cursor.getString(4)" );
                delivery.setDeliver(cursor.getString(5));
                Log.i(tag, "cursor.getString(5)" );
                arrayList.add(delivery);
            }while (cursor.moveToNext());
        }
        if(arrayList.toString().isEmpty()) {
            Log.i(tag,"Empty");}
        Log.i(tag,  arrayList.toString());
        return arrayList;
    }

    public List<Dish> getDataSearch(String chef) {

        ArrayList<Dish> arrayList = new ArrayList<>();

        Dish dish = new Dish();
        dish.chef = chef;

        SQLiteDatabase db = this .getWritableDatabase();
        // Cursor cursor = db.rawQuery(select_query, null);
        Cursor cursor =  db.query(TABLE_DISH_DETAIL,// Selecting Table
                new String[]{KEY_DISH_ID, KEY_DISH_NAME, KEY_DISH_Description, KEY_Dish_Price,
                        KEY_Dish_chef,KEY_Dish_USEREmail,KEY_Dish_Quantity},//Selecting columns want to query
                KEY_Dish_chef + "=?",
                new String[]{ dish.chef},//Where clause
                null, null, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                dish.setDishId(cursor.getString(0));
                dish.setDishName(cursor.getString(1));
                dish.setDishDescription(cursor.getString(2));
                dish.setDishPrice(cursor.getString(3));
                dish.setChef(cursor.getString(4));
                arrayList.add(dish);
            }while (cursor.moveToNext());
        }
        return arrayList;
    }

    //Getting all the record of AddeddishofChef
    public List<Dish> getAllAddeddishofChef(String userEmail) {

        ArrayList<Dish> arrayList = new ArrayList<>();


        SQLiteDatabase db = this .getWritableDatabase();
        // Cursor cursor = db.rawQuery(select_query, null);
        Cursor cursor =  db.query(TABLE_DISH_DETAIL,// Selecting Table
                new String[]{KEY_DISH_ID, KEY_DISH_NAME, KEY_DISH_Description, KEY_Dish_Price, KEY_Dish_chef,KEY_Dish_USEREmail,KEY_Dish_Quantity},//Selecting columns want to query
                KEY_Dish_USEREmail + "=?",
                new String[]{userEmail},//Where clause
                null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Dish dish = new Dish();
                dish.setDishId(cursor.getString(0));
                dish.setDishName(cursor.getString(1));
                dish.setDishDescription(cursor.getString(2));
                dish.setDishPrice(cursor.getString(3));
                dish.setChef(cursor.getString(4));
                arrayList.add(dish);
            }while (cursor.moveToNext());
        }
        return arrayList;
    }

    //delete the dish
    public void deleteDish(String ID) {
        Dish dish = new Dish();
        dish.dishid =ID;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //deleting row
        sqLiteDatabase.delete(TABLE_DISH_DETAIL, "dishID=" + dish.dishid, null);
        sqLiteDatabase.close();
    }

    public void deleteUser(String ID) {
        User user = new User();
        user.id =ID;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //deleting row
        sqLiteDatabase.delete(TABLE_USERS, "id=" + user.id, null);
        sqLiteDatabase.close();
    }

    public void updatePwd(String Email,String Password ) {
        User user = new User();
        user.email =Email;
        user.password = Password;
        Log.i(tag," user.email after update"+ user.email);
        try {

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();

            //Put email in  @values
            values.put(KEY_EMAIL, user.email);

            //Put password in  @values
            values.put(KEY_PASSWORD, user.password);
//            String updateQuery ="UPDATE users  SET  password = "+ user.password +" WHERE email = "+;
//            Cursor c= dbManager.rawQuery(updateQuery, null);

            db.update(TABLE_USERS, values, "email=" + KEY_EMAIL, null);
            db.close();
            Log.i(tag,"Updating  user Pwd"+values);
        }catch (Exception ex){

            Log.e(Error,"Updating  user Pwd"+ex);

        }
        //updating row

    }

    public void updateOrder(String id,String delivered ) {
        Delivery delivery = new Delivery();
        delivery.id =id;
        delivery.Deliver = delivered;
        Log.i(tag," user.email after update"+ delivery.Deliver);
        try {

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();



            values.put(KEY_CART_DELIVERY, delivery.Deliver);
//

            db.update(TABLE_CART, values, "cartID=" + delivery.id, null);
            db.close();
            Log.i(tag,"Updating  CART delivered"+values);
        }catch (Exception ex){

            Log.e(Error,"Updating  CART delivered"+ex);

        }
        //updating row

    }

    public void updateUserDetail(String id,String UserName,String Email,String Password ) {
        User user = new User();
        user.id = id;
        user.userName =UserName;
        user.email =Email;
        user.password = Password;
        Log.i(tag," user.email after update"+ user.email);
        try {

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(KEY_USER_NAME, user.userName);

            values.put(KEY_EMAIL, user.email);

            //Put password in  @values
            values.put(KEY_PASSWORD, user.password);
//            String updateQuery ="UPDATE users  SET  password = "+ user.password +" WHERE email = "+;
//            Cursor c= dbManager.rawQuery(updateQuery, null);

            db.update(TABLE_USERS, values, "id=" + user.id, null);
            db.close();
            Log.i(tag,"Updating  user details"+values);
        }catch (Exception ex){

            Log.e(Error,"Updating  user details"+ex);

        }
        //updating row

    }

    //update the dish
    public void updateDish(String id,String Name, String Description,String price, String chef ) {
        Dish dish = new Dish();
        dish.dishid =id;
        Log.i(tag,"id after update"+dish.dishid);
        dish.dishName =Name;
        dish.dishDescription =Description;
        dish.dishprice =price;
        dish.chef =chef;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values =  new ContentValues();

        values.put(KEY_DISH_NAME, dish.dishName);

        values.put(KEY_DISH_Description, dish.dishDescription);

        values.put(KEY_Dish_Price, dish.dishprice);
        values.put(KEY_Dish_chef, dish.chef);
        //updating row
        sqLiteDatabase.update(TABLE_DISH_DETAIL, values, "dishID=" + dish.dishid, null);
        sqLiteDatabase.close();
    }
}
