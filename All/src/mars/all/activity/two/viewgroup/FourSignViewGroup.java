package mars.all.activity.two.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 * 描述 分布在四周的layout控件
 * 
 * @author Mars zhang
 * @created 2015-12-4 上午9:35:52
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
     * 描述 通过测量子控件的宽高 来设置自己的宽高
     * 
     * @author Mars zhang
     * @created 2015-12-4 上午9:38:59
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     * @see android.view.View#onMeasure(int, int)
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式（非wrap_content时的宽高）
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);// xml中配的多少dp 时用
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);// xml中配的多少dp 时用

        // 计算子出所有的子控件的宽高
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        /**
         * 属性为wrap_content时获取宽高
         */
        int wc_width = 0;
        int wc_height = 0;
        // 子控件个数
        int cCount = getChildCount();
        // 子控件宽高
        int cwidth = 0;
        int cheight = 0;

        // 用于计算左边两个childView的高度 0 2
        int lHeight = 0;
        // 用于计算右边两个childView的高度，最终高度取二者之间大值 1 3
        int rHeight = 0;
        // 用于计算上边两个childView的宽度 0 1
        int tWidth = 0;
        // 用于计算下面两个childiew的宽度，最终宽度取二者之间大值 2 3
        int bWidth = 0;

        for (int i = 0; i < cCount; i++) {
            View childview = getChildAt(i);
            cheight = childview.getMeasuredHeight();// 获取子控件计算后的高度
            cwidth = childview.getMeasuredWidth();// 获取子控件计算后的宽度
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childview.getLayoutParams(); // LayoutParams从而获取marginright值等

            switch (i) {
                case 0:// 左面第一个子控件高度与margin
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

        // 判断ViewGroup的layout_width是那种模式并设置宽高 EXACTLY为多少dp
        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? sizeWidth : wc_width,
                (heightMode == MeasureSpec.EXACTLY) ? sizeHeight : wc_height);
    }

    /**
     * 
     * 描述 计算子控件位置
     * 
     * @author Mars zhang
     * @created 2015-12-4 上午9:37:55
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     * @see android.view.ViewGroup#onLayout(boolean, int, int, int, int)
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // 子控件的宽高
        int cwidth = 0;
        int cheight = 0;
        // 控件的个数
        int cCount = getChildCount();
        for (int i = 0; i < cCount; i++) {
            View childView = getChildAt(i);
            cwidth = childView.getMeasuredWidth();
            cheight = childView.getMeasuredHeight();
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childView.getLayoutParams(); // LayoutParams从而获取marginright值等
            int x = 0, y = 0, xo = 0, yo = 0;// 控件的\对角两点的坐标
            switch (i) {// 计算每个控件的xy值
                case 0:
                    x = marginLayoutParams.leftMargin;
                    y = marginLayoutParams.topMargin;
                    break;
                case 1:
                    x = getWidth() - marginLayoutParams.rightMargin - cwidth;// 父控件的宽度-rightMargin-cwidth
                    y = marginLayoutParams.topMargin;
                    break;
                case 2:
                    x = marginLayoutParams.leftMargin;
                    y = getHeight() - marginLayoutParams.bottomMargin - cheight;// 父控件的宽度-
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
            //把位置作用到子控件
            childView.layout(x, y, xo, yo);
        }

    }

    /**
     * 
     * 描述 为了获取margin参数的支持
     * 
     * @author Mars zhang
     * @created 2015-12-4 下午2:16:21
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
