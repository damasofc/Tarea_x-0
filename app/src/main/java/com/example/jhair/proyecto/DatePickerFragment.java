package com.example.jhair.proyecto;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Locale;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }
    private Calendar sc;

    public Calendar getSc() {
        return sc;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        TextView tv1= (TextView) getActivity().findViewById(R.id.edit_date);
        sc = Calendar.getInstance();
        sc.set(view.getYear(),view.getMonth(),view.getDayOfMonth());
        Locale locale = Locale.getDefault();
        String dia = sc.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.SHORT,locale);
        String mes = sc.getDisplayName(Calendar.MONTH,Calendar.SHORT,locale);
        tv1.setText(dia.substring(0,mes.length()-1)+", "+ mes+" "+view.getDayOfMonth()+", "+view.getYear());

    }
}
