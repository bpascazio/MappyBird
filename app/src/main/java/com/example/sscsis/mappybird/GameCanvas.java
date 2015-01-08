package com.example.sscsis.mappybird;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by sscsis on 1/7/15.
 */
public class GameCanvas extends SurfaceView implements SurfaceHolder.Callback {

    MainThread thread;
    Background background;
    Bird mappy;


    public GameCanvas(Context context) {
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();

        background = new Background(BitmapFactory.decodeResource(
                getResources(), R.drawable.flappy_background), 0, 0);


        mappy = new Bird(BitmapFactory.decodeResource(getResources(), R.drawable.bird), 0, 0);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        mappy.updateCoord(height, width);

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    int loopcolor = 0;
    boolean colors = false;
    public void render(Canvas canvas) {

        background.draw(canvas);
        mappy.draw(canvas);

        if (colors) {
            if (loopcolor == 0) {
                canvas.drawColor(Color.BLUE);
                loopcolor = 1;
            } else if (loopcolor == 1) {
                canvas.drawColor(Color.RED);
                loopcolor = 2;
            } else if (loopcolor == 2) {
                canvas.drawColor(Color.GREEN);
                loopcolor = 0;
            }
        }
    }

    public void update() {

        mappy.update();

    }


}
