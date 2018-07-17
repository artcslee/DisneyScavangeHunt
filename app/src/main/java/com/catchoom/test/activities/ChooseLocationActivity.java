package com.catchoom.test.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.catchoom.test.R;

public class ChooseLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_location);

        Button disneyland = (Button) findViewById(R.id.disneyland_button);
        Button disneyworld = (Button) findViewById(R.id.disneyworld_button);

        disneyland.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToChoosePark();
            }
        });

        disneyworld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToChoosePark();
            }
        });
    }

    private void moveToChoosePark() {
        Intent chooseParkActivity = new Intent(this, ChooseLocationActivity.class);
        startActivity(chooseParkActivity);
    }
}
