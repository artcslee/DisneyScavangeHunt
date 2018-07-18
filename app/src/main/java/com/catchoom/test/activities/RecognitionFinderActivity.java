package com.catchoom.test.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.catchoom.test.R;
import com.craftar.CraftARActivity;
import com.craftar.CraftARError;
import com.craftar.CraftAROnDeviceIR;
import com.craftar.CraftARResult;
import com.craftar.CraftARSDK;
import com.craftar.CraftARSearchResponseHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecognitionFinderActivity  extends CraftARActivity implements CraftARSearchResponseHandler {
    private final static String TAG = "RecognitionFinder";

    CraftARSDK mCraftARSDK;
    CraftAROnDeviceIR mOnDeviceIR;

    ProgressBar mProgress;
    long startFinderTimeMillis;
    private final static long FINDER_SESSION_TIME_MILLIS= 10000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgress = (ProgressBar) findViewById(R.id.search_progress_bar);
    }

    @Override
    public void onPostCreate() {
        View mainLayout= getLayoutInflater().inflate(R.layout.activity_recognition_finder, null);
        setContentView(mainLayout);

        mCraftARSDK = CraftARSDK.Instance();
        mCraftARSDK.startCapture(this);

        //Get the instance to the OnDeviceIR singleton (it has already been initialized in the SplashScreenActivity, and the collectoins are already loaded).
        mOnDeviceIR = CraftAROnDeviceIR.Instance();

        //Tell the SDK that the OnDeviceIR who manage the calls to singleShotSearch() and startFinding().
        //In this case, as we are using on-device-image-recognition, we will tell the SDK that the OnDeviceIR singleton will manage this calls.
        mCraftARSDK.setSearchController(mOnDeviceIR.getSearchController());

        //Tell the SDK that we want to receive the search responses in this class.
        mOnDeviceIR.setCraftARSearchResponseHandler(this);

        startFinding();
    }

    @Override
    public void onCameraOpenFailed() {
        Log.e(TAG, "CAMERA FAILED TO OPEN");
        Toast.makeText(getApplicationContext(), "Failed to open Camera", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onPreviewStarted(int i, int i1) {}

    private void startFinding(){
        mCraftARSDK.startFinder();
        startFinderTimeMillis= System.currentTimeMillis();
    }

    private void stopFinding(List<CraftARResult> results){
        mCraftARSDK.stopFinder();
        processResults(results);
    }

    @Override
    public void searchResults(ArrayList<CraftARResult> results, long searchTimeMillis, int requestCode) {
        Log.d(TAG, "Search results found:" + results);
        if(results.size() > 0){
            //We found something! Show the results
            stopFinding(results);
        }else{
            continueSearch();
        }
    }

    @Override
    public void searchFailed(CraftARError craftARError, int i) {
        Log.d(TAG, "Search Failed");
        continueSearch();
    }

    private void continueSearch() {
        long ellapsedTime = System.currentTimeMillis() - startFinderTimeMillis;
        if (ellapsedTime > FINDER_SESSION_TIME_MILLIS ) {
            stopFinding(Collections.<CraftARResult>emptyList());
        } else {
            mProgress.setProgress((int)(ellapsedTime/FINDER_SESSION_TIME_MILLIS));
        }
    }

    private void processResults(List<CraftARResult> results) {
        mProgress.setProgress(100);
        if (results !=null || results.size() > 0) {

        }
    }

}
