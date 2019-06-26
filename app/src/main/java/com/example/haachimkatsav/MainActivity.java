package com.example.haachimkatsav;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterViewFlipper;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {

    String carType;
    Boolean slctFlag = false;

    Button startLogoBtn;

    String Department;

    Button treatmmentsLogoBtn;
    Button treatmmentsTxtBtn;
    Button departmentsLogoBtn;
    Button departmentsTxtBtn;
    Button infoLogoBtn;

    Button periodicalCare_btn;
    Button test_btn;
    Button frontAdjustment_btn;
    Button mechanicsDepartment_btn;
    Button technicalDepartment_btn;
    Button tinsmithsDepartment_btn;

    private AdapterViewFlipper AVF;

    int[] IMAGES = {
            R.drawable.picture1,
            R.drawable.picture2,
            R.drawable.picture3,
            R.drawable.picture4,
            R.drawable.picture5,
            R.drawable.picture6,
            R.drawable.picture8,
            R.drawable.picture9
    };

    String[] NAMES = {
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };


    Button waze;
    Button facebook;
    Button call;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        infoLogoBtn = findViewById(R.id.infoLogoBtn);
        infoLogoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FifthActivity.class);
                startActivity(intent);
            }
        });


        startLogoBtn = findViewById(R.id.startLogoBtn);
        startLogoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ForthActivity.class);
                startActivity(intent);
            }
        });

        AVF = (AdapterViewFlipper) findViewById(R.id.AVF);
        CustomAdapter customAdapter = new CustomAdapter (getApplicationContext(), NAMES, IMAGES);
        AVF.setAdapter(customAdapter);
        AVF.setFlipInterval(2600);
        AVF.setAutoStart(true);


        treatmmentsLogoBtn = (Button) findViewById(R.id.treatmmentsLogoBtn);
        treatmmentsTxtBtn = findViewById(R.id.treatmmentsLogoBtn);
        departmentsLogoBtn = findViewById(R.id.departmentsLogoBtn);
        departmentsTxtBtn = findViewById(R.id.departmentsTxtBtn);


        call = findViewById(R.id.call_btn);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "035508621";

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+number));
                startActivity(intent);
            }
        });

        waze = findViewById(R.id.navigate_btn);
        waze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lat = "32.0142734";
                String lng = "34.8038668";

                try
                {
                    // Launch Waze to look for Hawaii:
                    String url = "waze://?ll="+lat+","+lng+"&navigate=yes";
                    Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
                    startActivity( intent );
                }
                catch ( ActivityNotFoundException ex  )
                {
                    // If Waze is not installed, open it in Google Play:
                    Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "market://details?id=com.waze" ) );
                    startActivity(intent);
                }
            }
        });


        waze = findViewById(R.id.navigate_btn);
        waze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lat = "32.0142734";
                String lng = "34.8038668";

                try
                {
                    // Launch Waze to look for Hawaii:
                    String url = "waze://?ll="+lat+","+lng+"&navigate=yes";
                    Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
                    startActivity( intent );
                }
                catch ( ActivityNotFoundException ex  )
                {
                    // If Waze is not installed, open it in Google Play:
                    Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "market://details?id=com.waze" ) );
                    startActivity(intent);
                }
            }
        });

        facebook = findViewById(R.id.facebookBtn);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent facebookIntent = openFacebook(MainActivity.this);
                startActivity(facebookIntent);
            }
        });


    }           // סגירת onCreate


    public static Intent openFacebook(Context context){
        try
        {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW,Uri.parse("fb://page/467228249974143?referrer=app_link"));
        }
        catch ( Exception e  )
        {
           return new Intent( Intent.ACTION_VIEW, Uri.parse( "market://details?id=com.facebook" ));
        }

    }

    class CustomAdapter extends BaseAdapter{
        Context context;
        int[] images;
        String[] names;
        LayoutInflater inflater;

        public CustomAdapter (Context applicationContext, String[] names, int[] images){
            this.context = applicationContext;
            this.images = images;
            this.names = names;
            inflater = (LayoutInflater.from(applicationContext));
        }

        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {

            view = inflater.inflate(R.layout.pic_list, null);
            TextView name = (TextView) view.findViewById(R.id.name);
            ImageView image = (ImageView) view.findViewById(R.id.image);
            name.setText(names[position]);
            image.setImageResource(images[position]);
            return view;
        }
    }

    public void selectCarType(final View view) {       // תחילת תפריט selectCarType

        PopupMenu popupMenu = new PopupMenu(this, treatmmentsLogoBtn);
        popupMenu.getMenuInflater().inflate(R.menu.car_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override

            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.honda: {
                        carType = item.toString();
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        intent.putExtra("carType", carType);
                        startActivity(intent);
                        break;
                    }

                    case R.id.subaru: {
                        carType = item.toString();
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        intent.putExtra("carType", carType);
                        startActivity(intent);
                        break;
                    }

                    case R.id.toyota: {
                        carType = item.toString();
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        intent.putExtra("carType", carType);
                        startActivity(intent);
                        break;
                    }
                }
                return true;
            }
        });
        popupMenu.show();
    }


//////////////

    public void selectDepartment(final View view) {       // תחילת תפריט selectDepartment

        PopupMenu popupMenu = new PopupMenu(this, departmentsLogoBtn);
        popupMenu.getMenuInflater().inflate(R.menu.departments_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override

            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.tinsmithsDepartment: {            // פחחות
                        Department = item.toString();
                        Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                        intent.putExtra("Department", Department);
                        startActivity(intent);
                        break;
                    }

                    case R.id.mechanicsDepartment: {            // מכאנית
                        Department = item.toString();
                        Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                        intent.putExtra("Department", Department);
                        startActivity(intent);
                        break;
                    }

                    case R.id.technicalDepartment: {            // טכנית
                        Department = item.toString();
                        Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                        intent.putExtra("Department", Department);
                        startActivity(intent);
                        break;
                    }
                }
                return true;
            }
        });
        popupMenu.show();
    }
}

