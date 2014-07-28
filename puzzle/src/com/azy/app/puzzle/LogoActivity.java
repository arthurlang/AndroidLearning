package com.azy.app.puzzle;

import com.azy.app.puzzle.view.LogoView;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class LogoActivity extends Activity {
	public static int ScreenW,ScreenH;
	public static LogoActivity instence;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instence=this;
		//setFullScreen();
		ScreenW=getWindowManager().getDefaultDisplay().getWidth();
		ScreenH=getWindowManager().getDefaultDisplay().getHeight();
		setContentView(new LogoView(this));
		
	}
	//��ת��Menu����
	public void gotoMenu(){
		Intent intent=new Intent();
		intent.setClass(this, MenuActivity.class);
		startActivity(intent);
		finish();
	}
	
//	����ȫ��
//	private void setFullScreen(){
//		//ȥ��������
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		//ȥ����Ϣ��
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//	}
	
}
