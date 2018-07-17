package com.catchoom.test.controllers;

import android.content.res.Resources;

import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.catchoom.test.models.ScavangeItem;
import io.realm.Realm;

/**
 * Created by artcslee on 7/9/18.
 */
public class RealmController {
    public static final String TOMORROWLAND = "tomorrowland";
    public static final Map<String, String> AREA_STRINGS;
    static {
        AREA_STRINGS = new HashMap<>();
        AREA_STRINGS.put(TOMORROWLAND, "Tomorrow Land");
    }

    private Realm mRealm;

    public RealmController() {
        mRealm = Realm.getDefaultInstance();
    }

    public void closeRealm() {
        if (mRealm.isInTransaction()) {
            mRealm.cancelTransaction();
        }
        mRealm.close();
    }

    public boolean initialized() {
        return mRealm.isEmpty();
    }

    public List<ScavangeItem> initializeArea(String area, Resources resources, String packageName) {
        List<ScavangeItem> scavangeList = new ArrayList<>();
        try {
            CSVReader cr = new CSVReader(new InputStreamReader(resources.getAssets().open(area+".csv")));
            List<String[]> csvList = cr.readAll();
            for (String[] row : csvList) {
                ScavangeItem item = new ScavangeItem(row[0], row[1], resources.getIdentifier(row[2], "drawable", packageName), area);
                scavangeList.add(item);
            }

            mRealm.beginTransaction();
            scavangeList = mRealm.copyToRealm(scavangeList);
            mRealm.commitTransaction();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scavangeList;
    }

    public List<ScavangeItem> queryArea(String area) {
        return mRealm.copyFromRealm(mRealm.where(ScavangeItem.class).equalTo("area", area).findAll());
    }
}
