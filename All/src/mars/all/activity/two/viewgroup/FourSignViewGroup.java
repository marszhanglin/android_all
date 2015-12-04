package mars.all.activity.two.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 * ���� �ֲ������ܵ�layout�ؼ�
 * 
 * @author Mars zhang
 * @created 2015-12-4 ����9:35:52
 */
public class FourSignViewGroup extends ViewGroup {

    public FourSignViewGroup(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public FourSignViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public FourSignViewGroup(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     * ���� ͨ�������ӿؼ��Ŀ�� �������Լ��Ŀ��
     * 
     * @author Mars zhang
     * @created 2015-12-4 ����9:38:59
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     * @see android.view.View#onMeasure(int, int)
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // ��ô�ViewGroup�ϼ�����Ϊ���Ƽ��Ŀ�͸ߣ��Լ�����ģʽ����wrap_contentʱ�Ŀ�ߣ�
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);// xml����Ķ���dp ʱ��
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);// xml����Ķ���dp ʱ��

        // �����ӳ����е��ӿؼ��Ŀ��
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        /**
         * ����Ϊwrap_contentʱ��ȡ���
         */
        int wc_width = 0;
        int wc_height = 0;
        // �ӿؼ�����
        int cCount = getChildCount();
        // �ӿؼ����
        int cwidth = 0;
        int cheight = 0;

        // ���ڼ����������childView�ĸ߶� 0 2
        int lHeight = 0;
        // ���ڼ����ұ�����childView�ĸ߶ȣ����ո߶�ȡ����֮���ֵ 1 3
        int rHeight = 0;
        // ���ڼ����ϱ�����childView�Ŀ�� 0 1
        int tWidth = 0;
        // ���ڼ�����������childiew�Ŀ�ȣ����տ��ȡ����֮���ֵ 2 3
        int bWidth = 0;

        for (int i = 0; i < cCount; i++) {
            View childview = getChildAt(i);
            cheight = childview.getMeasuredHeight();// ��ȡ�ӿؼ������ĸ߶�
            cwidth = childview.getMeasuredWidth();// ��ȡ�ӿؼ������Ŀ��
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childview.getLayoutParams(); // LayoutParams�Ӷ���ȡmarginrightֵ��

            switch (i) {
                case 0:// �����һ���ӿؼ��߶���margin
                    lHeight += cheight + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
                    tWidth += cwidth + marginLayoutParams.rightMargin + marginLayoutParams.leftMargin;
                    break;
                case 1:
                    rHeight += cheight + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
                    tWidth += cwidth + marginLayoutParams.rightMargin + marginLayoutParams.leftMargin;
                    break;
                case 2:
                    lHeight += cheight + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
                    bWidth += cwidth + marginLayoutParams.rightMargin + marginLayoutParams.leftMargin;
                    break;
                case 3:
                    rHeight += cheight + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
                    bWidth += cwidth + marginLayoutParams.rightMargin + marginLayoutParams.leftMargin;
                    break;
                default:
                    break;
            }
        }
        wc_width = Math.max(tWidth, bWidth);
        wc_height = Math.max(rHeight, lHeight);

        // �ж�ViewGroup��layout_width������ģʽ�����ÿ�� EXACTLYΪ����dp
        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? sizeWidth : wc_width,
                (heightMode == MeasureSpec.EXACTLY) ? sizeHeight : wc_height);
    }

    /**
     * 
     * ���� �����ӿؼ�λ��
     * 
     * @author Mars zhang
     * @created 2015-12-4 ����9:37:55
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     * @see android.view.ViewGroup#onLayout(boolean, int, int, int, int)
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // �ӿؼ��Ŀ��
        int cwidth = 0;
        int cheight = 0;
        // �ؼ��ĸ���
        int cCount = getChildCount();
        for (int i = 0; i < cCount; i++) {
            View childView = getChildAt(i);
            cwidth = childView.getMeasuredWidth();
            cheight = childView.getMeasuredHeight();
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childView.getLayoutParams(); // LayoutParams�Ӷ���ȡmarginrightֵ��
            int x = 0, y = 0, xo = 0, yo = 0;// �ؼ���\�Խ����������
            switch (i) {// ����ÿ���ؼ���xyֵ
                case 0:
                    x = marginLayoutParams.leftMargin;
                    y = marginLayoutParams.topMargin;
                    break;
                case 1:
                    x = getWidth() - marginLayoutParams.rightMargin - cwidth;// ���ؼ��Ŀ��-rightMargin-cwidth
                    y = marginLayoutParams.topMargin;
                    break;
                case 2:
                    x = marginLayoutParams.leftMargin;
                    y = getHeight() - marginLayoutParams.bottomMargin - cheight;// ���ؼ��Ŀ��-
                    break;
                case 3:
                    x = getWidth() - marginLayoutParams.rightMargin - cwidth;
                    y = getHeight() - marginLayoutParams.bottomMargin - cheight;
                    break;
                default:
                    break;
            }
            xo = x + cwidth;
            yo = y + cheight;
            //��λ�����õ��ӿؼ�
            childView.layout(x, y, xo, yo);
        }

    }

    /**
     * 
     * ���� Ϊ�˻�ȡmargin������֧��
     * 
     * @author Mars zhang
     * @created 2015-12-4 ����2:16:21
     * @param attrs
     * @return
     * @see android.view.ViewGroup#generateLayoutParams(android.util.AttributeSet)
     */
    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new MarginLayoutParams(p);
    }

}
