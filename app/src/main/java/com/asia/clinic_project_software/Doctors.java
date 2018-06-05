package com.asia.clinic_project_software;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import adabtors.DoctorAdapter;
import classes.DoctorClass;


/**
 * A simple {@link Fragment} subclass.
 */
public class Doctors extends Fragment {


    public Doctors() {
        // Required empty public constructor
    }
    // public  final static  String URL="http://10.0.2.2/sw/alldoctor.php"; //local
    private final static  String URL="https://speedless-expiratio.000webhostapp.com/alldoctor.php";// online
    private static ListView doctorList;
    private static String clinicId;
    private static String departmentId;
    private static ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_doctors, container, false);

        doctorList=view.findViewById(R.id.doctorList);
        progressBar=view.findViewById(R.id.progressBar);

        //get data from Departments
        Bundle bundle_get=getArguments();
        if (bundle_get !=null) {
            clinicId=bundle_get.getString("clinicId");
            departmentId=bundle_get.getString("departmentId");
        }



        doctorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView MyId=view.findViewById(R.id.doctorId);
                String doctorId=MyId.getText().toString();

                //send data to DoctorSetails
                Bundle bundle_set =new Bundle();
                bundle_set.putString("doctorId",doctorId);
                bundle_set.putString("clinicId",clinicId);


                DoctorDetails doctorDetails =new DoctorDetails();
                doctorDetails.setArguments(bundle_set);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment, doctorDetails)
                        .addToBackStack(null)
                        .commit();


            }
        });

        getAllData(clinicId,departmentId);






        return view;
    }
    private   void getAllData(String clinicId,String departmentId) {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                URL+"?clinicid="+clinicId+"&departmentid=" + departmentId,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // create arrayList
                            ArrayList<DoctorClass> myArray = new ArrayList<DoctorClass>();
                            // get all categories from server
                            JSONArray array = response.getJSONArray("data");
                            for(int index=0; index<array.length(); index++){
                                // get category
                                JSONObject myobject = array.getJSONObject(index);
                                // set category object into categories arrayList
                                myArray.add(new DoctorClass(myobject.getInt("id")
                                        , myobject.getString("name")
                                        , myobject.getString("image")));
                            }
                            DoctorAdapter mydiabets = new DoctorAdapter(getContext(), R.layout.all_doctor, myArray);
                            // set adapter to list view
                            doctorList.setAdapter(mydiabets);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
        progressBar.setVisibility(View.GONE);
    }

}
