package com.example.sscsis.mappybird;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by sscsis on 1/7/15.
 */
public class GameCanvas extends SurfaceView implements SurfaceHolder.Callback {

    MainThread thread;

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

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    int loopcolor = 0;

    public void render(Canvas canvas) {

        if (loopcolor==0) {
            canvas.drawColor(Color.BLUE);
            loopcolor = 1;
        } else if (loopcolor==1) {
            canvas.drawColor(Color.RED);
            loopcolor = 2;
        } else if (loopcolor==2) {
            canvas.drawColor(Color.GREEN);
            loopcolor = 0;
        }
    }

    public void update() {

    }


}
