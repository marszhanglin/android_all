package mars.all.activity.at_staggeredgrid.viewpager;

import java.util.ArrayList;
import java.util.List;

import mars.all.R;
import mars.all.activity.base.BaseActivity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
/**
 * 这个Viewpager里面只有图片   在原有的基础上做了点变化这里就不需要imageViews这个集合改成Views
 * 用上LayoutInflater所以这里就需要 10个的layout文件   比较麻烦所以就弄了2个
 * LayoutInflater mLi = LayoutInflater.from(this);  
 * View view1 = mLi.inflate(R.layout.view1, null);  
 * 2015-2-16下午4:25:16 类MyviewPagerActivity
 * @author Mars zhang
 *
 */
public class MyviewPagerActivity01 extends BaseActivity {
	private ViewPager viewpager; 
	
	List<View> views=new ArrayList<View>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_viewpager);
		
		//1、先初始化好几个view并加载到views里面
		LayoutInflater layoutInflater=LayoutInflater.from(this);
		View view1=layoutInflater.inflate(R.layout.my_viewpageractivity_01_01, null);
		View view2=layoutInflater.inflate(R.layout.my_viewpageractivity_01_02, null);
		views.add(view1);
		views.add(view2);
		
		//2、
		viewpager=(ViewPager) findViewById(R.id.my_viewpager_id);
		//3、设置适配器
		viewpager.setAdapter(new PagerAdapter() {
			@Override//初始化item   在这里布置每个page的布局在这里添加控件
			public Object instantiateItem(ViewGroup container, int position) { 
				container.addView(views.get(position)); 
				return views.get(position);
			}
			@Override//destroyItem
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView(views.get(position));
			}
			@Override //判断  每个adapter都有这个
			public boolean isViewFromObject(View view, Object object) {
				return view==object;
			}
			@Override  //返回有多少页
			public int getCount() {
				return views.size();
			}
		});
	}
	//第一个页面的按钮
	public void my_viewpager_activity_01_01_button_onclick(View view) {
		Toast.makeText(getApplicationContext(), "让你点你就点啊！点你妹==凸", 1).show();
	}
	//第二个页面的按钮
	public void my_viewpager_activity_02_01_button_onclick(View view) {
		Toast.makeText(getApplicationContext(), "说了不要点！点你妹=。=", 1).show();
	}
	
}
