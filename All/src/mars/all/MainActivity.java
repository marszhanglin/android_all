package mars.all;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mars.all.activity.NiftyDialogEffectsActivity;
import mars.all.activity.StaggeredGridViewChildFour;
import mars.all.activity.StaggeredGridViewChildOne;
import mars.all.activity.StaggeredGridViewChildThree;
import mars.all.activity.StaggeredGridViewChildTwo;
import mars.all.adapter.PictureAdapter;
import mars.all.bean.ItemDataAtMain;
import mars.all.view.StaggeredGridView.StaggeredGridView;
import mars.all.view.StaggeredGridView.StaggeredGridView.OnItemClickListener;
import mars.all.view.qq50style.MyHorizontalScrollView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;


/**
 *	首页
 * @author Administrator
 *
 */
public class MainActivity extends Activity {
	/**自定义的横向滚动*/
	private MyHorizontalScrollView myHorizontalScrollView;
	/**瀑布流布局*/
	private StaggeredGridView staggeredGridView;//staggeredGridView1
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/**不要标题栏*/ 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main); 
		myHorizontalScrollView=(MyHorizontalScrollView) findViewById(R.id.activity_main_myhorizontal);
	
		staggeredGridView=(StaggeredGridView) findViewById(R.id.staggeredGridView1); 
		List<ItemDataAtMain> items=new ArrayList<ItemDataAtMain>();
		ItemDataAtMain item1=new ItemDataAtMain("比较乱还未整理:\n瀑布流布局\nYOYO动画\n手势\n矩阵\nViewpager\n滑动浮动控件\n背景音乐", new Date());
		ItemDataAtMain item2=new ItemDataAtMain("自定义View集合:", new Date());
		ItemDataAtMain item3=new ItemDataAtMain("鸿祥自定义View", new Date());
		ItemDataAtMain item4=new ItemDataAtMain("AccessibilityService\n功能详解\n微信抢红包助手原理", new Date());
		ItemDataAtMain item5=new ItemDataAtMain("没用", new Date());
		ItemDataAtMain item6=new ItemDataAtMain("没用", new Date());
		ItemDataAtMain item7=new ItemDataAtMain("没用", new Date());
		ItemDataAtMain item8=new ItemDataAtMain("没用", new Date());
		ItemDataAtMain item9=new ItemDataAtMain("没用", new Date());
		ItemDataAtMain item10=new ItemDataAtMain("没用", new Date());
		items.add(item1);
		items.add(item2);
		items.add(item3);
		items.add(item4);
		items.add(item5);
		items.add(item6);
		items.add(item7);
		items.add(item8);
		items.add(item9);
		items.add(item10);
        PictureAdapter pictureAdapter = new PictureAdapter(items,
                MainActivity.this);
        staggeredGridView.setAdapter(pictureAdapter);
        staggeredGridView.setOnItemClickListener(new OnItemClickListener() {
			/**瀑布流单项点击事件*/
			public void onItemClick(StaggeredGridView parent, View view, int position,
					long id) {
				YoYo.with(Techniques.Tada).duration(700).playOn(view);
				Toast.makeText(getApplicationContext(), "position:"+position, 0).show();
				
				Intent intent=null;
				switch (position) {
				case 0:
					intent=new Intent(getApplicationContext(), StaggeredGridViewChildOne.class);
					startActivity(intent);
					overridePendingTransition(R.anim.activity_in_heart , R.anim.activity_out_heart);
					break;
				case 1:
					intent=new Intent(getApplicationContext(), StaggeredGridViewChildTwo.class);
					startActivity(intent);
					break;
				case 2:
				    intent=new Intent(getApplicationContext(), StaggeredGridViewChildThree.class);
                    startActivity(intent);
					break;
				case 3: 
				    intent=new Intent(getApplicationContext(), StaggeredGridViewChildFour.class);
                    startActivity(intent);
					break;
				case 5:
					break;
				default:
					break;
				} 
			}
		});
        /**将点击时的背景颜色有黄色改成透明*/
        staggeredGridView.setSelector(new ColorDrawable(Color.parseColor("#00000000")));
        pictureAdapter.notifyDataSetChanged();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	/**切换监听事件*/
	public void toggle(View view){
		myHorizontalScrollView.toggle();
	}
	/**左边第一个item点击*/
	public void NiftyDialogEffects(View view){
		Intent intent=new Intent(getApplicationContext(), NiftyDialogEffectsActivity.class);
		startActivity(intent);
	}
}



