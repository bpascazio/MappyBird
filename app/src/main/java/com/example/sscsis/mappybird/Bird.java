package com.example.sscsis.mappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.util.Log;

public class Bird {

		private Bitmap bitmap;	// the actual bitmap
		private int x;			// the X coordinate
		private int y;			// the Y coordinate
		private int height;
		private int width;
		private boolean sound = false;
		private boolean dead; ////
		private boolean winner; ////
		private int thrust; ////
		private int accel; ////
		private int score;
		
		private MediaPlayer mediaPlayerFlap, mediaPlayerHit;
		
		public Bird(Bitmap bitmap, int x, int y) {
			this.bitmap = bitmap;
			this.x = x;
			this.y = y;
			dead = false; //////
			winner = false; //////
			thrust = 0; //////
			accel = 0;
			score = 1;
		}
		public void setPlayer(MediaPlayer mediaPlayerFlap, MediaPlayer mediaPlayerHit) {
			this.mediaPlayerFlap = mediaPlayerFlap;
			this.mediaPlayerHit = mediaPlayerHit;
		}
		public void soundOn(boolean sound) {
			this.sound = sound;
		}
		public void updateCoord(int height, int width) {
			this.height=height;
			this.width=width;
			x=0;
			y=height/2;
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
		/////////
		public boolean isDead() {
			return dead;
		}
		public boolean isWinner() {
			return winner;
		}
		public void flap() {
			thrust = thrust + 150;
			if (sound) {
				mediaPlayerFlap.seekTo(0);
				mediaPlayerFlap.start();
			}
			dead=false;
		}
		public int getScore() {
			return score;
		}
		public void update() {
			// move mappy across screen
			x=x+3;
			
			// if mappy goes off edge of screen, reset his position to 0
			if(x>width) {
				x=0;
				winner=true;
				if (!dead) {
					score++;
					Log.d("Bird", ""+score);
				}
			}
			/*
			// move mappy down because of gravity
			if (thrust > 0) {
				if (thrust > 100) {
					y=y-20;
					thrust=thrust-20;
				} else if (thrust > 50) {
					y=y-10;
					thrust=thrust-10;
				} else {
					y=y-5;
					thrust=thrust-5;
				}
				accel = 0;
			} else {
				if (accel<5) {
					y=y+5;
				} else if (accel<10) {
					y=y+9;
				} else {
					y=y+11;
				}
				accel = accel + 1;
			}
			// check if mappy went off screen
			if(y>height && !dead) {
				score=0;
				Log.d("Bird", ""+score);
				if (score<0)score=0;
				dead=true;
				y=height;
				if (sound) {
					mediaPlayerHit.seekTo(0);
					mediaPlayerHit.start();
				}
			}*/
		}
		/////////
	}
