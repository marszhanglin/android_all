package mars.all.activity.at_staggeredgrid.viewpager;

import java.util.ArrayList;
import java.util.List;

import mars.all.R;
import mars.all.activity.at_staggeredgrid.viewpager.pagetransformer.RotateDownTransformer;
import mars.all.activity.base.BaseActivity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
/**
 * 这个Viewpager里面只有图片    3.0后才可以用
 * 2015-2-16下午4:25:16 类MyviewPagerActivity
 * @author Mars zhang
 *
 */
public class MyviewPagerActivity extends BaseActivity {
	private ViewPager viewpager;
	private int[] imagesId={R.drawable.pager_image1,
			R.drawable.pager_image2,
			R.drawable.pager_image3,
			R.drawable.pager_image4,
			R.drawable.pager_image5,
			R.drawable.pager_image6,
			R.drawable.pager_image7,
			R.drawable.pager_image8,
			R.drawable.pager_image9,
			R.drawable.pager_image10};
	private List<ImageView> imageViews =new ArrayList<ImageView>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_viewpager);
		//1、
		viewpager=(ViewPager) findViewById(R.id.my_viewpager_id);
		//4、属性动画DepthPageTransformer
//		viewpager.setPageTransformer(true, new DepthPageTransformer());
		viewpager.setPageTransformer(true, new RotateDownTransformer());
		//2、设置适配器
		viewpager.setAdapter(new PagerAdapter() {
			@Override//初始化item   在这里布置每个page的布局在这里添加控件
			public Object instantiateItem(ViewGroup container, int position) {
				//打算在每个页面上添加一个图片    也可以不是图片switch判断下每个page要放什么
				ImageView imageView=new ImageView(MyviewPagerActivity.this);
				imageView.setImageResource(imagesId[position]);
				//不让图片变形
				imageView.setScaleType(ScaleType.CENTER_CROP);
				
				//把imageView加到container中  ？？如果一个page不止一个控件怎么办
				container.addView(imageView);
				//把imageView加到list中
				imageViews.add(imageView);
				//返回imageView
				return imageView;
			}
			@Override//destroyItem
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView(imageViews.get(position));
			}
			
			@Override //判断  每个adapter都有这个
			public boolean isViewFromObject(View view, Object object) {
				return view==object;
			}
			
			@Override  //返回有多少页
			public int getCount() {
				return imagesId.length;
			}
		});
	}
}
