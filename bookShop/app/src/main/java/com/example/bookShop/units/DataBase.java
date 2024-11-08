package com.example.bookShop.units;




import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.bookShop.model.BookModel;
import com.example.bookShop.model.FavoriteModel;
import com.example.bookShop.model.NotificationModel;
import com.example.bookShop.model.OrderCartModel;
import com.example.bookShop.model.OrderModel;
import com.example.bookShop.model.ViewBookModel;

import java.util.ArrayList;
import java.util.List;


public class DataBase extends SQLiteOpenHelper {


    private static final int VERSION = 7;
    public DataBase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public DataBase(@Nullable Context context) {
        super(context, Constants.DB_NAME, null , VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String tableAccount = "CREATE TABLE IF NOT EXISTS " + Constants.TABLE_ACCOUNT + " ("
                + Constants.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Constants.PERSONAL_NAME + " TEXT, "
                + Constants.USERNAME + " TEXT, "
                + Constants.PASSWORD + " TEXT, "
                + Constants.LOCATION + " TEXT, "
                + Constants.PHONE_NUMBER + " TEXT)";

        sqLiteDatabase.execSQL(tableAccount);

        String createBook = "CREATE TABLE IF NOT EXISTS " + Constants.TABLE_BOOK + " ("
                + Constants.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Constants.IMAGE_BOOK + " BLOB, "
                + Constants.NAME_BOOK + " TEXT, "
                + Constants.AUTHOR_BOOK + " TEXT, "
                + Constants.NUMBER_PAGE + " TEXT, "
                + Constants.DATE_BOOK + " TEXT, "
                + Constants.KIND_BOOK + " TEXT, "
                + Constants.OBJECT_BOOK + " TEXT, "
                + Constants.NOTE_BOOK + " TEXT, "
                + Constants.PRICE_BOOK + " REAL,"
                + Constants.RANK_BOOK + " TEXT)";

        sqLiteDatabase.execSQL(createBook);

        String createKindBook = "CREATE TABLE IF NOT EXISTS " + Constants.TABLE_KIND_BOOK + " ("
                + Constants.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Constants.NAME_KIND_BOOK + " TEXT)";
        sqLiteDatabase.execSQL(createKindBook);

        String createFavorite= "CREATE TABLE IF NOT EXISTS " + Constants.TABLE_FAVORITE + " ("
                + Constants.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Constants.ID_USER + " TEXT, "
                + Constants.IMAGE_BOOK + " BLOB,"
                + Constants.NAME_BOOK + " TEXT, "
                + Constants.AUTHOR_BOOK + " TEXT, "
                + Constants.PRICE_BOOK + " REAL)";
        sqLiteDatabase.execSQL(createFavorite);

        String createCart= "CREATE TABLE IF NOT EXISTS " + Constants.TABLE_CART + " ("
                + Constants.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Constants.ID_USER + " TEXT, "
                + Constants.IMAGE_BOOK + " BLOB,"
                + Constants.NAME_BOOK + " TEXT,"
                + Constants.AUTHOR_BOOK + " TEXT,"
                + Constants.PRICE_BOOK + " REAL)";
        sqLiteDatabase.execSQL(createCart);

        String createAccount = "CREATE TABLE IF NOT EXISTS "+Constants.TABLE_ACCOUNT+" ("
                +Constants.ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +Constants.PERSONAL_NAME + " TEXT, "
                +Constants.USERNAME+" TEXT, "
                +Constants.PASSWORD+" TEXT, "
                +Constants.PHONE_NUMBER+" TEXT, "
                +Constants.LOCATION+" TEXT)";
        sqLiteDatabase.execSQL(createAccount);

        String createTABLE_NOTIFICATION  = "CREATE TABLE IF NOT EXISTS  " + Constants.TABLE_NOTIFICATION + " ("
                + Constants.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Constants.TYPE_NOTIFICATION + " TEXT,"
                + Constants.CONTENT_NOTIFICATION + " TEXT,"
                + Constants.TIMER_ORDER + " TEXT)";
        sqLiteDatabase.execSQL(createTABLE_NOTIFICATION );

        String createCartOrder = "CREATE TABLE IF NOT EXISTS " + Constants.TABLE_ORDER + " ("
                + Constants.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Constants.ID_USER + " TEXT, "
                + Constants.IMAGE_BOOK + " BLOB,"
                + Constants.NAME_BOOK + " TEXT, "
                + Constants.AUTHOR_BOOK + " TEXT, "
                + Constants.TIMER_ORDER + " TEXT, "
                + Constants.QUANTITY_ORDER + " INTEGER, "
                + Constants.PRICE_BOOK + " REAL)";
        sqLiteDatabase.execSQL(createCartOrder);

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_FAVORITE);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_CART);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NOTIFICATION);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_ORDER);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("ALTER TABLE " + Constants.TABLE_NOTIFICATION + " ADD COLUMN " + Constants.ID_USER + " TEXT");

        sqLiteDatabase.execSQL("ALTER TABLE " + Constants.TABLE_ORDER + " ADD COLUMN " + Constants.LOCATION + " TEXT");
        sqLiteDatabase.execSQL("ALTER TABLE " + Constants.TABLE_ORDER + " ADD COLUMN " + Constants.PHONE_NUMBER + " TEXT");
        sqLiteDatabase.execSQL("ALTER TABLE " + Constants.TABLE_ORDER + " ADD COLUMN " + Constants.CARD_ORDER + " TEXT");
        sqLiteDatabase.execSQL("ALTER TABLE " + Constants.TABLE_ORDER + " ADD COLUMN " + Constants.TOTAL_AMOUNT_ORDER + " TEXT");
        sqLiteDatabase.execSQL("ALTER TABLE " + Constants.TABLE_ORDER + " ADD COLUMN " + Constants.NOTE_ORDER + " TEXT");

    }

    public  boolean insertNotification(String type, String content, String time, String user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.TYPE_NOTIFICATION, type);
        values.put(Constants.CONTENT_NOTIFICATION, content);
        values.put(Constants.TIMER_ORDER, time);
        values.put(Constants.ID_USER, user);
        long result =  db.insert( Constants.TABLE_NOTIFICATION, null, values);
        db.close();
        return result != -1;

    }
    @SuppressLint("Range")
    public List<NotificationModel> getNotification(String idUser) {
        List<NotificationModel> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = Constants.ID_USER + " = ?";
        String[] selectionArgs = {idUser};
        Cursor cursor = db.query(Constants.TABLE_NOTIFICATION, null, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(Constants.ID));
                String type = cursor.getString(cursor.getColumnIndex(Constants.TYPE_NOTIFICATION));
                String content = cursor.getString(cursor.getColumnIndex(Constants.CONTENT_NOTIFICATION));
                String time = cursor.getString(cursor.getColumnIndex(Constants.TIMER_ORDER));

                dataList.add(new NotificationModel(id,  type, content, time));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return dataList;
    }
//, String total, String card, String location, String phone, String note
    public  boolean insertOrder(String userAccount, byte[] image, String nameBook, String author, String price, String time, int sl , String total, String card, String location, String phone, String note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.ID_USER, userAccount);
        values.put(Constants.IMAGE_BOOK, image);
        values.put(Constants.NAME_BOOK, nameBook);
        values.put(Constants.AUTHOR_BOOK, author);
        values.put(Constants.PRICE_BOOK, price);
        values.put(Constants.TIMER_ORDER, time);
        values.put(Constants.QUANTITY_ORDER, sl);

        values.put(Constants.TOTAL_AMOUNT_ORDER, total);
        values.put(Constants.CARD_ORDER, card);
        values.put(Constants.LOCATION, location);
        values.put(Constants.PHONE_NUMBER, phone);
        values.put(Constants.NOTE_ORDER, note);
        long result =  db.insert( Constants.TABLE_ORDER, null, values);
        db.close();
        return result != -1;

    }
    @SuppressLint("Range")
    public List<OrderCartModel> getCartOrder(String idUser) {
        List<OrderCartModel> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = Constants.ID_USER + " = ?";
        String[] selectionArgs = {idUser};
        Cursor cursor = db.query(Constants.TABLE_ORDER, null, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int bookId = cursor.getInt(cursor.getColumnIndex(Constants.ID));
                byte[] image = cursor.getBlob(cursor.getColumnIndex(Constants.IMAGE_BOOK));
                String name = cursor.getString(cursor.getColumnIndex(Constants.NAME_BOOK));
                String author = cursor.getString(cursor.getColumnIndex(Constants.AUTHOR_BOOK));
                String price = cursor.getString(cursor.getColumnIndex(Constants.PRICE_BOOK));
                int soLuong = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.QUANTITY_ORDER)));
                String time = cursor.getString(cursor.getColumnIndex(Constants.TIMER_ORDER));
                String amont = cursor.getString(cursor.getColumnIndex(Constants.TOTAL_AMOUNT_ORDER));
                String card  = cursor.getString(cursor.getColumnIndex(Constants.CARD_ORDER));
                String location  = cursor.getString(cursor.getColumnIndex(Constants.LOCATION));
                String phone  = cursor.getString(cursor.getColumnIndex(Constants.PHONE_NUMBER));
                String note  = cursor.getString(cursor.getColumnIndex(Constants.NOTE_ORDER));
                dataList.add(new OrderCartModel(bookId, soLuong, image, name, author, price, amont, card, time, location, phone, idUser, note ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return dataList;
    }

    public Cursor getAccountData(String accountId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + Constants.LOCATION + ", " + Constants.PHONE_NUMBER +
                " FROM " + Constants.TABLE_ACCOUNT +
                " WHERE " + Constants.USERNAME + " = ?";
        return db.rawQuery(query, new String[]{accountId});
    }
    public List<String> getLocation() {
        List<String> names = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + Constants.LOCATION + " FROM " + Constants.TABLE_ACCOUNT;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                names.add(cursor.getString(cursor.getColumnIndexOrThrow(Constants.LOCATION)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return names;
    }
    public boolean updateLocationAndPhone(String accountId, String newLocation, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.LOCATION, newLocation);
        contentValues.put(Constants.PHONE_NUMBER, phone);

        // Xác định điều kiện where
        String whereClause = Constants.USERNAME + " = ?";
        String[] whereArgs = { String.valueOf(accountId) };

        // Cập nhật bảng
        int rowsAffected = db.update(Constants.TABLE_ACCOUNT, contentValues, whereClause, whereArgs);
        db.close();

        // Trả về true nếu ít nhất một hàng bị ảnh hưởng, ngược lại trả về false
        return rowsAffected > 0;
    }
    public  boolean insertBook(byte[] image, String nameBook, String author,String numberPage, String kind,String date, String object, String note,String rank, String price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.IMAGE_BOOK, image);
        values.put(Constants.NAME_BOOK, nameBook);
        values.put(Constants.AUTHOR_BOOK, author);
        values.put(Constants.NUMBER_PAGE, numberPage);
        values.put(Constants.DATE_BOOK, date);
        values.put(Constants.KIND_BOOK, kind);
        values.put(Constants.OBJECT_BOOK, object);
        values.put(Constants.NOTE_BOOK, note);
        values.put(Constants.PRICE_BOOK, price);
        values.put(Constants.RANK_BOOK, rank);

        long result =  db.insert( Constants.TABLE_BOOK, null, values);
        db.close();
        return result != -1;

    }
    @SuppressLint("Range")
    public List<BookModel> getAllData() {
        List<BookModel> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Constants.TABLE_BOOK, null);
        if (cursor.moveToFirst()) {
            do {
                 int id = cursor.getInt(cursor.getColumnIndex(Constants.ID));
                byte[] image = cursor.getBlob(cursor.getColumnIndex(Constants.IMAGE_BOOK));
                String name = cursor.getString(cursor.getColumnIndex(Constants.NAME_BOOK));
                String number = cursor.getString(cursor.getColumnIndex(Constants.NUMBER_PAGE));
                String author = cursor.getString(cursor.getColumnIndex(Constants.AUTHOR_BOOK));
                String date = cursor.getString(cursor.getColumnIndex(Constants.DATE_BOOK));
                String kind = cursor.getString(cursor.getColumnIndex(Constants.KIND_BOOK));
                String obj = cursor.getString(cursor.getColumnIndex(Constants.OBJECT_BOOK));
                String note = cursor.getString(cursor.getColumnIndex(Constants.NOTE_BOOK));
                String price = cursor.getString(cursor.getColumnIndex(Constants.PRICE_BOOK));
                String rank = cursor.getString(cursor.getColumnIndex(Constants.RANK_BOOK));

                dataList.add(new BookModel(id, image, name, number, author, date, kind, obj, note, price, rank));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return dataList;
    }


    @SuppressLint("Range")
    public List<ViewBookModel> getBookHot(String BooKHot) {
        List<ViewBookModel> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = Constants.RANK_BOOK + " = ?";
        String[] selectionArgs = {BooKHot};
        Cursor cursor = db.query(Constants.TABLE_BOOK, null, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(Constants.ID));
                byte[] image = cursor.getBlob(cursor.getColumnIndex(Constants.IMAGE_BOOK));
                String name = cursor.getString(cursor.getColumnIndex(Constants.NAME_BOOK));
                String author = cursor.getString(cursor.getColumnIndex(Constants.AUTHOR_BOOK));
                String price = cursor.getString(cursor.getColumnIndex(Constants.PRICE_BOOK));


                dataList.add(new ViewBookModel(id,  name, author, price ,image));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return dataList;
    }
    @SuppressLint("Range")
    public List<ViewBookModel> getAllBook() {
        List<ViewBookModel> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        String[] columns = {
                Constants.ID,
                Constants.IMAGE_BOOK,
                Constants.NAME_BOOK,
                Constants.AUTHOR_BOOK,
                Constants.PRICE_BOOK
        };
        Cursor cursor = db.query(Constants.TABLE_BOOK, columns, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(Constants.ID));
                byte[] image = cursor.getBlob(cursor.getColumnIndex(Constants.IMAGE_BOOK));
                String name = cursor.getString(cursor.getColumnIndex(Constants.NAME_BOOK));
                String author = cursor.getString(cursor.getColumnIndex(Constants.AUTHOR_BOOK));
                String price = cursor.getString(cursor.getColumnIndex(Constants.PRICE_BOOK));


                dataList.add(new ViewBookModel(id,  name, author, price ,image));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return dataList;
    }


    public  boolean insertKindBook(String NAME_KIND_BOOK){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.NAME_KIND_BOOK, NAME_KIND_BOOK);
        long result =  db.insert( Constants.TABLE_KIND_BOOK, null, values);
        db.close();
        return result != -1;

    }
    public void query_Kind(ArrayList<String> myList, ArrayAdapter<String> arrayAdapter) {
        myList.clear();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query(Constants.TABLE_KIND_BOOK, null, null, null, null, null, null);

        // Kiểm tra nếu con trỏ không trống và di chuyển đến dòng đầu tiên
        if (c != null && c.moveToFirst()) {
            String data;
            do {
                data = c.getString(0)+ "-" +c.getString(1);
                myList.add(data);
            } while (c.moveToNext());

            c.close();
        }
        db.close();
        arrayAdapter.notifyDataSetChanged();
    }
    public void deleteKind(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TABLE_KIND_BOOK, "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public List<String> getAllKindBook() {
        List<String> names = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + Constants.NAME_KIND_BOOK + " FROM " + Constants.TABLE_KIND_BOOK, null);
        if (cursor.moveToFirst()) {
            do {
                names.add(cursor.getString(cursor.getColumnIndexOrThrow(Constants.NAME_KIND_BOOK)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return names;
    }


    //*************************************// thong tin san pham
    @SuppressLint("Range")
    public List<OrderModel> getBooksById(int id) {
        List<OrderModel> bookList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Constants.TABLE_BOOK + " WHERE " + Constants.ID + "=?", new String[]{String.valueOf(id)});

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {

                     int bookId = cursor.getInt(cursor.getColumnIndex(Constants.ID));
                    byte[] image = cursor.getBlob(cursor.getColumnIndex(Constants.IMAGE_BOOK));
                    String name = cursor.getString(cursor.getColumnIndex(Constants.NAME_BOOK));
                    String number = cursor.getString(cursor.getColumnIndex(Constants.NUMBER_PAGE));
                    String author = cursor.getString(cursor.getColumnIndex(Constants.AUTHOR_BOOK));
                    String date = cursor.getString(cursor.getColumnIndex(Constants.DATE_BOOK));
                    String kind = cursor.getString(cursor.getColumnIndex(Constants.KIND_BOOK));
                    String obj = cursor.getString(cursor.getColumnIndex(Constants.OBJECT_BOOK));
                    String note = cursor.getString(cursor.getColumnIndex(Constants.NOTE_BOOK));
                    String price = cursor.getString(cursor.getColumnIndex(Constants.PRICE_BOOK));
                    String rank = cursor.getString(cursor.getColumnIndex(Constants.RANK_BOOK));
                    int soLuong = 1;

                    OrderModel book = new OrderModel(bookId, name, number, author, date, kind, obj, note, price, rank, soLuong, image);

                    bookList.add(book);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return bookList;
    }
    public Cursor getDataById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + Constants.TABLE_BOOK + " WHERE " + Constants.ID + "=?", new String[]{String.valueOf(id)});
    }
    @SuppressLint("Range")
    public void displayDataById(int id, ImageView imageView, TextView nameBook , TextView numberBook, TextView authorBook,
                                TextView dateBook, TextView kindBook, TextView objBook, TextView noteBook, TextView priceBook,
                                TextView rankBook, Context context) {
        Cursor cursor = this.getDataById(id);
        if (cursor != null && cursor.moveToFirst()) {
            byte[] image = cursor.getBlob(cursor.getColumnIndex(Constants.IMAGE_BOOK));
            String name = cursor.getString(cursor.getColumnIndex(Constants.NAME_BOOK));
            String number = cursor.getString(cursor.getColumnIndex(Constants.NUMBER_PAGE));
            String author = cursor.getString(cursor.getColumnIndex(Constants.AUTHOR_BOOK));
            String date = cursor.getString(cursor.getColumnIndex(Constants.DATE_BOOK));
            String kind = cursor.getString(cursor.getColumnIndex(Constants.KIND_BOOK));
            String obj = cursor.getString(cursor.getColumnIndex(Constants.OBJECT_BOOK));
            String note = cursor.getString(cursor.getColumnIndex(Constants.NOTE_BOOK));
            String price = cursor.getString(cursor.getColumnIndex(Constants.PRICE_BOOK));
            String rank = cursor.getString(cursor.getColumnIndex(Constants.RANK_BOOK));


            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            imageView.setImageBitmap(bitmap);
            nameBook.setText(name);
            numberBook.setText(number);
            authorBook.setText(author);
            dateBook.setText(date);
            kindBook.setText(kind);
            objBook.setText(obj);
            noteBook.setText(note);
            priceBook.setText(price);
            rankBook.setText(rank);


        } else {
            Toast.makeText(context, "Không có thông tin", Toast.LENGTH_SHORT).show();
        }
        if (cursor != null) {
            cursor.close();
        }
    }
    //*************************************************************// yeu thich

    public  boolean insertFavorite(String userAccount, byte[] image, String nameBook, String author, String price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.ID_USER, userAccount);
        values.put(Constants.IMAGE_BOOK, image);
        values.put(Constants.NAME_BOOK, nameBook);
        values.put(Constants.AUTHOR_BOOK, author);
        values.put(Constants.PRICE_BOOK, price);
        long result =  db.insert( Constants.TABLE_FAVORITE, null, values);
        db.close();
        return result != -1;

    }
    @SuppressLint("Range")
    public List<FavoriteModel> getFavorite(String idUser) {
        List<FavoriteModel> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = Constants.ID_USER + " = ?";
        String[] selectionArgs = {idUser};
        Cursor cursor = db.query(Constants.TABLE_FAVORITE, null, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(Constants.ID));
                byte[] image = cursor.getBlob(cursor.getColumnIndex(Constants.IMAGE_BOOK));
                String name = cursor.getString(cursor.getColumnIndex(Constants.NAME_BOOK));
                String author = cursor.getString(cursor.getColumnIndex(Constants.AUTHOR_BOOK));
                String price = cursor.getString(cursor.getColumnIndex(Constants.PRICE_BOOK));


                dataList.add(new FavoriteModel(id,  name, author, price ,image));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return dataList;
    }
    public boolean isNameBookExists(String nameBook) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(Constants.TABLE_FAVORITE,
                    new String[]{Constants.NAME_BOOK},
                    Constants.NAME_BOOK + "=?",
                    new String[]{nameBook},
                    null, null, null);
            return cursor != null && cursor.moveToFirst();
        } catch (SQLException e) {
            Log.e("error", "Lỗi truy vấn");
            return false;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }

    public void deleteFavorite(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TABLE_FAVORITE, "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }
    //*******************************************************************************//


    public  boolean insertCart(String userAccount, byte[] image, String nameBook, String author, String price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.ID_USER, userAccount);
        values.put(Constants.IMAGE_BOOK, image);
        values.put(Constants.NAME_BOOK, nameBook);
        values.put(Constants.AUTHOR_BOOK, author);
        values.put(Constants.PRICE_BOOK, price);
        long result =  db.insert( Constants.TABLE_CART, null, values);
        db.close();
        return result != -1;

    }
    @SuppressLint("Range")
    public List<FavoriteModel> getCart(String idUser) {
        List<FavoriteModel> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = Constants.ID_USER + " = ?";
        String[] selectionArgs = {idUser};
        Cursor cursor = db.query(Constants.TABLE_CART, null, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(Constants.ID));
                byte[] image = cursor.getBlob(cursor.getColumnIndex(Constants.IMAGE_BOOK));
                String name = cursor.getString(cursor.getColumnIndex(Constants.NAME_BOOK));
                String author = cursor.getString(cursor.getColumnIndex(Constants.AUTHOR_BOOK));
                String price = cursor.getString(cursor.getColumnIndex(Constants.PRICE_BOOK));


                dataList.add(new FavoriteModel(id,  name, author, price ,image));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return dataList;
    }
    public boolean isCartExists(String nameBook) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(Constants.TABLE_CART,
                    new String[]{Constants.NAME_BOOK},
                    Constants.NAME_BOOK + "=?",
                    new String[]{nameBook},
                    null, null, null);
            return cursor != null && cursor.moveToFirst();
        } catch (SQLException e) {
            Log.e("error", "Lỗi truy vấn");
            return false;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }

    public void deleteCart(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TABLE_CART, "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }


//****************************************************************************************//






















    public boolean check_All(String userName, String passWord)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+Constants.TABLE_ACCOUNT+" WHERE "+Constants.USERNAME+" = ? AND "+Constants.PASSWORD+" = ?", new String[]{userName, passWord});

        if(cursor.getCount() >0)
        {
            return true;
        }
        else {
            return false;
        }
    }
    public  boolean insertAccount(String PERSONAL_NAME, String USERNAME, String PASSWORD, String PHONE_NUMBER, String LOCATION){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.PERSONAL_NAME, PERSONAL_NAME);
        values.put(Constants.USERNAME, USERNAME);
        values.put(Constants.PASSWORD, PASSWORD);
        values.put(Constants.PHONE_NUMBER, PHONE_NUMBER);
        values.put(Constants.LOCATION, LOCATION);
        long result = db.insert( Constants.TABLE_ACCOUNT, null, values);
        db.close();
        return result != -1;

    }
    public boolean isUsernameExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(Constants.TABLE_ACCOUNT,
                    new String[]{Constants.USERNAME},
                    Constants.USERNAME + "=?",
                    new String[]{username},
                    null, null, null);
            return cursor != null && cursor.moveToFirst();
        } catch (SQLException e) {
            Log.e("error", "Lỗi truy vấn");
            return false;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }

}
