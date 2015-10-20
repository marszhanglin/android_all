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
 * 瀑布流布局的界面   不一般的九宫格    
 * 2014-11-6下午2:09:05 类StaggeredGridViewActivity
 * @author Mars zhang
 */
public class StaggeredGridViewActivity extends Activity {
//	/**自定义的横向滚动*/
//	private MyHorizontalScrollView myHorizontalScrollView;
	/**瀑布流布局*/
	protected StaggeredGridView staggeredGridView;//staggeredGridView1
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/**不要标题栏*/ 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.staggered_gridview_main); 
//		myHorizontalScrollView=(MyHorizontalScrollView) findViewById(R.id.activity_main_myhorizontal);
	
		staggeredGridView=(StaggeredGridView) findViewById(R.id.staggeredGridView1); 
		
		
        /**将点击时的背景颜色有黄色改成透明*/
        staggeredGridView.setSelector(new ColorDrawable(Color.parseColor("#00000000")));
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
//	/**切换监听事件*/
//	public void toggle(View view){
//		myHorizontalScrollView.toggle();
//	} 
	/**左边第一个item点击*/
	public void NiftyDialogEffects(View view){
		Intent intent=new Intent(getApplicationContext(), NiftyDialogEffectsActivity.class);
		startActivity(intent);
	}
}



