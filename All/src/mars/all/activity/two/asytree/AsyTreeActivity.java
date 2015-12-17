package mars.all.activity.two.asytree;

import java.util.ArrayList;
import java.util.HashMap;

import mars.all.R;
import net.mutil.view.aystree.AsyTreeAdapter;
import net.mutil.view.aystree.AsyTreeData;
import net.mutil.view.aystree.AsyTreeItemOnClick;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * 
 * 描述 N级异步Tree   
 * select id,typename as name from  EventType  where parenttypeid=? 
 * 
 * @author Mars zhang
 * @created 2015-12-15 上午10:54:51
 */
public class AsyTreeActivity extends Activity {

    private ListView treeListView;
    private AsyTreeAdapter asyTreeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tree_activity);
        initTree();
    }

    private void initTree() {
        treeListView = (ListView) findViewById(R.id.tree_at_listview);
        ArrayList<AsyTreeData> topNodes = new ArrayList<AsyTreeData>();
        ArrayList<AsyTreeData> allNodes = new ArrayList<AsyTreeData>();
        HashMap<String, String> postmap = new HashMap<String, String>();
//        postmap.put("sys_loginName", "sysadmin");
//        postmap.put("sys_imei", "898602C5139101001344");
//        postmap.put("sys_code", "3nbt5LXKusnGZ7cZ8fIiIdDBsyILhD0e1CziUqVdE4eRlMRO7j+wJMU92X/90c/");
        AsyTreeData root = new AsyTreeData("事件类型", 0, "10000", "", true, false);
        topNodes.add(root);
        allNodes.add(root);
        asyTreeAdapter = new AsyTreeAdapter(topNodes, allNodes, getApplicationContext(), "jfs/ecssp/mobile/eventCtr/getAsyEventTypeTree",postmap);
        treeListView.setAdapter(asyTreeAdapter);
        treeListView.setOnItemClickListener(new AsyTreeItemOnClick(asyTreeAdapter));
    }

}
