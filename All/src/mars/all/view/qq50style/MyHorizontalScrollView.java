package mars.all.view.qq50style;

import mars.all.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewHelper;

/**
 * �Զ���ؼ�ע��3�㣺 1��onMesure�����ڲ�View����View���Ŀ�͸ߣ������Լ��Ŀ�� 2��onLayout������View�ڷŵ�λ��
 * 3��onTouchEvent����Ҫʵ����ָ����ʱʵ�ָ÷�����
 * 
 * @author Mars zhang
 * 
 */
public class MyHorizontalScrollView extends HorizontalScrollView {
	/** ��������LinearLayout */
	private LinearLayout mWapper;
	/** ���˵��� */
	private ViewGroup leftMenu;
	/** �Ҳ����� */
	private ViewGroup mContent;
	/** ��Ļ��� */
	private int mScreenWidth;
	/** �Ҳ���������ʱʣ���� */
	private int mMenuRightPadding = 50;

	/**��ΪonMesure�᲻ֹһ�εı���������Ҫ�жϲ���ֻ��һ��*/
	private boolean onMesureOnce=false;
	
	/**����˵�����Ŀ�*/
	private int menuWidth;
	
	/**�л�״̬  Ĭ��false*/
	private boolean isOpen;
	/**
	 * δʹ���Զ�������2�����캯���ķ���
	 * 
	 * @param context
	 * @param attrs
	 */
	public MyHorizontalScrollView(Context context, AttributeSet attrs) { 
		this(context,attrs,0);
	}
	/**
	 * ��ʹ���Զ�������ʱ����3����������ķ���
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public MyHorizontalScrollView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		/**��ȡ�Զ�������*/
		TypedArray typedArray=context.getTheme().obtainStyledAttributes(attrs, R.styleable.SlidingMenu, defStyle, 0);
		int n =typedArray.getIndexCount();
		for(int i=0;i<n;i++){
			int attr=typedArray.getIndex(i);
			switch (attr) {
			case R.styleable.SlidingMenu_rightPadding:
				mMenuRightPadding=typedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context
				.getResources().getDisplayMetrics()));
				break; 
			}
			
		} 
		/**�ͷ�typedArray*/
		typedArray.recycle();
		
		
		
		
		/** ��ȡ��Ļ��� */
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		/** �ѿն���metrics����ȥ�����¾���ֵ�� */
		wm.getDefaultDisplay().getMetrics(outMetrics);
		mScreenWidth = outMetrics.widthPixels;
		/**��dp����px   ��Ϊ�����Զ���Ͳ���Ҫ��δ�����*/
//		mMenuRightPadding=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context
//				.getResources().getDisplayMetrics()); 
	}
	/**
	 * �ڴ�����newʱ�����һ�������Ĺ��췽��
	 * @param context
	 */
	public MyHorizontalScrollView(Context context) {
		this(context,null);
	}

	/**
	 * 1��onMesure�����ڲ�View����View���Ŀ�͸ߣ������Լ��Ŀ��
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if(!onMesureOnce){
			mWapper=(LinearLayout) getChildAt(0);
			leftMenu=(ViewGroup) mWapper.getChildAt(0);
			mContent=(ViewGroup) mWapper.getChildAt(1);
			
			/**����leftMenu�Ŀ��Ϊ��Ļ�Ŀ��-mMenuRightPadding*/
			menuWidth=leftMenu.getLayoutParams().width=mScreenWidth-mMenuRightPadding;
			/**mContent���Ϊ��Ļ�Ŀ��*/
			mContent.getLayoutParams().width=mScreenWidth;
			
			onMesureOnce=true;
		} 
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	} 
	
	/**
	 * 2��onLayout������View�ڷŵ�λ��
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) { 
		super.onLayout(changed, l, t, r, b); 
		
		if(changed){
			/**������xy����Ĺ���   һ��������������  ���˵�����*/
			this.scrollTo(menuWidth, 0);
		}
	}
	/**
	 * 3��onTouchEvent����Ҫʵ����ָ����ʱʵ�ָ÷�����
	 */
	@SuppressLint("NewApi")
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		int action=ev.getAction();
		int x=getScrollX();
		switch (action) {
		case MotionEvent.ACTION_UP:  //��ָ̧���¼�
			if(x<(menuWidth/2)){//�����������˵���һ��ʱ��ʾ�˵�
//				this.scrollTo(0, 0);
				this.smoothScrollTo(0, 0);
				isOpen=true;
			}else {//���������ڲ˵���һ��ʱ���ز˵�
//				this.scrollTo(menuWidth, 0);
				this.smoothScrollTo(menuWidth, 0);
				isOpen=false;
			}
			return true;  
		} 
		return super.onTouchEvent(ev);
	}
	/**�رղ˵�*/
	private void closeMenu(){ 
		if(!isOpen)return;
		this.smoothScrollTo(menuWidth, 0);
		isOpen=false;
	}
	/**�򿪲˵�*/
	private void openMenu(){
		if(isOpen)return;
		this.smoothScrollTo(0, 0);
		isOpen=true; 
	}

	/** �л��˵� */
	public void toggle() {
		if (isOpen) {//��ʱ�ر�
			closeMenu();
		}else{//�ر�ʱ��
			openMenu();
		}
	}
	
	/**
	 * ����ʽ�໬�Ĺؼ�����   ���Զ�����̬����ƫ����   �����ǹ����ı����   Ҳ���ǲ໬ʵ�ֵĹؼ�λ��
	 * l  Current horizontal scroll origin. �������ֵ
		t  Current vertical scroll origin.  �������ֵ
		oldl  Previous horizontal scroll origin.   ��������ݶ�
		oldt  Previous vertical scroll origin.  ��������ݶ� 
	 * 
	 * */
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {  
		super.onScrollChanged(l, t, oldl, oldt); 
		float scale=l*1.0f/menuWidth;//1~0 
		//�������Զ���������TranslationXƫ����
		//���Զ���3.0���ϲ�����nineodeandroids-2.4.0.jar��
		ViewHelper.setTranslationX(leftMenu, menuWidth*scale*0.7f);   //�������0.7��Ϊ�˻�����ʼʱ���ò˵�ȫ��ƫ��    Ҳ����������ʱ�˵�Ҳ����
		
		
		/**
		 * һ����qq�˵�������  ͸���ȵ�Ч��
		 */
		//�������������   scale 1~0   ����rightScale   1~0.7   ������������
		float rightScale=0.7f+0.3f*scale;
		// Ҫ���������ĵ��赽���
		ViewHelper.setPivotX(mContent, 0);
		ViewHelper.setPivotY(mContent, mContent.getHeight()/2);
		//��������     
		ViewHelper.setScaleX(mContent, rightScale);
		ViewHelper.setScaleY(mContent, rightScale);
		
		//�˵����������   scale 1~0   ����leftScale   0.7~1   ���ڲ˵�����
		float leftScale=0.8f-0.3f*scale;
		// Ҫ���������ĵ��赽���
		ViewHelper.setPivotX(leftMenu, 0);
		ViewHelper.setPivotY(leftMenu, leftMenu.getHeight()/2);
		//��������     
		ViewHelper.setScaleX(leftMenu, leftScale);
		ViewHelper.setScaleY(leftMenu, leftScale);
		
		
		//�˵�͸���ȱ仯Ҫ��0.6~1    scale 1~0   leftAlpha=1.0f-0.4f*scale
		float leftAlpha=1.0f-0.4f*scale;
		ViewHelper.setAlpha(leftMenu, leftAlpha);
	}
}
