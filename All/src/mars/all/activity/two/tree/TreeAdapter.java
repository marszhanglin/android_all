package mars.all.activity.two.tree;

import java.util.ArrayList;

import mars.all.R;
import mars.all.util.MyLayoutUtil;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * 
 * ���� ����������
 * 
 * @author Mars zhang
 * @created 2015-12-15 ����10:59:51
 */
public class TreeAdapter extends BaseAdapter {
    /** ���е����ݼ��� */
    private ArrayList<TreeData> allNodes;
    /** ����Ԫ�ؽ�� */
    private ArrayList<TreeData> topNodes;
    /** LayoutInflater */
    private LayoutInflater inflater;
    /** item�������������� */
    private int indentionBase;

    public TreeAdapter(ArrayList<TreeData> topNodes, ArrayList<TreeData> allNodes, Context context) {
        super();
        indentionBase = 70;
        this.inflater = LayoutInflater.from(context);
        this.allNodes = allNodes;
        this.topNodes = topNodes;
    }

    public ArrayList<TreeData> getAllNodes() {
        return allNodes;
    }

    public ArrayList<TreeData> getTopNodes() {
        return topNodes;
    }
    
    public void setAllNodes(ArrayList<TreeData> allNodes) {
        this.allNodes = allNodes;
    }

    public void setTopNodes(ArrayList<TreeData> topNodes) {
        this.topNodes = topNodes;
    }

    @Override
    public int getCount() {
        return null == topNodes ? 0 : topNodes.size();
    }

    @Override
    public Object getItem(int position) {
        return topNodes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (null == convertView) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.tree_data_item, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.tree_data_item_image);
            holder.textView = (TextView) convertView.findViewById(R.id.tree_data_item_text);
            convertView.setTag(holder);// setTag�洢View�Ķ�����Ϣ
        } else {
            holder = (ViewHolder) convertView.getTag(); // �������ܻ�ȡ��ViewHolder����
        }

        TreeData itemdata = topNodes.get(position);
        // ����
        MyLayoutUtil.setMargin(holder.imageView, itemdata.getLevel() * indentionBase, holder.imageView.getPaddingTop(),
                holder.imageView.getPaddingRight(), holder.imageView.getPaddingBottom());
        if (itemdata.isHasChildren() && itemdata.isExpanded()) {
            holder.imageView.setBackgroundResource(R.drawable.ic_launcher);
            holder.textView.setText(itemdata.getContentText());
            holder.imageView.setVisibility(View.VISIBLE);
            holder.textView.setVisibility(View.VISIBLE);
        } else if (itemdata.isHasChildren() && !itemdata.isExpanded()) {
            holder.imageView.setBackgroundResource(R.drawable.ic_launcher);
            holder.textView.setText(itemdata.getContentText());
            holder.textView.setText(itemdata.getContentText());
            holder.imageView.setVisibility(View.VISIBLE);
            holder.textView.setVisibility(View.VISIBLE);
        } else if (!itemdata.isHasChildren()) {
            holder.imageView.setBackgroundResource(R.drawable.ic_launcher);
            holder.textView.setText(itemdata.getContentText());
            holder.imageView.setVisibility(View.VISIBLE);
            holder.textView.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView textView;
    }

}
