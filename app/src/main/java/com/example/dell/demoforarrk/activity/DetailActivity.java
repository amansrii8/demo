package com.example.dell.demoforarrk.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.dell.demoforarrk.R;
import com.example.dell.demoforarrk.model.Results;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.dell.demoforarrk.constants.Constants.RESULT_DETAIL;

public class DetailActivity extends Activity {

    @BindView(R.id.textview_name)
    TextView textViewName;
    @BindView(R.id.textview_height)
    TextView textViewHeight;
    @BindView(R.id.textview_mass)
    TextView textViewMass;
    @BindView(R.id.textview_date)
    TextView textViewCreated;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);

        Results results = (Results) getIntent().getSerializableExtra(RESULT_DETAIL);

        if (results == null) finish();

        textViewName.setText(results.getName());
        textViewMass.setText(results.getMass());
        textViewHeight.setText(results.getHeight());
        textViewCreated.setText(getCreatedAt(results.getCreated()));
    }

    private String getCreatedAt(String createdAt) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
            Date date = dateFormat.parse(createdAt);//You will get date object relative to server/client timezone wherever it is parsed
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //If you need time just put specific format for time like 'HH:mm:ss'
            String dateOnly = formatter.format(date);
            DateFormat formatter1 = new SimpleDateFormat("HH:mm:ss"); //If you need time just put specific format for time like 'HH:mm:ss'
            String timeOnly = formatter1.format(date);
            return dateOnly + " : " + timeOnly;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
