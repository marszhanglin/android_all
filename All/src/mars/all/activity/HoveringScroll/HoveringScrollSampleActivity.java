package mars.all.activity.HoveringScroll;

import mars.all.R;
import mars.all.activity.HoveringScroll.HoveringScrollview.OnScrollListener;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


/**
 * 上滑停靠顶端悬浮框,下滑恢复原有位置<br><br>
 * 
 * 
 * https://git.oschina.net/steve/HoveringScroll.git
 * @author steve
 *
 */
public class HoveringScrollSampleActivity extends Activity implements
		OnScrollListener {

	private HoveringScrollview hoveringScrollview;
	private int searchLayoutTop;

	LinearLayout hoveringLayout;
	LinearLayout search01, search02;
	RelativeLayout rlayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hovering_scoll);

		initViews();
	}

	private void initViews() {
		hoveringLayout = (LinearLayout) findViewById(R.id.hoveringLayout);
		hoveringScrollview = (HoveringScrollview) findViewById(R.id.hoveringScrollview);
		search01 = (LinearLayout) findViewById(R.id.search01);
		search02 = (LinearLayout) findViewById(R.id.search02);
		rlayout = (RelativeLayout) findViewById(R.id.rlayout);
		hoveringScrollview.setOnScrollListener(this);// set Listener
	}

	@Override //监控一个Activity加载完毕（完成渲染）-onWindowFocusChanged  在这个回调（调用时）能获取UI准确数据
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			searchLayoutTop = rlayout.getBottom();// 获取searchLayout的顶部位置
		}
	}

	@Override
	public void onScroll(int scrollY) {
		// TODO Auto-generated method stub
		Log.v("mars", "scrollY="+scrollY+"searchLayoutTop="+searchLayoutTop);
		Toast.makeText(getApplicationContext(), "scrollY="+scrollY+"searchLayoutTop="+searchLayoutTop, 0).show();
		if (scrollY >= searchLayoutTop) {
			if (hoveringLayout.getParent() != search01) {//当父控件不是search01时  将浮动控件addview进去
				search02.removeView(hoveringLayout);
				search01.addView(hoveringLayout);
			}
		} else {
			if (hoveringLayout.getParent() != search02) {//当父控件不是search02时  将浮动控件addview进去
				search01.removeView(hoveringLayout);
				search02.addView(hoveringLayout);
			}
		}
	}

	public void clickListenerMe(View view) {
		if (view.getId() == R.id.btnQiaBuy) {
			Toast.makeText(this, "抢购成功", 0).show();
		}
	}

}
