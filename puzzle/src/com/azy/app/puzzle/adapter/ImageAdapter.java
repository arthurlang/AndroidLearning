package com.azy.app.puzzle.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.azy.app.puzzle.SelectGameActivity;

public class ImageAdapter extends BaseAdapter {
	private SelectGameActivity mContext;
	private ImageView imageView;
	public ImageAdapter() {
	}

	public ImageAdapter(SelectGameActivity selectGameActivity) {
		this.mContext = selectGameActivity;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		    //if (convertView == null) {//列举出第一屏的所有数据
			// 给ImageView设置资源
			imageView = new ImageView(mContext);
			// 设置布局 图片显示
			imageView.setLayoutParams(new Gallery.LayoutParams(320, 480));
			// 设置显示比例类型
		    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
			
/*		} else {//如果列举出第二、三屏的所有数据，重复使用以前的holderView
			imageView = (ImageView) convertView;
		}*/
		    
			imageView.setImageResource(SelectGameActivity.imagesArray[position]);
			
			return imageView;//imagesArray[position]
	}
}
