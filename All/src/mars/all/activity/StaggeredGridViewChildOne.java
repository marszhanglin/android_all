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
 * �̳���StaggeredGridViewActivity 
 * ֻ����дoncreate����  �Ӷ���ͺ���
 * @author EVECOM-PC
 *
 */
public class StaggeredGridViewChildOne extends StaggeredGridViewActivity { 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		List<ItemDataAtMain> items=new ArrayList<ItemDataAtMain>();
		ItemDataAtMain item1=new ItemDataAtMain("�ɷŴ���С��Image\n���Զ���ؼ��� ", new Date());
		ItemDataAtMain item2=new ItemDataAtMain("AnimationsActivity\nYOYO", new Date());
		ItemDataAtMain item3=new ItemDataAtMain("���Ƶ�һ��\nGestureDetector", new Date());
		ItemDataAtMain item4=new ItemDataAtMain("���Ƶڶ���\n", new Date());
		ItemDataAtMain item5=new ItemDataAtMain("�������任��\nMatrix", new Date());
		ItemDataAtMain item6=new ItemDataAtMain("viewpager�Ķ�����3.0���µļ���", new Date());
		ItemDataAtMain item7=new ItemDataAtMain("viewpager�Ķ�����3.0���µļ��ݣ���֮ͬ��������������а�ť������ÿ��ҳ�治ͬ�����Զ���һЩ�ؼ�", new Date());
		ItemDataAtMain item8=new ItemDataAtMain("viewpager�Ķ�����3.0���µļ��ݣ���֮ͬ���������������Զ���ViewHepler", new Date());
		ItemDataAtMain item9=new ItemDataAtMain("activity����������", new Date());
		ItemDataAtMain item10=new ItemDataAtMain("HoveringScroll������View", new Date());
		ItemDataAtMain item11=new ItemDataAtMain("�������ּ�activity����������ʹ��", new Date());
		ItemDataAtMain item12=new ItemDataAtMain("����ʱ����Ч��", new Date());
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
