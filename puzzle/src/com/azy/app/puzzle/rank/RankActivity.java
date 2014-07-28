package com.azy.app.puzzle.rank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ExpandableListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.Contacts.People;

import com.azy.app.puzzle.R;
import com.azy.app.puzzle.adapter.MyExpandableListAdapter;
import com.azy.app.puzzle.db.RankDBHelper;

public class RankActivity extends ExpandableListActivity {
    private static final String RANKNO = "RANKNO";  
    private static final String SCORE = "SCORE";  
    private static final String NAME = "NAME";
    private static final String GROUP_ID = "GROUP_ID";
    private static final String GROUP_NAME = "GROUP_NAME";
	private MyExpandableListAdapter mAdapter; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.rank_list);
		
		List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();  
        List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();  
        for (int i = 0; i < 3; i++) {  
            Map<String, String> curGroupMap = new HashMap<String, String>();  
            groupData.add(curGroupMap);  
            curGroupMap.put(NAME, "¼òµ¥");
            curGroupMap.put(GROUP_ID, i+"");  
            List<Map<String, String>> children = new ArrayList<Map<String, String>>();  
            for (int j = 0; j < 10; j++) {
                Map<String, String> curChildMap = new HashMap<String, String>();  
                children.add(curChildMap);
                curChildMap.put(RANKNO, "" + j);
                curChildMap.put(SCORE, "" + 100);
                curChildMap.put(NAME, "LJ");  
            }
            childData.add(children);  
        }
        
        //Query database  
		RankDBHelper sqliteHelper = new RankDBHelper(this);
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			db = sqliteHelper.openWritable();
			cursor = sqliteHelper.fetchAllScores(db);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			if(db.isOpen()){
				db.close();
			}
		}
        // Query for people  
        //Cursor groupCursor = managedQuery(People.CONTENT_URI, new String[] {People._ID, People.NAME}, null, null, null);  
  
		if(cursor.moveToFirst()){
	        int infoId = cursor.getInt(cursor.getColumnIndexOrThrow(RankDBHelper.KEY_RANK_ID));
	        String player = cursor.getString(cursor.getColumnIndexOrThrow(RankDBHelper.KEY_NAME));
	        System.out.println("id username== "+infoId+"   "+player);
		}
        // Set up our adapter
        mAdapter = new MyExpandableListAdapter(cursor,  
                this,
                android.R.layout.simple_expandable_list_item_1,  
                R.layout.simple_expandable_list,
                new String[] {GROUP_NAME}, // Name for group layouts  
                new int[] {android.R.id.text1},
                new String[] {RANKNO}, // Number for child layouts  
                new int[] {android.R.id.text1});
        
        if(!cursor.isClosed()){
        	cursor.close();
        }
        setListAdapter(mAdapter);  
		
	}

}
