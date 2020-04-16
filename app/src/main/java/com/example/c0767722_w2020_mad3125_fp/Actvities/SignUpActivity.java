package com.example.c0767722_w2020_mad3125_fp.Actvities;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.c0767722_w2020_mad3125_fp.R;

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
    @InjectView(R.id.btnCancel)
    AppCompatTextView btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.inject(this);
        ActionBar bar = getSupportActionBar();
        bar.hide();
    }
}
