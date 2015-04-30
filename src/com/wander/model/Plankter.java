package com.wander.model;

import android.content.Context;
import android.graphics.*;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import com.wander.Utils.BitmapUtils;
import com.wander.wdTrip.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by wander on IDEA.
 * Date:15-4-29
 * Email:18955260352@163.com
 */
public class Plankter implements Runnable {
    private boolean direction;
    private Context context;
    private SurfaceHolder holder;
    private int sHeight;
    private int sWidth;
    private boolean running;
//    private int[] tent = new int[]{R.drawable.tentacles_1, R.drawable.tentacles_2, R.drawable.tentacles_3, R.drawable.tentacles_4, R.drawable.tentacles_5,
//            R.drawable.tentacles_6, R.drawable.tentacles_7, R.drawable.tentacles_8, R.drawable.tentacles_9, R.drawable.tentacles_10};
//    private int[] face = new int[]{R.drawable.face_1_001, R.drawable.face_1_002, R.drawable.face_1_003, R.drawable.face_1_004, R.drawable.face_1_005,
//            R.drawable.face_1_006, R.drawable.face_1_007, R.drawable.face_1_008};
//
//    private Bitmap[] tentacles = new Bitmap[tent.length];
//    private Bitmap[] faces = new Bitmap[face.length];
    List<Bitmap>  tentacles=new ArrayList<Bitmap>();
    List<Bitmap>  faces=new ArrayList<Bitmap>();
    private int tentacle = 0;
    int face_pos = 0;

    public Plankter(Context context, SurfaceHolder holder) {
        this.context = context;
        this.holder = holder;
//        BitmapFactory.Options opts = new BitmapFactory.Options();
//        opts.inSampleSize = 4;
//        for (int i = 0; i < tentacles.length; i++) {
//            tentacles[i] = BitmapFactory.decodeResource(context.getResources(), tent[i], opts);
//        }
//        BitmapFactory.Options opts2 = new BitmapFactory.Options();
//        opts2.inSampleSize = 2;
//        for (int i = 0; i < face.length; i++) {
//            faces[i] = BitmapFactory.decodeResource(context.getResources(), face[i], opts2);
//        }
        faces = BitmapUtils.getFace(context, 1);
        tentacles=BitmapUtils.getFace(context,0);

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        sHeight = metrics.heightPixels;
        sWidth = metrics.widthPixels;
    }

    @Override
    public void run() {
        running = true;
        Integer step = 0;
        int n = 1;
        Paint paint = new Paint();
        paint.setShadowLayer(2, 20, 20, Color.BLACK);
        paint.setTextSize(30);
        paint.setColor(Color.WHITE);
        Paint paint1 = new Paint();
        Bitmap bitmap = faces.get(0);
        int right = bitmap.getWidth() / 2 - 5;
        int top = 280 + bitmap.getHeight();
        while (running) {

            Canvas canvas = holder.lockCanvas();
            paint1.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            canvas.drawPaint(paint1);
//                canvas.save();
                Log.d("step", step+Thread.currentThread().getName()+"");

            canvas.drawBitmap(faces.get(face_pos), step, 280, paint);
            canvas.drawBitmap(tentacles.get(tentacle), 5 + step, top, paint);
            canvas.drawBitmap(tentacles.get(tentacle), right + step, top, paint);
            canvas.drawText("" + step, 200, top + 400, paint);
//                canvas.restore();
            holder.unlockCanvasAndPost(canvas);

            tentacle = ++tentacle % tentacles.size();
            face_pos = ++face_pos % faces.size();
            step += n * 10;
            if (step > sWidth - bitmap.getWidth() || step == 0) {
                n *= -1;
                int rotate = 0;
                Canvas canvas2 = holder.lockCanvas();
                Matrix matrix = new Matrix();
                matrix.postRotate((rotate += 48) % 360, step, 280);
                Paint paint2 = new Paint();
                canvas2.drawBitmap(faces.get(face_pos), matrix, paint2);
                holder.unlockCanvasAndPost(canvas2);
            }
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

   public  void exitThread(){
        running=false;
    }

}
