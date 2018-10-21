package com.example.wuyiying.weather01;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageView;
//
//
//
//public class SelectCity extends Activity implements View.OnClickListener, SelectCity1 {
//        private ImageView mBackBtn;
//
//   @Override
//   public void onCreat(Bundle saveInstanceState){
//        super.onCreate(saveInstanceState);
//
//        setContentView(R.layout.select_city);
//        mBackBtn=(ImageView)findViewById(R.id.title_back);
//        mBackBtn.setOnClickListener(this);
//
//    }
//  @Override
//    public void onClick(View v){
//        switch (v.getId()){
//            case R.id.title_back:
//                finish();
//                break;
//                default:
//                    break;
//        }
//    }
//
//
//}



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;



public class SelectCity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.select_city);

        mBackBtn = (ImageView) findViewById(R.id.title_back);
        mBackBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                Intent i = new Intent();
                i.putExtra("cityCode", "101160101");
                setResult(RESULT_OK, i);
                finish();
                break;
            default:
                break;
        }
    }

}