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

import classes.DoctorClass;
import com.asia.clinic_project_software.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by 106 on 1/3/2018.
 */

public class DoctorAdapter extends ArrayAdapter {
    public ArrayList<DoctorClass> myDoctor;
    public Context context;
    public int resource;
    public DoctorAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<DoctorClass> myDoctor) {
        super(context, resource, myDoctor);
        this.myDoctor =myDoctor;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);
        // get views from convert view
        TextView doctorId = (TextView) convertView.findViewById(R.id.doctorId);
        TextView doctorName = (TextView) convertView.findViewById(R.id.doctorName);
        ImageView image = (ImageView) convertView.findViewById(R.id.doctorImage);
        // get item from arrayList
        DoctorClass doctor = (DoctorClass) getItem(position);
        Picasso.get()
                .load(doctor.getImage())
                .error(R.drawable.logo)
                .into(image);

       // image.setImageDrawable(image.getDrawable());
        doctorId.setText(Integer.toString(doctor.getId()));
        doctorName.setText(doctor.getName().toString());

        return convertView;
    }
}
