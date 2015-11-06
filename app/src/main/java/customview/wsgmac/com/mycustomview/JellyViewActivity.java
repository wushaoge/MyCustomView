package customview.wsgmac.com.mycustomview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import customview.wsgmac.com.mycustomview.view.Customview1;
import customview.wsgmac.com.mycustomview.view.JellyTextView;


/**
 * 果冻
 */
public class JellyViewActivity extends Activity {
    private JellyTextView textView;
    public static int distance = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jelly_view);
        textView = (JellyTextView) findViewById(R.id.tv);
    }

    public void click(View view) {

        switch (view.getId()) {
            case R.id.btn_scroll_to:
                textView.scrollTo(distance, 0);
                distance += 10;
                break;
            case R.id.btn_scroll_by:
                textView.scrollBy(30, 0);
                break;
            case R.id.btn_sping_back:
                textView.spingBack();
                break;
        }

    }






}
