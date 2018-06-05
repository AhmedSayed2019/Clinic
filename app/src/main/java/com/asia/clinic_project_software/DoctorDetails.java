package com.asia.clinic_project_software;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class DoctorDetails extends Fragment {
    TextView dId;
    ImageView dImage;
    TextView dName;
    TextView dAge;
    TextView dPhone;
    TextView dgname;
    TextView detection;

    String clinicId;
    String doctorId;
    // public  final static  String URL="http://10.0.2.2/sw/doctor.php";
    public  final static  String URL="https://speedless-expiratio.000webhostapp.com/doctor.php";

    public DoctorDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_doctor_details, container, false);

        dId=view.findViewById(R.id.dId);
        dImage=view.findViewById(R.id.dImage);
        dName=view.findViewById(R.id.dName);
        dPhone=view.findViewById(R.id.dPhone);
        dAge=view.findViewById(R.id.dAge);
        dgname=view.findViewById(R.id.dgname);
        detection=view.findViewById(R.id.detection);

        Bundle bundle_get =getArguments();
        if(bundle_get !=null){
           clinicId= bundle_get.getString("clinicId");
           doctorId= bundle_get.getString("doctorId");

        }

        getAllData(doctorId);


        Button reservation= view.findViewById(R.id.reservation);
        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send data to Reservation
                Dates dates =new Dates();
                Bundle bundle_set =new Bundle();
                bundle_set.putString("doctorId",doctorId);
                bundle_set.putString("clinicId",clinicId);
                bundle_set.putString("doctorName",dName.getText().toString());
                dates.setArguments(bundle_set);

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment,dates )
                        .addToBackStack(null)
                        .commit();

            }
        });


        return view;
    }








    public  void getAllData(String doctorId) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,URL+"?doctorid="+doctorId,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // get all categories from server
                            JSONArray array = response.getJSONArray("data");
                            // get category
                            JSONObject myobject = array.getJSONObject(0);

                            Picasso.get()
                                    .load(myobject.getString("image"))
                                    .error(R.drawable.logo)
                                    .into(dImage);
                            String id=String.valueOf(myobject.getInt("id"));
                            String name=myobject.getString("name");
                            String phone=myobject.getString("phone");
                            String age=myobject.getString("age");
                            String gname=myobject.getString("qname");
                            String mydetection=myobject.getString("detection");
                            dId.setText(id);
                            dName.setText(name);
                            dPhone.setText(phone);
                            dAge.setText(age);
                            dgname.setText(gname);
                            detection.setText(mydetection);



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
    }

/*
    public void reservation(View view) {
        //send data to Reservation
        Reservation reservation=new Reservation();
        Bundle bundle_set =new Bundle();
        bundle_set.putString("doctorId",doctorId);
        bundle_set.putString("clinicId",clinicId);
        bundle_set.putString("doctorName",dName.getText().toString());
        reservation.setArguments(bundle_set);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, reservation)
                .addToBackStack(null)
                .commit();
    }*/
}
