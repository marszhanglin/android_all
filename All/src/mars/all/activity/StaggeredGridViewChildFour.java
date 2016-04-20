package mars.all.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mars.all.R;
import mars.all.activity.four.accessabilityservicebase1.Base1AccessibilityActivity;
import mars.all.activity.four.slidemenu.SlideMenu1Activity;
import mars.all.activity.two.addview.AddViewActivity;
import mars.all.activity.two.androidannotations.AndroidAnnotationsTest_;
import mars.all.activity.two.asytree.AsyTreeActivity;
import mars.all.activity.two.circlemenu.CircleMenuActivity;
import mars.all.activity.two.eventBus.EventFirstActivity;
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
  * 
  * 描述  跟着鸿详学自定义View
  * @author Mars zhang
  * @created 2016-2-29 下午8:38:53
  */
public class StaggeredGridViewChildFour extends StaggeredGridViewActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        List<ItemDataAtMain> items = new ArrayList<ItemDataAtMain>();
        ItemDataAtMain item1 = new ItemDataAtMain("AccessibilityService 功能详解", new Date()); 
        ItemDataAtMain item2 = new ItemDataAtMain("SlidingMenu \n使用详解\nDemo1", new Date()); 
        items.add(item1);
        items.add(item2);
        /* items.add(item3);
        items.add(item4);
        items.add(item5);
        items.add(item6);
        items.add(item7);
        items.add(item8);
        items.add(item9);
        items.add(item10);
        items.add(item11);
        items.add(item12);*/
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
                        intent=new Intent(getApplicationContext(),
                        Base1AccessibilityActivity.class); 
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getApplicationContext(), SlideMenu1Activity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getApplicationContext(), XiaomiClockActivity.class);
                        startActivity(intent);
                        // 设置activity切换动画
                        overridePendingTransition(R.anim.activity_in_heart, R.anim.activity_out_heart);
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
                        AndroidAnnotationsTest_.intent(StaggeredGridViewChildFour.this)
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
