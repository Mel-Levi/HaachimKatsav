package com.example.haachimkatsav;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import static java.net.Proxy.Type.HTTP;

public class ThirdActivity extends AppCompatActivity {

    LinearLayout tinsmithsLayout;
    LinearLayout mechanicsLayout;
    LinearLayout technicalLayout;

    Button callDep1;
    Button callDep2;
    Button callDep3;

    Button alertDep1;
    EditText hurET1;
    EditText minET1;

    Button alertDep2;
    EditText hurET2;
    EditText minET2;

    Button alertDep3;
    EditText hurET3;
    EditText minET3;

    Button depBtn;
    String dep;

    Button previousBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.third_activity);

        tinsmithsLayout = findViewById(R.id.tinsmithsLayout);
        mechanicsLayout = findViewById(R.id.mechanicsLayout);
        technicalLayout = findViewById(R.id.technicalLayout);


        hurET1 = findViewById(R.id.hurET1);
        minET1 = findViewById(R.id.minET1);
        alertDep1 = findViewById(R.id.alertDep1);

        hurET2 = findViewById(R.id.hurET2);
        minET2 = findViewById(R.id.minET2);
        alertDep2 = findViewById(R.id.alertDep2);

        hurET3 = findViewById(R.id.hurET3);
        minET3 = findViewById(R.id.minET3);
        alertDep3 = findViewById(R.id.alertDep3);

        hurET1.getText().clear();
        hurET2.getText().clear();
        hurET3.getText().clear();
        minET1.getText().clear();
        minET2.getText().clear();
        minET3.getText().clear();

        depBtn = (Button) findViewById(R.id.depBtn);

        final String Department = getIntent().getStringExtra("Department");
        String tinsmithsDepartment = getString(R.string.tinsmithsDepartment);
        String mechanicsDepartment = getString(R.string.mechanicsDepartment);
        String technicalDepartment = getString(R.string.technicalDepartment);


        if (Department.equals(tinsmithsDepartment)) {

            tinsmithsLayout.setVisibility(View.VISIBLE);
            mechanicsLayout.setVisibility(View.INVISIBLE);
            technicalLayout.setVisibility(View.INVISIBLE);
            hurET1.getText().clear();
            hurET2.getText().clear();
            hurET3.getText().clear();
            minET1.getText().clear();
            minET2.getText().clear();
            minET3.getText().clear();




        } else if (Department.equals(mechanicsDepartment)) {
            tinsmithsLayout.setVisibility(View.INVISIBLE);
            mechanicsLayout.setVisibility(View.VISIBLE);
            technicalLayout.setVisibility(View.INVISIBLE);
            hurET1.getText().clear();
            hurET2.getText().clear();
            hurET3.getText().clear();
            minET1.getText().clear();
            minET2.getText().clear();
            minET3.getText().clear();



        } else if (Department.equals(technicalDepartment)) {
            tinsmithsLayout.setVisibility(View.INVISIBLE);
            mechanicsLayout.setVisibility(View.INVISIBLE);
            technicalLayout.setVisibility(View.VISIBLE);
            hurET1.getText().clear();
            hurET2.getText().clear();
            hurET3.getText().clear();
            minET1.getText().clear();
            minET2.getText().clear();
            minET3.getText().clear();


        }

////////////////////////////////// לחצן חזרה////////////////////////////////////
        previousBtn = findViewById(R.id.previousBtn);
        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThirdActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
// שיחת טלפון//////////////////////////////////////////////////////////////////////
        callDep1 = findViewById(R.id.callDep1);
        callDep1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "0537401209";

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + number));
                startActivity(intent);
            }
        });

        callDep2 = findViewById(R.id.callDep2);
        callDep2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "0542555429";

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + number));
                startActivity(intent);
            }
        });

        callDep3 = findViewById(R.id.callDep3);
        callDep3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "0537401210";

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + number));
                startActivity(intent);
            }
        });


        // תזכורת לחיוג חוזר //////////////////////////////////////////////////////////////////////




    }       // סגירת OnCreate ////////////////////////////////////////////////////



    public void selectDepartment(final View view) {       // תחילת תפריט selectDepartment

        PopupMenu popupMenu = new PopupMenu(this, depBtn);
        popupMenu.getMenuInflater().inflate(R.menu.departments_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override

            public boolean onMenuItemClick(MenuItem item) {

                //String tinsmithsDepartment = getString(R.string.tinsmithsDepartment);
             //   String mechanicsDepartment = getString(R.string.mechanicsDepartment);
               // String technicalDepartment = getString(R.string.technicalDepartment);

                switch (item.getItemId()) {

                    case R.id.tinsmithsDepartment: {            // פחחות
                            tinsmithsLayout.setVisibility(View.VISIBLE);
                            mechanicsLayout.setVisibility(View.INVISIBLE);
                            technicalLayout.setVisibility(View.INVISIBLE);
                        hurET1.getText().clear();
                        hurET2.getText().clear();
                        hurET3.getText().clear();
                        minET1.getText().clear();
                        minET2.getText().clear();
                        minET3.getText().clear();
                            break;
                    }

                    case R.id.mechanicsDepartment: {            // מכאנית
                            tinsmithsLayout.setVisibility(View.INVISIBLE);
                            mechanicsLayout.setVisibility(View.VISIBLE);
                            technicalLayout.setVisibility(View.INVISIBLE);
                        hurET1.getText().clear();
                        hurET2.getText().clear();
                        hurET3.getText().clear();
                        minET1.getText().clear();
                        minET2.getText().clear();
                        minET3.getText().clear();
                        break;
                    }

                    case R.id.technicalDepartment: {            // טכנית
                        tinsmithsLayout.setVisibility(View.INVISIBLE);
                        mechanicsLayout.setVisibility(View.INVISIBLE);
                        technicalLayout.setVisibility(View.VISIBLE);
                        hurET1.getText().clear();
                        hurET2.getText().clear();
                        hurET3.getText().clear();
                        minET1.getText().clear();
                        minET2.getText().clear();
                        minET3.getText().clear();
                        break;
                    }
                }
                return true;
            }
        });
        popupMenu.show();
    }


    public void setAlarm1 (View view) {
        String hourStr = hurET1.getText().toString();
        String minString = minET1.getText().toString();

        if (hourStr.equals("") || minString.equals("")) {
            Toast.makeText(ThirdActivity.this, R.string.notValidTime, Toast.LENGTH_SHORT).show();
        }

        else {
            int hour = Integer.parseInt(hourStr);
            int min = Integer.parseInt(minString);
            String alertMsg1 = getString(R.string.alertMsg1);

            if (hour >= 0 && hour < 24 && min >= 0 && min < 60) {
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                        .putExtra(AlarmClock.EXTRA_MESSAGE, alertMsg1)
                        .putExtra(AlarmClock.EXTRA_HOUR, hour)
                        .putExtra(AlarmClock.EXTRA_MINUTES, min);
                startActivity(intent);
            } else
                Toast.makeText(ThirdActivity.this, R.string.notValidTime, Toast.LENGTH_SHORT).show();
        }

    }



    public void setAlarm2 (View view) {
        String hourStr = hurET2.getText().toString();
        String minString = minET2.getText().toString();

        if (hourStr.equals("") || minString.equals("")) {
            Toast.makeText(ThirdActivity.this, R.string.notValidTime, Toast.LENGTH_SHORT).show();
        }

        else {
            int hour = Integer.parseInt(hourStr);
            int min = Integer.parseInt(minString);
            String alertMsg2 = getString(R.string.alertMsg2);

            if (hour >= 0 && hour < 24 && min >= 0 && min < 60) {
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                        .putExtra(AlarmClock.EXTRA_MESSAGE, alertMsg2)
                        .putExtra(AlarmClock.EXTRA_HOUR, hour)
                        .putExtra(AlarmClock.EXTRA_MINUTES, min);
                startActivity(intent);
            } else
                Toast.makeText(ThirdActivity.this, R.string.notValidTime, Toast.LENGTH_SHORT).show();
        }

    }



    public void setAlarm3 (View view) {
        String hourStr = hurET3.getText().toString();
        String minString = minET3.getText().toString();

        if (hourStr.equals("") || minString.equals("")) {
            Toast.makeText(ThirdActivity.this, R.string.notValidTime, Toast.LENGTH_SHORT).show();
        }

        else {
            int hour = Integer.parseInt(hourStr);
            int min = Integer.parseInt(minString);
            String alertMsg3 = getString(R.string.alertMsg3);

            if (hour >= 0 && hour < 24 && min >= 0 && min < 60) {
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                        .putExtra(AlarmClock.EXTRA_MESSAGE, alertMsg3)
                        .putExtra(AlarmClock.EXTRA_HOUR, hour)
                        .putExtra(AlarmClock.EXTRA_MINUTES, min);
                startActivity(intent);
            } else
                Toast.makeText(ThirdActivity.this, R.string.notValidTime, Toast.LENGTH_SHORT).show();
        }

    }
}