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
 * ����   Բ��������
 * @author Mars zhang
 * @created 2016-4-15 ����3:31:58
 */
public class CircleProgressBarView extends View {
    private int mfirstColor;

    private int msecondColor;

    /** Բ����� */
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
     * ���� ��ȡ�Զ�������
     * 
     * @author Mars zhang
     * @created 2016-4-15 ����9:13:05
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

        // ����TypedArray���Ա�������á��ڵ��������������Ͳ�����ʹ�����TypedArray��
        mtypedArray.recycle();// һ��Ҫ��
        mPaint = new Paint();
        // ������ͼ�߳�
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
                    postInvalidate();// /��Ϊ�����߳������Բ�����invalidate(); ˢ��View
                    try {
                        Thread.sleep(mspeed);// ˯�߼�����
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
     * ���� ��д ondraw ÿ��һ��postInvalidate�ͻ��һ��onDraw
     * 
     * @author Mars zhang
     * @created 2016-4-15 ����10:50:33
     * @param canvas
     * @see android.view.View#onDraw(android.graphics.Canvas)
     */
    @Override
    protected void onDraw(Canvas canvas) {

        int center = getWidth() / 2;

        int radius = center - mCirclewidth / 2;

        mPaint.setStrokeWidth(mCirclewidth);// ����Բ�����

        mPaint.setAntiAlias(true); // �������

        mPaint.setStyle(Paint.Style.STROKE);// ���ÿ���
        // // ���ڶ����Բ������״�ʹ�С�Ľ���
        RectF rectF = new RectF(center - radius, center - radius, center + radius, center + radius);
        
        if(isNext){
            mPaint.setColor(mfirstColor); 
            canvas.drawCircle(center, center, radius, mPaint);//��������԰��
            mPaint.setColor(msecondColor);
            canvas.drawArc(rectF, -90, mPregress, false, mPaint);
        }else{
            mPaint.setColor(msecondColor); 
            canvas.drawCircle(center, center, radius, mPaint);//��������԰��
            mPaint.setColor(mfirstColor);
            canvas.drawArc(rectF, -90, mPregress, false, mPaint);
        }
        
        //http://blog.csdn.net/lmj623565791/article/details/24529807
        
    }
}
