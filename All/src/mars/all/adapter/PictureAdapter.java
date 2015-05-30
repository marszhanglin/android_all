/*
 * Copyright (c) 2005, 2014, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * 
 */
package mars.all.adapter;

import java.util.List;

import mars.all.R;
import mars.all.bean.ItemDataAtMain;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.TextView;

/** 
 * 2014-7-22下午4:52:37 类PictureAdapter 
 * @author Mars zhang 
 */
public class PictureAdapter extends BaseAdapter {
    /** MemberVariables */
    private LayoutInflater inflater;
    /**频幕宽度*/
    private int screenWidth;
    /**频幕宽度*/
    private int screenHeight;
    /** MemberVariables */
    private List<ItemDataAtMain> itemDataAtMains;

    /** MemberVariables */
    public PictureAdapter(List<ItemDataAtMain> itemDataAtMains, Context context) {
        super();
//        itemDataAtMains = new ArrayList<ItemDataAtMain>();
        inflater = LayoutInflater.from(context);
        this.itemDataAtMains=itemDataAtMains;
//        for (int i = 0; i < images.length; i++) {
//        	ItemDataAtMain picture = new ItemDataAtMain(titles[i], images[i]);
//            pictures.add(picture);
//        }
       //获取频幕宽度
      WindowManager wm=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
      DisplayMetrics displayMetrics=new DisplayMetrics();
      wm.getDefaultDisplay().getMetrics(displayMetrics);
      screenHeight=displayMetrics.heightPixels;
      screenWidth=displayMetrics.widthPixels;
    }

    @Override
    public int getCount() {
        if (itemDataAtMains != null) {
            return itemDataAtMains.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {

        return itemDataAtMains.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	TextView contentTextView;
    	TextView timeTextView;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.gvitem, null);
            contentTextView=(TextView) convertView.findViewById(R.id.gvitem_tv_content);
            timeTextView=(TextView) convertView.findViewById(R.id.gvitem_tv_time);
        } else {
        	contentTextView=(TextView) convertView.findViewById(R.id.gvitem_tv_content);
            timeTextView=(TextView) convertView.findViewById(R.id.gvitem_tv_time);
        }
        contentTextView.getLayoutParams().width=(screenWidth-10)/2;
        timeTextView.getLayoutParams().width=(screenWidth-10)/2;
        contentTextView.setText(itemDataAtMains.get(position).getContent());
        timeTextView.setText(itemDataAtMains.get(position).getDate().toLocaleString());
        return convertView;
    }
}
