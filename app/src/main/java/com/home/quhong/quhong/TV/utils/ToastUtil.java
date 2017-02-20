package com.home.quhong.quhong.TV.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.home.quhong.quhong.QuHong;

/**
 * Imitation by Abybxc on 16/8/4 21:18
 * weixin:aserbao
 * <p/>
 * Toast工具类
 */
public class ToastUtil
{

    public static void showShort(Context context, String text)
    {

        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(Context context, int resId)
    {

        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context context, String text)
    {

        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public static void showLong(Context context, int resId)
    {

        Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
    }

    public static void LongToast(final String text)
    {

        new Handler(Looper.getMainLooper()).post(() -> Toast.
                makeText(new QuHong(), text, Toast.LENGTH_LONG).show());
    }

    public static void LongToast(final int stringId)
    {

        new Handler(Looper.getMainLooper()).post(() -> Toast.
                makeText(new QuHong(), stringId, Toast.LENGTH_LONG).show());
    }

    public static void ShortToast(final String text)
    {

        new Handler(Looper.getMainLooper()).post(() -> Toast.
                makeText(new QuHong(), text, Toast.LENGTH_SHORT).show());
    }

    public static void ShortToast(final int stringId)
    {

        new Handler(Looper.getMainLooper()).post(() -> Toast.
                makeText(new QuHong(), stringId, Toast.LENGTH_SHORT).show());
    }
}
