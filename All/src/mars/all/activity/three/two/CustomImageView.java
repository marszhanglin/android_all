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
 * ����  http://blog.csdn.net/lmj623565791/article/details/24300125
 * 
 * 1���Զ���View������
    2����View�Ĺ��췽���л�������Զ��������
    [ 3����дonMesure ]
    4����дonDraw
 * @author Mars zhang
 * @created 2016-2-29 ����9:05:18
 */
public class CustomImageView extends View { 

    /** ���� */
    Paint mPaint = null;
    /** �������� */
    Rect rect = null;
    /** ���ֱ������ */
    Rect titleRect = null; 
    /** �������� */
    String titleStr = "";
    /** ���������С */
    int titleSize ;
    /** ������ɫ */
    int titleColor;
    /** ͼƬ��Դbitmap */
    Bitmap bitmap = null; //Drawable drawable = null;
    /** ͼƬ��Դid */
    int imageResourceId ;
    /** ͼƬ�������� */
    int scaleType;final int  FILLXY=0,CENTER=1,SCALECENTER=2; 
    
    /** �ؼ���� */
    int customImageViewWidth = 100;//Ĭ��UNSPECIFIED��100
    /** �ؼ��߶� */
    int customImageViewHeight = 100;//Ĭ��UNSPECIFIED��100
    
    
    
    public CustomImageView(Context context) {
        this(context,null);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    //1��2�����������캯���л�ȡ�Զ������ֵ
    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //��ȡ�Զ�����������
        TypedArray typedArray =context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.CustomImageView, defStyleAttr, 0);
        //��ȡ�Զ������Գ���
        int typeCount = typedArray.getIndexCount();
        //�����Զ�����������   ��ʵ���ñ���ֱ�ӻ�ȡ�ͺ���
        for (int i = 0; i < typeCount; i++) {
          //��ȡĳ���Զ�������id
            int arr = typedArray.getIndex(i);
            switch (arr) {
                case R.styleable.CustomImageView_titleText://�������ݵ�id string
                    titleStr = typedArray.getString(arr);  
                    break;
                case R.styleable.CustomImageView_titleTextSize://���������С   dimension��
                    //Ĭ��Ϊ16sp  ����paint�յ���px ����Ҫ��spת��px
                    titleSize = typedArray.getDimensionPixelSize(arr, 
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP , 16, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomImageView_titleTextColor://����������ɫ   color��
                    //��ȡ������ɫ      Ĭ��#77a121
                    titleColor = typedArray.getColor(arr, Color.parseColor("#77a121"));
                    break;
                case R.styleable.CustomImageView_image://��ȡͼƬ��Դ  reference  2�ַ�ʽ�Ǹ���
                    bitmap = BitmapFactory.decodeResource(getResources(), typedArray.getResourceId(arr, 0));//���200��
//                    drawable = getResources().getDrawable(typedArray.getResourceId(arr, R.drawable.ic_launcher));//�ܵ�1000��
                    //imageResourceId = typedArray.getResourceId(arr, R.drawable.ic_launcher);
                    break;
                case R.styleable.CustomImageView_imageScaleType://��ȡͼƬ��������    enum
                    scaleType = typedArray.getInt(arr, 0);
                  break;
                default:
                    break; 
            }
        }
        //����TypedArray���Ա�������á��ڵ��������������Ͳ�����ʹ�����TypedArray��
        typedArray.recycle();//һ��Ҫ��
        
        //��������
        mPaint = new Paint();
        //���������ô�С
        mPaint.setTextSize(titleSize);
        //���������ο�
        rect = new Rect();
        //����������ο�
        titleRect = new Rect();
        //���ݻ��ʡ����ݡ���ʼ����λ�á����þ��εĿ��;
        mPaint.getTextBounds(titleStr, 0, titleStr.length(), titleRect);
    }
    
    
    //3����д����  ����CustomImageView���
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        
        /**
         * �߶�   heightMeasureSpec��Mode��Size���
         */
        //��ȡ�߶ȵ� ��ֵģʽ   
        int  heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //��ȡ�߶ȵ�ֵ
        int  heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //�жϸ߶ȵ�ģʽ  EXACTLY����heightSize  AT_MOST����ͼƬ�����ĸ߶Ⱥ�
        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                customImageViewHeight = heightSize;
                break;
            case MeasureSpec.AT_MOST:
                //��ȡͼƬ�߶ȼ�����߶�
                customImageViewHeight= getPaddingTop()+bitmap.getHeight()+titleRect.height()+getPaddingBottom();
                break;
            default:
                break;
        }
        
        /**
         * ���   heightMeasureSpec��Mode��Size���
         */
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        // �ж�ģʽ EXACTLY����widthSize AT_MOST����ͼƬ���������߶�
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
        
        
        //������ÿؼ����  ÿ��View����setMeasuredDimension    ������ViewGroup��onmeasure()�л��ж���view�����ÿ��
        setMeasuredDimension(customImageViewWidth, customImageViewHeight);
    }
    
    //4��
    @Override
    protected void onDraw(Canvas canvas) {
        //�߿���ɫ
        mPaint.setColor(titleColor);
        mPaint.setStrokeWidth(5);//���ñ߿�Ŀ��
        mPaint.setStyle(Paint.Style.STROKE);//���û��߿�
        //�ڻ����ϻ��Ʊ߿�
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        
        
      //����ͼƬ
        switch (scaleType) {
            case FILLXY:
                canvas.drawBitmap(bitmap, getPaddingLeft(), getPaddingTop(), mPaint);//ɵ���Ƶ���ʾ�����Ͻ�
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
        
        
        mPaint.setStyle(Style.FILL);//���ñ߿�Ŀ��   ���û��ı�
        //��ǰ�����Ŀ��С����Ҫ�Ŀ��ʱ��xxx...   ��Ȼ��onMeasure�м���  �����п�����onDraw
        if(titleRect.width()>customImageViewWidth){
            //�������ֻ���  ��ȡxxx...
            TextPaint textPaint = new TextPaint(mPaint);
            String msg = (String) TextUtils.ellipsize(titleStr, textPaint,
                    customImageViewWidth-getPaddingLeft()+getPaddingRight(), TextUtils.TruncateAt.END);
            
            //���Ʊ���
            canvas.drawText(msg, getPaddingLeft(), getPaddingBottom(), mPaint);
        }else{ //����������������� 
          //���Ʊ���      �����ı�      �ı����λ��      ����λ��    ����
            canvas.drawText(titleStr, (customImageViewWidth-getPaddingRight()-getPaddingLeft())/2-titleRect.width()/2+getPaddingLeft(),customImageViewHeight*5/6, mPaint);
        }
        
        
        
        
    }

}
