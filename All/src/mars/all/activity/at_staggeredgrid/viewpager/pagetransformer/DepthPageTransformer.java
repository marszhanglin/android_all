package mars.all.activity.at_staggeredgrid.viewpager.pagetransformer;

import com.nineoldandroids.view.ViewHelper;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;
/**
 * Viewpager的动画
 * 2015-3-1下午7:34:15 类DepthPageTransformer
 * @author Mars zhang
 *
 */
public class DepthPageTransformer implements  PageTransformer {
    private static final float MIN_SCALE = 0.75f;

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        //[-Infinity,-1) 左看不见  [-1,0] 左看的见    (0,1]有看的见       (1,+Infinity]看不见
        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
//            view.setAlpha(0);
        	ViewHelper.setAlpha(view, 0);//改成属性动画

        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when moving to the left page
//            view.setAlpha(1);
//            view.setTranslationX(0);
//            view.setScaleX(1);
//            view.setScaleY(1);
        	ViewHelper.setAlpha(view, 1);//透明度
        	ViewHelper.setTranslationX(view, 0);//位置
        	ViewHelper.setScaleX(view, 1);//x缩放
        	ViewHelper.setScaleY(view, 1);//Y缩放
        } else if (position <= 1) { // (0,1]   在右侧可见时改变缩放及透明
            // Fade the page out.
//            view.setAlpha(1 - position);
            ViewHelper.setAlpha(view, 1-position);  
            // Counteract the default slide transition
//            view.setTranslationX(pageWidth * -position);
            ViewHelper.setTranslationX(view, pageWidth * -position);
            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
//            view.setScaleX(scaleFactor);
//            view.setScaleY(scaleFactor);
            ViewHelper.setScaleX(view, scaleFactor);
        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
//            view.setAlpha(0);
        	ViewHelper.setAlpha(view, 0);
        }
    }
}

