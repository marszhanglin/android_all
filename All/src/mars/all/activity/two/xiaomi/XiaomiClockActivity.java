package mars.all.activity.two.xiaomi;

import mars.all.activity.two.xiaomi.view.ClockView;
import android.app.Activity;
import android.os.Bundle;

public class XiaomiClockActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new ClockView(this));
	}

	
}
