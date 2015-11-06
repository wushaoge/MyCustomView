package customview.wsgmac.com.mycustomview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.lidroid.xutils.util.LogUtils;

import customview.wsgmac.com.mycustomview.R;

/**
 * 心电图
 */
public class ECGView extends View {

    private Paint mPaint; //画笔
    private Path mPath; //路径对象


    private int screenW,screenH; //屏幕宽高
    private float x,y; //路径初始坐标
    private float transX,moveX; //画布移动的距离

    private boolean isCanvasMove; //画布是否需要平移

    private boolean isUpDown = false;

    private int speed = 30; //绘制心电图的紧凑程度  两个点间距

    private int drawSpeed = 100; //绘制速率


    public ECGView(Context context) {
        super(context);
    }

    public ECGView(Context context, AttributeSet attrs) {
        super(context, attrs);

        /**
         * 实例化画笔并设置属性
         */
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(5);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setShadowLayer(2, 0, 0, Color.GREEN);

        mPath = new Path();
        transX = 0;
        isCanvasMove = false;



    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        /**
         * 获取屏幕宽高
         */
        screenW = w;
        screenH = h;

        /**
         * 设置起点坐标
         */
        x = 0;
        y = (screenH / 2);


        //初始X轴坐标
        moveX = (screenW/24);

        mPath.moveTo(x,y);

        LogUtils.e("onSizeChanged");

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        setLayerType(LAYER_TYPE_SOFTWARE, null);

        canvas.drawColor(Color.WHITE);

        mPath.lineTo(x, y);

        //canvas.drawText("测试",screenW/2,screenH/2+50,mPaint);


        //向左平移画布
        canvas.translate(-transX, 0);

        //计算坐标
        calCoors();

        //绘制路径
        canvas.drawPath(mPath,mPaint);


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Thread.sleep(40);
//            }
//        }).start();

        try {
            Thread.sleep(drawSpeed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        invalidate();

    }


    /**
     * 计算坐标
     */
    private void calCoors(){
//        if(isCanvasMove = true){
//            transX += 2;
//        }

        y = (screenH / 2);

        isUpDown = !isUpDown;

        int random = (int)(Math.random()*200+1);


        x += speed;

        if(random>=100){
            if(isUpDown){
                y += random;
            }else{
                y -= random;
            }
        }





        if(x > screenW/3*2){
            transX += speed;
        }





    }


}
