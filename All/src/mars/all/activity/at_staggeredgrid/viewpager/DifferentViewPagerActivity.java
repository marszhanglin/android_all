package mars.all.activity.at_staggeredgrid.viewpager;

import java.util.ArrayList;
import java.util.List;

import mars.all.R;
import mars.all.activity.at_staggeredgrid.viewpager.pagetransformer.DepthPageTransformer;
import mars.all.activity.base.BaseActivity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
/**
 *  
 * �����������ǰ�����汾����������˶�����ע������3.0֮ǰû�����Զ����������㶮�ġ����� 
 * 2015-2-16����4:25:16 ��MyviewPagerActivity
 * @author Mars zhang
 *
 */
public class DifferentViewPagerActivity extends BaseActivity {
	private ViewPager viewpager; 
	
	List<View> views=new ArrayList<View>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_viewpager);
		
		//1���ȳ�ʼ���ü���view�����ص�views����
		LayoutInflater layoutInflater=LayoutInflater.from(this);
		View view1=layoutInflater.inflate(R.layout.my_viewpageractivity_01_01, null);
		View view2=layoutInflater.inflate(R.layout.my_viewpageractivity_01_02, null);
		views.add(view1);
		views.add(view2);
		
		//2��
		viewpager=(ViewPager) findViewById(R.id.my_viewpager_id);
		
		//4���ؼ�����
		viewpager.setPageTransformer(true, new DepthPageTransformer());
		//3������������
		viewpager.setAdapter(new PagerAdapter() {
			@Override//��ʼ��item   �����ﲼ��ÿ��page�Ĳ�����������ӿؼ�
			public Object instantiateItem(ViewGroup container, int position) { 
				container.addView(views.get(position)); 
				return views.get(position);
			}
			@Override//destroyItem
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView(views.get(position));
			}
			@Override //�ж�  ÿ��adapter�������
			public boolean isViewFromObject(View view, Object object) {
				return view==object;
			}
			@Override  //�����ж���ҳ
			public int getCount() {
				return views.size();
			}
		});
	}
	//��һ��ҳ��İ�ť
	public void my_viewpager_activity_01_01_button_onclick(View view) {
		Toast.makeText(getApplicationContext(), "�������͵㰡��������==͹", 1).show();
	}
	//�ڶ���ҳ��İ�ť
	public void my_viewpager_activity_02_01_button_onclick(View view) {
		Toast.makeText(getApplicationContext(), "˵�˲�Ҫ�㣡������=��=", 1).show();
	}
	
}
