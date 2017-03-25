package com.example.dream.place_picker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import static com.google.android.gms.location.places.ui.PlacePicker.*;

public class MainActivity extends AppCompatActivity {

    private TextView getPlace;
    private final int REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPlace = (TextView) findViewById(R.id.getPlace);
        getPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPlacePickerActivity();
            }

        });
    }


    private void startPlacePickerActivity() {

        IntentBuilder intentBuilder = new IntentBuilder();
        Intent intent = null;
        try {
             intent = intentBuilder.build(MainActivity.this);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
        startActivityForResult(intent,REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST)
        {
            if (resultCode == RESULT_OK)
            {
                Place place = PlacePicker.getPlace(data,this);
                String address = String.format("Place: %s",place.getAddress());
                getPlace.setText(address);
            }
            //super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
