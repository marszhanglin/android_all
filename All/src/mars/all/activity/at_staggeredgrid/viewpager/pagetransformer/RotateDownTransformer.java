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
public class RotateDownTransformer implements  PageTransformer {
	//�����ת�Ƕ�
	private static final int  MAX_ROTATE=20;
	//�Ƕȱ仯0~20   -20~0
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();
        //[-Infinity,-1) �󿴲���  [-1,0] �󿴵ļ�    (0,1]�п��ļ�       (1,+Infinity]������
        if (position < -1) { // [-Infinity,-1)
        	ViewHelper.setAlpha(view, 0);
        } else if (position <= 0) { // [-1,0]  
        	//������ת����Ϊ�ײ�����
             ViewHelper.setPivotX(view, pageWidth/2);
             ViewHelper.setPivotY(view, pageHeight);
             //������ת�Ƕ�
             ViewHelper.setRotation(view, position*MAX_ROTATE); //-20~0
        } else if (position <= 1) { // (0,1]   ���Ҳ�ɼ�ʱ�ı����ż�͸��
        	//������ת����Ϊ�ײ�����
            ViewHelper.setPivotX(view, pageWidth/2);
            ViewHelper.setPivotY(view, pageHeight);
            //������ת�Ƕ�
            ViewHelper.setRotation(view, position*MAX_ROTATE); //0~20
        } else { // (1,+Infinity]
        	ViewHelper.setAlpha(view, 0);
        }
    }
}

