package mars.all.activity.at_staggeredgrid.viewpager.pagetransformer;

import com.nineoldandroids.view.ViewHelper;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;
/**
 * Viewpager�Ķ���
 * 2015-3-1����7:34:15 ��DepthPageTransformer
 * @author Mars zhang
 *
 */
public class DepthPageTransformer implements  PageTransformer {
    private static final float MIN_SCALE = 0.75f;

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        //[-Infinity,-1) �󿴲���  [-1,0] �󿴵ļ�    (0,1]�п��ļ�       (1,+Infinity]������
        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
//            view.setAlpha(0);
        	ViewHelper.setAlpha(view, 0);//�ĳ����Զ���

        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when moving to the left page
//            view.setAlpha(1);
//            view.setTranslationX(0);
//            view.setScaleX(1);
//            view.setScaleY(1);
        	ViewHelper.setAlpha(view, 1);//͸����
        	ViewHelper.setTranslationX(view, 0);//λ��
        	ViewHelper.setScaleX(view, 1);//x����
        	ViewHelper.setScaleY(view, 1);//Y����
        } else if (position <= 1) { // (0,1]   ���Ҳ�ɼ�ʱ�ı����ż�͸��
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

