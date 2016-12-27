package io.github.staray.richtextview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.RelativeLayout;

import io.github.staray.library.RichTextView;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout = (RelativeLayout) findViewById(R.id.main_layout);
        init();
    }

    private void init() {
        RichTextView richTextView = new RichTextView(this);
        richTextView.setmHeight(200);
        richTextView.setmWidth(200);
        richTextView.setmLocation(RichTextView.RIGHT);
        richTextView.drawDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        richTextView.setText("test");
        richTextView.setGravity(Gravity.CENTER);
        mainLayout.addView(richTextView);
    }
}
