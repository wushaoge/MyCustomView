package customview.wsgmac.com.mycustomview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import customview.wsgmac.com.mycustomview.view.Customview1;


/**
 * 不停绘制一个变大的圆环
 */
public class CustomView1Activity extends Activity {

    private Customview1 customview1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customview1);

        customview1 = (Customview1) findViewById(R.id.customview1);

        new Thread(customview1).start();


    }


}
