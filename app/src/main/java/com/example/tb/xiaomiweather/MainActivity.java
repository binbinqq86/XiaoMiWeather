package com.example.tb.xiaomiweather;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ScrollFutureDaysWeatherView scrollFutureDaysWeatherView;
    private FutureDaysChart futureDaysChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scrollFutureDaysWeatherView= (ScrollFutureDaysWeatherView) findViewById(R.id.sfdwv);
        futureDaysChart=scrollFutureDaysWeatherView.getSevenDaysChart();
//        futureDaysChart.setCubic(true);
        List<Weather> datas=new ArrayList<>();
        Weather w=new Weather();
        w.setAirQuality("良");
        w.setDate("5月24日");
        w.setHighTemperature(25);
        w.setLowTemperature(20);
        w.setWeatherDay("多云");
        w.setWeatherNight("晴");
        w.setWeek("昨天");
        w.setWind("微风");
        w.setWindLevel("<2级");
        datas.add(w);

        w=new Weather();
        w.setAirQuality("差");
        w.setDate("5月25日");
        w.setHighTemperature(26);
        w.setLowTemperature(22);
        w.setWeatherDay("阴天");
        w.setWeatherNight("晴");
        w.setWeek("今天");
        w.setWind("西风");
        w.setWindLevel("<3级");
        datas.add(w);

        w=new Weather();
        w.setAirQuality("优");
        w.setDate("5月26日");
        w.setHighTemperature(28);
        w.setLowTemperature(24);
        w.setWeatherDay("多云");
        w.setWeatherNight("晴");
        w.setWeek("明天");
        w.setWind("微风");
        w.setWindLevel("<2级");
        datas.add(w);

        w=new Weather();
        w.setAirQuality("良");
        w.setDate("5月27日");
        w.setHighTemperature(25);
        w.setLowTemperature(20);
        w.setWeatherDay("多云");
        w.setWeatherNight("晴");
        w.setWeek("周六");
        w.setWind("北风");
        w.setWindLevel("<2级");
        datas.add(w);

        w=new Weather();
        w.setAirQuality("霾");
        w.setDate("5月28日");
        w.setHighTemperature(22);
        w.setLowTemperature(18);
        w.setWeatherDay("阴");
        w.setWeatherNight("多云");
        w.setWeek("周日");
        w.setWind("南风");
        w.setWindLevel("1级");
        datas.add(w);

        w=new Weather();
        w.setAirQuality("良");
        w.setDate("5月29日");
        w.setHighTemperature(25);
        w.setLowTemperature(20);
        w.setWeatherDay("多云");
        w.setWeatherNight("晴");
        w.setWeek("周一");
        w.setWind("无风");
        w.setWindLevel("<3级");
        datas.add(w);

        w=new Weather();
        w.setAirQuality("差");
        w.setDate("5月30日");
        w.setHighTemperature(23);
        w.setLowTemperature(21);
        w.setWeatherDay("晴");
        w.setWeatherNight("晴");
        w.setWeek("周二");
        w.setWind("微风");
        w.setWindLevel("<2级");
        datas.add(w);

        w=new Weather();
        w.setAirQuality("良");
        w.setDate("5月31日");
        w.setHighTemperature(20);
        w.setLowTemperature(16);
        w.setWeatherDay("小雨");
        w.setWeatherNight("阴");
        w.setWeek("周三");
        w.setWind("微风");
        w.setWindLevel("<4级");
        datas.add(w);

        w=new Weather();
        w.setAirQuality("优");
        w.setDate("6月1日");
        w.setHighTemperature(25);
        w.setLowTemperature(20);
        w.setWeatherDay("晴");
        w.setWeatherNight("晴");
        w.setWeek("周四");
        w.setWind("微风");
        w.setWindLevel("<2级");
        datas.add(w);

        w=new Weather();
        w.setAirQuality("良");
        w.setDate("6月2日");
        w.setHighTemperature(27);
        w.setLowTemperature(22);
        w.setWeatherDay("多云");
        w.setWeatherNight("晴");
        w.setWeek("周五");
        w.setWind("微风");
        w.setWindLevel("<1级");
        datas.add(w);

        w=new Weather();
        w.setAirQuality("优");
        w.setDate("6月3日");
        w.setHighTemperature(24);
        w.setLowTemperature(21);
        w.setWeatherDay("多云");
        w.setWeatherNight("晴");
        w.setWeek("周六");
        w.setWind("北风");
        w.setWindLevel("<3级");
        datas.add(w);

        w=new Weather();
        w.setAirQuality("良");
        w.setDate("6月4日");
        w.setHighTemperature(28);
        w.setLowTemperature(22);
        w.setWeatherDay("晴");
        w.setWeatherNight("晴");
        w.setWeek("周日");
        w.setWind("微风");
        w.setWindLevel("<1级");
        datas.add(w);

        w=new Weather();
        w.setAirQuality("良");
        w.setDate("6月5日");
        w.setHighTemperature(26);
        w.setLowTemperature(21);
        w.setWeatherDay("多云");
        w.setWeatherNight("晴");
        w.setWeek("周一");
        w.setWind("南风");
        w.setWindLevel("<2级");
        datas.add(w);

        w=new Weather();
        w.setAirQuality("良");
        w.setDate("6月6日");
        w.setHighTemperature(25);
        w.setLowTemperature(20);
        w.setWeatherDay("多云");
        w.setWeatherNight("晴");
        w.setWeek("周二");
        w.setWind("微风");
        w.setWindLevel("<2级");
        datas.add(w);

        w=new Weather();
        w.setAirQuality("良");
        w.setDate("6月7日");
        w.setHighTemperature(29);
        w.setLowTemperature(25);
        w.setWeatherDay("晴");
        w.setWeatherNight("晴");
        w.setWeek("周三");
        w.setWind("微风");
        w.setWindLevel("<1级");
        datas.add(w);

        w=new Weather();
        w.setAirQuality("良");
        w.setDate("6月8日");
        w.setHighTemperature(22);
        w.setLowTemperature(18);
        w.setWeatherDay("小雨");
        w.setWeatherNight("阴");
        w.setWeek("周四");
        w.setWind("北风");
        w.setWindLevel("<5级");
        datas.add(w);

        futureDaysChart.setDatas(datas);
        List<View> viewList=scrollFutureDaysWeatherView.getAllViews();
        for (int i = 0; i < viewList.size(); i++) {
            View view=viewList.get(i);
            TextView tvWeek= (TextView) view.findViewById(R.id.tv_week);
            TextView tvDate= (TextView) view.findViewById(R.id.tv_date);
            TextView tvWeatherDay= (TextView) view.findViewById(R.id.tv_weather_day);
            TextView tvWeatherNight= (TextView) view.findViewById(R.id.tv_weather_night);
            TextView tvWind= (TextView) view.findViewById(R.id.tv_wind);
            TextView tvWindLevel= (TextView) view.findViewById(R.id.tv_wind_level);
            TextView tvAirQuality= (TextView) view.findViewById(R.id.tv_air_quality);
            tvWeek.setText(datas.get(i).getWeek());
            tvDate.setText(datas.get(i).getDate());
            tvWeatherDay.setText(datas.get(i).getWeatherDay());
            tvWeatherNight.setText(datas.get(i).getWeatherNight());
            tvWind.setText(datas.get(i).getWind());
            tvWindLevel.setText(datas.get(i).getWindLevel());
            tvAirQuality.setText(datas.get(i).getAirQuality());
            if(datas.get(i).getAirQuality().equals("优")){
                tvAirQuality.setBackgroundColor(Color.GREEN);
            }else if(datas.get(i).getAirQuality().equals("良")){
                tvAirQuality.setBackgroundColor(0xffaaa234);
            }else{
                tvAirQuality.setBackgroundColor(Color.BLACK);
            }

            if(i==0){
                tvWeek.setTextColor(Color.GRAY);
            }else if(i==1){
                tvWeek.setTextColor(Color.BLUE);
                view.setBackgroundColor(0x22808080);
            }else{
                tvWeek.setTextColor(Color.BLACK);
            }
        }
    }
}
