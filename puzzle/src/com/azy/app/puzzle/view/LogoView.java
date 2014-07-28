package com.azy.app.puzzle.view;

import com.azy.app.puzzle.LogoActivity;
import com.azy.app.puzzle.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class LogoView extends View implements Runnable {
	// int resIds
	int[] logoID = new int[] { R.drawable.mmlogo, R.drawable.and1,R.drawable.logo };
	// 画笔
	Paint paint = new Paint();
	// Bitmaps
	Bitmap[] pics;
	int index = 0;

	public LogoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
		new Thread(this).start();
	}

	public LogoView(Context context) {
		super(context);
		init();
		new Thread(this).start();
	}

	private void init() {
		pics=new Bitmap[logoID.length];
		for (int i = 0; i < logoID.length; i++) {
			pics[i] = BitmapFactory.decodeResource(getResources(), logoID[i]);
			pics[i]=Bitmap.createScaledBitmap(pics[i], LogoActivity.ScreenW, LogoActivity.ScreenH, true);
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawBitmap(pics[index], 0, 0, paint);
	}

	@Override
	public void run() {
		while(true){		
			try {
				Thread.sleep(2000);//休眠2
				//index++
				index++;
				if(index>=3){
					//跳转界面
					LogoActivity.instence.gotoMenu();
					break;
				}
				//强制重绘
				postInvalidate();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}		
		}
		
		
	}

}
