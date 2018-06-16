package com.rgh.histogram;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author by RGH
 * @date on 2018/6/14.
 */

public abstract class BaseView extends View {

    private Context mContext;

    /**
     * X,Y轴等份划分
     */
    protected int axisDivideSizeX;
    protected int axisDivideSizeY;

    /**
     * 第一个维度为值，第二个为颜色
     */
    protected int[][] columInfo;
    protected String[] columInfoName;

    protected float maxAxisValueX;
    protected float maxAxisValueY;


    private String mGahTitle;
    private String mXAxisName;
    private String mYAxisName;

    private float mAxisTitleSize;
    private int mAxisTitleColor;

    protected int width;
    protected int height;

    /**
     * 起点坐标
     */
    protected int originalX = 100;
    protected int originalY = 800;


    private Paint mPaint;

    public BaseView(Context context) {
        this(context, null);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Histogram);

        mGahTitle = typedArray.getString(R.styleable.Histogram_grahTitle);
        mXAxisName = typedArray.getString(R.styleable.Histogram_xAxisName);
        mYAxisName = typedArray.getString(R.styleable.Histogram_yAxisName);
        mAxisTitleSize = typedArray.getDimension(R.styleable.Histogram_axisTitleSize, 20);
        mAxisTitleColor = typedArray.getColor(R.styleable.Histogram_axisTitleColor, context.getResources().getColor(R.color.black));
       // axisDivideSizeX = typedArray.getColor(R.styleable.Histogram_axisDivideSizeX, 5);
      //  axisDivideSizeY = typedArray.getInteger(R.styleable.Histogram_axisDivideSizeY, 5);

        if (typedArray != null) {
            typedArray.recycle();
        }

        initPaint();
    }

    private void initPaint() {

        if (mPaint == null) {
            mPaint = new Paint();
            mPaint.setDither(true);//防抖动
            mPaint.setAntiAlias(true);//去锯齿
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
     // width = getWidth() - originalX;
      height = (originalY > getHeight() ? getHeight() : originalY) - 300;
        width = getWidth() - originalX-50;
       // height = getHeight() - 400;

        drawXAxis(canvas, mPaint);
        drawYAxis(canvas, mPaint);
        drawTitle(canvas, mPaint);
        drawXAxisScale(canvas, mPaint);
        drawXAxisScaleValue(canvas, mPaint);
        drawYAxisScale(canvas, mPaint);
        drawYAxisScaleValue(canvas, mPaint);
        drawXAxiArrow(canvas, mPaint);
        drawYAxiArrow(canvas, mPaint);
        drawColumn(canvas, mPaint);
    }

    protected abstract void drawColumn(Canvas canvas, Paint paint);

    /**
     * 画Y箭头
     *
     * @param canvas
     * @param paint
     */
    private void drawYAxiArrow(Canvas canvas, Paint paint) {

        Path yPath = new Path();
        yPath.moveTo(originalX, originalY - height - 30);
        yPath.lineTo(originalX - 10, originalY - height);
        yPath.lineTo(originalX + 10, originalY - height);
        yPath.close();
        canvas.drawPath(yPath, paint);
        canvas.drawText(mYAxisName, originalX - 50, originalY - height - 30, paint);
    }


    /**
     * 画X箭头
     *
     * @param canvas
     * @param paint
     */
    private void drawXAxiArrow(Canvas canvas, Paint paint) {

        Path xPath = new Path();
        xPath.moveTo(originalX + width + 30, originalY);
        xPath.lineTo(originalX + width, originalY - 10);
        xPath.lineTo(originalX + width, originalY + 10);
        xPath.close();
        canvas.drawPath(xPath, paint);
        canvas.drawText(mXAxisName, originalX + width, originalY + 30, paint);
    }

    /**
     * 画Y轴刻度值
     *
     * @param canvas
     * @param paint
     */
    protected abstract void drawYAxisScaleValue(Canvas canvas, Paint paint);

    /**
     * 画Y轴刻度
     *
     * @param canvas
     * @param paint
     */
    protected abstract void drawYAxisScale(Canvas canvas, Paint paint);

    /**
     * 画X轴刻度值
     *
     * @param canvas
     * @param paint
     */
    protected abstract void drawXAxisScaleValue(Canvas canvas, Paint paint);

    /**
     * 画X轴刻度
     *
     * @param canvas
     * @param paint
     */
    protected abstract void drawXAxisScale(Canvas canvas, Paint paint);

    /**
     * 设置标题
     *
     * @param canvas
     * @param paint
     */
    private void drawTitle(Canvas canvas, Paint paint) {

        if (!TextUtils.isEmpty(mGahTitle)) {
            mPaint.setTextSize(mAxisTitleSize);
            mPaint.setColor(mAxisTitleColor);
            mPaint.setFakeBoldText(true);
            canvas.drawText(mGahTitle, (getWidth() / 2) - (mPaint.measureText(mGahTitle) / 2), originalX + 40, paint);
        }
    }

    /**
     * 画X轴
     *
     * @param canvas
     * @param paint
     */
    protected abstract void drawXAxis(Canvas canvas, Paint paint);

    /**
     * 画X轴
     *
     * @param canvas
     * @param paint
     */
    protected abstract void drawYAxis(Canvas canvas, Paint paint);


    /**
     * 手动设置X轴最大值及等份数
     * @param maxAxisValueX
     * @param dividedSize
     */
    public void setXAxisValue(float maxAxisValueX,int dividedSize) {
        this.maxAxisValueX = maxAxisValueX;
        this.axisDivideSizeX = dividedSize;
    }

    /**
     * 手动设置Y轴最大值及等份数
     * @param maxAxisValueY
     * @param dividedSize
     */
    public void setYAxisValue(float maxAxisValueY,int dividedSize) {
        this.maxAxisValueY = maxAxisValueY;
        this.axisDivideSizeY = dividedSize;
    }


    public int[][] getColumInfo() {
        return columInfo;
    }

    public void setColumInfo(int[][] columInfo) {
        this.columInfo = columInfo;
    }

    public String[] getColumInfoName() {
        return columInfoName;
    }

    public void setColumInfoName(String[] columInfoName) {
        this.columInfoName = columInfoName;
    }
}
