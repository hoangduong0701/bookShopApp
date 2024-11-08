package com.example.bookShop.authencation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookShop.MainUserActivity;
import com.example.bookShop.R;
import com.example.bookShop.admin.MainAdminActivity;
import com.example.bookShop.units.Constants;
import com.example.bookShop.units.DataBase;
import com.example.bookShop.units.PreferenceManager;

public class LoginActivity extends AppCompatActivity {
    EditText inputEmail, inputPassword;
    Button loginBtn;
    TextView CreateNewAccountBtn;
    AppCompatImageView seePassword;
    DataBase dataBase;
    PreferenceManager preferenceManager;
    private int seePass = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setContent();
        loginBtn.setOnClickListener(v -> {
            login();
        });
        seePassword.setOnClickListener(v -> {
            if (seePass == 0) {
                inputPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                seePass = 1;
            } else {
                inputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                seePass = 0;
            }
        });
        CreateNewAccountBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, SignUpActivity.class));
        });
    }

    void login(){
        String userName = inputEmail.getText().toString().trim();
        String passWord = inputPassword.getText().toString().trim();

        if (userName.isEmpty() || passWord.isEmpty()){
            Toast.makeText(this, "Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu", Toast.LENGTH_SHORT).show();
        }else {

            if (userName.equals("admin") && passWord.equals("admin")){
                startActivity(new Intent(this, MainAdminActivity.class));
                preferenceManager.putString(Constants.USERNAME, userName);

            }else {
                boolean isValid = dataBase.check_All(userName, passWord);
                if (isValid) {
                    preferenceManager.putString(Constants.USERNAME, userName);
                    startActivity(new Intent(this, MainUserActivity.class));
                } else {
                    Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
    void setContent(){
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        seePassword = findViewById(R.id.seePassword);
        loginBtn = findViewById(R.id.loginBtn);
        dataBase = new DataBase(this);
        preferenceManager = new PreferenceManager(this);
        CreateNewAccountBtn= findViewById(R.id.CreateNewAccountBtn);
    }
}