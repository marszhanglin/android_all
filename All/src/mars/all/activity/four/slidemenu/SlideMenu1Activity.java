package mars.all.activity.four.slidemenu;

import mars.all.R;
import mars.all.activity.base.BaseActivity;
import android.os.Bundle;
import android.view.View;

import com.slidingmenu.lib.SlidingMenu;

/**
 * 
 * 描述
 * 
 * @author Mars zhang
 * @created 2016-3-8 下午2:59:45
 */
public class SlideMenu1Activity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.four_slidemenu1_activity);
        
        SlidingMenu leftSlidingMenu = new SlidingMenu(this);
        // 左侧还是右侧
        leftSlidingMenu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        leftSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        // 阴影宽度
        leftSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);
        // 阴影图片
        leftSlidingMenu.setShadowDrawable(R.drawable.shadow);
        // 设置滑动菜单视图的宽度
        leftSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);

        // 设置渐入渐出效果的值
        leftSlidingMenu.setFadeDegree(0.35f);
        leftSlidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        leftSlidingMenu.setMenu(R.layout.four_slidemenu1_layout);
    }

    /**
     * 
     * 描述
     * 
     * @author Mars zhang
     * @created 2016-3-8 下午3:22:59
     * @param view
     */
    public void clickme(View view) {
        toast("点点点点点点", 0);
    }

    /**
     * 
     * 描述
     * 
     * @author Mars zhang
     * @created 2016-3-8 下午3:23:02
     * @param view
     */
    public void noclickme(View view) {
        toast("点你妹", 0);
    }
}
