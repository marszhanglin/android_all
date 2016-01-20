package mars.all.activity.two.circlemenu;

import mars.all.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * 描述
 * 
 * @author Mars zhang
 * @created 2015-12-11 下午2:27:16
 */
public class CircleMenuLayout extends ViewGroup {

	/** 各个菜单的文字 */
	private String[] mItemTexts = {};
	/** 各个菜单的图片资源 */
	private int[] mItemImageResourceIds = {};
	private CircleMenuInterface mCircleMenuInterface;
	/** 大圆直径 */
	private int mRadius = 0;
	/** 子项的个数 */
	private int itemCount = 0;

	/** 子控件的半径比例 */
	private final static float RADIO_DEFAULT_CHILD_DIMENSION = 1 / 4f;
	/** 中间控件的半径比例 */
	private final static float RADIO_DEFAULT_CENTERITEM_DIMENSION = 1 / 3f;
	/** mpadding */
	private float mpadding = 0f;
	/** 布局时开始的角度 */
	private double mStartAngle = 0;

	/** 设置回调 */
	public void setmCircleMenuInterface(CircleMenuInterface mCircleMenuInterface) {
		this.mCircleMenuInterface = mCircleMenuInterface;
	}

	/** 回调接口 */
	public interface CircleMenuInterface {
		public void itemOnClick(View view, int pos);

		public void centerClick(View view);
	}

	public CircleMenuLayout(Context context, AttributeSet attrs) {
		super(context, attrs);// xml方式调用会进这个方法
		setPadding(0, 0, 0, 0);// 无视padding
	}

	public CircleMenuLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CircleMenuLayout(Context context) {
		super(context);
	}

	/**
	 * 
	 * 描述 计算子控件位置 无非就是遍历计算设置left与top <br>
	 * 执行一次就要将所有控件摆上去
	 * 
	 * @author Mars zhang
	 * @created 2015-12-11 下午2:28:07
	 * @param changed
	 * @param l
	 * @param t
	 * @param r
	 * @param b
	 * @see android.view.ViewGroup#onLayout(boolean, int, int, int, int)
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// 控件位置
		int left, top;
		// 子控件的个数
		int childCount = getChildCount();
		// 父控件的大小
		int fathersize = mRadius;
		// item的宽度 也就是直径
		int childwidth = (int) (mRadius * RADIO_DEFAULT_CHILD_DIMENSION);
		// item的角度
		float angleDelay = 360 / (childCount - 1);// 除去中间的
		for (int i = 0; i < childCount; i++) {
			View childView = getChildAt(i);
			// 中间和那些不可见的控件就略过
			if (childView.getId() == R.id.id_circle_menu_item_center
					|| childView.getVisibility() == GONE) {
				continue;
			}

			// 计算 中心点到menu item中心的距离
			float temp = mRadius / 2 - childwidth / 2 - mpadding;

			mStartAngle %= 360;//在线程中改变这个值再requestLayout下就执行onLayout
			left = (int) (mRadius / 2 + Math.round(temp * 0.9
					* Math.cos(Math.toRadians(mStartAngle)) - childwidth / 2));
			top = (int) (mRadius / 2 + Math.round(temp * 0.9
					* Math.sin(Math.toRadians(mStartAngle)) - childwidth / 2));
			childView.layout(left, top, left + childwidth, top + childwidth);
			mStartAngle += angleDelay;
		}

		// 对中心那个控件进行布局
		View centerView = findViewById(R.id.id_circle_menu_item_center);
		if (null != centerView) {
			centerView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mCircleMenuInterface.centerClick(v);// 中心点点击回调
				}
			});
			int centerR = mRadius / 2 - centerView.getMeasuredWidth() / 2;
			int centerL = centerR + centerView.getMeasuredWidth();
			centerView.layout(centerR, centerR, centerL, centerL);
		}

	}

	/**
	 * 
	 * 描述 测量并设置自己的宽高（精确宽高>背景宽高>getWidth） 并设置子item的宽高
	 * 
	 * @author Mars zhang
	 * @created 2015-12-11 下午2:43:41
	 * @param widthMeasureSpec
	 * @param heightMeasureSpec
	 * @see android.view.View#onMeasure(int, int)
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// 背景资源的宽高
		int resWidth = 0;
		int resHeight = 0;

		// 获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式（非wrap_content时的宽高）
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);// xml中配的多少dp 时用
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);// xml中配的多少dp 时用

		// 宽或高不是精确模式时用图片资源的宽高 是精确模式时用精确模式的宽高
		if (widthMode != MeasureSpec.EXACTLY
				|| heightMode != MeasureSpec.EXACTLY) {
			// 背景宽高
			resWidth = getSuggestedMinimumWidth();
			resHeight = getSuggestedMinimumHeight();

			// getwith
			resWidth = resWidth == 0 ? getWidth() : resWidth;
			resHeight = resHeight == 0 ? getHeight() : resHeight;

		} else {
			// 精确
			resWidth = resHeight = Math.min(sizeWidth, sizeHeight);
		}
		// 设置ViewGroup的宽高
		setMeasuredDimension(resWidth, resHeight);

		// 测量后的ViewGroup宽高可以认为是大圆形的直径
		mRadius = Math.max(getMeasuredHeight(), getMeasuredWidth());

		itemCount = getChildCount();

		int childSize = (int) (mRadius * RADIO_DEFAULT_CHILD_DIMENSION);

		// 迭代设置子控件的大小
		for (int i = 0; i < itemCount; i++) {
			View childVew = getChildAt(i);
			// 当控件不可见是跳过
			if (childVew.getVisibility() == GONE) {
				continue;
			}

			// 模式变换后的大小
			int makeMeasureSqec = -1;
			// 如果是圆心的控件
			if (childVew.getId() == R.id.id_circle_menu_item_center) {
				// 根据模式及大小获取大小
				makeMeasureSqec = MeasureSpec.makeMeasureSpec(
						(int) (mRadius * RADIO_DEFAULT_CENTERITEM_DIMENSION),
						MeasureSpec.EXACTLY);
			} else {// 是item
				// 根据模式及大小获取大小
				makeMeasureSqec = MeasureSpec.makeMeasureSpec(childSize,
						MeasureSpec.EXACTLY);
			}
			// 设置子控件的值根据以上计算的值
			childVew.measure(makeMeasureSqec, makeMeasureSqec);
		}

	}
	
	/**
     * 主要为了action_down时，返回true    View与ViewGroup事件分发机制要理清楚
     */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
	    return  true;
	}

	/**
	 * 
	 * 描述 公开给Activity的设置item的图标与文字的
	 * 
	 * @author Mars zhang
	 * @created 2015-12-11 下午2:45:37
	 */
	public void setMenuIconAndText(String[] texts, int[] resourceIds) {
		if (null == texts && resourceIds == null) {
			System.out.println("你傻啊。。");
		} else {
			itemCount = texts == null ? resourceIds.length : texts.length;
			if (null != texts && resourceIds != null) {
				itemCount = Math.max(resourceIds.length, texts.length);
			}
		}

		mItemTexts = texts;
		mItemImageResourceIds = resourceIds;

		addItems();

	}

	/**
	 * 
	 * 描述 将每个item添加到ViewGroup容器中
	 * 
	 * @author Mars zhang
	 * @created 2015-12-11 下午2:50:58
	 */
	private void addItems() {
		LayoutInflater inflater = LayoutInflater.from(getContext());
		for (int i = 0; i < itemCount; i++) {
			final int tempi = i;
			View itemView = inflater.inflate(R.layout.circle_menu_item, null);
			ImageView itemImageView = (ImageView) itemView
					.findViewById(R.id.id_circle_menu_item_image);
			TextView itemTextView = (TextView) itemView
					.findViewById(R.id.id_circle_menu_item_text);
			itemImageView.setImageResource(mItemImageResourceIds[i]);
			itemTextView.setText(mItemTexts[i]);
			itemImageView.setVisibility(View.VISIBLE);
			itemTextView.setVisibility(View.VISIBLE);
			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (null != mCircleMenuInterface) {
						mCircleMenuInterface.itemOnClick(v, tempi);// 构造该容器函数时实现接口
																	// 此处回调
					}
				}
			});
			// 往ViewGroup容器加item ViewGroup的关键
			addView(itemView);
		}

	}
	
	
	   /**
     * 根据触摸的位置，计算角度
     * 
     * @param xTouch
     * @param yTouch
     * @return
     */
    private float getAngle(float xTouch, float yTouch)
    {
        double x = xTouch - (mRadius / 2d);
        double y = yTouch - (mRadius / 2d);
        return (float) (Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI);
    }
    
    
    /**
     * 根据当前位置计算象限
     * 
     * @param x
     * @param y
     * @return
     */
    private int getQuadrant(float x, float y)
    {
        int tmpX = (int) (x - mRadius / 2);
        int tmpY = (int) (y - mRadius / 2);
        if (tmpX >= 0)
        {
            return tmpY >= 0 ? 4 : 1;
        } else
        {
            return tmpY >= 0 ? 3 : 2;
        }

    }
}
