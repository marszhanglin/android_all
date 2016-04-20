package mars.all.activity.three.three;

import mars.all.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 * 
 * ���� Բ������
 * 
 * @author Mars zhang
 * @created 2016-4-15 ����3:36:52
 */
public class CircleVolumeView extends View {

    private int mfirstColor;
    private int msecondColor;
    private int mcircleWidth;
    private int mdotCount;
    private int msplitSize;
    private Bitmap mBitmap;
    private Paint mPaint;
    private Rect mRect;
    private Rect titleRect;
    /** 
     * ��ǰ���� 
     */  
    private int mCurrentCount = 3;
    public CircleVolumeView(Context context) {
        this(context, null);
    }

    public CircleVolumeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleVolumeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray mTypedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircleVolume,
                defStyleAttr, 0);

        int count = mTypedArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr = mTypedArray.getIndex(i);
            switch (attr) {
                case R.styleable.CircleVolume_firstColor4:
                    mfirstColor = mTypedArray.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CircleVolume_secondColor4:
                    msecondColor = mTypedArray.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CircleVolume_circleWidth4:
                    mcircleWidth = mTypedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CircleVolume_dotCount4:
                    mdotCount = mTypedArray.getInt(attr, 20);
                    break;
                case R.styleable.CircleVolume_splitSize4:
                    msplitSize = mTypedArray.getInt(attr, 20);
                    break;
                case R.styleable.CircleVolume_bg4:
                    mBitmap = BitmapFactory.decodeResource(getResources(), mTypedArray.getResourceId(attr, 0));
                    break;
                default:
                    break;
            }
        }
        mTypedArray.recycle();
        mPaint = new Paint();
        mRect = new Rect();
    }
    
    
    @Override
    protected void onDraw(Canvas canvas)
    {
        mPaint.setAntiAlias(true); // �������
        mPaint.setStrokeWidth(mcircleWidth); // ����Բ���Ŀ��
        mPaint.setStrokeCap(Paint.Cap.BUTT); // �����߶ζϵ���״Ϊ  ԲͷROUND    ƽͷBUTT     SQUARE
        mPaint.setAntiAlias(true); // �������
        mPaint.setStyle(Paint.Style.STROKE); // ���ÿ���
        int centre = getWidth() / 2; // ��ȡԲ�ĵ�x����
        int radius = centre - mcircleWidth / 2;// �뾶
        /**
         * �����ȥ
         */
        drawOval(canvas, centre, radius);

        /**
         * �������������ε�λ��
         */
        int relRadius = radius - mcircleWidth / 2;// �����Բ�İ뾶
        /**
         * ���������εľ��붥�� = mCircleWidth + relRadius - ��2 / 2
         */
        mRect.left = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius) + mcircleWidth;
        /**
         * ���������εľ������ = mCircleWidth + relRadius - ��2 / 2
         */
        mRect.top = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius) + mcircleWidth;
        mRect.bottom = (int) (mRect.left + Math.sqrt(2) * relRadius);
        mRect.right = (int) (mRect.left + Math.sqrt(2) * relRadius);

        /**
         * ���ͼƬ�Ƚ�С����ô����ͼƬ�ĳߴ���õ�������
         */
        if (mBitmap.getWidth() < Math.sqrt(2) * relRadius)
        {
            mRect.left = (int) (mRect.left + Math.sqrt(2) * relRadius * 1.0f / 2 - mBitmap.getWidth() * 1.0f / 2);
            mRect.top = (int) (mRect.top + Math.sqrt(2) * relRadius * 1.0f / 2 - mBitmap.getHeight() * 1.0f / 2);
            mRect.right = (int) (mRect.left + mBitmap.getWidth());
            mRect.bottom = (int) (mRect.top + mBitmap.getHeight());

        }
        // ��ͼ
        canvas.drawBitmap(mBitmap, null, mRect, mPaint);
        
        //��������
      //����������ο�
        mPaint.setStyle(Style.FILL);//���ֲ��ܿ���
        mPaint.setTextSize(25);//�������ִ�С
        mPaint.setColor(Color.GRAY);
        titleRect = new Rect();
        //���ݻ��ʡ����ݡ���ʼ����λ�á����þ��εĿ��;
        String titlestr="������"+(int)(mCurrentCount/(mdotCount*1.0f)*100);
        mPaint.getTextBounds(titlestr, 0, titlestr.length(), titleRect); 
        canvas.drawText(titlestr, (getWidth()-titleRect.right)/2, (int)((getHeight()+titleRect.top)*0.80), mPaint);
    }

    /**
     * ���ݲ�������ÿ��С��
     * 
     * @param canvas
     * @param centre
     * @param radius
     */
    private void drawOval(Canvas canvas, int centre, int radius)
    {
        /**
         * ������Ҫ���ĸ����Լ���϶����ÿ�������ռ�ı���*360
         */
        float itemSize = (270 * 1.0f - mdotCount * msplitSize) / mdotCount;
        
        radius=(int) (radius*0.618);
        
        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius); // ���ڶ����Բ������״�ʹ�С�Ľ���

        mPaint.setColor(mfirstColor); // ����Բ������ɫ
        for (int i = 0; i < mdotCount; i++)
        {
            // 1  2�����￪ʼ��   3�����ٶ�   4   5����          x--->��ʾ0��     y���±�ʾ90��      ����Ҫ������0��45 135��360   ���нǶȼ���90�Ͷ���  ������ʽ����90���񻹲���  ֻ����������
            canvas.drawArc(oval, i * (itemSize + msplitSize)+140, itemSize, false, mPaint); // ���ݽ��Ȼ�Բ��
        }

        mPaint.setColor(msecondColor); // ����Բ������ɫ 
        for (int i = 0; i < mCurrentCount; i++)
        {
            canvas.drawArc(oval, i * (itemSize + msplitSize)+140, itemSize, false, mPaint); // ���ݽ��Ȼ�Բ��
        }
    }

    /**
     * ��ǰ����+1
     */
    public void up()
    {
        
        mCurrentCount++;
        if(mCurrentCount>mdotCount){
            mCurrentCount=mdotCount;
            return;
        }
        postInvalidate();
    }

    /**
     * ��ǰ����-1
     */
    public void down()
    {
        
        mCurrentCount--;
        if(mCurrentCount<0){
            mCurrentCount=0;
            return ;
        }
        
        postInvalidate();
    }

    private int xDown, xUp;

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

        switch (event.getAction())
        {
        case MotionEvent.ACTION_DOWN:
            xDown = (int) event.getY();
            break;

        case MotionEvent.ACTION_UP:
            xUp = (int) event.getY();
//            System.out.println(xUp+"    "+xDown);   �й���������Ͳ�������
            if (xUp > xDown)// �»�
            { 
                int distance = (xUp -xDown)/50; 
                mCurrentCount-=distance;
                if(mCurrentCount<=0){
                    mCurrentCount=1;
                }
                down();
            } 
            if(xUp < xDown)
            { 
                int distance = (xDown -xUp)/50; 
                mCurrentCount+=distance;
                if(mCurrentCount>=mdotCount){
                    mCurrentCount=mdotCount-1;
                }
                up();
            }
            break;
        }

        return true;
    }
}
