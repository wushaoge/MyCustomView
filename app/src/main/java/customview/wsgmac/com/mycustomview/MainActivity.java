package customview.wsgmac.com.mycustomview;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import customview.wsgmac.com.mycustomview.view.ECGView;


/**
 * 2015年11月06日16:04:31 33
 */
public class MainActivity extends Activity implements View.OnClickListener{


    private Button btn1; //按钮1
    private Button btn2; //2222222222
    private Button btn3;
    private Button btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }


    private void initView(){
        btn1 = (Button)findViewById(R.id.btn_view1);
        btn2 = (Button)findViewById(R.id.btn_view2);
        btn3 = (Button)findViewById(R.id.btn_view3);
        btn4 = (Button)findViewById(R.id.btn_view4);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, ECGViewActivity.class);
        switch (v.getId()){
            case R.id.btn_view1:
                i = new Intent(this, ECGViewActivity.class);
                break;
            case R.id.btn_view2:
                i = new Intent(this, CustomView1Activity.class);
                break;
            case R.id.btn_view3:
                i = new Intent(this, JellyViewActivity.class);
                break;
            case R.id.btn_view4:
                i = new Intent(this, WaveViewActivity.class);
                break;
        }
        startActivity(i);
    }
}
