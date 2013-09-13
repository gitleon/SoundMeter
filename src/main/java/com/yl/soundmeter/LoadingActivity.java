package com.yl.soundmeter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

public class LoadingActivity extends Activity {

    private ProgressDialog mDlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDlg = ProgressDialog.show(LoadingActivity.this, "", getString(R.string.loading), true, false);
        startActivity(new Intent(LoadingActivity.this, SoundMeter.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    
}
