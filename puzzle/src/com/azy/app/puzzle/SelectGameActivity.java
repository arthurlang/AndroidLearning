package com.azy.app.puzzle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;

import com.azy.app.puzzle.adapter.ImageAdapter;

public class SelectGameActivity extends Activity{
	
	public static int []imagesArray ={R.drawable.b0,R.drawable.b1,R.drawable.b2,R.drawable.b3,R.drawable.b4,R.drawable.b5,R.drawable.b6,R.drawable.b7,R.drawable.b8,R.drawable.b9};
	private OnItemClickListener listener;
	public static int levelIndex = 0;
	public static int imageIndex = 0; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 设置全屏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_selectgame);

		Gallery gga = (Gallery)findViewById(R.id.gallery_activity_view);
		
	    ImageAdapter iadapter = new ImageAdapter(this);
		
	    gga.setAdapter(iadapter);

	    gga.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				imageIndex = position;
				new AlertDialog.Builder(SelectGameActivity.this).setTitle("请选择难度")
				.setIcon(R.drawable.icon)
				.setPositiveButton("简单", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						SelectGameActivity.levelIndex = 3;
						MenuActivity.instance.gotoGameActivity(levelIndex);
					}
				})
				.setNeutralButton("普通", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						SelectGameActivity.levelIndex = 4;
						MenuActivity.instance.gotoGameActivity(levelIndex);
					}
				})
				.setNegativeButton("困难", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						SelectGameActivity.levelIndex = 5;
						MenuActivity.instance.gotoGameActivity(levelIndex);
					}
				}).create().show();
			}
		});
	}
}
