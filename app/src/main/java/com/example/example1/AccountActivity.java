package com.example.example1;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;

public class AccountActivity extends AppCompatActivity {

   private Button btnRegister;
   private Button btnCancel;
   private EditText editUserNameAccount;
   private EditText editPasswordAccount;

   @Override
   protected void onCreate(Bundle saveInstanceState) {
      super.onCreate(saveInstanceState);
      setContentView(R.layout.activity_account);
      ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
         Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
         v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
         return insets;
      });

      btnRegister = findViewById(R.id.btnRegister);
      btnCancel = findViewById(R.id.btnCancel);
      editUserNameAccount = findViewById(R.id.editUsernameAccount);
      editPasswordAccount = findViewById(R.id.editTextPasswordAccount);

      btnRegister.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setUsername(editUserNameAccount.getText().toString());
            accountEntity.setPassword(editPasswordAccount.getText().toString());

            Gson gson = new Gson();
            String json = gson.toJson(accountEntity);
            Intent data = new Intent();

            data.putExtra("account",json);
            setResult(RESULT_OK, data);

            finish();
         }
      });

      btnCancel.setOnClickListener(v -> {
         Intent CancelAccount = new Intent(getApplicationContext(), LoginActivity.class);
         startActivity(CancelAccount);
      });
   }
}


