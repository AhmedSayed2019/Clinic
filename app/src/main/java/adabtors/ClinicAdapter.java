package adabtors;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.asia.clinic_project_software.R;
import java.util.ArrayList;
import classes.ClinicClass;


/**
 * Created by 106 on 1/3/2018.
 */

public class ClinicAdapter extends ArrayAdapter {
    public ArrayList<ClinicClass> myClinic;
    public Context context;
    public int resource;
    public ClinicAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<ClinicClass> myClinic) {
        super(context, resource, myClinic);
        this.myClinic =myClinic;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);
        // get views from convert view
        TextView clinicId = (TextView) convertView.findViewById(R.id.clinicId);
        TextView clinicName = (TextView) convertView.findViewById(R.id.clinicName);
        TextView clinicAddress = (TextView) convertView.findViewById(R.id.clinicAddress);
        TextView clinicPhone = (TextView) convertView.findViewById(R.id.clinicPhone);

        // get item from arrayList
        ClinicClass clinic = (ClinicClass) getItem(position);


        clinicId.setText(Integer.toString(clinic.getId()));
        clinicName.setText(clinic.getName().toString());
        clinicAddress.setText(clinic.getAddress().toString());
        clinicPhone.setText(clinic.getPhone().toString());

        return convertView;
    }
}
