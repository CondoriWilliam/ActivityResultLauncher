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
    private static String TAG = "MainActivity";
    private TextView editCreateAccountLogin;
    private Button btnLogueoLogin;
    private EditText editUsernameLogin;
    private EditText editPasswordLogin;
    private String dataCreateAccount;

//    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult activityResult) {
//
//                    String username;
//                    String password;
//                    if (activityResult.getResultCode() == RESULT_OK) {
//                        Intent data = activityResult.getData();
//                        if(data != null){
//                            String account = data.getStringExtra("account");
//                            Gson gson = new Gson();
//                            AccountEntity accountEntity = gson.fromJson(account, AccountEntity.class);
//                            username = accountEntity.getUsername();
//                            password = accountEntity.getPassword();
//                            editUsernameLogin.setText(username);
//                            editPasswordLogin.setText(password);
//                        }
//                    } else {
//                        Log.d(TAG, "Nulo");
//                    }
//                }
//            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        editUsernameLogin = findViewById(R.id.editUsernameLogin);
        editCreateAccountLogin = findViewById(R.id.editCreateAccountLogin);
        editPasswordLogin = findViewById(R.id.editPasswordLogin);

        editCreateAccountLogin.setOnClickListener( v -> {
            Intent createAccount = new Intent(getApplicationContext(), AccountActivity.class);
            activityResultLauncher.launch(createAccount);
        });

        btnLogueoLogin = findViewById(R.id.btnLogueoLogin);
        btnLogueoLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Gson gson = new Gson();
                AccountEntity accountEntity = gson.fromJson(dataCreateAccount, AccountEntity.class);

                String username = accountEntity.getUsername();
                String password = accountEntity.getPassword();

                if (editUsernameLogin.getText().toString().equals(username) && editPasswordLogin.getText(). toString().equals(password)) {
                    Toast.makeText(getApplicationContext(),"Bienvenido a mi App", Toast.LENGTH_LONG).show();
                    Log.d(TAG,"Bienvenido a mi App");

                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.putExtra("ACCOUNT", dataCreateAccount);
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_LONG).show();
                    Log.d(TAG,"Error");
                }
            }
        });
    }

    ActivityResultLauncher activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult activityResult) {
                    Integer resultCode = activityResult.getResultCode();
                    if(resultCode.equals(AccountActivity.ACCOUNT_ACCEPT)){
                        Intent data = activityResult.getData();
                        dataCreateAccount = data.getStringExtra(AccountActivity.ACCOUNT_RECORD);

                        Gson gson = new Gson();
                        AccountEntity accountEntity = gson.fromJson(dataCreateAccount, AccountEntity.class);

                        String firstName = accountEntity.getUsername();
                        String password = accountEntity.getPassword();

                        editUsernameLogin.setText(firstName);
                        editPasswordLogin.setText(password);

                        Toast.makeText(getApplicationContext(), "NAME " + firstName, Toast.LENGTH_SHORT).show();
                        Log.d("LoginActivity", "NAME " + firstName);

                    } else if (resultCode.equals(AccountActivity.ACCOUNT_CANCEL)){
                        Toast.makeText(getApplicationContext(), "CANCEL", Toast.LENGTH_SHORT).show();
                        Log.d("LoginActivity", "Cancel");
                    }
                }
            }
    );
}