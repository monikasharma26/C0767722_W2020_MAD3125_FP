package com.example.c0767722_w2020_mad3125_fp.Actvities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.c0767722_w2020_mad3125_fp.R;
import com.example.c0767722_w2020_mad3125_fp.Shared.DataBaseManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SignUpActivity extends AppCompatActivity {

    @InjectView(R.id.email)
    EditText email;
    @InjectView(R.id.passwrd)
    EditText passwrd;
    @InjectView(R.id.confPasword)
    EditText confPasword;
    @InjectView(R.id.btnSignUp)
    AppCompatTextView btnSignUp;
    private final static String TABLE_NAME = "users";
    //sql string to create the table
    private static final String table =
            "CREATE TABLE " + TABLE_NAME + " (id text primary key, loginTime text);";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DataBaseManager dataBaseManager;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.inject(this);
        ActionBar bar = getSupportActionBar();
        bar.hide();
        mAuth = FirebaseAuth.getInstance();
        try {
            dataBaseManager = new DataBaseManager(this);
            //create the table
            dataBaseManager.dbInitialize(TABLE_NAME, table);
        } catch (Exception exception) {
            Toast.makeText(SignUpActivity.this,
                    exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ", exception.getMessage());
        }
        loginCheck();
    }

    private void loginCheck() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation()) {
                    final String userUsername = email.getText().toString().trim();
                    String userPassword = passwrd.getText().toString().trim();
                    String confirmPassword = confPasword.getText().toString().trim();
                     mAuth.createUserWithEmailAndPassword(userUsername,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    showAlert("Successfully Registered");
                                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    Toast.makeText(SignUpActivity.this, "Registration Failed", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                }
            }
        });
    }
    private void showAlert(String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Alert!");
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setIcon(R.drawable.ic_action_alerts);
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialogBuilder.setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
}
    private boolean validation() {
        if (email.getText().toString().trim().length() == 0) {
            email.setError("Enter Email ID");
            return false;
        } else if (passwrd.getText().toString().trim().length() == 0) {
            passwrd.setError("Enter Password");
            return false;
        } else if(confPasword.getText().toString().trim().length()==0) {
            confPasword.setError("Enter Confirm Password");
            return false;
        } else if(!confPasword.getText().toString().equals(passwrd.getText().toString())) {
            confPasword.setError("Confirm Password does not matched with password");
            return false;
        }
        else if (!isValidEmail(email.getText().toString())) {
            email.setError("Enter Valid Email");
            return false;
        } else {
            return true;
        }
    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

}
