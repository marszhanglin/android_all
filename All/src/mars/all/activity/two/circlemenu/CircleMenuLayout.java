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
 * ����
 * 
 * @author Mars zhang
 * @created 2015-12-11 ����2:27:16
 */
public class CircleMenuLayout extends ViewGroup {

	/** �����˵������� */
	private String[] mItemTexts = {};
	/** �����˵���ͼƬ��Դ */
	private int[] mItemImageResourceIds = {};
	private CircleMenuInterface mCircleMenuInterface;
	/** ��Բֱ�� */
	private int mRadius = 0;
	/** ����ĸ��� */
	private int itemCount = 0;

	/** �ӿؼ��İ뾶���� */
	private final static float RADIO_DEFAULT_CHILD_DIMENSION = 1 / 4f;
	/** �м�ؼ��İ뾶���� */
	private final static float RADIO_DEFAULT_CENTERITEM_DIMENSION = 1 / 3f;
	/** mpadding */
	private float mpadding = 0f;
	/** ����ʱ��ʼ�ĽǶ� */
	private double mStartAngle = 0;

	/** ���ûص� */
	public void setmCircleMenuInterface(CircleMenuInterface mCircleMenuInterface) {
		this.mCircleMenuInterface = mCircleMenuInterface;
	}

	/** �ص��ӿ� */
	public interface CircleMenuInterface {
		public void itemOnClick(View view, int pos);

		public void centerClick(View view);
	}

	public CircleMenuLayout(Context context, AttributeSet attrs) {
		super(context, attrs);// xml��ʽ���û���������
		setPadding(0, 0, 0, 0);// ����padding
	}

	public CircleMenuLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CircleMenuLayout(Context context) {
		super(context);
	}

	/**
	 * 
	 * ���� �����ӿؼ�λ�� �޷Ǿ��Ǳ�����������left��top <br>
	 * ִ��һ�ξ�Ҫ�����пؼ�����ȥ
	 * 
	 * @author Mars zhang
	 * @created 2015-12-11 ����2:28:07
	 * @param changed
	 * @param l
	 * @param t
	 * @param r
	 * @param b
	 * @see android.view.ViewGroup#onLayout(boolean, int, int, int, int)
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// �ؼ�λ��
		int left, top;
		// �ӿؼ��ĸ���
		int childCount = getChildCount();
		// ���ؼ��Ĵ�С
		int fathersize = mRadius;
		// item�Ŀ�� Ҳ����ֱ��
		int childwidth = (int) (mRadius * RADIO_DEFAULT_CHILD_DIMENSION);
		// item�ĽǶ�
		float angleDelay = 360 / (childCount - 1);// ��ȥ�м��
		for (int i = 0; i < childCount; i++) {
			View childView = getChildAt(i);
			// �м����Щ���ɼ��Ŀؼ����Թ�
			if (childView.getId() == R.id.id_circle_menu_item_center
					|| childView.getVisibility() == GONE) {
				continue;
			}

			// ���� ���ĵ㵽menu item���ĵľ���
			float temp = mRadius / 2 - childwidth / 2 - mpadding;

			mStartAngle %= 360;//���߳��иı����ֵ��requestLayout�¾�ִ��onLayout
			left = (int) (mRadius / 2 + Math.round(temp * 0.9
					* Math.cos(Math.toRadians(mStartAngle)) - childwidth / 2));
			top = (int) (mRadius / 2 + Math.round(temp * 0.9
					* Math.sin(Math.toRadians(mStartAngle)) - childwidth / 2));
			childView.layout(left, top, left + childwidth, top + childwidth);
			mStartAngle += angleDelay;
		}

		// �������Ǹ��ؼ����в���
		View centerView = findViewById(R.id.id_circle_menu_item_center);
		if (null != centerView) {
			centerView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mCircleMenuInterface.centerClick(v);// ���ĵ����ص�
				}
			});
			int centerR = mRadius / 2 - centerView.getMeasuredWidth() / 2;
			int centerL = centerR + centerView.getMeasuredWidth();
			centerView.layout(centerR, centerR, centerL, centerL);
		}

	}

	/**
	 * 
	 * ���� �����������Լ��Ŀ�ߣ���ȷ���>�������>getWidth�� ��������item�Ŀ��
	 * 
	 * @author Mars zhang
	 * @created 2015-12-11 ����2:43:41
	 * @param widthMeasureSpec
	 * @param heightMeasureSpec
	 * @see android.view.View#onMeasure(int, int)
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// ������Դ�Ŀ��
		int resWidth = 0;
		int resHeight = 0;

		// ��ô�ViewGroup�ϼ�����Ϊ���Ƽ��Ŀ�͸ߣ��Լ�����ģʽ����wrap_contentʱ�Ŀ�ߣ�
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);// xml����Ķ���dp ʱ��
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);// xml����Ķ���dp ʱ��

		// ���߲��Ǿ�ȷģʽʱ��ͼƬ��Դ�Ŀ�� �Ǿ�ȷģʽʱ�þ�ȷģʽ�Ŀ��
		if (widthMode != MeasureSpec.EXACTLY
				|| heightMode != MeasureSpec.EXACTLY) {
			// �������
			resWidth = getSuggestedMinimumWidth();
			resHeight = getSuggestedMinimumHeight();

			// getwith
			resWidth = resWidth == 0 ? getWidth() : resWidth;
			resHeight = resHeight == 0 ? getHeight() : resHeight;

		} else {
			// ��ȷ
			resWidth = resHeight = Math.min(sizeWidth, sizeHeight);
		}
		// ����ViewGroup�Ŀ��
		setMeasuredDimension(resWidth, resHeight);

		// �������ViewGroup��߿�����Ϊ�Ǵ�Բ�ε�ֱ��
		mRadius = Math.max(getMeasuredHeight(), getMeasuredWidth());

		itemCount = getChildCount();

		int childSize = (int) (mRadius * RADIO_DEFAULT_CHILD_DIMENSION);

		// ���������ӿؼ��Ĵ�С
		for (int i = 0; i < itemCount; i++) {
			View childVew = getChildAt(i);
			// ���ؼ����ɼ�������
			if (childVew.getVisibility() == GONE) {
				continue;
			}

			// ģʽ�任��Ĵ�С
			int makeMeasureSqec = -1;
			// �����Բ�ĵĿؼ�
			if (childVew.getId() == R.id.id_circle_menu_item_center) {
				// ����ģʽ����С��ȡ��С
				makeMeasureSqec = MeasureSpec.makeMeasureSpec(
						(int) (mRadius * RADIO_DEFAULT_CENTERITEM_DIMENSION),
						MeasureSpec.EXACTLY);
			} else {// ��item
				// ����ģʽ����С��ȡ��С
				makeMeasureSqec = MeasureSpec.makeMeasureSpec(childSize,
						MeasureSpec.EXACTLY);
			}
			// �����ӿؼ���ֵ�������ϼ����ֵ
			childVew.measure(makeMeasureSqec, makeMeasureSqec);
		}

	}
	
	/**
     * ��ҪΪ��action_downʱ������true    View��ViewGroup�¼��ַ�����Ҫ�����
     */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
	    return  true;
	}

	/**
	 * 
	 * ���� ������Activity������item��ͼ�������ֵ�
	 * 
	 * @author Mars zhang
	 * @created 2015-12-11 ����2:45:37
	 */
	public void setMenuIconAndText(String[] texts, int[] resourceIds) {
		if (null == texts && resourceIds == null) {
			System.out.println("��ɵ������");
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
	 * ���� ��ÿ��item��ӵ�ViewGroup������
	 * 
	 * @author Mars zhang
	 * @created 2015-12-11 ����2:50:58
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
						mCircleMenuInterface.itemOnClick(v, tempi);// �������������ʱʵ�ֽӿ�
																	// �˴��ص�
					}
				}
			});
			// ��ViewGroup������item ViewGroup�Ĺؼ�
			addView(itemView);
		}

	}
	
	
	   /**
     * ���ݴ�����λ�ã�����Ƕ�
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
     * ���ݵ�ǰλ�ü�������
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
