package com.azy.app.puzzle.sprite;

import android.graphics.RectF;

public class Block {
	public int xLine,yLine;
	public int id;//��ͼƬ�����еĵڼ���ͼ
	public float x,y;//������Ͻǵ�����
	
	public RectF myRect;//���Σ�������ײ����
	
	public Block(int level,int id){
		xLine=id/level;  //��ȡ���ӵ�����0~..
		yLine=id%level;  //��ȡ���ӵ�������
		this.id=id;
		x=10+yLine*(ImageRect.rectW+1);//��λ��x������
		y=120+xLine*(ImageRect.rectH+1);//��λ��y������
		
		myRect=new RectF(x, y, x+ImageRect.rectW, y+ImageRect.rectH);
	}
	
	public boolean onClick(float touchX,float touchY){
		return myRect.contains(touchX, touchY);
	}
	
}
