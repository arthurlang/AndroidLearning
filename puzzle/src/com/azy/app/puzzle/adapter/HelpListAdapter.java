package com.azy.app.puzzle.adapter;

import java.util.List;
import java.util.Map;

import android.database.Cursor;
import android.text.Spannable;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import com.azy.app.puzzle.HelpActivity;
import com.azy.app.puzzle.R;
import com.azy.app.puzzle.view.HelpView;

public class HelpListAdapter extends BaseAdapter  {

	private LayoutInflater mInflater;
	private List<HelpView> mlist;
	public static Map<Integer, Boolean> isSelected;
	private static String[] IdsArray;
	private int size;
	Cursor c;
	
	public HelpListAdapter(HelpActivity context, List<HelpView> listData,
			int helpListRow, String[] from, int[] to) {
		super();
		this.mlist = listData;
		 mInflater = LayoutInflater.from(context);
	}

	public int getCount() {//返回数据的条数
		if(mlist != null){
			return mlist.size();
		}else{
			return 0;
	    }
	}

	public Object getItem(int position) {
		return mlist.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {//有多少数据，就调用多少次
		final ViewHolder holder;
		System.out.println("convertView==============="+convertView);
		
		if (convertView == null) {//列举出第一屏的所有数据
			convertView = mInflater.inflate(R.layout.help_list_row, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);//保存holder对象
		} else {//如果列举出第二、三屏的所有数据，重复使用以前的holderView
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.title.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(holder.content.getVisibility()==View.GONE){
					holder.content.setVisibility(View.VISIBLE);
				}else{
					holder.content.setVisibility(View.GONE);
				}
			}
		});

        if(mlist != null && mlist.size()>0){
	        holder.title.setText(mlist.get(position).getTitle(),BufferType.EDITABLE);
	        Spannable sp = (Spannable)  holder.title.getText();
	        sp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, holder.title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	        holder.content.setText(sp);
	        
	        holder.content.setText(mlist.get(position).getDialogue().toString(),BufferType.EDITABLE);
	        sp = (Spannable)  holder.content.getText();
	        sp.setSpan(new StyleSpan(android.graphics.Typeface.NORMAL), 0, holder.content.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	        holder.content.setText(sp);
        }
        
        return convertView;
    }
	
	public static String[] getIdsArray() {
		return IdsArray;
	}

	public static void setIdsArray(String[] idsArray) {
		IdsArray = idsArray;
	}

	public final class ViewHolder {
		public TextView title;
		public TextView content;
		
		public ViewHolder(View view){
			this.title = (TextView) view.findViewById(R.id.mTitle);
			this.content = (TextView) view.findViewById(R.id.mDialogue);
			
		}
	}
}