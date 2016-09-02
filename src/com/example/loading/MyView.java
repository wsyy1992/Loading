package com.example.loading;

import java.text.BreakIterator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {
	private Paint mPaint;
	int sweepAngle = 0;

	public MyView(Context context, AttributeSet attrs) {

		super(context, attrs);
		init();

	}

	private void init() {
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.GRAY);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeWidth(5);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawCircle(100, 100, 2, mPaint);
		// 空心进度条，进度条在环上
		drawLoading1(canvas);
		drawLoading2(canvas);

	}

	/**
	 * 画Loading 空心，进度条在环上
	 * 
	 * @param canvas
	 */
	private void drawLoading1(Canvas canvas) {
		mPaint.setColor(Color.GRAY);
		mPaint.setStyle(Paint.Style.STROKE);
		canvas.drawCircle(100, 100, 50, mPaint);
		canvas.save();
		RectF oval = new RectF(50, 50, 150, 150);
		sweepAngle++;
		System.out.println(sweepAngle);
		mPaint.setColor(Color.RED);
		canvas.drawArc(oval, 0, sweepAngle, false, mPaint);
		String s =100*sweepAngle/360+"%" ;
		float x = 100-mPaint.measureText(s)/2;
		float y = 100-(mPaint.descent()+mPaint.ascent())/2;
		mPaint.setTextSize(25);
		mPaint.setStrokeWidth(2);
		canvas.drawText(s,x, y, mPaint);
		if (sweepAngle <= 360) {
			postInvalidateDelayed(10);
		}
	}
	private void drawLoading2(Canvas canvas) {
		mPaint.setColor(Color.GRAY);
		mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
		canvas.drawCircle(200, 200, 50, mPaint);
		canvas.save();
		RectF oval = new RectF(150, 150, 250, 250);
		sweepAngle++;
		System.out.println(sweepAngle);
		mPaint.setColor(Color.WHITE);
		mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
		canvas.drawArc(oval, -90, sweepAngle, true, mPaint);
		if (sweepAngle <= 360) {
			postInvalidateDelayed(10);
		}
	}
}
