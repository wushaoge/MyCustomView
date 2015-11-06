package customview.wsgmac.com.mycustomview.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by wushaoge on 15/9/24.
 */
public class Customview1 extends View implements Runnable{


    private Paint mPaint;
    private Context  mContext;
    private int width = 0;
    private int height = 0;

    private int radiu = 0; //圆环半径


    public Customview1(Context context) {
        super(context);
        mContext = context;
    }

    public Customview1(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

//        WindowManager wm = ((Activity)mContext).getWindowManager();
//        width = wm.getDefaultDisplay().getWidth();
//        height = wm.getDefaultDisplay().getHeight();


        DisplayMetrics dm = new DisplayMetrics();
        ((Activity)mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;//宽度
        height = dm.heightPixels ;//高度



        initPaint();

    }





    /**
     * 初始化画笔
     */
    private void initPaint(){
        //实例化画笔并打开抗锯齿
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


        /**
         * 画笔样式分三种:
         * 1.Paint.Style.STROKE:描边
         * 2.Paint.Style.FILL_AND_STROKE:描边并填充
         * 3.Paint.Style.Fill:填充
         */
        mPaint.setStyle(Paint.Style.STROKE);

        //设置画笔颜色为浅灰色
        mPaint.setColor(Color.LTGRAY);

        /**
         * 设置描边的粗细,单位:像素px
         * 注意:当setStrokeWidth(0)的时候描边宽度并不为0而是只占一个像素
         */
        mPaint.setStrokeWidth(10);


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(width / 2, height / 2, radiu, mPaint);

        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.RED);

        //canvas.drawText("测试", width / 2, height / 2, textPaint);
        //canvas.drawLine(width/2,0,width/2,height/2,textPaint);

        //textPaint.setColor(Color.GREEN);
        //canvas.drawLine(width/2,height/2,width/2,height,textPaint);



    }


    @Override
    public void run() {
        //确保线程不断执行刷新界面
        while (true) {
            if(radiu <= 200){
                radiu += 10;

                //刷新VIEW
                postInvalidate();
            }else{
                radiu = 0;
            }

            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }
}
