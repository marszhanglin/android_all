package mars.all.activity.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
/**
 *  基础的Activity类
 * 2015-2-15下午7:21:37 类BaseActivity
 * @author Mars zhang
 *
 */
public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 启动activity时不自动弹出软键盘
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		//不要标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	}
}
