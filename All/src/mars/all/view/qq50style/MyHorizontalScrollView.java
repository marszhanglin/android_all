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
 * 自定义控件注意3点： 1、onMesure决定内部View（子View）的宽和高，包括自己的宽高 2、onLayout决定子View摆放的位置
 * 3、onTouchEvent（当要实现手指操作时实现该方法）
 * 
 * @author Mars zhang
 * 
 */
public class MyHorizontalScrollView extends HorizontalScrollView {
	/** 滚动条的LinearLayout */
	private LinearLayout mWapper;
	/** 左侧菜单栏 */
	private ViewGroup leftMenu;
	/** 右侧内容 */
	private ViewGroup mContent;
	/** 屏幕宽度 */
	private int mScreenWidth;
	/** 右侧内容隐藏时剩多少 */
	private int mMenuRightPadding = 50;

	/**因为onMesure会不止一次的被调用所以要判断并且只调一次*/
	private boolean onMesureOnce=false;
	
	/**储存菜单区域的宽*/
	private int menuWidth;
	
	/**切换状态  默认false*/
	private boolean isOpen;
	/**
	 * 未使用自定义会调用2个构造函数的方法
	 * 
	 * @param context
	 * @param attrs
	 */
	public MyHorizontalScrollView(Context context, AttributeSet attrs) { 
		this(context,attrs,0);
	}
	/**
	 * 当使用自定义属性时调用3个构造参数的方法
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public MyHorizontalScrollView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		/**获取自定义属性*/
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
		/**释放typedArray*/
		typedArray.recycle();
		
		
		
		
		/** 获取屏幕宽度 */
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		/** 把空对象metrics传进去处理下就有值了 */
		wm.getDefaultDisplay().getMetrics(outMetrics);
		mScreenWidth = outMetrics.widthPixels;
		/**把dp传成px   因为有了自定义就不需要这段代码了*/
//		mMenuRightPadding=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context
//				.getResources().getDisplayMetrics()); 
	}
	/**
	 * 在代码中new时会调用一个参数的构造方法
	 * @param context
	 */
	public MyHorizontalScrollView(Context context) {
		this(context,null);
	}

	/**
	 * 1、onMesure决定内部View（子View）的宽和高，包括自己的宽高
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if(!onMesureOnce){
			mWapper=(LinearLayout) getChildAt(0);
			leftMenu=(ViewGroup) mWapper.getChildAt(0);
			mContent=(ViewGroup) mWapper.getChildAt(1);
			
			/**设置leftMenu的宽度为屏幕的宽度-mMenuRightPadding*/
			menuWidth=leftMenu.getLayoutParams().width=mScreenWidth-mMenuRightPadding;
			/**mContent宽度为屏幕的宽度*/
			mContent.getLayoutParams().width=mScreenWidth;
			
			onMesureOnce=true;
		} 
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	} 
	
	/**
	 * 2、onLayout决定子View摆放的位置
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) { 
		super.onLayout(changed, l, t, r, b); 
		
		if(changed){
			/**滚动条xy方向的滚动   一进来就是内容区  将菜单隐藏*/
			this.scrollTo(menuWidth, 0);
		}
	}
	/**
	 * 3、onTouchEvent（当要实现手指操作时实现该方法）
	 */
	@SuppressLint("NewApi")
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		int action=ev.getAction();
		int x=getScrollX();
		switch (action) {
		case MotionEvent.ACTION_UP:  //手指抬起事件
			if(x<(menuWidth/2)){//当滚动超过菜单的一半时显示菜单
//				this.scrollTo(0, 0);
				this.smoothScrollTo(0, 0);
				isOpen=true;
			}else {//当滚动低于菜单的一半时隐藏菜单
//				this.scrollTo(menuWidth, 0);
				this.smoothScrollTo(menuWidth, 0);
				isOpen=false;
			}
			return true;  
		} 
		return super.onTouchEvent(ev);
	}
	/**关闭菜单*/
	private void closeMenu(){ 
		if(!isOpen)return;
		this.smoothScrollTo(menuWidth, 0);
		isOpen=false;
	}
	/**打开菜单*/
	private void openMenu(){
		if(isOpen)return;
		this.smoothScrollTo(0, 0);
		isOpen=true; 
	}

	/** 切换菜单 */
	public void toggle() {
		if (isOpen) {//打开时关闭
			closeMenu();
		}else{//关闭时打开
			openMenu();
		}
	}
	
	/**
	 * 抽屉式侧滑的关键代码   属性动画动态设置偏移量   这里是滚动改变监听   也正是侧滑实现的关键位置
	 * l  Current horizontal scroll origin. 横向滚动值
		t  Current vertical scroll origin.  纵向滚动值
		oldl  Previous horizontal scroll origin.   横向滚动梯度
		oldt  Previous vertical scroll origin.  纵向滚动梯度 
	 * 
	 * */
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {  
		super.onScrollChanged(l, t, oldl, oldt); 
		float scale=l*1.0f/menuWidth;//1~0 
		//调用属性动画，设置TranslationX偏移量
		//属性动画3.0以上不易引nineodeandroids-2.4.0.jar包
		ViewHelper.setTranslationX(leftMenu, menuWidth*scale*0.7f);   //这里乘以0.7是为了滑动开始时不让菜单全部偏移    也就是内容左滑时菜单也有左滑
		
		
		/**
		 * 一下是qq菜单的缩放  透明度等效果
		 */
		//内容区域的缩放   scale 1~0   所以rightScale   1~0.7   用于内容缩放
		float rightScale=0.7f+0.3f*scale;
		// 要把内容中心点设到左侧
		ViewHelper.setPivotX(mContent, 0);
		ViewHelper.setPivotY(mContent, mContent.getHeight()/2);
		//内容缩放     
		ViewHelper.setScaleX(mContent, rightScale);
		ViewHelper.setScaleY(mContent, rightScale);
		
		//菜单区域的缩放   scale 1~0   所以leftScale   0.7~1   用于菜单缩放
		float leftScale=0.8f-0.3f*scale;
		// 要把内容中心点设到左侧
		ViewHelper.setPivotX(leftMenu, 0);
		ViewHelper.setPivotY(leftMenu, leftMenu.getHeight()/2);
		//内容缩放     
		ViewHelper.setScaleX(leftMenu, leftScale);
		ViewHelper.setScaleY(leftMenu, leftScale);
		
		
		//菜单透明度变化要求0.6~1    scale 1~0   leftAlpha=1.0f-0.4f*scale
		float leftAlpha=1.0f-0.4f*scale;
		ViewHelper.setAlpha(leftMenu, leftAlpha);
	}
}
