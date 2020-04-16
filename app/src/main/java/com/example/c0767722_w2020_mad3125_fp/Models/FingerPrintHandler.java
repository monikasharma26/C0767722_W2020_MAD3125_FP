package com.example.c0767722_w2020_mad3125_fp.Models;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;

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
       
    }

}

