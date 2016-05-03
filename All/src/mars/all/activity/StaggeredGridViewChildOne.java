package mars.all.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mars.all.activity.HoveringScroll.HoveringScrollSampleActivity;
import mars.all.activity.activitysource.StartOtherActivity;
import mars.all.activity.at_staggeredgrid.AnimationsActivity;
import mars.all.activity.at_staggeredgrid.GestureDetectorActivity;
import mars.all.activity.at_staggeredgrid.MatrixActivity;
import mars.all.activity.at_staggeredgrid.ZoomImageActivity;
import mars.all.activity.at_staggeredgrid.viewpager.DifferentViewPagerActivity;
import mars.all.activity.at_staggeredgrid.viewpager.MyviewPagerActivity;
import mars.all.activity.at_staggeredgrid.viewpager.MyviewPagerActivity01;
import mars.all.activity.mediaplay.MediaActivity;
import mars.all.activity.one.timeline.TimeLineActivity;
import mars.all.adapter.PictureAdapter;
import mars.all.bean.ItemDataAtMain;
import mars.all.view.StaggeredGridView.StaggeredGridView;
import mars.all.view.StaggeredGridView.StaggeredGridView.OnItemClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;


/**
 * 继承至StaggeredGridViewActivity 
 * 只需重写oncreate方法  加对象就好了
 * @author EVECOM-PC
 *
 */
public class StaggeredGridViewChildOne extends StaggeredGridViewActivity { 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		List<ItemDataAtMain> items=new ArrayList<ItemDataAtMain>();
		ItemDataAtMain item1=new ItemDataAtMain("可放大缩小的Image\n（自定义控件） ", new Date());
		ItemDataAtMain item2=new ItemDataAtMain("AnimationsActivity\nYOYO", new Date());
		ItemDataAtMain item3=new ItemDataAtMain("手势第一种\nGestureDetector", new Date());
		ItemDataAtMain item4=new ItemDataAtMain("手势第二种\n", new Date());
		ItemDataAtMain item5=new ItemDataAtMain("对象矩阵变换，\nMatrix", new Date());
		ItemDataAtMain item6=new ItemDataAtMain("viewpager的动画及3.0以下的兼容", new Date());
		ItemDataAtMain item7=new ItemDataAtMain("viewpager的动画及3.0以下的兼容（不同之处在于这个上面有按钮，而且每个页面不同可以自定义一些控件", new Date());
		ItemDataAtMain item8=new ItemDataAtMain("viewpager的动画及3.0以下的兼容（不同之处在于增加了属性动画ViewHepler", new Date());
		ItemDataAtMain item9=new ItemDataAtMain("activity的启动机制", new Date());
		ItemDataAtMain item10=new ItemDataAtMain("HoveringScroll浮动的View", new Date());
		ItemDataAtMain item11=new ItemDataAtMain("背景音乐及activity的生命周期使用", new Date());
		ItemDataAtMain item12=new ItemDataAtMain("纵向时间轴效果", new Date());
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
		items.add(item11);
		items.add(item12);
        PictureAdapter pictureAdapter = new PictureAdapter(items,
                this);
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
				case 8:
					intent=new Intent(getApplicationContext(), StartOtherActivity.class);
					startActivity(intent);
					break;
				case 9:
					intent=new Intent(getApplicationContext(), HoveringScrollSampleActivity.class);
					startActivity(intent);
					break;
				case 10:
					intent=new Intent(getApplicationContext(), MediaActivity.class);
					startActivity(intent);
					break;
				case 11:
                    intent=new Intent(getApplicationContext(), TimeLineActivity.class);
                    startActivity(intent);
                    break;
				default:
					break;
				}
			}
		});
        pictureAdapter.notifyDataSetChanged();
	}
	
}
