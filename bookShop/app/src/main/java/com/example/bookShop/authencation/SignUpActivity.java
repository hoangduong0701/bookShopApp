package com.example.bookShop.authencation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookShop.R;
import com.example.bookShop.units.DataBase;

public class SignUpActivity extends AppCompatActivity {
    EditText inputName, inputEmail, inputPassword, inputConfirmPassword;
    Button CreateAccountBtn;
    TextView LoginAccountBtn;
    ImageButton back_btn;
    DataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigin);
        setContent();
        setOnclick();
    }
    void setOnclick(){
        CreateAccountBtn.setOnClickListener(v -> {
            if (isValidSignUpDetails()){
                signUp();
            }
        });
        LoginAccountBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
        });
        back_btn.setOnClickListener(v -> {
            onBackPressed();
        });
    }
    void signUp(){
        String name = inputName.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String conFirmPass = inputConfirmPassword.getText().toString();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || conFirmPass.isEmpty()){
            showToast("Điền đầy đủ thông tin");
        }else {
            boolean checkEmail = dataBase.isUsernameExists(email);
            if (!checkEmail){
                boolean insertAccount = dataBase.insertAccount(name, email, password, "", "");
                if (insertAccount){
                    showToast("Đăng ký thành công");
                }else {
                    showToast("Đăng ký thất bại");
                }
            }else {
                showToast("Tài khoản này đã đăng ký rồi");
            }

        }

    }
    private void setContent(){
        inputName = findViewById(R.id.inputName);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputConfirmPassword = findViewById(R.id.inputConfirmPassword);
        CreateAccountBtn = findViewById(R.id.CreateAccountBtn);
        LoginAccountBtn = findViewById(R.id.LoginAccountBtn);
        back_btn = findViewById(R.id.back_btn);
        dataBase = new DataBase(this);
    }
    private Boolean isValidSignUpDetails() {
        if (inputName.getText().toString().trim().isEmpty()) {
            showToast("Nhập tên tài khoản");
            return false;
        } else if (inputEmail.getText().toString().trim().isEmpty()) {
            showToast("Nhập email");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail.getText().toString()).matches()) {
            showToast("Hãy nhập email hợp lệ");
            return false;
        } else if (inputPassword.getText().toString().trim().isEmpty()) {
            showToast("Nhập mật khẩu");
            return false;
        }
        else if (inputPassword.getText().toString().trim().length() <= 6) {
            showToast("Mật khẩu phải có độ dài trên 6 ký tự");
            return false;
        }else if (inputConfirmPassword.getText().toString().trim().isEmpty()) {
            showToast("Nhập lại mật khẩu");
            return false;
        } else if (!inputPassword.getText().toString().equals(inputConfirmPassword.getText().toString())) {
            showToast("Mật khẩu nhập lại không khớp với mật khẩu đã nhập trước đó!");
            return false;
        } else {
            return true;
        }

    }
    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}