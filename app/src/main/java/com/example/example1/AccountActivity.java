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
   public final static String ACCOUNT_RECORD = "ACCOUNT_RECORD";
   public final static Integer ACCOUNT_ACCEPT = 100;
   public final static Integer ACCOUNT_CANCEL = 200;
   private Button btnCreateAccount;
   private Button btnCancelAccount;
   private EditText editFirstNameAccount;
   private EditText editLastNameAccount;
   private EditText editEmailAddressAccount;
   private EditText editPhoneAccount;
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

      editFirstNameAccount = findViewById(R.id.editFirstNameAccount);
      editLastNameAccount = findViewById(R.id.editLastNameAccount);
      editEmailAddressAccount = findViewById(R.id.editEmailAddressAccount);
      editPhoneAccount = findViewById(R.id.editPhoneAccount);
      editPasswordAccount = findViewById(R.id.editPasswordAccount);
      editUserNameAccount = findViewById(R.id.editUsernameAccount);

      btnCreateAccount = findViewById(R.id.btnCreateAccount);
      btnCancelAccount = findViewById(R.id.btnCancelAccount);

      btnCreateAccount.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

            AccountEntity accountEntity = new AccountEntity();

            accountEntity.setFirstname(editFirstNameAccount.getText().toString());
            accountEntity.setLastname(editLastNameAccount.getText().toString());
            accountEntity.setEmail(editEmailAddressAccount.getText().toString());
            accountEntity.setPhone(editPhoneAccount.getText().toString());
            accountEntity.setUsername(editUserNameAccount.getText().toString());
            accountEntity.setPassword(editPasswordAccount.getText().toString());

            Intent data = new Intent();
            Gson gson = new Gson();
            String accountJson = gson.toJson(accountEntity);
            data.putExtra(ACCOUNT_RECORD,accountJson);

            setResult(ACCOUNT_ACCEPT, data);
            finish();
         }
      });

      btnCancelAccount.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            setResult(ACCOUNT_CANCEL);
            finish();
         }
      });
   }
}


