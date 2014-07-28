package com.azy.app.puzzle.view;

import com.azy.app.puzzle.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyView1 extends View {
	Paint paint;
	Bitmap bitmap;

	public MyView1(Context context) {
		super(context);
		paint=new Paint();
		bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.about);
	}

	public MyView1(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint=new Paint();
		bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.about);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);		
		canvas.drawBitmap(bitmap, 0, 0, paint);		
	}
}
