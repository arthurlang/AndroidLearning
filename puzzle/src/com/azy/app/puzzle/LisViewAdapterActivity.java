package com.azy.app.puzzle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class LisViewAdapterActivity extends Activity {

	private ListView listView1;
	private ListAdapter adapter;
	List<String> data = new ArrayList<String>();
	
	List<Map<String,Object>> newsdata = null;
	private View textView_title;
	private View textView_content;
	private ImageView pic_ic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
        fillData();
		//listView1 = (ListView)findViewById(R.id.listView1);
		textView_title = (TextView)findViewById(R.id.textView_title);
		textView_content = (TextView)findViewById(R.id.textView_content);
		pic_ic = (ImageView)findViewById(R.id.pic_ic);
		
		String[] from = new String[]{"pic","title","content"};
		
		int[] to = new int[]{R.id.pic_ic,R.id.textView_title,R.id.textView_content};
		
		SimpleAdapter adapter = new SimpleAdapter(this,newsdata,R.layout.item_list,from,to);
		
		//setAdapter
		listView1.setAdapter(adapter);
		
		//click
		listView1.setOnItemClickListener(itemListener);
		
		//longclick
		listView1.setOnItemLongClickListener(itemLonglistener);
		
	}
	
	private void fillData() {
		// TODO Auto-generated method stub
		newsdata = new ArrayList<Map<String,Object>>();
		
		Map<String,Object> map1 = new HashMap<String, Object>();
		map1.put("pic", R.drawable.play);
		map1.put("title", "111");
		map1.put("content", "111111");
		newsdata.add(map1);
		
		Map<String,Object> map2 = new HashMap<String, Object>();
		map2.put("pic", R.drawable.stop);
		map2.put("title","2222");
		map2.put("content","2222222");
		newsdata.add(map2);
		
	}

	private android.widget.AdapterView.OnItemLongClickListener itemLonglistener = new OnItemLongClickListener(){
	    
		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			Toast.makeText(LisViewAdapterActivity.this, "doublie clicked !position is "+position, Toast.LENGTH_SHORT).show();
			return true; //当listview既有单击监听、又有长按监听，只返回true，否者可以是ture或者false；
		}
	};
	
	private android.widget.AdapterView.OnItemClickListener itemListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
			Toast.makeText(LisViewAdapterActivity.this, "this is position "+position, Toast.LENGTH_SHORT).show();
		}
	};
}
