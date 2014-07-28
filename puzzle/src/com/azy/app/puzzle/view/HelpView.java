package com.azy.app.puzzle.view;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

public class HelpView extends LinearLayout {

	private String title;
	
	private String dialogue;
	
	private Boolean isExpanded;

	public HelpView(Context context,String title,String dialogue,Boolean isExpanded) {
		
		super(context);
		
		this.title =  title;
		
		this.dialogue =  dialogue;
		
		this.isExpanded =  isExpanded;
}
	
	public void addView(View myview){
		
		this.addView(myview);
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setDialogue(String words)
	{
		dialogue = words;
	}
	public void setExpanded(boolean expanded){
		isExpanded = expanded;
	}

	public Boolean getIsExpanded() {
		return isExpanded;
	}

	public void setIsExpanded(Boolean isExpanded) {
		this.isExpanded = isExpanded;
	}

	public String getTitle() {
		return title;
	}

	public String getDialogue() {
		return dialogue;
	}

}
