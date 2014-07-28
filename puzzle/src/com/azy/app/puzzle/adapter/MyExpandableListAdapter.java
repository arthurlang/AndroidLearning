package com.azy.app.puzzle.adapter;

import android.content.Context;
import android.database.Cursor;
import android.widget.SimpleCursorTreeAdapter;

public class MyExpandableListAdapter extends SimpleCursorTreeAdapter {

	public MyExpandableListAdapter(Cursor cursor, Context context, int groupLayout,  
            int childLayout, String[] groupFrom, int[] groupTo, String[] childrenFrom,  
            int[] childrenTo) {
		super(context, cursor, childLayout, childLayout, childrenFrom, childrenTo, childLayout, childLayout, childrenFrom, childrenTo);
	}

	@Override
	protected Cursor getChildrenCursor(Cursor groupCursor) {
		return null;
	}

}
