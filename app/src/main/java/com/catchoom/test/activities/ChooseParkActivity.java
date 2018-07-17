package com.catchoom.test.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.catchoom.test.R;
import com.catchoom.test.controllers.RealmController;

public class ChooseParkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_park);

        Button disneyland = (Button) findViewById(R.id.disneyland_park_button);
        Button caladventure = (Button) findViewById(R.id.california_adventure_park_button);

        disneyland.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent disneyMapActivity = new Intent(ChooseParkActivity.this, ScavangeListActivity.class);
                disneyMapActivity.putExtra(ScavangeListActivity.AREA_FIELD, RealmController.TOMORROWLAND);
                startActivity(disneyMapActivity);
            }
        });

        caladventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
