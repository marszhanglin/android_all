package mars.all.activity.two.tree;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 
 * 描述
 * 
 * @author Mars zhang
 * @created 2015-12-15 上午11:10:49
 */
public class TreeItemOnClick implements OnItemClickListener {

    private TreeAdapter treeAdapter;

    public TreeItemOnClick(TreeAdapter treeAdapter) {
        super();
        this.treeAdapter = treeAdapter;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TreeData itemData = (TreeData) treeAdapter.getItem(position);

        if (!itemData.isHasChildren()) {// 无子节点
            return;
        }
        List<TreeData> topTreeDatas = treeAdapter.getTopNodes();
        List<TreeData> allTreeDatas = treeAdapter.getAllNodes();
        if (itemData.isExpanded()) {// 张开的
            itemData.setExpanded(false);
            List<TreeData> removeTreeData = new ArrayList<TreeData>();
            for (int i = position + 1; i < topTreeDatas.size(); i++) {
                TreeData indexTreeData = topTreeDatas.get(i);
                if (!(indexTreeData.getLevel() > itemData.getLevel()))
                    break;
                removeTreeData.add(indexTreeData);
            }
            topTreeDatas.removeAll(removeTreeData);// 删除不要的 
            treeAdapter.notifyDataSetChanged();
        } else {// 关闭的
            itemData.setExpanded(true);
            int i = 1;
            for (TreeData treeData : allTreeDatas) {
                if (treeData.getParendId() == itemData.getId()) {
                    treeData.setExpanded(false);
                    topTreeDatas.add(position + i, treeData);
                    i++;
                }
            }
            treeAdapter.notifyDataSetChanged();
        }

    }

}
