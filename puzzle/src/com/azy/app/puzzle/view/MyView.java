package com.azy.app.puzzle.view;

import com.azy.app.puzzle.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

	public MyView(Context context) {
		super(context);
	}
	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint paint=new Paint();
		paint.setColor(Color.YELLOW);
		paint.setTextSize(30);
		canvas.drawText("这是我的控件", 100, 100, paint);
		Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.about);
		canvas.drawBitmap(bitmap, 150, 150, paint);
	}
	
}
