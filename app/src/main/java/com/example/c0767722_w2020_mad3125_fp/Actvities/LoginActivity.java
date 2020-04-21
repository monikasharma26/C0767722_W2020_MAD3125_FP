package com.example.c0767722_w2020_mad3125_fp.Actvities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.c0767722_w2020_mad3125_fp.Models.FingerPrintHandler;
import com.example.c0767722_w2020_mad3125_fp.R;
import com.example.c0767722_w2020_mad3125_fp.Shared.DataBaseManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginActivity extends AppCompatActivity {

    private final static String TABLE_NAME = "users";
    //sql string to create the table
    private static final String table =
            "CREATE TABLE " + TABLE_NAME + " (id text primary key, loginTime text);";

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth mAuth;
    EditText username, passwrd;
    TextView btnLogin;
    TextView fingerprintMessage;
    Intent intent;
    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;
    private DataBaseManager dataBaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar bar = getSupportActionBar();
        bar.hide();
        init();
        mAuth = FirebaseAuth.getInstance();
        try {
            dataBaseManager = new DataBaseManager(this);
            //create the table
            dataBaseManager.dbInitialize(TABLE_NAME, table);
        }
        catch(Exception exception)
        {
            Toast.makeText(LoginActivity.this,
                    exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());
        }
       login();
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            //btnLogin.setVisibility(View.GONE);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
                keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

                if(!fingerprintManager.isHardwareDetected()){
                    Toast.makeText(this, "Fingerprint Scanner Not Detected", Toast.LENGTH_SHORT).show();
                }else if(ContextCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Permission Not Granted.", Toast.LENGTH_SHORT).show();
                }
                else if(!keyguardManager.isKeyguardSecure()){
                    Toast.makeText(this, "Secure your phone with Phone Lock", Toast.LENGTH_SHORT).show();
                }
                else if(!fingerprintManager.hasEnrolledFingerprints()){
                    Toast.makeText(this, "You should add Atleast one fingerprint to use this feature. ", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Place your finger on scanner to start scanning. ", Toast.LENGTH_SHORT).show();
                    FingerPrintHandler fingerPrintHandler = new FingerPrintHandler(this);
                    fingerPrintHandler.startAuth(fingerprintManager, null);
                }
            }
        }
    }


    private void init() {
        username = findViewById(R.id.username);
        passwrd = findViewById(R.id.passwrd);
        btnLogin = findViewById(R.id.btnLogin);
        intent = new Intent(LoginActivity.this, DashBoardActivity.class);
       /* if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            username.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        }*/
    }

    private void login() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation()) {
                    final String userUsername = username.getText().toString().trim();
                    String userPassword = passwrd.getText().toString().trim();
                    mAuth.signInWithEmailAndPassword(userUsername, userPassword)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        addLogindetails();
                                        startActivity(intent);
                                        finish();

                                    } else {
                                        showAlert("Enter Correct Credentials.");
                                    }
                                }
                            });
                }
            }
        });
    }

    private void showAlert(String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Login Failed!");
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setIcon(R.drawable.ic_action_alerts);
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
    private boolean validation() {
        if (username.getText().toString().trim().length() == 0) {
            username.setError("Enter Email ID");
            return false;
        } else if (passwrd.getText().toString().trim().length() == 0) {
            passwrd.setError("Enter Password");
            return false;
        } else if (!isValidEmail(username.getText().toString())) {
            username.setError("Enter Valid Email");
            return false;
        } else {
            return true;
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

   public void addLogindetails() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        String currentDateandTime = sdf.format(new Date());
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", currentDateandTime);
        contentValues.put("loginTime", currentDateandTime);
        try {
            dataBaseManager.addRecord(contentValues);
        } catch (Exception exception) {
            Toast.makeText(LoginActivity.this,
                    exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

