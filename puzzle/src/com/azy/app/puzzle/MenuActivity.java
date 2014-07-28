package com.azy.app.puzzle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.azy.app.puzzle.rank.RankActivity;
import com.azy.app.puzzle.sound.Music;

public class MenuActivity extends Activity{
	private ImageView help_btn;
	private ImageView imageView_test;
	private ImageView imageView1;
	private ImageView imageView2;
	private ImageView imageView3;
	private ImageView imageView5;
	private ImageView imageView6;
	private ImageView imageView4;
	private ImageView configMedia;
	private ImageView quit;
	public static MenuActivity instance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		instance = this;//��ʼ�����ֱ���
		
		Music.initMusic(this);
		
		Music.play(this, R.raw.heros, true);
		
		initWidget();
		
		initOnclickListener();
	}
	
	private void initOnclickListener() {
		// TODO Auto-generated method stub
		quit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
				builder.setPositiveButton("�˳�", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						MenuActivity.this.finish();
					}
				});
				builder.setNegativeButton("ȡ��", null);
				AlertDialog mDialog  = builder.create();
				mDialog.show();
				//mDialog.getWindow().setContentView(R.layout.audio_selection_dialog);
				
			}
		});
		
		//��ʼ��Ϸ
		imageView1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MenuActivity.instance.gotoSelectGameActivity();
			}
		});
		
		imageView2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MenuActivity.instance.gotoSelectRankActivity();
			}
		});
		
		imageView3.setOnClickListener(new OnClickListener() {//��Ϸ����
			@Override
			public void onClick(View v) {
				MenuActivity.instance.gotoOptionActivity();
			}
		});
		
		help_btn = (ImageView)findViewById(R.id.imageView4);
		help_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MenuActivity.instance.gotoHelpActivity();
			}
		});
		
		imageView_test = (ImageView)findViewById(R.id.imageView_test);
		imageView_test.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MenuActivity.instance.gotoListViewAdapterActivity();
			}
		});
	}

	protected void gotoSelectRankActivity() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(MenuActivity.this,RankActivity.class);//SelectGameActivity  GameActivity.
		startActivity(intent);
	}

	private void initWidget() {
		imageView1 = (ImageView)findViewById(R.id.imageView1);//��ʼ��Ϸ
		imageView2 = (ImageView)findViewById(R.id.imageView2);//����
		imageView3 = (ImageView)findViewById(R.id.imageView3);//��Ϸ����
		imageView4 = (ImageView)findViewById(R.id.imageView4);//��Ϸ����
		imageView5 = (ImageView)findViewById(R.id.imageView5);//������Ϸ
		quit = (ImageView)findViewById(R.id.quit);//�˳���Ϸ
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Music.stop();
	}
	
	protected void gotoSelectGameActivity() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(MenuActivity.this,SelectGameActivity.class);   //SelectGameActivity  GameActivity.
		startActivity(intent);
	}
	
	protected void gotoGameActivity(int levelIndex) {
		Intent intent = new Intent(MenuActivity.this,GameActivity.class);
		//����һ���ѶȲ��������ݵ���һ������
		
		startActivity(intent);
	}

	protected void gotoOptionActivity() {
		Intent intent = new Intent(MenuActivity.this,OptionActivity.class);
		startActivity(intent);
	}

	private void gotoListViewAdapterActivity() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(MenuActivity.this,LisViewAdapterActivity.class);
		//intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	
	public void gotoMusicActivity() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(MenuActivity.this,MusicActivity.class);
		startActivity(intent);
	}

	public void gotoHelpActivity() {
		// TODO Auto-generated method stub
		Intent intent  = new Intent();   //..intent...
		intent.setClass(this, HelpActivity.class);
		startActivity(intent);
	}

	public void gotoInputNameActivity() {
		// TODO Auto-generated method stub
		Intent intent  = new Intent();                 //..intent...
		intent.setClass(this, InputNameActivity.class);
		startActivity(intent);
		
	}
}
