package mars.all.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mars.all.R;
import mars.all.activity.at_staggeredgrid.AnimationsActivity;
import mars.all.activity.at_staggeredgrid.GestureDetectorActivity;
import mars.all.activity.at_staggeredgrid.MatrixActivity;
import mars.all.activity.at_staggeredgrid.ZoomImageActivity;
import mars.all.activity.at_staggeredgrid.viewpager.DifferentViewPagerActivity;
import mars.all.activity.at_staggeredgrid.viewpager.MyviewPagerActivity;
import mars.all.activity.at_staggeredgrid.viewpager.MyviewPagerActivity01;
import mars.all.adapter.PictureAdapter;
import mars.all.bean.ItemDataAtMain;
import mars.all.view.StaggeredGridView.StaggeredGridView;
import mars.all.view.StaggeredGridView.StaggeredGridView.OnItemClickListener;
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
 * �ٲ������ֵĽ���   ��һ��ľŹ���    
 * 2014-11-6����2:09:05 ��StaggeredGridViewActivity
 * @author Mars zhang
 */
public class StaggeredGridViewActivity extends Activity {
//	/**�Զ���ĺ������*/
//	private MyHorizontalScrollView myHorizontalScrollView;
	/**�ٲ�������*/
	private StaggeredGridView staggeredGridView;//staggeredGridView1
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/**��Ҫ������*/ 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.staggered_gridview_main); 
//		myHorizontalScrollView=(MyHorizontalScrollView) findViewById(R.id.activity_main_myhorizontal);
	
		staggeredGridView=(StaggeredGridView) findViewById(R.id.staggeredGridView1); 
		List<ItemDataAtMain> items=new ArrayList<ItemDataAtMain>();
		ItemDataAtMain item1=new ItemDataAtMain("�ɷŴ���С��Image\n���Զ���ؼ��� ", new Date());
		ItemDataAtMain item2=new ItemDataAtMain("AnimationsActivity\nYOYO", new Date());
		ItemDataAtMain item3=new ItemDataAtMain("���Ƶ�һ��\nGestureDetector", new Date());
		ItemDataAtMain item4=new ItemDataAtMain("���Ƶڶ���\n", new Date());
		ItemDataAtMain item5=new ItemDataAtMain("�������任��\nMatrix", new Date());
		ItemDataAtMain item6=new ItemDataAtMain("viewpager�Ķ�����3.0���µļ���", new Date());
		ItemDataAtMain item7=new ItemDataAtMain("viewpager�Ķ�����3.0���µļ��ݣ���֮ͬ��������������а�ť������ÿ��ҳ�治ͬ�����Զ���һЩ�ؼ�", new Date());
		ItemDataAtMain item8=new ItemDataAtMain("viewpager�Ķ�����3.0���µļ��ݣ���֮ͬ���������������Զ���ViewHepler", new Date());
		ItemDataAtMain item9=new ItemDataAtMain("�ٲ��������ڲ�������item gridview��item����9  ", new Date());
		ItemDataAtMain item10=new ItemDataAtMain("�ٲ��������ڲ�������item gridview��item����10  ", new Date());
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
                StaggeredGridViewActivity.this);
        staggeredGridView.setAdapter(pictureAdapter);
        staggeredGridView.setOnItemClickListener(new OnItemClickListener() {
			/**�ٲ����������¼�*/
			public void onItemClick(StaggeredGridView parent, View view, int position,
					long id) {
				YoYo.with(Techniques.Tada).duration(700).playOn(view);
				Toast.makeText(getApplicationContext(), "position:"+position, 0).show();
				
				Intent intent=null;
				switch (position) {
				case 0:
					intent=new Intent(getApplicationContext(), ZoomImageActivity.class);
					startActivity(intent);
					break;
				case 1:
					intent=new Intent(getApplicationContext(), AnimationsActivity.class);
					startActivity(intent);
					break;
				case 2:
					intent=new Intent(getApplicationContext(), GestureDetectorActivity.class);
					startActivity(intent);
					break;
				case 4:
					intent=new Intent(getApplicationContext(), MatrixActivity.class);
					startActivity(intent);
					break;
				case 5:
					intent=new Intent(getApplicationContext(), MyviewPagerActivity.class);
					startActivity(intent);
					break;
				case 6:
					intent=new Intent(getApplicationContext(), MyviewPagerActivity01.class);
					startActivity(intent);
					break;
				case 7:
					intent=new Intent(getApplicationContext(), DifferentViewPagerActivity.class);
					startActivity(intent);
					break;
				default:
					break;
				}
				
				
			}
		});
        /**�����ʱ�ı�����ɫ�л�ɫ�ĳ�͸��*/
        staggeredGridView.setSelector(new ColorDrawable(Color.parseColor("#00000000")));
        pictureAdapter.notifyDataSetChanged();
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



