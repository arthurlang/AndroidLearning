package com.azy.app.puzzle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.azy.app.puzzle.view.GameView;

public class GameActivity extends Activity {
	public static float ScreenW,ScreenH;     //�������ڼ�¼ʵ����Ļ�Ŀ��
	public static GameActivity instance;
	private GameView gv;
	private View tipView;
	private TextView tipText;
	private ImageView thumbnail;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instance = this;
		// ����ȫ��
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		// ����ȫ���Ĵ�С
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		ScreenW = metrics.widthPixels;
		ScreenH = metrics.heightPixels;
		
		// �Զ���surfaceView
		gv = new GameView(this);
		setContentView(gv);
		
	}
	
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~onRestart");
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~onStop");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(Menu.NONE, Menu.FIRST, Menu.NONE,R.string.menu_back).setIcon(android.R.drawable.ic_menu_revert);
		menu.add(Menu.NONE, Menu.FIRST+1, Menu.NONE,R.string.menu_help).setIcon(android.R.drawable.ic_menu_help);
		menu.add(Menu.NONE, Menu.FIRST+2, Menu.NONE,R.string.menu_setting).setIcon(android.R.drawable.ic_menu_set_as);
		menu.add(Menu.NONE, Menu.FIRST+3, Menu.NONE,R.string.menu_home).setIcon(android.R.drawable.menu_frame);
		
		if(gv.isEnabled()){//�����˵���ֹͣ����ͼ��
			gv.setEnabled(false);
		};
		
		return super.onCreateOptionsMenu(menu);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
        switch(item.getItemId()){
        
	        case Menu.FIRST:
	        	break;
	        	
	        case Menu.FIRST+1://help
	        	//Game
	        	GameActivity.instance.gotoHelpActivity();
	        	break;
	        
	        case Menu.FIRST+2://setting
	        	GameActivity.instance.gotoOptionActivity();
	        	break;
	        
	        case Menu.FIRST+3://MENU
	        	
	        	AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
	        
	            Bitmap bigImage  = BitmapFactory.decodeResource(getResources(), SelectGameActivity.imagesArray[SelectGameActivity.imageIndex]);
	            Bitmap smallmap = Bitmap.createScaledBitmap(bigImage, 100, 100, true);
	            
	            //�ڵ�����dialog�Ի����м䣬��ʾ�Զ���view
	            
	    		LayoutInflater layoutFactory = LayoutInflater.from(this);
	    		
	    		tipView = layoutFactory.inflate(R.layout.view_return_tip, null);
	    		
	    		thumbnail = (ImageView)tipView.findViewById(R.id.thumbnail);
	    		
	    		tipText = (TextView)tipView.findViewById(R.id.tips);
	    		
	    		thumbnail.setImageBitmap(smallmap);
	    		
	    		tipText.setText("�Ƿ񷵻�ѡ��");
	    		
	            builder.setView(tipView);
	            
		        builder.setPositiveButton("����", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						GameActivity.instance.gotoMenuActivity();
					}
				});
		        builder.setNegativeButton("ȡ��", null).setTitle("�˳���Ϸ");
		        builder.create().show();
	        	break;
        }
		
		return super.onOptionsItemSelected(item);
	}
	
	public void gotoInputNameActivity() {
		// TODO Auto-generated method stub
		Intent intent  = new Intent();                 //..intent...
		intent.setClass(GameActivity.this, InputNameActivity.class);
		intent.putExtra("score", GameView.score);
		startActivity(intent);
		GameActivity.instance.finish();
	}


	private void gotoMenuActivity() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(GameActivity.this,MenuActivity.class);
		startActivity(intent);
		GameActivity.instance.finish();//��GameActivity��ջ��ɾ��
	}

	private void gotoOptionActivity() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(GameActivity.this,OptionActivity.class);
		startActivity(intent);
	}


	private void gotoHelpActivity() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(GameActivity.this,HelpActivity.class);
		startActivity(intent);
	}
	

}
