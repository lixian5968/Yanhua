package com.yjmfortune.motherhappynewyear;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixian on 2015/10/22.
 */
public class YjmYanhua extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    int screen_width = 0;
    int screen_height = 0;
    Bitmap backGroundBitmap;
    Paint mPaint;
    List<MyYanhuaBean> listBean = new ArrayList<>();
    Context ct;
    int myleft = 90;
    int leftPage = 250;
    int speed = 35;
    int mPlayID = 0;
    int trail = 0;
    int endY = 100;
    int subY = 50;
    int[] fw1 = new int[]{R.drawable.fw_01,
            R.drawable.fw_02, R.drawable.fw_03, R.drawable.fw_04,
            R.drawable.fw_05, R.drawable.fw_06, R.drawable.fw_07,
            R.drawable.fw_08, R.drawable.fw_09, R.drawable.fw_10,
            R.drawable.fw_11, R.drawable.fw_12, R.drawable.fw_13};

    List<Bitmap> Bitmapsmfw1 = new ArrayList<>();


    int[] fw2 =  new int[] { R.drawable.fw2_01, R.drawable.fw2_02,
            R.drawable.fw2_03, R.drawable.fw2_04,
            R.drawable.fw2_05, R.drawable.fw2_06,
            R.drawable.fw2_07, R.drawable.fw2_08,
            R.drawable.fw2_09, R.drawable.fw2_10,
            R.drawable.fw2_11 };
    List<Bitmap> Bitmapsmfw2 = new ArrayList<>();


    int[] fw3 =  new int[] {
            R.drawable.fw3_01, R.drawable.fw3_02,
            R.drawable.fw3_03, R.drawable.fw3_04,
            R.drawable.fw3_05, R.drawable.fw3_06,
            R.drawable.fw3_07, R.drawable.fw3_08,
            R.drawable.fw3_09, R.drawable.fw3_10,
            R.drawable.fw3_11, R.drawable.fw3_12,
            R.drawable.fw3_13, R.drawable.fw3_14 };
    List<Bitmap> Bitmapsmfw3 = new ArrayList<>();


    int[] fw4 =  new int[] { R.drawable.fw4_01, R.drawable.fw4_02,
            R.drawable.fw4_03, R.drawable.fw4_04,
            R.drawable.fw4_05, R.drawable.fw4_06,
            R.drawable.fw4_07, R.drawable.fw4_08,
            R.drawable.fw4_09, R.drawable.fw4_10,
            R.drawable.fw4_11, R.drawable.fw4_12,
            R.drawable.fw4_13 };
    List<Bitmap> Bitmapsmfw4 = new ArrayList<>();

    int[] fw5 =  new int[] { R.drawable.fw5_01, R.drawable.fw5_02,
            R.drawable.fw5_03, R.drawable.fw5_04,
            R.drawable.fw5_05, R.drawable.fw5_06,
            R.drawable.fw5_07, R.drawable.fw5_08,
            R.drawable.fw5_09, R.drawable.fw5_10,
            R.drawable.fw5_11, R.drawable.fw5_12,
            R.drawable.fw5_13, R.drawable.fw5_14,
            R.drawable.fw5_15 };
    List<Bitmap> Bitmapsmfw5 = new ArrayList<>();



    int[] mTrail = new int[]{R.drawable.trail1,
            R.drawable.trail2, R.drawable.trail3, R.drawable.trail4,
            R.drawable.trail5, R.drawable.trail6};
    List<Bitmap> BitmapsmTrail = new ArrayList<>();



    int AllYanhua =10;



    private SurfaceHolder holder;
    private Thread th;

    boolean flag = true;
    private Canvas canvas;

    public YjmYanhua(Context context, AttributeSet attrs) {
        super(context, attrs);
        ct = context;
        holder = this.getHolder();
        holder.addCallback(this);



    }

    public YjmYanhua(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public YjmYanhua(Context context) {
        super(context);
        ct = context;
        holder = this.getHolder();
        holder.addCallback(this);



    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {




        //屏幕的宽度
        screen_width = this.getWidth();
        screen_height = this.getHeight();

        for(int i=0;i<fw1.length;i++){
            Bitmapsmfw1.add(ReadBitMap(ct,fw1[i]));
        }
        for(int i=0;i<fw2.length;i++){
            Bitmapsmfw2.add(ReadBitMap(ct,fw2[i]));
        }
        for(int i=0;i<fw3.length;i++){
            Bitmapsmfw3.add(ReadBitMap(ct,fw3[i]));
        }
        for(int i=0;i<fw4.length;i++){
            Bitmapsmfw4.add(ReadBitMap(ct,fw4[i]));
        }
        for(int i=0;i<fw5.length;i++){
            Bitmapsmfw5.add(ReadBitMap(ct,fw5[i]));
        }


        for(int i=0;i<mTrail.length;i++){
            BitmapsmTrail.add(ReadBitMap(ct,mTrail[i]));
        }
        backGroundBitmap = ReadBitMap(ct, R.drawable.bg);
        backGroundBitmap = resizeImage(backGroundBitmap, screen_width, screen_height);

        mPaint = new Paint();

        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);
        int color = 0xff000000 | red << 16 | green << 8 | blue;
        mPaint.setColor(color);

        for (int i = 0; i <AllYanhua; i++) {
//            listBean.add(new MyYanhuaBean());
//            (int mPlayID, int x, int y, int trail, boolean open, int endY)
            listBean.add(new MyYanhuaBean(
                    mPlayID,
                    myleft + (screen_width - leftPage) * i / AllYanhua,
                    (int) ((screen_height / 3) * (1.5 + Math.random())),
                    trail,
                    true,
                    80 + (int) ((screen_height / 3) * (Math.random())),
                    i%5+1));
        }
//        InitListBean();
        flag = true;
        th = new Thread(this);
        th.start();

    }

    public void InitListBean() {
        if (listBean != null && listBean.size() > 0) {
            for (int i = 0; i < listBean.size(); i++) {
                MyYanhuaBean bean = listBean.get(i);
                bean.mPlayID = mPlayID;
                bean.x = myleft + (screen_width - leftPage) * i / listBean.size();
                bean.trail = trail;
                bean.open = true;
                bean.y = (int) ((screen_height / 3) * (1.5 + Math.random()));
                bean.endY = 80 + (int) ((screen_height / 3) * (Math.random()));
            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag = false;
    }

    @Override
    public void run() {

        while (flag) {
            long start = System.currentTimeMillis();
            logic();
            draw();
            long end = System.currentTimeMillis();
            try {
                if (end - start < 50) {
                    Thread.sleep(50 - (end - start));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void logic() {
        synchronized (listBean) {
            if (listBean.size() > 0) {
                for (int i = 0; i < listBean.size(); i++) {
                    MyYanhuaBean bean = listBean.get(i);
                    if (bean.y > bean.endY) {
                        bean.y = bean.y - speed;
                        bean.trail = bean.trail + 1;
                    } else {
                        if(bean.type==1){
                            if (bean.mPlayID < fw1.length - 1) {
                                bean.mPlayID = bean.mPlayID + 1;
                            } else {
                                bean.open = false;
                            }
                        }else  if(bean.type==2){
                            if (bean.mPlayID < fw2.length - 1) {
                                bean.mPlayID = bean.mPlayID + 1;
                            } else {
                                bean.open = false;
                            }
                        }else  if(bean.type==3){
                            if (bean.mPlayID < fw3.length - 1) {
                                bean.mPlayID = bean.mPlayID + 1;
                            } else {
                                bean.open = false;
                            }
                        }else  if(bean.type==4){
                            if (bean.mPlayID < fw4.length - 1) {
                                bean.mPlayID = bean.mPlayID + 1;
                            } else {
                                bean.open = false;
                            }
                        }else  if(bean.type==5){
                            if (bean.mPlayID < fw5.length - 1) {
                                bean.mPlayID = bean.mPlayID + 1;
                            } else {
                                bean.open = false;
                            }
                        }

                    }
                }
            }
        }
    }


    private void draw() {
        try {
            canvas = holder.lockCanvas();
            if (canvas != null) {
                canvas.drawColor(Color.BLACK);
//                canvas.drawBitmap(backGroundBitmap, 0, 0, null);
                synchronized (holder) {
                    synchronized (listBean) {
                        for (int i = 0; i < listBean.size(); i++) {
                            MyYanhuaBean bean = listBean.get(i);
                            if (bean.open) {
                                if (bean.y > bean.endY) {
                                    canvas.drawBitmap(BitmapsmTrail.get(bean.trail % mTrail.length), (float) bean.x, (float) bean.y, mPaint);
                                } else {
                                    Bitmap myBit = null;
                                    if(bean.type==1){
                                        myBit = Bitmapsmfw1.get(bean.mPlayID);
                                    }else  if(bean.type==2){
                                        myBit = Bitmapsmfw2.get(bean.mPlayID);
                                    }else  if(bean.type==3){
                                        myBit = Bitmapsmfw3.get(bean.mPlayID);
                                    }else  if(bean.type==4){
                                        myBit = Bitmapsmfw4.get(bean.mPlayID);
                                    }else  if(bean.type==5){
                                        myBit = Bitmapsmfw5.get(bean.mPlayID);
                                    }
                                    canvas.drawBitmap(myBit, (float) bean.x - myBit.getWidth() / 2,
                                            (float) bean.y - myBit.getHeight() / 2, mPaint);
                                }
                            } else {
                                bean.mPlayID = mPlayID;
                                bean.x = myleft + (screen_width - leftPage) * i / listBean.size();
                                bean.trail = trail;
                                bean.open = true;
                                bean.y = (int) ((screen_height / 3) * (1.5 + Math.random()));
                                bean.endY = 80 + (int) ((screen_height / 3) * (Math.random()));
                            }
                        }

                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (canvas != null)
                holder.unlockCanvasAndPost(canvas);
        }
    }

    public Bitmap ReadBitMap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.ARGB_8888;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        // 获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }

    public Bitmap resizeImage(Bitmap mBitmap, int w, int h) {
        Bitmap BitmapOrg = mBitmap;
        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();
        int newWidth = w;
        int newHeight = h;
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap tmp = Bitmap.createBitmap(BitmapOrg, 0, 0, width, height,
                matrix, true);
        return tmp;
    }


}
