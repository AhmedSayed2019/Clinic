package adabtors;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.asia.clinic_project_software.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import classes.DateClass;
import classes.DoctorClass;

/**
 * Created by 106 on 1/3/2018.
 */

public class DateAdapter extends ArrayAdapter {
    public ArrayList<DateClass> myDate;
    public Context context;
    public int resource;
    public DateAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<DateClass> myDate) {
        super(context, resource, myDate);
        this.myDate =myDate;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);
        // get views from convert view
        TextView day = (TextView) convertView.findViewById(R.id.day);
        TextView start = (TextView) convertView.findViewById(R.id.start);
        TextView end = (TextView) convertView.findViewById(R.id.end);
        TextView dayId = (TextView) convertView.findViewById(R.id.dayId);


        // get item from arrayList
        DateClass date = (DateClass) getItem(position);

       // set date in list

        day.setText(date.getDay().toString());
        start.setText(date.getStart().toString());
        end.setText(date.getEnd().toString());
        dayId.setText(Integer.toString(date.getDayId()));

        return convertView;
    }
}
