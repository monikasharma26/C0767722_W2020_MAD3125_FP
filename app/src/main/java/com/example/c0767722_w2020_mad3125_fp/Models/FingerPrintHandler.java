package com.example.c0767722_w2020_mad3125_fp.Models;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.c0767722_w2020_mad3125_fp.Actvities.DashBoardActivity;
import com.example.c0767722_w2020_mad3125_fp.R;

public class FingerPrintHandler extends FingerprintManager.AuthenticationCallback {
    private Context context;
    CancellationSignal cancellationSignal = new CancellationSignal();
    public FingerPrintHandler(Context context) {
        this.context = context;
    }
    public void startAuth(FingerprintManager fingerprintManager,FingerprintManager.CryptoObject cryptoObject){
        CancellationSignal cancellationSignal =  new CancellationSignal();
        fingerprintManager.authenticate(cryptoObject,cancellationSignal,0,this,null);
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        this.updateText("There was an Error "+ errString,false);
    }
    @Override
    public void onAuthenticationFailed() {
        this.updateText("Authentication Failed. ", false);
    }
    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        this.updateText("Error "+helpString,false);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        this.updateText("Authentication SuccessFull",true);
    }



    private void updateText(String s, boolean b) {
        TextView fingerprintMessage = ((Activity)context).findViewById(R.id.fingerprintMessage);
        fingerprintMessage.setText(s);
        if(b == false){
            fingerprintMessage.setTextColor(ContextCompat.getColor(context,R.color.colorAccent));
        }else{
            fingerprintMessage.setTextColor(ContextCompat.getColor(context,R.color.colorAccent));
            ((Activity)context).setIntent(new Intent(context, DashBoardActivity.class));
            context.startActivity(new Intent(context,DashBoardActivity.class));
            context.startActivity(new Intent(context,DashBoardActivity.class));
            ((Activity) context).finish();
        }
    }

}

