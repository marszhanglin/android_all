package mars.all.activity.three.two;

import mars.all.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * 
 * 描述  http://blog.csdn.net/lmj623565791/article/details/24300125
 * 
 * 1、自定义View的属性
    2、在View的构造方法中获得我们自定义的属性
    [ 3、重写onMesure ]
    4、重写onDraw
 * @author Mars zhang
 * @created 2016-2-29 下午9:05:18
 */
public class CustomImageView extends View { 

    /** 画笔 */
    Paint mPaint = null;
    /** 最外层矩形 */
    Rect rect = null;
    /** 文字标题矩形 */
    Rect titleRect = null; 
    /** 标题内容 */
    String titleStr = "";
    /** 标题字体大小 */
    int titleSize ;
    /** 标题颜色 */
    int titleColor;
    /** 图片资源bitmap */
    Bitmap bitmap = null; //Drawable drawable = null;
    /** 图片资源id */
    int imageResourceId ;
    /** 图片缩放类型 */
    int scaleType;final int  FILLXY=0,CENTER=1,SCALECENTER=2; 
    
    /** 控件宽度 */
    int customImageViewWidth = 100;//默认UNSPECIFIED用100
    /** 控件高度 */
    int customImageViewHeight = 100;//默认UNSPECIFIED用100
    
    
    
    public CustomImageView(Context context) {
        this(context,null);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    //1、2、在三个构造函数中获取自定义参数值
    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义属性数组
        TypedArray typedArray =context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.CustomImageView, defStyleAttr, 0);
        //获取自定义属性长度
        int typeCount = typedArray.getIndexCount();
        //遍历自定义属性数组   其实不用遍历直接获取就好了
        for (int i = 0; i < typeCount; i++) {
          //获取某个自定义属性id
            int arr = typedArray.getIndex(i);
            switch (arr) {
                case R.styleable.CustomImageView_titleText://标题内容的id string
                    titleStr = typedArray.getString(arr);  
                    break;
                case R.styleable.CustomImageView_titleTextSize://标题字体大小   dimension？
                    //默认为16sp  但是paint收的是px 所以要将sp转成px
                    titleSize = typedArray.getDimensionPixelSize(arr, 
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP , 16, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomImageView_titleTextColor://标题字体颜色   color？
                    //获取标题颜色      默认#77a121
                    titleColor = typedArray.getColor(arr, Color.parseColor("#77a121"));
                    break;
                case R.styleable.CustomImageView_image://获取图片资源  reference  2种方式那个好
                    bitmap = BitmapFactory.decodeResource(getResources(), typedArray.getResourceId(arr, 0));//最多200张
//                    drawable = getResources().getDrawable(typedArray.getResourceId(arr, R.drawable.ic_launcher));//能到1000张
                    //imageResourceId = typedArray.getResourceId(arr, R.drawable.ic_launcher);
                    break;
                case R.styleable.CustomImageView_imageScaleType://获取图片缩放类型    enum
                    scaleType = typedArray.getInt(arr, 0);
                  break;
                default:
                    break; 
            }
        }
        //回收TypedArray，以便后面重用。在调用这个函数后，你就不能再使用这个TypedArray。
        typedArray.recycle();//一定要加
        
        //创建画笔
        mPaint = new Paint();
        //给画笔配置大小
        mPaint.setTextSize(titleSize);
        //创建外框矩形框
        rect = new Rect();
        //创建标题矩形框
        titleRect = new Rect();
        //根据画笔、内容、起始结束位置、设置矩形的宽度;
        mPaint.getTextBounds(titleStr, 0, titleStr.length(), titleRect);
    }
    
    
    //3、重写测量  设置CustomImageView宽高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        
        /**
         * 高度   heightMeasureSpec由Mode与Size组成
         */
        //获取高度的 数值模式   
        int  heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //获取高度的值
        int  heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //判断高度的模式  EXACTLY就用heightSize  AT_MOST就用图片与标题的高度和
        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                customImageViewHeight = heightSize;
                break;
            case MeasureSpec.AT_MOST:
                //获取图片高度及标题高度
                customImageViewHeight= getPaddingTop()+bitmap.getHeight()+titleRect.height()+getPaddingBottom();
                break;
            default:
                break;
        }
        
        /**
         * 宽度   heightMeasureSpec由Mode与Size组成
         */
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        // 判断模式 EXACTLY就用widthSize AT_MOST就用图片与标题的最大高度
        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                customImageViewWidth = widthSize;
                break;
            case MeasureSpec.AT_MOST:
                customImageViewWidth = getPaddingTop() + Math.max(bitmap.getWidth(), titleRect.width())
                        + getPaddingBottom();
                break;
            default:
                break;
        }
        
        
        //最后设置控件宽高  每个View都有setMeasuredDimension    所以在ViewGroup的onmeasure()中还有对子view的设置宽高
        setMeasuredDimension(customImageViewWidth, customImageViewHeight);
    }
    
    //4、
    @Override
    protected void onDraw(Canvas canvas) {
        //边框颜色
        mPaint.setColor(titleColor);
        mPaint.setStrokeWidth(5);//设置边框的宽度
        mPaint.setStyle(Paint.Style.STROKE);//适用画边框
        //在画板上绘制边框
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        
        
      //绘制图片
        switch (scaleType) {
            case FILLXY:
                canvas.drawBitmap(bitmap, getPaddingLeft(), getPaddingTop(), mPaint);//傻瓜似的显示在右上角
                break;
            case CENTER:
                canvas.drawBitmap(bitmap, 
                        customImageViewWidth/2-getPaddingLeft()-bitmap.getWidth()/2, 
                        customImageViewHeight/2-getPaddingTop()-bitmap.getHeight()/2, mPaint);
                break;
            case SCALECENTER:
                
                break;
            default:
                break;
        }
        
        
        mPaint.setStyle(Style.FILL);//设置边框的宽度   适用画文本
        //当前标题框的宽度小于需要的宽度时，xxx...   虽然在onMeasure有计算  但是有可能重onDraw
        if(titleRect.width()>customImageViewWidth){
            //创建文字画笔  获取xxx...
            TextPaint textPaint = new TextPaint(mPaint);
            String msg = (String) TextUtils.ellipsize(titleStr, textPaint,
                    customImageViewWidth-getPaddingLeft()+getPaddingRight(), TextUtils.TruncateAt.END);
            
            //绘制标题
            canvas.drawText(msg, getPaddingLeft(), getPaddingBottom(), mPaint);
        }else{ //正常情况绘制在中心 
          //绘制标题      绘制文本      文本左边位置      基线位置    画笔
            canvas.drawText(titleStr, (customImageViewWidth-getPaddingRight()-getPaddingLeft())/2-titleRect.width()/2+getPaddingLeft(),customImageViewHeight*5/6, mPaint);
        }
        
        
        
        
    }

}
