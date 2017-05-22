package com.example.tb.xiaomiweather;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by tb on 2017/5/13.
 * 未来若干天天气滑动控件
 */

public class ScrollFutureDaysWeatherView extends ViewGroup{
    private static final String TAG = "ScrollFutureDaysWeatherView";
    private List<View> contents=new ArrayList<>();
    private FutureDaysChart sevenDaysChart;
    private static final int days=7;
    public ScrollFutureDaysWeatherView(Context context) {
        this(context,null);
    }

    public ScrollFutureDaysWeatherView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public ScrollFutureDaysWeatherView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ScrollFutureDaysWeatherView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        setBackgroundColor(Color.WHITE);
        for (int i = 0; i < days; i++) {
            View view=LayoutInflater.from(context).inflate(R.layout.sevendayweather_everyone, null, false);
            contents.add(view);
            addView(view,new LayoutParams(getResources().getDimensionPixelOffset(R.dimen.sevenday_each_width),
                    getResources().getDimensionPixelOffset(R.dimen.sevenday_total_height)));

        }
        sevenDaysChart=new FutureDaysChart(context);
        addView(sevenDaysChart,new LayoutParams(getResources().getDimensionPixelOffset(R.dimen.sevenday_totoal_width),
                getResources().getDimensionPixelOffset(R.dimen.sevenday_chart_height)));
    }

    public List<View> getAllViews(){
        return contents;
    }

    public FutureDaysChart getSevenDaysChart(){
        return sevenDaysChart;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            if(childView.getVisibility()!=View.GONE){
                measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            }
        }
        //为ViewGroup设置宽高
        setMeasuredDimension(getResources().getDimensionPixelOffset(R.dimen.sevenday_totoal_width),
                getResources().getDimensionPixelOffset(R.dimen.sevenday_total_height));
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int left=0;
        for (int j = 0; j < getChildCount()-1; j++) {
            View child=getChildAt(j);
            if(child.getVisibility()!=View.GONE){
                child.layout(left,0,left+child.getMeasuredWidth(),child.getMeasuredHeight());
                if(j%2==0){
                    left+=getResources().getDimensionPixelOffset(R.dimen.sevenday_each_width);
                }else{
                    left+=getResources().getDimensionPixelOffset(R.dimen.sevenday_line);
                }
            }
        }
        View emptyView=contents.get(0).findViewById(R.id.view);
        int top=emptyView.getTop();
        View last=getChildAt(getChildCount()-1);
        last.layout(0,top,getMeasuredWidth(),top+getResources().getDimensionPixelOffset(R.dimen.sevenday_chart_height));
    }

}
