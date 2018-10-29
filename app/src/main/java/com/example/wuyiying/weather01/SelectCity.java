package com.example.wuyiying.weather01;
//


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

import wuyiyng.bean.City;
import app.MyApplication;

public  class SelectCity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mBackBtn;
    private ListView citylist;
    private List<City> mCityList;

    private MyApplication mApplication;
    private ArrayList<String> mArrayList;
    private int updateCityCode = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.select_city);

        mBackBtn = (ImageView) findViewById(R.id.title_back);
        mBackBtn.setOnClickListener(this);

       //String[] listdata={"1","2","3"};
        mApplication = (MyApplication) getApplication();
        mCityList = mApplication.getCityList();
        mArrayList = new ArrayList<String>();
        for (int i = 0; i < mCityList.size(); i++) {
            String cityName = mCityList.get(i).getCity();
            mArrayList.add(cityName);
        }
        citylist = (ListView) findViewById(R.id.title_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SelectCity.this, android.R.layout.simple_list_item_1,  mArrayList );
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
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.title_back:
                Intent i = new Intent();
                i.putExtra("cityCode",Integer.toString(updateCityCode));//"101160101" "101160101"
                setResult(RESULT_OK, i);
                finish();
                break;
            default:
                break;
        }
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.title_back:
//                Intent intent = new Intent(this,MainActivity.class);
//                intent.putExtra("cityCode","101010100");//updateCityCode
//                startActivity(intent);
//             //   finish();
//                break;
//            default:
//                break;
//        }
//    }
}

