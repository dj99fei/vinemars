package com.vine.vinemars.app.fragment;

import android.os.Bundle;
import android.view.View;

import com.squareup.timessquare.CalendarPickerView;
import com.vine.vinemars.R;

import java.util.Calendar;
import java.util.Date;

import butterknife.InjectView;

/**
 * Created by chengfei on 14-10-28.
 */
public class CalendarFragment extends BaseFragment implements CalendarPickerView.OnDateSelectedListener {


    @InjectView(R.id.calendar_view)
    protected CalendarPickerView calendarPickerView;
    public static CalendarFragment newInstance() {
        CalendarFragment fragment = new CalendarFragment();
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_calendar;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
        Date today = new Date();
        calendarPickerView.init(today, nextYear.getTime())
                .withSelectedDate(today);
        calendarPickerView.setOnDateSelectedListener(this);
    }

    @Override
    public void onDateSelected(Date date) {

    }

    @Override
    public void onDateUnselected(Date date) {

    }
}
