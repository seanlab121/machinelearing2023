package com.dcca.jane.mmy.activities.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.dcca.jane.mmy.R;
import com.dcca.jane.mmy.activities.DailyViewActivity;

import java.util.Calendar;
import java.util.Date;

///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link DatePickerFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link DatePickerFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private int dayOfWeek;

    public DatePickerFragment() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Use the current date as the default date in the date picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        return new DatePickerDialog(getActivity(),this, year, month, day);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_date_picker, container, false);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        ((DailyViewActivity)getActivity()).setDayStorage(year, month, day);

        TextView tv = getActivity().findViewById(R.id.tv_day_title);
        String monthName = ((DailyViewActivity)getActivity()).getMonthName(month);
        String dayName = ((DailyViewActivity)getActivity()).getDayName(dayOfWeek);


        String thisString = dayName + ", " + day + " " + monthName + " " + year;
        tv.setText(thisString);

        Date startDate = ((DailyViewActivity)getActivity()).setDateLimits(calendar, 0);
        Date endDate = ((DailyViewActivity)getActivity()).setDateLimits(calendar, 1);
        ((DailyViewActivity)getActivity()).fetchData(startDate, endDate);
    }
}
