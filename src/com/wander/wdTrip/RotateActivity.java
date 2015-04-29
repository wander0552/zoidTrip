package com.wander.wdTrip;

/**
 * Created by wander on IDEA.
 * Date:15-4-29
 * Email:18955260352@163.com
 */

import android.graphics.Canvas;
import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

/**
 * User: ye.yang
 * Date: 13-5-10
 * Time: 上午10:57
 */
public class RotateActivity extends Activity {
    private float earthDegree = 0f;
    private float moonDegree = 0f;
    private Canvas c;
    private Paint p;
    private Handler handler = new Handler();
    private ImageView iv;
    private int w, h;
    private Bitmap earthBmp, moonBmp;
    private int earthW, earthH;
    private float moonX, moonY;
    private Runnable task = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, (long) (0.01 * 1000));
            earthDegree++;
            Bitmap backgroundBmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            c = new Canvas(backgroundBmp);
            p = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DEV_KERN_TEXT_FLAG);
            Rect r = new Rect(w / 2 - earthW / 2, h / 2 - earthH / 2, w / 2 + earthW / 2, h / 2 + earthH / 2);
            c.save();
            c.rotate(earthDegree, w / 2, h / 2);
            c.drawBitmap(earthBmp, null, r, p);
            c.restore();
            p.setStyle(Paint.Style.STROKE);
            c.drawRect(r, p);
            moonDegree += (1f / 30);//自转一天的角度=公转30天的角度，都是360°，所以简单/30
            float tempX = moonX;
            moonX = xn(tempX, moonY, w / 2, h / 2, moonDegree);
            moonY = yn(tempX, moonY, w / 2, h / 2, moonDegree);
            c.drawBitmap(moonBmp, moonX, moonY, p);
            iv.setImageBitmap(backgroundBmp);
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rotate);
        w = getMetrics().get("width");
        h = getMetrics().get("height");
        earthBmp = BitmapFactory.decodeResource(getResources(), R.drawable.paperplane_icon_76);
        moonBmp = BitmapFactory.decodeResource(getResources(), R.drawable.paperplane_icon_50);
        earthW = earthBmp.getWidth();
        earthH = earthBmp.getHeight();
        moonX = w / 2 + 100;
        moonY = h / 2;
        iv = (ImageView) findViewById(R.id.rotate_iv);
        handler.post(task);
    }

    private Map<String, Integer> getMetrics() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = getWindowManager();
        if (wm == null)
            wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
            wm.getDefaultDisplay().getMetrics(displayMetrics);
        }
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        Map<String, Integer> resultMap = new HashMap<String, Integer>();
        resultMap.put("width", width);
        resultMap.put("height", height);
        return resultMap;
    }

    protected float xn(float pointX, float pointY, float x, float y, double angle) {
        double angleHude = Math.toRadians(angle);//角度转弧度
        double cosAngle = Math.cos(angleHude);
        double sinAngle = Math.sin(angleHude);
        return (float) ((pointX - x) * cosAngle - (pointY - y) * sinAngle + x);
    }

    protected float yn(float pointX, float pointY, float x, float y, double angle) {
        double angleHude = Math.toRadians(angle);//角度转弧度
        double cosAngle = Math.cos(angleHude);
        double sinAngle = Math.sin(angleHude);
        return (float) ((pointX - x) * sinAngle + (pointY - y) * cosAngle + y);
    }
}
