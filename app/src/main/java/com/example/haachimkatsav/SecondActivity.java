package com.example.haachimkatsav;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class SecondActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, FinishDialog.MyDialogListener {


    Boolean slctFlag = false;
    Boolean finishFlag = false;
    EditText nameET;
    EditText telET;
    EditText emailET;


    LinearLayout periodicalCarLayout;

    Button DatePickerBtn;
    TextView dateView;
    Calendar clndr;

    Button hourBtn;
    TextView hourView;


    Button loadinsuranceBtn;
    Integer REQUEST_CAMERA = 1;
    Integer SELECT_INSURANCE_FILE = 0;
    TextView insurance_TV;
    ImageView insurance_IV;     // תצוגת תמונת ביטוח


    EditText licenseET;
    String selectedTreatType;

    String licensePlate;
    int garageAddress;

    int selectedDay;
    int selectedMonth;
    int selectedYear;
    int selectedMinute;
    int selectedHour;
    Long selectedStartTime;
    Long selectedEndTime;
    Long selectedAlarmTime;


    String title;

    Button finishBtn;
    Button prevBtn;


    public void onOkBtnClicked() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onOkBtnClicked2() {
        addEvent();
    }

    final String DIALOG_FRAGMENT_TAG = "finish_dialog_fragment";

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.second_activity);



        licenseET = findViewById(R.id.licenseET);


        // טעינת תצלום ביטוח //////////////////////////////////////////

        loadinsuranceBtn = findViewById(R.id.loadinsuranceBtn);
        insurance_TV = findViewById(R.id.insurance_TV);
        insurance_IV = findViewById(R.id.insurance_IV);

       loadinsuranceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }

        });


        // הכנסת תאריך //////////////////////////////////////////

        DatePickerBtn = findViewById(R.id.openDatepickerBtn);
        dateView = findViewById(R.id.date_TV);


        DatePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Date today = new Date();
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                /* set min date */
                calendar.setTime(today);
                final long minDate = calendar.getTime().getTime();


                /* set max date */
                calendar.setTime(today);
                calendar.add(calendar.MONTH, +1);
                final long maxDate = calendar.getTime().getTime();


                // הכנסת שעה //////////////////////////////////////////


                DatePickerDialog dpd = new DatePickerDialog(SecondActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {


                       selectedDay = i2;
                       selectedMonth = i1;
                       selectedYear = i;

                      calendar.set(i, i1, i2);
                      Long selectedDate = calendar.getTimeInMillis();

                        //String selectedDateStr = Long.toString(selectedDate);
                        dateView.setText(i2+ "/" +(i1+1)+ "/" +i);
                    }

                }, year, month, day);
                dpd.show();
            }
        });


        hourBtn = findViewById(R.id.hourBtn);
        hourView = findViewById(R.id.hour_TV);

        hourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog tpd = new TimePickerDialog(SecondActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int i, int i1) {

                        selectedHour = i;
                        selectedMinute = i1;

                        calendar.set(selectedYear, selectedMonth, selectedDay, selectedHour, selectedMinute);
                        selectedStartTime = calendar.getTimeInMillis();

                        calendar.set(selectedYear, selectedMonth, selectedDay, selectedHour+1, selectedMinute);
                        selectedEndTime = calendar.getTimeInMillis();

                        //String selectedDateStr = Long.toString(selectedDate);
                        hourView.setText(i + ":" +i1);

                    }
                }, hour, minute, true);
                tpd.show();
            }
        });


        // הכנסת אירוע ללוח שנה //////////////////////////////////////////

        nameET = findViewById(R.id.nameET);
        telET = findViewById(R.id.telET);
        emailET = findViewById(R.id.emailET);


        finishBtn = findViewById(R.id.finishBtn);
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nameStr = nameET.getText().toString();
                String telStr = telET.getText().toString();
                String emailStr = emailET.getText().toString();
                String dateStr = dateView.getText().toString();
                String hourStr = hourView.getText().toString();
                String insuranceString = insurance_TV.getText().toString();

                if (nameStr.equals("") || telStr.equals("") || emailStr.equals("") || dateStr.equals("") || hourStr.equals("") || insuranceString.equals("")) {
                    Toast.makeText(SecondActivity.this, R.string.fillAll, Toast.LENGTH_SHORT).show();
                    return;
                }

                else
                    {
                    FinishDialog dialog = new FinishDialog();
                    dialog.show(getSupportFragmentManager(), DIALOG_FRAGMENT_TAG);
                }

                // addEvent(selectedTreatType, garageAddressStr, selectedStartTime, selectedEndTime);
            }
        });


        // הכנסת שעון מעורר //////////////////////////////////////////

        prevBtn = findViewById(R.id.prevBtn);
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);

                /*Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                    Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                            .putExtra(AlarmClock.EXTRA_MESSAGE, title)
                            .putExtra(AlarmClock.EXTRA_HOUR, hour+1)
                            .putExtra(AlarmClock.EXTRA_MINUTES, minute);
                   // if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    //}*/
            }
        });



        periodicalCarLayout = findViewById(R.id.periodicalCarLayout);       // רפרנס לשכבת טיפול תקופתי

        /* הדפסת דואגים לרכב שלך */
        TextView txtType = findViewById(R.id.txtType);      // שדה דואגים לרכב שלך
        final String carType = getIntent().getStringExtra("carType");
        String care = getString(R.string.care);
        String yours = getString(R.string.yours);
        txtType.setText(care + carType + yours);

        /* תפריט טיפולים */
        Spinner treatmentType_spinner = findViewById(R.id.treatmentTypeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.treatmentType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        treatmentType_spinner.setAdapter(adapter);
        treatmentType_spinner.setOnItemSelectedListener(this);


    }  ////////////////////////////////////////////////////////////////////////////////// סגירת onCreate


    private void selectImage() {
        String cameraStr = getString(R.string.camera);
        String galleryStr = getString(R.string.gallery);
        final CharSequence[] items = {cameraStr, galleryStr};

        AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
        builder.setTitle(R.string.insurance);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (items[i].equals(getString(R.string.camera))) {

                    //Toast.makeText(SecondActivity.this, "Check!!!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                   startActivityForResult(intent, REQUEST_CAMERA);

                } else if (items[i].equals(getString(R.string.gallery))) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent.createChooser(intent, "Select File"), SELECT_INSURANCE_FILE);
                }
            }
        });
        builder.show();
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void addEvent(){

        String licensePlate = licenseET.getText().toString();
        String carStr = getString(R.string.car);

        title = selectedTreatType + " " + carStr + " " + licensePlate;

        String locationStr = getString(R.string.address);

        Intent intent1 = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, title)
                .putExtra(CalendarContract.Events.EVENT_LOCATION, locationStr)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, selectedStartTime)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, selectedEndTime);
        if (intent1.resolveActivity(getPackageManager()) != null) {
            startActivity(intent1);
        }
    }


/* מצלמה או טעינת תמונה */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, requestCode, data);
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            //Bundle bundle = data.getExtras();
            Bitmap bmp = (Bitmap) data.getExtras().get("data");
            insurance_TV.setText(R.string.insurance);
            insurance_IV.setImageBitmap(bmp);
        }

        else if (requestCode == SELECT_INSURANCE_FILE && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            insurance_TV.setText(R.string.insurance);
            insurance_IV.setImageURI(selectedImageUri);
            }
    }

    /* תפריט טיפולים */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        nameET.getText().clear();
        telET.getText().clear();
        emailET.getText().clear();
        licenseET.getText().clear();
        dateView.setText("");
        hourView.setText("");

        slctFlag = false;
        finishFlag = false;
        finishBtn.setVisibility(View.INVISIBLE);
        if (position != 0) {
            periodicalCarLayout.setVisibility(View.VISIBLE);
            slctFlag = true;
            finishFlag = true;
            finishBtn.setVisibility(View.VISIBLE);
            selectedTreatType = parent.getItemAtPosition(position).toString();
        }
        else {
            slctFlag = false;
            finishFlag = false;
            finishBtn.setVisibility(View.INVISIBLE);
            periodicalCarLayout.setVisibility(View.INVISIBLE);

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }



  /*  public void selectAnHour(final View view) {       // תחילת תפריט selectAnHour

        PopupMenu popupMenu = new PopupMenu(this, hourBtn);
        popupMenu.getMenuInflater().inflate(R.menu.hour_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override

            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.nine: {
                        hourView.setText("09:00");
                        selectedHour = "09:00";
                        break;
                    }

                    case R.id.ten: {
                        Integer ten = R.id.ten;
                        String tenStr = Integer.toString(R.id.ten);
                        hourView.setText("10:00");
                        selectedHour = "10:00";
                        break;
                    }

                    case R.id.eleven: {
                        Integer nine = R.id.eleven;
                        String elevenStr = Integer.toString(R.id.eleven);
                        hourView.setText("11:00");
                        selectedHour = "11:00";
                        break;
                    }

                    case R.id.twelve: {
                        Integer twelve = R.id.twelve;
                        String twelveStr = Integer.toString(R.id.twelve);
                        hourView.setText("12:00");
                        selectedHour = "12:00";
                        break;
                    }

                    case R.id.one: {
                        Integer one = R.id.one;
                        String oneStr = Integer.toString(R.id.one);
                        hourView.setText("13:00");
                        selectedHour = "13:00";
                        break;
                    }

                    case R.id.two: {
                        Integer two = R.id.two;
                        String twoStr = Integer.toString(R.id.two);
                        hourView.setText("14:00");
                        selectedHour = "14:00";
                        break;
                    }

                    case R.id.three: {
                        Integer three = R.id.three;
                        String threeStr = Integer.toString(R.id.three);
                        hourView.setText("15:00");
                        selectedHour = "15:00";
                        break;
                    }
                }
                return true;
            }
        });
        popupMenu.show();
    }*/

}