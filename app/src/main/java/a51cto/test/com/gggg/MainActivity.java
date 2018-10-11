package a51cto.test.com.gggg;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends Activity {
    private int defalutValue = 75;
    private SeekBar light_seekBar;
    private SlideSelectView slideSelectView;
    private String[] textStrings;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        light_seekBar = (SeekBar)findViewById(R.id.light_seekBar);
        slideSelectView = (SlideSelectView)findViewById(R.id.slideSelectView);
        button = findViewById(R.id.button);

        light_seekBar.setOnSeekBarChangeListener(seekBarChange);

      //  textStrings = new String[]{"小", "中", "大", "特大","超大"};
        textStrings = new String[]{"小", "中", "大"};//只有小中大三个选项
        slideSelectView.setString(textStrings);
        slideSelectView.setOnSelectListener(onSelect);

        slideSelectView.setCurrentPosition(0);

        /**
         *  下面这个方法不起作用
         */

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideSelectView.setCurrentPosition(1);
            }
        });



    }

    private SeekBar.OnSeekBarChangeListener seekBarChange = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            setScreenLight(progress);
        }
    };

    //设置屏幕亮度
    public void setScreenLight(int progress) {
        if (progress < 1) {
            progress = 1;
        } else if (progress > 255) {
            progress = 255;
        }
        final WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.screenBrightness = progress / 255f;
        getWindow().setAttributes(attrs);
        defalutValue = progress;
    }


    private SlideSelectView.onSelectListener onSelect = new SlideSelectView.onSelectListener() {
        @Override
        public void onSelect(int index) {
            Toast.makeText(MainActivity.this,"当前滑动到位置:"+textStrings[index],Toast.LENGTH_SHORT).show();
        }
    };
}
