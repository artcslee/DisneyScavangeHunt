package com.catchoom.test.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.catchoom.test.controllers.RealmController;
import com.craftar.CraftARError;
import com.craftar.CraftAROnDeviceCollection;
import com.craftar.CraftAROnDeviceCollectionManager;
import com.craftar.CraftAROnDeviceCollectionManager.AddCollectionListener;
import com.craftar.CraftAROnDeviceIR;
import com.craftar.CraftARSDK;
import com.craftar.SetOnDeviceCollectionListener;

import java.util.List;

import com.catchoom.test.R;

import io.realm.Realm;

public class SplashScreenActivity extends AppCompatActivity implements AddCollectionListener,
        SetOnDeviceCollectionListener {
    static final String CRAFT_AR_TOKEN = "1a89da7006424738";

    CraftAROnDeviceCollectionManager mCollectionManager;
    CraftAROnDeviceIR mCraftAROnDeviceIR;
    RealmController mRealmController;

    private void initializeCraftAR() {
        CraftARSDK.Instance().init(getApplicationContext());
        mCollectionManager = CraftAROnDeviceCollectionManager.Instance();
        mCraftAROnDeviceIR = CraftAROnDeviceIR.Instance();

        CraftAROnDeviceCollection collection = mCollectionManager.get(CRAFT_AR_TOKEN);
        if (collection != null) {
            Log.v("ARTHUR", "Collection not null!");
            mCraftAROnDeviceIR.setCollection(collection, this);
        } else {
            Log.v("ARTHUR", "Collection null");
            // if file exists locally as zip use that else download
            if (true) {
                mCollectionManager.addCollectionWithToken(CRAFT_AR_TOKEN, this);
            } else {

            }
        }
    }

    private void initializeRealm() {
        Realm.init(this);
        /*
        mRealmController = new RealmController();
        if (!mRealmController.initialized()) {
            mRealmController.initializeArea(RealmController.TOMORROWLAND, getResources(), getPackageName());
        }
        mRealmController.closeRealm();
        */
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        initializeRealm();
        initializeCraftAR();
    }

    @Override
    public void collectionAdded(CraftAROnDeviceCollection craftAROnDeviceCollection) {
        Log.v("ARTHUR", "collectionAdded");
        mCraftAROnDeviceIR.setCollection(craftAROnDeviceCollection, this);
    }

    @Override
    public void addCollectionFailed(CraftARError craftARError) {
        Log.v("ARTHUR", "addCollectionFailed: [" + craftARError.getErrorCode() + "]" + craftARError.getErrorMessage());
        ///endSplashScreen();
    }

    @Override
    public void addCollectionProgress(float v) {
        Log.v("ARTHUR", "addCollectionProgress");
    }

    @Override
    public void setCollectionProgress(double v) {
        Log.v("ARTHUR", "setCollectionProgress");

    }

    @Override
    public void collectionReady(List<CraftARError> list) {
        Log.v("ARTHUR", "collectionReady");

        //Collection is ready for recognition.
        endSplashScreen();
    }

    @Override
    public void setCollectionFailed(CraftARError craftARError) {
        Log.v("ARTHUR", "setCollectionFailed");
        //endSplashScreen();
    }

    private void endSplashScreen() {
        Intent chooseLocationActivity = new Intent(this, ChooseLocationActivity.class);
        startActivity(chooseLocationActivity);
        finish();
    }
}
