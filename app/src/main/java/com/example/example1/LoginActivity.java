package com.example.example1;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.example1.databinding.ActivityLoginBinding;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {
    private TextView editRegister;
    private Button btnLogin;
    private EditText editUsername;
    private EditText editPassword;
    private EditText editUsernameHome;

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult activityResult) {

                    String username;
                    String password;
                    if (activityResult.getResultCode() == RESULT_OK) {
                        Intent data = activityResult.getData();
                        if(data != null){
                            String account = data.getStringExtra("account");
                            Gson gson = new Gson();
                            AccountEntity accountEntity = gson.fromJson(account, AccountEntity.class);
                            username = accountEntity.getUsername();
                            password = accountEntity.getPassword();
                            editUsername.setText(username);
                            editPassword.setText(password);
                        }
                    } else {
                        Log.d(TAG, "Nulo");
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        editUsername = findViewById(R.id.editUsername);
        editRegister = findViewById(R.id.editRegistro);
        editPassword = findViewById(R.id.editPassword);
        //editUsernameHome = findViewById(R.id.editUsername);

        editRegister.setOnClickListener( v -> {
            Intent register = new Intent(getApplicationContext(), AccountActivity.class);
            activityResultLauncher.launch(register);
        });

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String username = editUsername.getText().toString();
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }
}