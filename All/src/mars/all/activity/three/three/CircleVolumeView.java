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
 * 描述 圆环音量
 * 
 * @author Mars zhang
 * @created 2016-4-15 下午3:36:52
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
     * 当前进度 
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
        mPaint.setAntiAlias(true); // 消除锯齿
        mPaint.setStrokeWidth(mcircleWidth); // 设置圆环的宽度
        mPaint.setStrokeCap(Paint.Cap.BUTT); // 定义线段断电形状为  圆头ROUND    平头BUTT     SQUARE
        mPaint.setAntiAlias(true); // 消除锯齿
        mPaint.setStyle(Paint.Style.STROKE); // 设置空心
        int centre = getWidth() / 2; // 获取圆心的x坐标
        int radius = centre - mcircleWidth / 2;// 半径
        /**
         * 画块块去
         */
        drawOval(canvas, centre, radius);

        /**
         * 计算内切正方形的位置
         */
        int relRadius = radius - mcircleWidth / 2;// 获得内圆的半径
        /**
         * 内切正方形的距离顶部 = mCircleWidth + relRadius - √2 / 2
         */
        mRect.left = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius) + mcircleWidth;
        /**
         * 内切正方形的距离左边 = mCircleWidth + relRadius - √2 / 2
         */
        mRect.top = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius) + mcircleWidth;
        mRect.bottom = (int) (mRect.left + Math.sqrt(2) * relRadius);
        mRect.right = (int) (mRect.left + Math.sqrt(2) * relRadius);

        /**
         * 如果图片比较小，那么根据图片的尺寸放置到正中心
         */
        if (mBitmap.getWidth() < Math.sqrt(2) * relRadius)
        {
            mRect.left = (int) (mRect.left + Math.sqrt(2) * relRadius * 1.0f / 2 - mBitmap.getWidth() * 1.0f / 2);
            mRect.top = (int) (mRect.top + Math.sqrt(2) * relRadius * 1.0f / 2 - mBitmap.getHeight() * 1.0f / 2);
            mRect.right = (int) (mRect.left + mBitmap.getWidth());
            mRect.bottom = (int) (mRect.top + mBitmap.getHeight());

        }
        // 绘图
        canvas.drawBitmap(mBitmap, null, mRect, mPaint);
        
        //绘制文字
      //创建标题矩形框
        mPaint.setStyle(Style.FILL);//文字不能空心
        mPaint.setTextSize(25);//设置文字大小
        mPaint.setColor(Color.GRAY);
        titleRect = new Rect();
        //根据画笔、内容、起始结束位置、设置矩形的宽度;
        String titlestr="音量："+(int)(mCurrentCount/(mdotCount*1.0f)*100);
        mPaint.getTextBounds(titlestr, 0, titlestr.length(), titleRect); 
        canvas.drawText(titlestr, (getWidth()-titleRect.right)/2, (int)((getHeight()+titleRect.top)*0.80), mPaint);
    }

    /**
     * 根据参数画出每个小块
     * 
     * @param canvas
     * @param centre
     * @param radius
     */
    private void drawOval(Canvas canvas, int centre, int radius)
    {
        /**
         * 根据需要画的个数以及间隙计算每个块块所占的比例*360
         */
        float itemSize = (270 * 1.0f - mdotCount * msplitSize) / mdotCount;
        
        radius=(int) (radius*0.618);
        
        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius); // 用于定义的圆弧的形状和大小的界限

        mPaint.setColor(mfirstColor); // 设置圆环的颜色
        for (int i = 0; i < mdotCount; i++)
        {
            // 1  2从哪里开始画   3画多少度   4   5画笔          x--->表示0度     y向下表示90度      我们要画的是0到45 135到360   所有角度加上90就对了  不过样式加了90好像还不够  只能慢慢调了
            canvas.drawArc(oval, i * (itemSize + msplitSize)+140, itemSize, false, mPaint); // 根据进度画圆弧
        }

        mPaint.setColor(msecondColor); // 设置圆环的颜色 
        for (int i = 0; i < mCurrentCount; i++)
        {
            canvas.drawArc(oval, i * (itemSize + msplitSize)+140, itemSize, false, mPaint); // 根据进度画圆弧
        }
    }

    /**
     * 当前数量+1
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
     * 当前数量-1
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
//            System.out.println(xUp+"    "+xDown);   有滚动条这个就不管用了
            if (xUp > xDown)// 下滑
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
