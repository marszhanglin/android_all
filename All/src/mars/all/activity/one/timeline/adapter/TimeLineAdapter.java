package mars.all.activity.one.timeline.adapter;

import java.util.List;
import java.util.Map;

import mars.all.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TimeLineAdapter extends BaseAdapter {

    private Context mContext;
    private List<Map<String, Object>> mList;
    private LayoutInflater mLayoutInflater;
    
    
    
    public TimeLineAdapter(Context context,List<Map<String, Object>> list) {
        super();
        this.mContext=context;
        this.mList=list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        mLayoutInflater = LayoutInflater.from(parent.getContext());
        convertView=mLayoutInflater.inflate(R.layout.one_timeline_listitem, null);
        viewHolder = new ViewHolder();
        viewHolder.month =(TextView) convertView.findViewById(R.id.show_time);
        viewHolder.year=(TextView) convertView.findViewById(R.id.one_timeline_listitem_year);
        viewHolder.title=(TextView) convertView.findViewById(R.id.one_timeline_listitem_content_title);
        
        viewHolder.month.setText(mList.get(position).get("month").toString());
        viewHolder.year.setText(mList.get(position).get("year").toString());
        viewHolder.title.setText(mList.get(position).get("title").toString());
        
        return convertView;
    }
    
    
    
    static class ViewHolder{
        public TextView year;
        public TextView month;
        public TextView title;
    }

}
