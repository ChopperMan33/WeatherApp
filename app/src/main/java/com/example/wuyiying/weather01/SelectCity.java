package com.example.wuyiying.weather01;
//


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import wuyiyng.bean.City;
import app.MyApplication;


public  class SelectCity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mBackBtn;
    private ListView citylist;
    private List<City> mCityList;
    private EditText searchEt;
    private ImageView searchBtn;


    private MyApplication mApplication;
    private ArrayList<String> mArrayList;

    private int updateCityCode = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.select_city);

        mBackBtn = (ImageView) findViewById(R.id.title_back);
        mBackBtn.setOnClickListener(this);
        //绑定组件
        searchEt=(EditText)findViewById(R.id.select_search);
        searchBtn=(ImageView)findViewById(R.id.search_button);
        searchBtn.setOnClickListener(this); //点击事件

        //String[] listdata={"1","2","3"};
        mApplication = (MyApplication) getApplication();
        mCityList = mApplication.getCityList();
        mArrayList = new ArrayList<String>();
        for (int i = 0; i < mCityList.size(); i++) {
            String No_=Integer.toString(i+1);
            String number=mCityList.get(i).getNumber();
            String  provinceName=mCityList.get(i).getProvince();
            String cityName = mCityList.get(i).getCity();
            mArrayList.add("NO."+No_+":"+number+"-"+provinceName+"-"+cityName);
        }
        citylist =  (ListView) findViewById(R.id.title_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SelectCity.this, android.R.layout.simple_list_item_1, mArrayList);
        citylist.setAdapter(adapter);

        //添加ListView项的点击事件的动作
        //点击时，将主界面的天气数据更新为点击的城市的天气数据。
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                updateCityCode = Integer.parseInt(mCityList.get(position).getNumber());
                Log.d("update city code", Integer.toString(updateCityCode));
            }
        };
        //为组件绑定监听
        citylist.setOnItemClickListener(itemClickListener);

    }

    //    返回主界面时，传递城市代码数据
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        //设置动作，将获取EditText获取的数据（这里我们获取的是citycode。
            // 传给MainActivity更新天气数据
            case R.id.search_button:
                String citycode = searchEt.getText().toString();
                Log.d("Search",citycode);
                ArrayList<String> mSearvhList=new ArrayList<String>();
                for (int i = 0; i < mCityList.size(); i++) {
                    String No_=Integer.toString(i+1);
                    String number=mCityList.get(i).getNumber();
                    String  provinceName=mCityList.get(i).getProvince();
                    String cityName = mCityList.get(i).getCity();
                    if (number.equals(citycode)){
                        mSearvhList.add("NO."+No_+":"+number+"-"+provinceName+"-"+cityName);
                        Log.d("change adapter data","NO."+No_+":"+number+"-"+provinceName+"-"+cityName);
                    }
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(SelectCity.this,android.R.layout.simple_list_item_1,mSearvhList);
                    citylist.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    //mArrayList.add("NO."+No_+":"+number+"-"+provinceName+"-"+cityName);
                }
                Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra("cityCode",citycode);
                setResult(RESULT_OK, intent);
                finish();

//            case R.id.title_back:
//                Intent i = new Intent();
//                i.putExtra("cityCode", Integer.toString(updateCityCode));//"101160101" "101160101"
//                setResult(RESULT_OK, i);
//                finish();
//                break;
            default:
                break;
        }
    }
}


