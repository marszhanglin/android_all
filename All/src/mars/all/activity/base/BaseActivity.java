package mars.all.activity.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
/**
 *  ������Activity��
 * 2015-2-15����7:21:37 ��BaseActivity
 * @author Mars zhang
 *
 */
public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ����activityʱ���Զ����������
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		//��Ҫ����
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	}
}
