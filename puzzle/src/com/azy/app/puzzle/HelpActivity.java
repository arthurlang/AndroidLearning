package com.azy.app.puzzle;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.azy.app.puzzle.adapter.HelpListAdapter;
import com.azy.app.puzzle.view.HelpView;

public class HelpActivity extends ListActivity {

	private ListView listView1;
	private ListAdapter adapter;
	List<String> data = new ArrayList<String>();
	private CharSequence title;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		Drawable drawable = this.getResources().getDrawable(R.drawable.helpback);
		this.getWindow().setBackgroundDrawable(drawable);
		
		setFullScreen();
		
/*		HelpView helpView = new HelpView(HelpActivity.this,"title","content",true);
		helpView.setOrientation(LinearLayout.VERTICAL);*/
		
		List<HelpView> listData = this.getData();
        String[] from = new String[]{"title","content"};
        int[] to = new int[]{R.id.mTitle,R.id.mDialogue};
		HelpListAdapter helpAdapter = new HelpListAdapter(this,listData,R.layout.help_list_row,from,to);
        this.setListAdapter(helpAdapter);
        
		/*
        fillData();
		listView1 = (ListView)findViewById(R.id.listView1);
		
		ArrayAdapter adapter = new ArrayAdapter(HelpActivity.this, android.R.layout.simple_expandable_list_item_1, data);
		
		//setAdapter
		listView1.setAdapter(adapter);
		
		//click
		listView1.setOnItemClickListener(itemListener);
		
		//longclick
		listView1.setOnItemLongClickListener(itemLonglistener);
		*/
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Toast.makeText(HelpActivity.this, "position="+position, Toast.LENGTH_SHORT);
	}
	
	private List<HelpView> getData() {

		List<HelpView> listData = new ArrayList<HelpView>();

		String title1 = "游戏操作";
		String content1 = "\n一、基本操作\n" + "点击欲移动格子进行移动.\n"+ "点击缩略图查看原图。\n"+"点击播放图标重放游戏步骤。\n"
				+ "游戏中按手机返回键:弹出菜单中选择返回选关界面;\n\n"
				+ "二、难度设定\n" + "游戏根据简单，普通，困难三个难度将拼图分割为3*3,4*4,5*5三种样式。\n\n";
		String title2= "游戏系统";
		String content2 = "\n在主菜单点击积分排名按钮可查看每个难度下过关的最短时间。\n";
		
		
		HelpView map1 = new HelpView(this,title1,content1,true);
		
		HelpView map2 = new HelpView(this,title2,content2,true);
		
		listData.add(map1);
		
		listData.add(map2);
		
		return listData;
	}

	private android.widget.AdapterView.OnItemLongClickListener itemLonglistener = new OnItemLongClickListener(){
	    
		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
				int position, long id) {
			Toast.makeText(HelpActivity.this, "doublie clicked !position is "+position, Toast.LENGTH_SHORT).show();
			return true;                 //当listview既有单击监听、又有长按监听，只返回true，否者可以是ture或者false；
		}
	};
	
	private android.widget.AdapterView.OnItemClickListener itemListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
			Toast.makeText(HelpActivity.this, "this is position "+position, Toast.LENGTH_SHORT).show();
		}
	};
	
	private void setFullScreen(){
        // 加上这句设置为全屏 不加则只隐藏title 
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}//设定全屏显示；
	
}
