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
 * ���Viewpager����ֻ��ͼƬ    3.0��ſ�����
 * 2015-2-16����4:25:16 ��MyviewPagerActivity
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
		//1��
		viewpager=(ViewPager) findViewById(R.id.my_viewpager_id);
		//4�����Զ���DepthPageTransformer
//		viewpager.setPageTransformer(true, new DepthPageTransformer());
		viewpager.setPageTransformer(true, new RotateDownTransformer());
		//2������������
		viewpager.setAdapter(new PagerAdapter() {
			@Override//��ʼ��item   �����ﲼ��ÿ��page�Ĳ�����������ӿؼ�
			public Object instantiateItem(ViewGroup container, int position) {
				//������ÿ��ҳ�������һ��ͼƬ    Ҳ���Բ���ͼƬswitch�ж���ÿ��pageҪ��ʲô
				ImageView imageView=new ImageView(MyviewPagerActivity.this);
				imageView.setImageResource(imagesId[position]);
				//����ͼƬ����
				imageView.setScaleType(ScaleType.CENTER_CROP);
				
				//��imageView�ӵ�container��  �������һ��page��ֹһ���ؼ���ô��
				container.addView(imageView);
				//��imageView�ӵ�list��
				imageViews.add(imageView);
				//����imageView
				return imageView;
			}
			@Override//destroyItem
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView(imageViews.get(position));
			}
			
			@Override //�ж�  ÿ��adapter�������
			public boolean isViewFromObject(View view, Object object) {
				return view==object;
			}
			
			@Override  //�����ж���ҳ
			public int getCount() {
				return imagesId.length;
			}
		});
	}
}
