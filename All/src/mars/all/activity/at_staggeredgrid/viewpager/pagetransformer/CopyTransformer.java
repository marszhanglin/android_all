package mars.all.activity.at_staggeredgrid.viewpager.pagetransformer;

import com.nineoldandroids.view.ViewHelper;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;
/**
 * Viewpager�Ķ��� ����
 * 2015-3-1����7:34:15 ��DepthPageTransformer
 * @author Mars zhang
 *
 */
public class CopyTransformer implements  PageTransformer {

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        //[-Infinity,-1) �󿴲���  [-1,0] �󿴵ļ�    (0,1]�п��ļ�       (1,+Infinity]������
        if (position < -1) { // [-Infinity,-1)
        	ViewHelper.setAlpha(view, 0);
        } else if (position <= 0) { // [-1,0]
             
        } else if (position <= 1) { // (0,1]   ���Ҳ�ɼ�ʱ�ı����ż�͸��
             
        } else { // (1,+Infinity]
        	ViewHelper.setAlpha(view, 0);
        }
    }
}

