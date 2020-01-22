package com.example.seekbar_example;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;

import org.json.JSONException;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.txtMinPrices)
    TextView txtMinPrices;
    @BindView(R.id.txtMinumimPrices)
    TextView txtMinumimPrices;
    @BindView(R.id.txtMaxPrices)
    TextView txtMaxPrices;
    @BindView(R.id.txtMaxmimuPrices)
    TextView txtMaxmimuPrices;
    @BindView(R.id.txtDisplayRange)
    TextView txtDisplayRange;
    @BindView(R.id.txtGuestsWithNight)
    TextView txtGuestsWithNight;
    @BindView((R.id.price_range))
    CrystalRangeSeekbar price_range;

    private Context mContext;
    DecimalFormat formatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        price_range = (CrystalRangeSeekbar) findViewById(R.id.price_range);

        mContext = this;
        formatter = new DecimalFormat("#,###,###");

        filterUpdateView();
    }

    public void filterUpdateView() {


        price_range.setMinValue(0).setMaxValue(50);
        price_range.setMinStartValue(0).setMaxStartValue(20);

        String poundSymple = "£";

        price_range.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                if (minValue.equals(0)) {
                    txtDisplayRange.setText("No price range selected");
                }

                txtMinPrices.setText(poundSymple + " " + String.valueOf(formatter.format(minValue)));
                txtMaxPrices.setText(poundSymple + " " + String.valueOf(formatter.format(maxValue)));
            }
        });


        price_range.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number minValue, Number maxValue) {
                if (minValue.equals(0)) {
                    txtDisplayRange.setText("Selected price:-" + String.valueOf(minValue));
                }
            }
        });

    }
}
