package mars.all.activity.at_staggeredgrid.viewpager.pagetransformer;

import com.nineoldandroids.view.ViewHelper;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;
/**
 * Viewpager的动画 滚动
 * 2015-3-1下午7:34:15 类DepthPageTransformer
 * @author Mars zhang
 *
 */
public class CopyTransformer implements  PageTransformer {

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        //[-Infinity,-1) 左看不见  [-1,0] 左看的见    (0,1]有看的见       (1,+Infinity]看不见
        if (position < -1) { // [-Infinity,-1)
        	ViewHelper.setAlpha(view, 0);
        } else if (position <= 0) { // [-1,0]
             
        } else if (position <= 1) { // (0,1]   在右侧可见时改变缩放及透明
             
        } else { // (1,+Infinity]
        	ViewHelper.setAlpha(view, 0);
        }
    }
}

