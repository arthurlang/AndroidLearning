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

		String title1 = "��Ϸ����";
		String content1 = "\nһ����������\n" + "������ƶ����ӽ����ƶ�.\n"+ "�������ͼ�鿴ԭͼ��\n"+"�������ͼ���ط���Ϸ���衣\n"
				+ "��Ϸ�а��ֻ����ؼ�:�����˵���ѡ�񷵻�ѡ�ؽ���;\n\n"
				+ "�����Ѷ��趨\n" + "��Ϸ���ݼ򵥣���ͨ�����������ѶȽ�ƴͼ�ָ�Ϊ3*3,4*4,5*5������ʽ��\n\n";
		String title2= "��Ϸϵͳ";
		String content2 = "\n�����˵��������������ť�ɲ鿴ÿ���Ѷ��¹��ص����ʱ�䡣\n";
		
		
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
			return true;                 //��listview���е������������г���������ֻ����true�����߿�����ture����false��
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
        // �����������Ϊȫ�� ������ֻ����title 
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}//�趨ȫ����ʾ��
	
}
