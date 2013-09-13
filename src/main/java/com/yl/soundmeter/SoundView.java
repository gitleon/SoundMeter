package com.yl.soundmeter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Admin on 13-9-10.
 */
public class SoundView extends View implements View.OnLongClickListener, View.OnTouchListener {

    private Context mContext;
    private Bitmap imgSndMeter;
    private Bitmap imgSndMeedle;
    private Bitmap imgSndWheel;
    private Bitmap imgSndChart;
    private Bitmap imgBtnText;
    private Bitmap imgBtnChart;
    private String[] str;

    public SoundView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        imgSndMeter = BitmapFactory.decodeResource(getResources(), R.drawable.sound_meter);
        imgSndMeedle = BitmapFactory.decodeResource(getResources(), R.drawable.sound_needle);
        imgSndWheel = BitmapFactory.decodeResource(getResources(), R.drawable.sound_wheel);
        imgSndChart = BitmapFactory.decodeResource(getResources(), R.drawable.sound_chart);
        imgBtnText = BitmapFactory.decodeResource(getResources(), R.drawable.button_text);
        imgBtnChart = BitmapFactory.decodeResource(getResources(), R.drawable.button_chart);
        str = new String[13];
        str[0] = ((String)mContext.getText(R.string.db20_msg));
        str[1] = ((String)mContext.getText(R.string.db30_msg));
        str[2] = ((String)mContext.getText(R.string.db40_msg));
        str[3] = ((String)mContext.getText(R.string.db50_msg));
        str[4] = ((String)mContext.getText(R.string.db60_msg));
        str[5] = ((String)mContext.getText(R.string.db70_msg));
        str[6] = ((String)mContext.getText(R.string.db80_msg));
        str[7] = ((String)mContext.getText(R.string.db90_msg));
        str[8] = ((String)mContext.getText(R.string.db100_msg));
        str[9] = ((String)mContext.getText(R.string.db110_msg));
        str[10] = ((String)mContext.getText(R.string.db120_msg));
        str[11] = ((String)mContext.getText(R.string.db130_msg));
        str[12] = ((String)mContext.getText(R.string.db180_msg));

        setFocusable(true);
    }

    @Override
    public boolean onLongClick(View view) {
        return false;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}
