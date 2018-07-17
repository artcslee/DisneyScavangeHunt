package com.catchoom.test.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import com.catchoom.test.R;
import com.catchoom.test.controllers.RealmController;
import com.catchoom.test.controllers.ScavangeListAdapter;
import com.catchoom.test.models.ScavangeItem;

public class ScavangeListActivity extends AppCompatActivity {
    RealmController mRc;
    public static final String AREA_FIELD = "area";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scavange_list);

        String scavangeArea = getIntent().getStringExtra(AREA_FIELD);

        mRc = new RealmController();
        List<ScavangeItem> scavangeItems = mRc.queryArea(scavangeArea);
        if (scavangeItems.size() == 0) {
            scavangeItems = mRc.initializeArea(scavangeArea, getResources(), getPackageName());
        }

        ScavangeListAdapter listAdapter = new ScavangeListAdapter(scavangeItems);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.scavange_list_view);
        recyclerView.setAdapter(listAdapter);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRc.closeRealm();
    }
}
