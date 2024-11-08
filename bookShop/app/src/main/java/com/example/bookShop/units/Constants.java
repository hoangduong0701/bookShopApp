package com.example.bookShop.units;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Constants {
    public static final String DB_NAME = "bookshop";
    public static final String ID = "id";

    public static final String ID_USER = "id_user";

    //service

    public static final String TABLE_ACCOUNT = "account";
    public static final String TABLE_BOOK = "book";
    public static final String TABLE_KIND_BOOK = "loaisach";
    public static final String TABLE_FAVORITE = "yeuthich";
    public static final String TABLE_CART = "giohang";

    public static final String TABLE_ORDER = "donhang";
    //*****************************************************//
 //sach
    public  static  final String IMAGE_BOOK = "imagebook";
    public  static  final String NAME_BOOK = "tensach";
    public  static  final String NUMBER_PAGE = "sotrang";
    public  static  final String AUTHOR_BOOK = "tacgia";
    public  static  final String DATE_BOOK = "phathanh";
    public  static  final String KIND_BOOK = "loaisach";
    public  static  final String OBJECT_BOOK = "doituong";
    public  static  final String NOTE_BOOK = "ghichu";
    public  static  final String PRICE_BOOK = "gia";
    public static  final String PHONE_CALL = "0914574187";
    public  static  final String RANK_BOOK = "hangmuc";
    //*****************************************************//
    //loai sach
    public  static  final String NAME_KIND_BOOK = "tenloaisach";
    //*****************************************************//
    //dang nhap
    public  static  final String USERNAME = "taikhoan";
    public  static  final String PASSWORD = "matkau";
    public  static  final String PERSONAL_NAME = "ten";
    public  static  final String PHONE_NUMBER = "sdt";
    public  static  final String LOCATION = "diachi";



    //*****************************************************//
    public  static  final String TABLE_NOTIFICATION = "thongbao";
    public  static  final String TYPE_NOTIFICATION = "loaithongbao";
    public  static  final String CONTENT_NOTIFICATION = "noidungthongbao";


    public  static  final String TIMER_ORDER = "thoigian";
    public  static  final String QUANTITY_ORDER = "soluong";
    public  static  final String TOTAL_AMOUNT_ORDER = "tongtien";
    public  static  final String CARD_ORDER = "card";
    public  static  final String NOTE_ORDER = "ghichu_order";
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss yyyy/MM/dd", Locale.getDefault());
        return sdf.format(Calendar.getInstance().getTime());
    }

    public static void check(Context context, Activity activity){
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }
    public static boolean  validateFields(TextInputEditText nameBookEdt, TextInputEditText authorEdt, TextInputEditText numberPageEdt, TextInputEditText priceEdt, TextInputEditText dateEdt, TextInputEditText noteEdt, AutoCompleteTextView kindBook, AutoCompleteTextView doiTuongEdt, String nameBook, String author,  String numberPage , String kind , String date , String obj, String price, String note) {

        boolean isValid = true;

        if (nameBook.isEmpty()) {
            nameBookEdt.setError("Book name is required");
            isValid = false;
        }
        if (author.isEmpty()) {
            authorEdt.setError("Author is required");
            isValid = false;
        }
        if (numberPage.isEmpty()) {
            numberPageEdt.setError("Number of pages is required");
            isValid = false;
        }
        if (kind.isEmpty()) {
            kindBook.setError("Kind is required");
            isValid = false;
        }
        if (date.isEmpty()) {
            dateEdt.setError("Date is required");
            isValid = false;
        }
        if (obj.isEmpty()) {
            doiTuongEdt.setError("Object is required");
            isValid = false;
        }
        if (price.isEmpty()) {
            priceEdt.setError("Price is required");
            isValid = false;
        }
        if (note.isEmpty()) {
            noteEdt.setError("Note is required");
            isValid = false;
        }

        return isValid;
    }
    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}
