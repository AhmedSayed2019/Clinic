package com.asia.clinic_project_software;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Departments extends Fragment {

    ListView departments;
    String clinicId;
    Bundle bundle_set;
    public Departments() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_departments, container, false);
        departments=view.findViewById(R.id.department);
        ArrayList<String> myList=new ArrayList<String>();
        myList.add("consideration");
        myList.add("Ear, Nose and Throat");
        myList.add("teeth");
        myList.add("bones");
        myList.add("Neurologists");
        myList.add("Belly");
        ArrayAdapter myAdabtor=new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,myList);
        departments.setAdapter(myAdabtor);




        departments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {




                bundle_set=new Bundle();

                switch (position)
                {
                    case 0:

                        bundle_set.putString("departmentId","1");
                        break;
                    case 1:
                        bundle_set.putString("departmentId","2");
                        break;
                    case 2:
                        bundle_set.putString("departmentId","3");
                        break;
                    case 3:
                        bundle_set.putString("departmentId","4");
                        break;
                    case 4:
                        bundle_set.putString("departmentId","5");
                        break;
                    case 5:
                        bundle_set.putString("departmentId","6");
                        break;
                    case 6:
                        bundle_set.putString("departmentId","7");
                        break;
                }
              bundle_set.putString("clinicId",clinicId);

                Bundle bundle1_get=getArguments();
                if (bundle1_get !=null) {
                    clinicId= bundle1_get.getString("clinicId");
                }


               Doctors doctors =new Doctors();
               doctors.setArguments(bundle_set);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment, doctors)
                        .addToBackStack(null)
                        .commit();

            }
        });



        return view;
    }

}
