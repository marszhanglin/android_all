package mars.all.activity;

import mars.all.R;
import mars.all.view.StaggeredGridView.StaggeredGridView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
/**
 * �ٲ������ֵĽ���   ��һ��ľŹ���    
 * 2014-11-6����2:09:05 ��StaggeredGridViewActivity
 * @author Mars zhang
 */
public class StaggeredGridViewActivity extends Activity {
//	/**�Զ���ĺ������*/
//	private MyHorizontalScrollView myHorizontalScrollView;
	/**�ٲ�������*/
	protected StaggeredGridView staggeredGridView;//staggeredGridView1
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/**��Ҫ������*/ 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.staggered_gridview_main); 
//		myHorizontalScrollView=(MyHorizontalScrollView) findViewById(R.id.activity_main_myhorizontal);
	
		staggeredGridView=(StaggeredGridView) findViewById(R.id.staggeredGridView1); 
		
		
        /**�����ʱ�ı�����ɫ�л�ɫ�ĳ�͸��*/
        staggeredGridView.setSelector(new ColorDrawable(Color.parseColor("#00000000")));
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
//	/**�л������¼�*/
//	public void toggle(View view){
//		myHorizontalScrollView.toggle();
//	} 
	/**��ߵ�һ��item���*/
	public void NiftyDialogEffects(View view){
		Intent intent=new Intent(getApplicationContext(), NiftyDialogEffectsActivity.class);
		startActivity(intent);
	}
}



