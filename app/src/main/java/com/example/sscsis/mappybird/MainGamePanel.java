package edu.pace.mappybird;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainGamePanel extends SurfaceView implements
		SurfaceHolder.Callback {

	private MainThread thread;
	private Context context;
	private Background background;
	private Bird mappy;
	private Sprite settings;
	private Sprite share;
	
	private boolean available = false;

	public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {

		int width = bm.getWidth();

		int height = bm.getHeight();

		float scaleWidth = ((float) newWidth) / width;

		float scaleHeight = ((float) newHeight) / height;

		// CREATE A MATRIX FOR THE MANIPULATION

		Matrix matrix = new Matrix();

		// RESIZE THE BIT MAP

		matrix.postScale(scaleWidth, scaleHeight);

		// RECREATE THE NEW BITMAP

		Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
				matrix, false);

		return resizedBitmap;

	}

	public MainGamePanel(Context context) {
		super(context);
		getHolder().addCallback(this);

		thread = new MainThread(getHolder(), this);

		background = new Background(BitmapFactory.decodeResource(
				getResources(), R.drawable.flappy_background), 0, 0);
		
		mappy = new Bird(BitmapFactory.decodeResource(getResources(),
				R.drawable.bird), 0, 0);
		
		settings = new Sprite(BitmapFactory.decodeResource(
				getResources(), R.drawable.gear), 0, 0);
		
		share = new Sprite(BitmapFactory.decodeResource(
				getResources(), R.drawable.share_transparent), 0, 0);
		
		MediaPlayer flapSound = MediaPlayer.create(context, R.raw.flap);
		MediaPlayer hitSound = MediaPlayer.create(context, R.raw.hit);
		mappy.setPlayer(flapSound, hitSound);
		
		setFocusable(true);
		
		this.context = context;

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		background.setBitmap(this.getResizedBitmap(background.getBitmap(),
				height, width));
		
		mappy.setBitmap(this.getResizedBitmap(mappy.getBitmap(),
				155, 155));

		settings.setBitmap(this.getResizedBitmap(settings.getBitmap(),
				125, 125));

		share.setBitmap(this.getResizedBitmap(share.getBitmap(),
				125, 125));
		
		mappy.updateCoord(height, width);
		
		settings.updateCoord(height, width, true);
		share.updateCoord(height, width, false);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		available = true;
		
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		boolean sound = sp.getBoolean("sound_on_off", true);		
		mappy.soundOn(sound);
		
		thread = new MainThread(getHolder(), this);
		thread.setRunning(true);
		thread.start();

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		available = false;
		boolean retry = true;
		while (retry) {
			try {
				thread.setRunning(false);
				thread.join();
				retry = false;
			} catch (InterruptedException e) {

			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (settings.hit(event.getX(), event.getY())) {
			Log.d("GamePanel", "settings");			
			
			Intent intent = new Intent(context, UserSettingActivity.class);
			context.startActivity(intent);
	
		} else if (share.hit(event.getX(), event.getY())) {
	
			Intent shareIntent = new Intent();
			shareIntent.setAction(Intent.ACTION_SEND);
			shareIntent.putExtra(Intent.EXTRA_TEXT, "I love this game MappyBird!!!");
			shareIntent.setType("text/plain");
			context.startActivity(shareIntent);
			
		} else {
			mappy.flap();
			Log.d("GamePanel", "flap!");
		}
		return super.onTouchEvent(event);
	}

	@Override
	protected void onDraw(Canvas canvas) {

	}

	public void drawText(Canvas canvas, String txt) {
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setTextSize(96);
		canvas.drawText(txt, 25, 80, paint);
	}
	
	public void render(Canvas canvas) {
		
		if(!available)return;
		
		canvas.drawColor(Color.GREEN);
		background.draw(canvas);
		mappy.draw(canvas);
		settings.draw(canvas);
		share.draw(canvas);
		
		drawText(canvas, "Score: "+mappy.getScore());	
		
	}

	public void update() {
		mappy.update();
	}

}
