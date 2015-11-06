package customview.wsgmac.com.mycustomview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.lidroid.xutils.util.LogUtils;

import customview.wsgmac.com.mycustomview.R;

/**
 * 倒水
 */
public class WaveView extends View {

    private Path mPath; //路径对象
    private Paint mPaint; //画笔对象


    private int vWidth,vHeight; //控件宽高
    private float ctrX,ctrY; //控制点的xy坐标
    private float waveY; //整个wave顶部两端点的y坐标,该坐标与控制点的y坐标增减幅一致

    private boolean isInc; //判断控制点是该右移还是左移

    private boolean isUpDown = true; //漏水还是注水 true是漏水  false是注水

    public WaveView(Context context) {
        super(context);
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //实例化画笔并设置参数
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        mPaint.setColor(context.getResources().getColor(R.color.lightgreen));

        //实例化路径对象
        mPath = new Path();

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        vWidth = w;
        vHeight = h;

        //计算控制点Y坐标
        waveY = 1/8f * vHeight;
//        waveY = 0;

        //计算端点Y坐标
        ctrY = -1/16f * vHeight;

        LogUtils.e("waveY" + waveY);
        LogUtils.e("ctrY" + ctrY);

    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        /**
         * 设置path起点
         * 注意我将path的起点设置在了控件的外部看不到的区域
         * 如果我们将起点设置在控件左端x=0的位置会使得贝塞尔曲线变得生硬
         * 至于为什么为什么刚才我已经说了
         * 所以我们稍微让起点往外走一点
         */
        mPath.moveTo(-1 / 4f * vWidth, waveY);

        /**
         * 以二阶曲线的方式通过控制点连接位于控件右边的终点
         * 终点的位置也是在控件外部
         * 我们只需不断让ctrX的大小变化即可实现波浪的效果
         */
        mPath.quadTo(ctrX, ctrY, vWidth + 1 / 4f * vWidth, waveY);


        //围绕控件闭合曲线
        mPath.lineTo(vWidth + 1 /4f * vWidth, vHeight);
        mPath.lineTo(-1 / 4f * vWidth, vHeight);
        mPath.close();;

        canvas.drawPath(mPath, mPaint);

        /**
         * 当控制点的x坐标大于或等于终点x坐标时更改标示值
         */
        if(ctrX >= vWidth + 1 / 4f * vWidth){
            isInc = false;
        }

        /**
         * 当控制点的x坐标小于或等于起点x坐标时更改标示值
         */
        if(ctrX <= -1 /4f * vWidth){
            isInc = true;
        }

        //根据标示值判断当前的控制点x坐标是改加还是减
        ctrX = isInc ? ctrX + 20 : ctrX - 20;


        //LogUtils.e(ctrY+"---"+vHeight);
        if(isUpDown&&ctrY <= vHeight){
            isUpDown = true;
            ctrY += 4;
            waveY += 4;
        }else{
            if(ctrY<=0){
                isUpDown = true;
            }else{
                isUpDown = false;
                ctrY -= 4;
                waveY -= 4;
            }
        }


        //实例化画笔并打开抗锯齿
        Paint mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint2.setStyle(Paint.Style.STROKE);
        //设置画笔颜色为浅灰色
        mPaint2.setColor(Color.WHITE);
        mPaint2.setTextSize(70);
        canvas.drawText("只是测试一哈而已",vWidth/2,vHeight/2,mPaint2);

        mPath.reset();

        //重绘
        invalidate();


    }



}
