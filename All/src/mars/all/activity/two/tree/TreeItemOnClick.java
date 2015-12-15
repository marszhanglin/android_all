package mars.all.activity.two.tree;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 
 * ����
 * 
 * @author Mars zhang
 * @created 2015-12-15 ����11:10:49
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

        if (!itemData.isHasChildren()) {// ���ӽڵ�
            return;
        }
        List<TreeData> topTreeDatas = treeAdapter.getTopNodes();
        List<TreeData> allTreeDatas = treeAdapter.getAllNodes();
        if (itemData.isExpanded()) {// �ſ���
            itemData.setExpanded(false);
            List<TreeData> removeTreeData = new ArrayList<TreeData>();
            for (int i = position + 1; i < topTreeDatas.size(); i++) {
                TreeData indexTreeData = topTreeDatas.get(i);
                if (!(indexTreeData.getLevel() > itemData.getLevel()))
                    break;
                removeTreeData.add(indexTreeData);
            }
            topTreeDatas.removeAll(removeTreeData);// ɾ����Ҫ�� 
            treeAdapter.notifyDataSetChanged();
        } else {// �رյ�
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
