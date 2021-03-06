package mars.all.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mars.all.R;
import mars.all.activity.two.addview.AddViewActivity;
import mars.all.activity.two.androidannotations.AndroidAnnotationsTest_;
import mars.all.activity.two.asytree.AsyTreeActivity;
import mars.all.activity.two.circlemenu.CircleMenuActivity;
import mars.all.activity.two.eventBus.EventFirstActivity;
import mars.all.activity.two.flinggallery.FlingGalleryActivity;
import mars.all.activity.two.touch.TouchEventdispatchActivity;
import mars.all.activity.two.tree.TreeActivity;
import mars.all.activity.two.viewgroup.FourSignViewGroupActivity;
import mars.all.activity.two.xiaomi.XiaomiClockActivity;
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
 * 继承至StaggeredGridViewActivity 只需重写oncreate方法 加对象就好了 <br>
 * 存放自定义View
 * 
 * @author EVECOM-PC
 * 
 */
public class StaggeredGridViewChildTwo extends StaggeredGridViewActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<ItemDataAtMain> items = new ArrayList<ItemDataAtMain>();
        ItemDataAtMain item1 = new ItemDataAtMain("Android徽章控件", new Date());
        ItemDataAtMain item2 = new ItemDataAtMain("uc和墨迹天气那样的左右拖动效果", new Date());
        ItemDataAtMain item3 = new ItemDataAtMain("小米时钟View", new Date());
        ItemDataAtMain item4 = new ItemDataAtMain("EventBus使用组件间通信", new Date());
        ItemDataAtMain item5 = new ItemDataAtMain("ViewGroup详解", new Date());
        ItemDataAtMain item6 = new ItemDataAtMain("建行圆形导航", new Date());
        ItemDataAtMain item7 = new ItemDataAtMain("树型", new Date());
        ItemDataAtMain item8 = new ItemDataAtMain("异步树", new Date());
        ItemDataAtMain item9 = new ItemDataAtMain("动态添加View", new Date());
        ItemDataAtMain item10 = new ItemDataAtMain("自定义View走马灯", new Date());
        ItemDataAtMain item11 = new ItemDataAtMain("AndroidAnnotations的IOC依赖注入框架", new Date());
        ItemDataAtMain item12 = new ItemDataAtMain("Android Touch事件传递机制通俗讲解", new Date());
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
        PictureAdapter pictureAdapter = new PictureAdapter(items, this);
        staggeredGridView.setAdapter(pictureAdapter);
        staggeredGridView.setOnItemClickListener(new OnItemClickListener() {
            /** 瀑布流单项点击事件 */
            public void onItemClick(StaggeredGridView parent, View view, int position, long id) {
                YoYo.with(Techniques.Tada).duration(700).playOn(view);
                Toast.makeText(getApplicationContext(), "position:" + position, 0).show();

                Intent intent = null;
                switch (position) {
                    case 0:
                        /*
                         * intent=new Intent(getApplicationContext(),
                         * ZoomImageActivity.class); startActivity(intent);
                         */
                        break;
                    case 1:
                        intent = new Intent(getApplicationContext(), FlingGalleryActivity.class);
                        startActivity(intent);
                        // 设置activity切换动画
                        overridePendingTransition(R.anim.activity_in_heart, R.anim.activity_out_heart);
                        // 1.它必需紧挨着startActivity()或者finish()函数之后调用"
                        // overridePendingTransition(R.anim.activity_in_from_right,
                        // R.anim.activity_out_to_left);
                        break;
                    case 2:
                        intent = new Intent(getApplicationContext(), XiaomiClockActivity.class);
                        startActivity(intent);
                        // 设置activity切换动画
                        overridePendingTransition(R.anim.activity_in_heart, R.anim.activity_out_heart);
                        // 1.它必需紧挨着startActivity()或者finish()函数之后调用"
                        // overridePendingTransition(R.anim.activity_in_from_right,
                        // R.anim.activity_out_to_left);
                        break;
                    case 3:
                        intent = new Intent(getApplicationContext(), EventFirstActivity.class);
                        startActivity(intent);
                        // 设置activity切换动画
                        overridePendingTransition(R.anim.activity_in_heart, R.anim.activity_out_heart);
                        break;
                    case 4:
                        intent = new Intent(getApplicationContext(), FourSignViewGroupActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(getApplicationContext(), CircleMenuActivity.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent = new Intent(getApplicationContext(), TreeActivity.class);
                        startActivity(intent);
                        break;
                    case 7:
                        intent = new Intent(getApplicationContext(), AsyTreeActivity.class);
                        startActivity(intent);
                        break;
                    case 8:
                        intent = new Intent(getApplicationContext(), AddViewActivity.class);
                        startActivity(intent);
                        break;
                    case 10: 
                        AndroidAnnotationsTest_.intent(StaggeredGridViewChildTwo.this)
                        .start();
                        break;
                    case 11:
                        intent = new Intent(getApplicationContext(), TouchEventdispatchActivity.class);
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
