package com.example.sscsis.mappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Sprite {
	
	private Bitmap bitmap;	// the actual bitmap
	private int x;			// the X coordinate
	private int y;			// the Y coordinate
	private int height;
	private int width;
	
	public boolean hit(float x, float y) {
		
		if (x >= this.x & y<=this.y+bitmap.getHeight()) {
			return true;
		}
		
		return false;
	}
	
	public Sprite(Bitmap bitmap, int x, int y) {
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
	}
	
	public void updateCoord(int height, int width, boolean top) {
		this.height=height;
		this.width=width;
		x=this.width-bitmap.getWidth();
		if (top) {
			y=0;
		} else {
			y=height-bitmap.getHeight();
		}
	}
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, x, y, null);
	}	
	
}
