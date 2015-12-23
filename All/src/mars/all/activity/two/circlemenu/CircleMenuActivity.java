package mars.all.activity.two.circlemenu;

import mars.all.MainActivity;
import mars.all.R;
import mars.all.activity.two.circlemenu.CircleMenuLayout.CircleMenuInterface;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.Toast;

/**
 * 
 * ���� ����Բ�ε��� ��˶�ύ
 * 
 * @author Mars zhang
 * @created 2015-12-11 ����2:18:30
 */
public class CircleMenuActivity extends Activity {
	private CircleMenuLayout mCircleMenuLayout;

	private String[] mItemTexts = new String[] { "��ȫ���� ", "��ɫ����", "Ͷ�����",
			"ת�˻��", "�ҵ��˻�", "���ÿ�" };
	private int[] mItemImgs = new int[] { R.drawable.home_mbank_1_normal,
			R.drawable.home_mbank_2_normal, R.drawable.home_mbank_3_normal,
			R.drawable.home_mbank_4_normal, R.drawable.home_mbank_5_normal,
			R.drawable.home_mbank_6_normal };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.circle_menu_at);
		mCircleMenuLayout = (CircleMenuLayout) findViewById(R.id.circle_menu_at_cl);
		mCircleMenuLayout.setMenuIconAndText(mItemTexts, mItemImgs);
		
 
		mCircleMenuLayout.setmCircleMenuInterface(new CircleMenuInterface()
		{

			@Override
			public void itemOnClick(View view, int pos) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void centerClick(View view) {
				// TODO Auto-generated method stub
				
			}
			
			 
		});
	}

}
