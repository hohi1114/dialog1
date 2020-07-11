package com.example.dialog1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.net.Uri;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CustomersClicked extends Activity implements View.OnClickListener {
    private TextView mNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_clicked);

        Intent intent = getIntent();

        TextView name = (TextView) findViewById(R.id.tv_name);
        TextView gender = (TextView) findViewById(R.id.tv_gender);
        TextView phone = (TextView) findViewById(R.id.tv_phone);

        Button mDialogCall = (Button) findViewById(R.id.btnDial);
        Button mCall = (Button) findViewById(R.id.btnCall);

        name.setText(intent.getStringExtra("name"));
        gender.setText(intent.getStringExtra("gender"));
        phone.setText(intent.getStringExtra("phone"));

        mNum = phone;
        mDialogCall.setOnClickListener(this);
        mCall.setOnClickListener(this);
    }

    public void onClick(View v){

        String tel = "tel:" + mNum.getText().toString();
        switch(v.getId()){
            case R.id.btnCall:
                startActivity(new Intent("android.intent.action.CALL", Uri.parse(tel)));
            case R.id.btnDial:
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));
        }
    }
}