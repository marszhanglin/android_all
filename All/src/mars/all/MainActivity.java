package mars.all;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mars.all.activity.NiftyDialogEffectsActivity;
import mars.all.activity.StaggeredGridViewActivity;
import mars.all.activity.mediaplay.MediaActivity;
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
 *	��ҳ
 * @author Administrator
 *
 */
public class MainActivity extends Activity {
	/**�Զ���ĺ������*/
	private MyHorizontalScrollView myHorizontalScrollView;
	/**�ٲ�������*/
	private StaggeredGridView staggeredGridView;//staggeredGridView1
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/**��Ҫ������*/ 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main); 
		myHorizontalScrollView=(MyHorizontalScrollView) findViewById(R.id.activity_main_myhorizontal);
	
		staggeredGridView=(StaggeredGridView) findViewById(R.id.staggeredGridView1); 
		List<ItemDataAtMain> items=new ArrayList<ItemDataAtMain>();
		ItemDataAtMain item1=new ItemDataAtMain("�ٲ��������ڲ�������item gridview��item����1  ", new Date());
		ItemDataAtMain item2=new ItemDataAtMain("�������ּ�activity����������ʹ��", new Date());
		ItemDataAtMain item3=new ItemDataAtMain("û��", new Date());
		ItemDataAtMain item4=new ItemDataAtMain("û��", new Date());
		ItemDataAtMain item5=new ItemDataAtMain("û��", new Date());
		ItemDataAtMain item6=new ItemDataAtMain("û��", new Date());
		ItemDataAtMain item7=new ItemDataAtMain("û��", new Date());
		ItemDataAtMain item8=new ItemDataAtMain("û��", new Date());
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
                MainActivity.this);
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
					intent=new Intent(getApplicationContext(), StaggeredGridViewActivity.class);
					startActivity(intent);
					break;
				case 1:
					intent=new Intent(getApplicationContext(), MediaActivity.class);
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
	/**�л������¼�*/
	public void toggle(View view){
		myHorizontalScrollView.toggle();
	} 
	/**��ߵ�һ��item���*/
	public void NiftyDialogEffects(View view){
		Intent intent=new Intent(getApplicationContext(), NiftyDialogEffectsActivity.class);
		startActivity(intent);
	}
}



