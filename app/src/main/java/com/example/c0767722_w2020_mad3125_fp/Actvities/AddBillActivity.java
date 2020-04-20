package com.example.c0767722_w2020_mad3125_fp.Actvities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.c0767722_w2020_mad3125_fp.Adapters.TypeAdapter;
import com.example.c0767722_w2020_mad3125_fp.Models.Bill;
import com.example.c0767722_w2020_mad3125_fp.Models.Hydro;
import com.example.c0767722_w2020_mad3125_fp.Models.Internet;
import com.example.c0767722_w2020_mad3125_fp.Models.Mobile;
import com.example.c0767722_w2020_mad3125_fp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AddBillActivity extends AppCompatActivity implements View.OnClickListener {


    @InjectView(R.id.imageView)
    ImageView imageView;
    @InjectView(R.id.textView7)
    TextView textView7;
    @InjectView(R.id.textView8)
    TextView textView8;
    @InjectView(R.id.linearLayout)
    LinearLayout linearLayout;
    @InjectView(R.id.textView9)
    TextView textView9;
    @InjectView(R.id.txt_title_spec)
    TextView txtTitleSpec;
    @InjectView(R.id.txt_ae_type)
    TextView txtAeType;
    @InjectView(R.id.ll_ae_type)
    LinearLayout llAeType;
    @InjectView(R.id.sp_billType)
    Spinner spBillType;
    @InjectView(R.id.img_Company)
    ImageButton imgCompany;
    @InjectView(R.id.txt)
    TextView txt;
    @InjectView(R.id.editBilladte)
    TextView editBilladte;
    @InjectView(R.id.edtMobileManu)
    EditText edtMobileManu;
    @InjectView(R.id.edtmobPlan)
    EditText edtmobPlan;
    @InjectView(R.id.countrycodeEt)
    EditText countrycodeEt;
    @InjectView(R.id.et_ae_phoneno)
    EditText mobileNo;
    @InjectView(R.id.edtInGbused)
    EditText edtInGbused;
    @InjectView(R.id.edtInMintuesused)
    EditText edtInMintuesused;
    @InjectView(R.id.llMobile)
    LinearLayout llMobile;
    @InjectView(R.id.edtGBUSed)
    EditText edtGBUSed;
    @InjectView(R.id.edtProviderName)
    EditText edtProviderName;
    @InjectView(R.id.llInternet)
    LinearLayout llInternet;
    @InjectView(R.id.edtAgencyName)
    EditText edtAgencyName;
    @InjectView(R.id.edtUnitsConsumed)
    EditText edtUnitsConsumed;
    @InjectView(R.id.llHydro)
    LinearLayout llHydro;
    @InjectView(R.id.contView)
    FrameLayout contView;
    @InjectView(R.id.saveBtn)
    Button saveBtn;
    @InjectView(R.id.rootView)
    ConstraintLayout rootView;
    ArrayList<String> billTypelist = new ArrayList<>();
    TypeAdapter spinnerAdapter;
    String billid;
    ConstraintLayout constraintLayout;
    @InjectView(R.id.edtBillId)
    EditText edtBillId;
    @InjectView(R.id.btn)
    Button btn;
    private DatePickerDialog datePicker;
    String cusId;
    String billID;
    String dateText;
    int maxId;
    String manufacture;
    String planName;
    String mobileNumber;
    String gbUsed;
    String mintuesUsed;
    String internetGbused;
    String providerName;
    String agencyName;
    String unitsConsumed;
    Bill bill;
    Mobile mobile;
    Hydro hydro;
    Internet internet;
    String amount;
    double totalFare, totalAmount;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("customerbills");
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill);
        ActionBar bar = getSupportActionBar();
        bar.hide();
        ButterKnife.inject(this);
        billTypelist.add("Internet");
        billTypelist.add("Hydro");
        billTypelist.add("Mobile");
        setupLayout();
        Intent intent = getIntent();
        if (intent.hasExtra("custDetails")) {
            cusId = intent.getStringExtra("custDetails");
            Log.d("Cusuus", cusId);
        }
        llAeType.setOnClickListener(this);
        spinnerAdapter = new TypeAdapter(AddBillActivity.this, billTypelist, 0);
        spBillType.setAdapter(spinnerAdapter);

        editBilladte.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    closeKeyboard();
                    openDatePicker();
                }
            }
        });
        editBilladte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker();
            }
        });


    }

    @Override
    public void onClick(View view) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        spBillType.performClick();

    }

    public void setupLayout() {
        constraintLayout = findViewById(R.id.rootView);
        constraintLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                constraintLayout.getWindowVisibleDisplayFrame(r);
                int screenHeight = constraintLayout.getRootView().getHeight();
                int keypadHeight = screenHeight - r.bottom;

            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveClicked();
            }
        });

    }

    public void saveClicked() {
        String billType = txtAeType.getText().toString();

        if (checkValidations()) {
            date = editBilladte.getText().toString();
            String billId = edtBillId.getText().toString();
            switch (billType) {
                case "Mobile":
                    manufacture = edtMobileManu.getText().toString();
                    planName = edtmobPlan.getText().toString();
                    mobileNumber = mobileNo.getText().toString();
                    gbUsed = edtInGbused.getText().toString();
                    mintuesUsed = edtInMintuesused.getText().toString();
                    billType = "Mobile";
                    String amount = String.valueOf(getTotalFare());
                    mobile = new Mobile(cusId, billId, date, billType, amount, manufacture, planName, mobileNumber, gbUsed, mintuesUsed);
                    myRef.push().setValue(mobile);
                    break;
                case "Hydro":

                    unitsConsumed = edtUnitsConsumed.getText().toString();
                    agencyName = edtAgencyName.getText().toString();
                    billType = "Hydro";
                    String amountH = String.valueOf(getTotalFare());
                    hydro = new Hydro(cusId, billId, date, billType, amountH, unitsConsumed, agencyName);
                    myRef.push().setValue(hydro);
                    break;
                case "Internet":
                    providerName = edtProviderName.getText().toString();
                    internetGbused = edtGBUSed.getText().toString();
                    billType = "Internet";
                    String amountI = String.valueOf(getTotalFare());
                    internet = new Internet(cusId, billId, date, billType, amountI, providerName, internetGbused);
                    myRef.push().setValue(internet);
                    break;
            }
           // Intent intent = new Intent(AddBillActivity.this, CustomerDetailActivity.class);
            showeDialog(AddBillActivity.this,"Are you sure you want to Save?");
           // finish();
        }

    }
    public void showeDialog(final Activity context, String msg) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_dialog_twobutton);

        TextView text = dialog.findViewById(R.id.dialogtext);
        text.setText(msg);

        Button dialogButton = dialog.findViewById(R.id.customButton);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                context.finish();
            }
            });
        Button dialogNoButton = dialog.findViewById(R.id.customButtonNo);
        dialogNoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public double getTotalFare() {
        String billType = txtAeType.getText().toString();
        switch (billType) {
            case "Mobile": {
                double data = Double.parseDouble(gbUsed);
                double mintsUsed = Double.parseDouble(mintuesUsed);
                totalFare = (data * 10) + (mintsUsed * 0.5);
                totalAmount = totalFare;
                break;
            }
            case "Internet": {
                double unitConsumed = Double.parseDouble(internetGbused);
                totalFare = unitConsumed * 0.5;
                totalAmount = totalFare;
                break;
            }
            case "Hydro": {
                double interUSed = Double.parseDouble(unitsConsumed);
                totalFare = interUSed * 0.5;
                totalAmount = totalFare;
                break;
            }
            default:
                totalFare = 0;
                break;
        }
        return totalFare;
    }


    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void showSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 0);
    }

    private void closeKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public void setdata(String s, int i,int position) {
        txtAeType.setText(s);
        if (s.equals("Mobile")) {
            llMobile.setVisibility(View.VISIBLE);
            llInternet.setVisibility(View.GONE);
            llHydro.setVisibility(View.GONE);
        } else if (s.equals("Hydro")) {
            llHydro.setVisibility(View.VISIBLE);
            llMobile.setVisibility(View.GONE);

            llInternet.setVisibility(View.GONE);

        } else if (s.equals("Internet")) {
            llInternet.setVisibility(View.VISIBLE);
            llMobile.setVisibility(View.GONE);
            llHydro.setVisibility(View.GONE);
        }
        if(i==0) {
            if (s.equals("Mobile")){
                    imgCompany.setBackgroundResource(R.drawable.mobile);
                } else if (s.equals("Hydro")){
                imgCompany.setBackgroundResource(R.drawable.hydro);
            } else if (s.equals("Internet")){
                imgCompany.setBackgroundResource(R.drawable.internet);
            }

        }
        spinnerAdapter = new TypeAdapter(AddBillActivity.this, billTypelist, 0);
        spBillType.setAdapter(spinnerAdapter);

    }

    private void openDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        datePicker = new DatePickerDialog(AddBillActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, monthOfYear, dayOfMonth);
                        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
                        String strDate = format.format(calendar.getTime());
                        editBilladte.setText(strDate.toUpperCase());
                    }
                }, year, month, day);
        datePicker.getDatePicker().setMaxDate(new Date().getTime());
        datePicker.show();
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
               // context.finish();
            }
        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
    private boolean checkValidations() {
        if (edtBillId.getText().toString().equals("")) {
            edtBillId.setError("Please enter bill Id");
            return false;
        } else if (txtAeType.getText().toString().equals("Select Company")) {
            txtAeType.setError("Please select the company");
            return false;
        } else if (editBilladte.getText().toString().equals("")) {
            editBilladte.setError("Please select the Date Of Bill");
            return false;
        } else if (txtAeType.getText().toString().equals("Mobile")) {
            if (edtMobileManu.getText().toString().isEmpty()) {
                edtMobileManu.setError("Please Enter Manufacture Name");
                return false;
            } else if (edtmobPlan.getText().toString().isEmpty()) {
                edtmobPlan.setError("Please Enter Plan Name");
                return false;
            } else if (mobileNo.getText().toString().isEmpty()) {
                mobileNo.setError("Please Enter Mobile Number");
                return false;
            } else if (edtInGbused.getText().toString().isEmpty()) {
                edtInGbused.setError("Please Enter GB used ");
                return false;
            } else if (edtInMintuesused.getText().toString().isEmpty()) {
                edtInMintuesused.setError("Please Enter Plan Name");
                return false;
            }
        } else if (txtAeType.getText().toString().equals("Hydro")) {
            if (edtUnitsConsumed.getText().toString().isEmpty()) {
                edtUnitsConsumed.setError("Please Enter Unit Consumed");
                return false;
            } else if (edtAgencyName.getText().toString().isEmpty()) {
                edtAgencyName.setError("Please Enter Agency Name");
            }

        } else if (txtAeType.getText().toString().equals("Internet")) {
            if (edtProviderName.getText().toString().isEmpty()) {
                edtProviderName.setError("Please Enter Provider name ");
            } else if (edtGBUSed.getText().toString().isEmpty()) {
                edtGBUSed.setError("Please Enter GB used ");
                return false;
            }
        }
        return true;
    }
    public void saveAlert(){

    }

}
