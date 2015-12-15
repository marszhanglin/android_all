package mars.all.activity.two.tree;

import java.util.ArrayList;
import java.util.UUID;

import mars.all.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * 
 * 描述 N级Tree
 * 
 * @author Mars zhang
 * @created 2015-12-15 上午10:54:51
 */
public class TreeActivity extends Activity {

    private ListView treeListView;
    private TreeAdapter treeAdapter;
    private ArrayList<TreeData> topNodes = new ArrayList<TreeData>();
    private ArrayList<TreeData> allNodes = new ArrayList<TreeData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tree_activity);
        init();
    }

    private void init() {
        treeListView = (ListView) findViewById(R.id.tree_at_listview);
        initdata();
        treeAdapter = new TreeAdapter(topNodes, allNodes, getApplicationContext());
        treeListView.setAdapter(treeAdapter);
        treeListView.setOnItemClickListener(new TreeItemOnClick(treeAdapter));
    }

    private void initdata() {
        TreeData fz = new TreeData("福州", 0, UUID.randomUUID() + "", "", true, false);
        TreeData gl = new TreeData("鼓楼", fz.getLevel() + 1, UUID.randomUUID() + "", fz.getId(), true, false);
        TreeData xh = new TreeData("西湖", gl.getLevel() + 1, UUID.randomUUID() + "", gl.getId(), true, false);
        TreeData xm = new TreeData("厦门", 0, UUID.randomUUID() + "", "", true, false);
        TreeData hl = new TreeData("湖里", xm.getLevel() + 1, UUID.randomUUID() + "", xm.getId(), true, false);


        allNodes.add(fz);
        allNodes.add(gl);
        allNodes.add(xh);
        allNodes.add(xm);
        allNodes.add(hl);
        
        topNodes.add(fz);
        topNodes.add(xm);
    }
}
