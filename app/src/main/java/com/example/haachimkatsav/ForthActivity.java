package com.example.haachimkatsav;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class ForthActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ImageView treatmentIV;

    Button setTimerBtn;
    Button prevBtn;
    int secForTimer;
    String selectedTreatType;
    Boolean slctFlag = false;
    String statTimerStr;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.forth_activity);

        treatmentIV = findViewById(R.id.treatmentIV);
        setTimerBtn = findViewById(R.id.setTimerBtn);
        statTimerStr = getString(R.string.statTimer);
        prevBtn = findViewById(R.id.prevBtn);


        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForthActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });




        /* תפריט טיפולים */
        Spinner treatmentType_spinner = findViewById(R.id.treatmentTypeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.treatmentType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        treatmentType_spinner.setAdapter(adapter);
        treatmentType_spinner.setOnItemSelectedListener(this);
    }


    /* תפריט טיפולים */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        slctFlag = false;

        if (position == 0) {

            slctFlag = false;
            treatmentIV.setVisibility(View.INVISIBLE);
            secForTimer = 0;
        }
            if (position == 1){

                slctFlag = true;
                treatmentIV.setVisibility(View.VISIBLE);
                treatmentIV.setImageResource(R.drawable.periodical);
                secForTimer = 10800;
                selectedTreatType = parent.getItemAtPosition(position).toString();
        }
        if (position == 2) {

            slctFlag = true;
            treatmentIV.setVisibility(View.VISIBLE);
            treatmentIV.setImageResource(R.drawable.test);
            secForTimer = 7200;
            selectedTreatType = parent.getItemAtPosition(position).toString();
        }

        if (position == 3) {

            slctFlag = true;
            treatmentIV.setVisibility(View.VISIBLE);
            treatmentIV.setImageResource(R.drawable.front_adjustment);
            secForTimer = 5400;
            selectedTreatType = parent.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }



    public void startTimer(View view) {

        if (!slctFlag){
            Toast.makeText(ForthActivity.this, R.string.pleaseSlctTreat, Toast.LENGTH_SHORT).show();
        }
    else {
            Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER)
                    .putExtra(AlarmClock.EXTRA_MESSAGE, selectedTreatType)
                    .putExtra(AlarmClock.EXTRA_LENGTH, secForTimer)
                    .putExtra(AlarmClock.EXTRA_SKIP_UI, true);
            if (intent.resolveActivity(getPackageManager()) != null) {
                Toast.makeText(ForthActivity.this, statTimerStr+" "+selectedTreatType, Toast.LENGTH_SHORT).show();

                startActivity(intent);
            }
        }
    }
}

