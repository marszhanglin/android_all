package mars.all.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mars.all.R;
import mars.all.activity.two.flinggallery.FlingGalleryActivity;
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
 * <br>����Զ���View
 * @author EVECOM-PC
 *
 */
public class StaggeredGridViewChildTwo extends StaggeredGridViewActivity { 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		List<ItemDataAtMain> items=new ArrayList<ItemDataAtMain>();
		ItemDataAtMain item1=new ItemDataAtMain("Android���¿ؼ�", new Date());
		ItemDataAtMain item2=new ItemDataAtMain("uc��ī�����������������϶�Ч��", new Date());
		ItemDataAtMain item3=new ItemDataAtMain("111111111111111111", new Date());
		ItemDataAtMain item4=new ItemDataAtMain("111111111111111111", new Date());
		ItemDataAtMain item5=new ItemDataAtMain("111111111111111111", new Date());
		ItemDataAtMain item6=new ItemDataAtMain("111111111111111111", new Date());
		ItemDataAtMain item7=new ItemDataAtMain("111111111111111111", new Date());
		ItemDataAtMain item8=new ItemDataAtMain("111111111111111111", new Date());
		ItemDataAtMain item9=new ItemDataAtMain("111111111111111111", new Date());
		ItemDataAtMain item10=new ItemDataAtMain("11111111111111111", new Date());
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
					/*intent=new Intent(getApplicationContext(), ZoomImageActivity.class);
					startActivity(intent);*/
					break;
				case 1:
					intent=new Intent(getApplicationContext(), FlingGalleryActivity.class);
					startActivity(intent); 
					//����activity�л�����
					overridePendingTransition(R.anim.activity_in_heart , R.anim.activity_out_heart);
					//1.�����������startActivity()����finish()����֮�����"
//					overridePendingTransition(R.anim.activity_in_from_right, R.anim.activity_out_to_left);
					break;
				default:
					break;
				} 
			}
		});
        pictureAdapter.notifyDataSetChanged();
	}
	
}
