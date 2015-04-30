package com.wander.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import com.wander.wdTrip.R;

public class BitmapUtils {
    static int[] tent = new int[]{R.drawable.tentacles_1, R.drawable.tentacles_2, R.drawable.tentacles_3, R.drawable.tentacles_4, R.drawable.tentacles_5,
            R.drawable.tentacles_6, R.drawable.tentacles_7, R.drawable.tentacles_8, R.drawable.tentacles_9, R.drawable.tentacles_10};

    static int[] face1 = new int[]{R.drawable.face_1_001, R.drawable.face_1_002, R.drawable.face_1_003, R.drawable.face_1_004, R.drawable.face_1_005,
            R.drawable.face_1_006, R.drawable.face_1_007, R.drawable.face_1_008};
    static int[] face2=new int[]{};

    public static List<Bitmap> getFace(Context context, int i) {
        List<Bitmap> list = new ArrayList<Bitmap>();
        switch (i) {
            //游戏过程中的尾巴
            case 0:
                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inSampleSize=4;
                for (int j = 0; j < tent.length; j++) {
                    Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), tent[i], opts);
                    list.add(bitmap);
                }
                break;
            case 1:
                BitmapFactory.Options opts1 = new BitmapFactory.Options();
                opts1.inSampleSize=2;
                for (int j = 0; j < face1.length; j++) {
                    Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), face1[i], opts1);
                    list.add(bitmap);
                }
                break;
            case 2:

                break;
        }

        return list;
    }

    public static List<Bitmap> getLeft(List<Bitmap> list) {
        int size = list.size();
        List<Bitmap> list2=new ArrayList<Bitmap>();
        for (int i = 0; i < size; i++) {
            list2.add(rotateBitmap(135,list.get(i)));
        }
        return list2;
    }

    public static List<Bitmap> getRight(List<Bitmap> list) {
        int size = list.size();
        List<Bitmap> list2 = new ArrayList<Bitmap>();
        for (int i = 0; i < size; i++) {
            list2.add(rotateBitmap(135, list.get(i)));
        }
        return list2;
    }

    /**
     * 获取指定图片文件的缩略图
     *
     * @param fileName  图片文件全路径名
     * @param maxWidth  缩略图最大宽度,0 表示原宽度
     * @param maxHeight 缩略图最大高度,0 表示原高度
     * @return Bitmap 缩略图
     */
    public static Bitmap getBitmapThumbnail(String fileName, int maxWidth, int maxHeight) {
        // 文件名为空或图片文件不存在，则返回null
        if (fileName == null || !new File(fileName).exists()) {
            return null;
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(fileName, options);
        // 获取原图片的宽度和高度
        int oldWidth = options.outWidth;
        int oldHeight = options.outHeight;

        int ratioWidth = maxWidth == 0 ? 1 : oldWidth / maxWidth;
        int ratioHeight = maxHeight == 0 ? 1 : oldHeight / maxHeight;

        options.inSampleSize = ratioHeight > ratioWidth ? ratioHeight : ratioWidth;

        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(fileName, options);
        return bitmap;
    }

    /* 旋转图片
     * @param angle
     * @param bitmap
     * @return Bitmap
     */
    public static Bitmap rotateBitmap(float angle, Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        //旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        // 创建新的图片
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return bitmap;
    }
}
