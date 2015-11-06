package customview.wsgmac.com.mycustomview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.BounceInterpolator;
import android.widget.OverScroller;
import android.widget.Scroller;
import android.widget.TextView;

import com.lidroid.xutils.util.LogUtils;

/**
 * Created by wushaoge on 15/10/14.
 */
public class JellyTextView extends TextView {


    private OverScroller mScroller;

    private float lastX;
    private float lastY;

    private float startX;
    private float startY;


    public JellyTextView(Context context) {
        super(context);
    }

    public JellyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new OverScroller(context, new BounceInterpolator());


    }

    public JellyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = event.getRawX();
                lastY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float disX = event.getRawX() - lastX;
                float disY = event.getRawY() - lastY;

                offsetLeftAndRight((int) disX);
                offsetTopAndBottom((int) disY);

                lastX = event.getRawX();
                lastY = event.getRawY();
                break;
            case MotionEvent.ACTION_UP:


                LogUtils.e("getX=" + getX());
                LogUtils.e("getY=" + getY());

//                LogUtils.e("getRawX="+event.getRawX());
//                LogUtils.e("getRawY="+event.getRawY());

//                dddF
//                ddFF

                LogUtils.e("x差值=" + -((int) getX() - (int) startX));
                LogUtils.e("y差值=" + -((int) getY() - (int) startY));


                mScroller.startScroll((int) getX(), (int) getY(), -((int) getX() - (int) startX), -((int) getY() - (int) startY));

                invalidate();
                break;

        }

        return super.onTouchEvent(event);
    }


    @Override
    public void computeScroll() {
        //LogUtils.e("computeScroll被调用");
        if (mScroller.computeScrollOffset()) {
            setX(mScroller.getCurrX());
            setY(mScroller.getCurrY());
//            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
//            scrollTo(mScroller.getFinalX(),mScroller.getFinalY());
            //LogUtils.e("当前" + mScroller.getCurrX());
            LogUtils.e("当前" + mScroller.getCurrY());
            LogUtils.e("当前" + mScroller.getFinalY());
            invalidate();
        }
        super.computeScroll();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        startX = getX();
        startY = getY();
        LogUtils.e("startX=" + startX);
        LogUtils.e("startY=" + startY);
    }

    public void spingBack() {
        if (mScroller.springBack((int) getX(), (int) getY(), 0, (int) getX(), 0, (int) getY() - 100)) {
            LogUtils.e("getX()=" + getX() + "------getY()=" + getY());
            invalidate();
        }


    }

}
