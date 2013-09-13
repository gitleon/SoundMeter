package com.yl.soundmeter;

import android.content.Context;
import android.media.SoundPool;

public class SoundPlay
{
    private static SoundPool mSoundPool;
    private static int[] c = new int[5];
    private static long d = 0L;
    private static long e = 0L;
    private static long f = 0L;
    private static long g = 0L;
    private static final int h = 50;
    private static final int i = 2400;
    private Context mContext;

    public SoundPlay(Context paramContext)
    {
        mContext = paramContext;
    }

    public static void b(int id)
    {
        mSoundPool.play(c[id], 1.0F, 1.0F, 1, 0, 1.0F);
    }

    public static void c(int paramInt)
    {
        e = System.currentTimeMillis() - d;
        d += e;
        f += e;
        if (paramInt == -1)
        {
            g = 50L;
            if (f >= g)
            {
                mSoundPool.play(c[4], 1.0F, 1.0F, 0, 0, 1.0F);
                f = 0L;
            }
            return;
        }
        if (paramInt < 50);
        int k;
        for (int j = 0; ; k = paramInt + -50)
        {
            g = (long)(2400.0D - 250.0D * Math.sqrt(j));
            if (g >= 50L)
                break;
            g = 50L;
            break;
        }
    }

    public void a()
    {
        a(0);
    }

    public void a(int id)
    {
        mSoundPool = new SoundPool(3, 3, 0);
        c[id] = mSoundPool.load(mContext, R.raw.tick1, 1);
    }
}
