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

import classes.DoctorClass;
import classes.MyReservationsClass;

public class MyReservationsAdapter extends ArrayAdapter {
        public ArrayList<MyReservationsClass> myReservations;
        public Context context;
        public int resource;
        public MyReservationsAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<MyReservationsClass> myReservations) {
            super(context, resource, myReservations);
            this.myReservations =myReservations;
            this.context = context;
            this.resource = resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            convertView = LayoutInflater.from(context).inflate(resource, null);
            // get views from convert view
            TextView t_start=convertView.findViewById(R.id.start);
            TextView t_end=convertView.findViewById(R.id.end);
            TextView t_day=convertView.findViewById(R.id.day);
            TextView t_doctorId=convertView.findViewById(R.id.doctorId);
            TextView t_doctorName=convertView.findViewById(R.id.clinicName);
            TextView t_clinicId=convertView.findViewById(R.id.clinicId);
            TextView t_clinicName=convertView.findViewById(R.id.clinicName);
            TextView t_dayId=convertView.findViewById(R.id.dayId);
            // get item from arrayList
            MyReservationsClass object = (MyReservationsClass) getItem(position);

            // set data in to place
            t_start.setText(object.getStart());
            t_end.setText(object.getEnd());
            t_day.setText(object.getDay());
            t_clinicName.setText(object.getClinicName());
            t_doctorName.setText(object.getDoctorName());
            t_doctorId.setText(Integer.toString(object.getDoctor_id()));
            t_clinicId.setText(Integer.toString(object.getClinic_id()));
            t_dayId.setText(Integer.toString(object.getDayId()));

            return convertView;
        }

}
