package mars.all.activity.three.three;

import mars.all.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;


/**
 * 
 * 描述   圆环进度条
 * @author Mars zhang
 * @created 2016-4-15 下午3:31:58
 */
public class CircleProgressBarView extends View {
    private int mfirstColor;

    private int msecondColor;

    /** 圆环宽度 */
    private int mCirclewidth;

    private int mspeed;

    private final int DEFAULT_SPEED = 20;

    private int mPregress = 0;

    public boolean isNext = true;

    private Paint mPaint;

    public CircleProgressBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressBarView(Context context) {
        this(context, null);
    }

    /**
     * 
     * 描述 获取自定义属性
     * 
     * @author Mars zhang
     * @created 2016-4-15 上午9:13:05
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public CircleProgressBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray mtypedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircleProgressBar,
                defStyleAttr, 0);

        int typeCount = mtypedArray.getIndexCount();

        for (int i = 0; i < typeCount; i++) {
            int attr = mtypedArray.getIndex(i);

            switch (attr) {
                case R.styleable.CircleProgressBar_firstColor:
                    mfirstColor = mtypedArray.getColor(attr, Color.RED);
                    break;
                case R.styleable.CircleProgressBar_secondColor:
                    msecondColor = mtypedArray.getColor(attr, Color.GREEN);
                    break;
                case R.styleable.CircleProgressBar_circleWidth:
                    mCirclewidth = mtypedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CircleProgressBar_speed:
                    mspeed = mtypedArray.getInt(attr, DEFAULT_SPEED);
                    break;
                default:
                    break;
            }

        }

        // 回收TypedArray，以便后面重用。在调用这个函数后，你就不能再使用这个TypedArray。
        mtypedArray.recycle();// 一定要加
        mPaint = new Paint();
        // 创建绘图线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (mPregress == 360) {
                        mPregress = 0;
                        if (!isNext) {
                            isNext = true;
                        } else {
                            isNext = false;
                        }
                    } 
                    mPregress++;
                    postInvalidate();// /因为在子线程中所以不能用invalidate(); 刷新View
                    try {
                        Thread.sleep(mspeed);// 睡眠几毫秒
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 
     * 描述 重写 ondraw 每调一次postInvalidate就会调一次onDraw
     * 
     * @author Mars zhang
     * @created 2016-4-15 上午10:50:33
     * @param canvas
     * @see android.view.View#onDraw(android.graphics.Canvas)
     */
    @Override
    protected void onDraw(Canvas canvas) {

        int center = getWidth() / 2;

        int radius = center - mCirclewidth / 2;

        mPaint.setStrokeWidth(mCirclewidth);// 设置圆环宽度

        mPaint.setAntiAlias(true); // 消除锯齿

        mPaint.setStyle(Paint.Style.STROKE);// 设置空心
        // // 用于定义的圆弧的形状和大小的界限
        RectF rectF = new RectF(center - radius, center - radius, center + radius, center + radius);
        
        if(isNext){
            mPaint.setColor(mfirstColor); 
            canvas.drawCircle(center, center, radius, mPaint);//画出整个园环
            mPaint.setColor(msecondColor);
            canvas.drawArc(rectF, -90, mPregress, false, mPaint);
        }else{
            mPaint.setColor(msecondColor); 
            canvas.drawCircle(center, center, radius, mPaint);//画出整个园环
            mPaint.setColor(mfirstColor);
            canvas.drawArc(rectF, -90, mPregress, false, mPaint);
        }
        
        //http://blog.csdn.net/lmj623565791/article/details/24529807
        
    }
}
