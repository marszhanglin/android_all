package mars.all.activity.two.xiaomi;

import mars.all.activity.base.BaseActivity;
import mars.all.activity.two.xiaomi.view.ClockView;
import android.os.Bundle;

public class XiaomiClockActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);  
		setContentView(new ClockView(this));
	}

	
}
