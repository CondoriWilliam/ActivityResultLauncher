package com.example.example1;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;

public class HomeActivity extends AppCompatActivity {
    private TextView editUsernameHome;
    private TextView editFirstNameHome;
    private TextView editLastNameHome;
    private TextView editEmailHome;
    private TextView editPhoneHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editUsernameHome = findViewById(R.id.editUsernameHome);
        editFirstNameHome = findViewById(R.id.editFirstNameHome);
        editLastNameHome = findViewById(R.id.editLastNameHome);
        editEmailHome = findViewById(R.id.editEmailHome);
        editPhoneHome = findViewById(R.id.editPhoneHome);
        printUser();
    }
    private void printUser(){
        Intent intent = getIntent();
        String jsonString = intent.getStringExtra("ACCOUNT");
        Gson gson = new Gson();
        AccountEntity user = gson.fromJson(jsonString, AccountEntity.class);

        String username = user.getUsername();
        String firstname = "Name: " + user.getFirstname();
        String lastname = "Last name: " + user.getLastname();
        String email = "Email: " + user.getEmail();
        String phone = "Phone: " + user.getPhone();

        editUsernameHome.setText(username);
        editFirstNameHome.setText(firstname);
        editLastNameHome.setText(lastname);
        editEmailHome.setText(email);
        editPhoneHome.setText(phone);
    }
}