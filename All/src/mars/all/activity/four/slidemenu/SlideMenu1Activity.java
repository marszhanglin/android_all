package mars.all.activity.four.slidemenu;

import mars.all.R;
import mars.all.activity.base.BaseActivity;
import android.os.Bundle;
import android.view.View;

import com.slidingmenu.lib.SlidingMenu;

/**
 * 
 * ����
 * 
 * @author Mars zhang
 * @created 2016-3-8 ����2:59:45
 */
public class SlideMenu1Activity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.four_slidemenu1_activity);
        
        SlidingMenu leftSlidingMenu = new SlidingMenu(this);
        // ��໹���Ҳ�
        leftSlidingMenu.setMode(SlidingMenu.LEFT);
        // ���ô�����Ļ��ģʽ
        leftSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        // ��Ӱ���
        leftSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);
        // ��ӰͼƬ
        leftSlidingMenu.setShadowDrawable(R.drawable.shadow);
        // ���û����˵���ͼ�Ŀ��
        leftSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);

        // ���ý��뽥��Ч����ֵ
        leftSlidingMenu.setFadeDegree(0.35f);
        leftSlidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        leftSlidingMenu.setMenu(R.layout.four_slidemenu1_layout);
    }

    /**
     * 
     * ����
     * 
     * @author Mars zhang
     * @created 2016-3-8 ����3:22:59
     * @param view
     */
    public void clickme(View view) {
        toast("�������", 0);
    }

    /**
     * 
     * ����
     * 
     * @author Mars zhang
     * @created 2016-3-8 ����3:23:02
     * @param view
     */
    public void noclickme(View view) {
        toast("������", 0);
    }
}
