package com.yl.soundmeter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


/**
 * Created by Admin on 13-9-10.
 */
public class SoundMeter extends Activity {

    final private int RATE_COUNT = 6;

    final private int MENU_CALIBRATE = 1;
    final private int MENU_MANUAL = 2;
    final private int MENU_SETTINGS = 3;
    final private int MENU_ABOUT = 4;

    static int a = 0;
    static int b = 0;
    static float c = 0.0F;
    static float d = 0.0F;
    static boolean e = false;
    static boolean f = false;
    static boolean g = false;
    static boolean h = false;
    private static final int k = 200;
//    private com.google.ads.AdView i = null;
//    private net.daum.adam.publisher.AdView j = null;
    private SoundView mSoundView;       //l
    private Handler mHandler = new Handler();               //m
    private SoundPlay mSoundPlay;         //aj n
    private Recorder mRecorder;         //ah o
    private SharedPreferences mPref;    //p
    private Runnable mUpdateTimer = new Runnable() {        //q
        @Override
        public void run() {

            mRecorder.SoundDB();
            mHandler.postDelayed(mUpdateTimer, 200L);
        }
    };

    static TextView mSoundDB;
//    private SubMenu r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sound);
        mSoundView = ((SoundView)findViewById(R.id.view_soundmeter));
        mRecorder = new Recorder(getApplicationContext());
        mSoundPlay = new SoundPlay(getApplicationContext());
        mSoundDB = (TextView) findViewById(R.id.text_sounddb);
//        a(false);
//        if (!getString(2131427344).contains("Sound"))
//            finish();
        mPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor localEditor = mPref.edit();
        int RateCnt = mPref.getInt("smartcount", 0);
        boolean bool = mPref.getBoolean("smartcomment", true);
        RateCnt = RateCnt + 1;
        localEditor.putInt("smartcount", RateCnt);
        localEditor.commit();
        if ((bool) && (RateCnt >= RATE_COUNT) && ((RateCnt - RATE_COUNT) % 3 == 0))
            new ShowDialog().RateDialog(this).show();
        setVolumeControlStream(3);
//        if (Build.VERSION.SDK_INT > 10)
//            setTheme(2131492951);
//        getSupportActionBar().setHomeButtonEnabled(true);
    }


    protected void onDestroy()
    {
        super.onDestroy();

        if (Build.VERSION.SDK_INT < 14)
            System.exit(0);
    }

    protected void onResume()
    {
        super.onResume();
    }

    protected void onStart()
    {
        super.onStart();

        //mRecorder.setSoundView(mSoundView);
        mRecorder.RecorderInit();

        mHandler.postDelayed(mUpdateTimer, 200L);

        mSoundPlay.a();
    }

    protected void onStop()
    {
        super.onStop();
        mRecorder.RecorderRel();

        mHandler.removeCallbacks(mUpdateTimer);
    }

    public boolean onOptionsItemSelected(MenuItem menuitem) {

        ShowDialog dlg = new ShowDialog();

        switch (menuitem.getItemId()) {
            case MENU_CALIBRATE:

                break;

            case MENU_MANUAL:

                break;

            case MENU_SETTINGS:

                break;

            case MENU_ABOUT:
                dlg.AboutDialog(this).show();
                break;

            default:
                break;
        }

        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        int id = 0;
        menu.add(Menu.NONE, MENU_CALIBRATE, id, R.string.menu_calibrate);
        menu.add(Menu.NONE, MENU_MANUAL, id, R.string.menu_manual);
        menu.add(Menu.NONE, MENU_SETTINGS, id, R.string.menu_settings);
        menu.add(Menu.NONE, MENU_ABOUT, id, R.string.menu_about);

        return super.onCreateOptionsMenu(menu);
    }

    class ShowDialog {

        private String getVersionName() throws Exception {
            PackageManager packageManager = getPackageManager();
            PackageInfo packInfo = null;
            if (packageManager != null) {
                packInfo = packageManager.getPackageInfo(getPackageName(),0);
            }
            String version = null;
            if (packInfo != null) {
                version = packInfo.versionName;
            }
            return version;
        }

        public Dialog AboutDialog(Context paramContext)
        {
            AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);

            try {
                localBuilder.setTitle(paramContext.getString(R.string.app_name) + ' ' + getVersionName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            localBuilder.setIcon(R.drawable.ic_launcher);
            localBuilder.setMessage(
                    paramContext.getString(R.string.about_developed_by) + ' ' + paramContext.getString(R.string.about_developer_name) + '\n' +
                            paramContext.getString(R.string.about_msg) + '\n' +
                            paramContext.getString(R.string.about_developer_email)
            );
            localBuilder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });

            localBuilder.setNeutralButton(R.string.about_more_apps, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });

            localBuilder.setNegativeButton(R.string.about_send_email, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            return localBuilder.create();
        }

        public Dialog RateDialog(Context paramContext) {

            final SharedPreferences.Editor localEditor = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
            final AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
            localBuilder.setTitle(R.string.rate_title);
            localBuilder.setIcon(R.drawable.ic_launcher);
            localBuilder.setMessage(paramContext.getString(R.string.rate_msg));
            localBuilder.setCancelable(false);

            localBuilder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + getPackageName()));
                    //startActivity(localIntent);
                    localEditor.putBoolean("smartcomment", false);
                    localEditor.commit();
                }
            });

            localBuilder.setNeutralButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });

            localBuilder.setNegativeButton(R.string.rate_never, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    localEditor.putBoolean("smartcomment", false);
                    localEditor.commit();
                }
            });

            return localBuilder.create();
        }
    }

}

