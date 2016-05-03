package mars.all.activity.one.timeline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mars.all.R;
import mars.all.activity.one.timeline.adapter.TimeLineAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * 
 * 描述   纵向 时间轴
 * @author Mars zhang
 * @created 2016-4-25 上午9:37:16
 */
public class TimeLineActivity extends Activity {

    ListView mListView ;
    
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.one_timeline_list);
        
        initView();
    }


    /**
     * 
     * 描述
     * @author Mars zhang
     * @created 2016-4-25 上午9:53:42
     */
    private void initView() {
        mListView = (ListView) findViewById(R.id.one_timeline_listview);
        List<Map<String, Object>> listData=new ArrayList<Map<String,Object>>();
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("year", "2014");
        map.put("month", "03/12");
        map.put("title", "标题标题标题标题标题标题1");
        listData.add(map);
        
        map=new HashMap<String, Object>();
        map.put("year", "2015");
        map.put("month", "03/13");
        map.put("title", "标题标题标题标题标题标题2");
        listData.add(map);
        
        map=new HashMap<String, Object>();
        map.put("year", "2016");
        map.put("month", "03/13");
        map.put("title", "标题标题标题标题标题标题4");
        listData.add(map);
        
        map=new HashMap<String, Object>();
        map.put("year", "2016");
        map.put("month", "03/17");
        map.put("title", "标题标题标题标题标题标题5");
        listData.add(map);
        
        mListView.setAdapter(new TimeLineAdapter(TimeLineActivity.this,listData));
        
        
    }
    
    
    
}
