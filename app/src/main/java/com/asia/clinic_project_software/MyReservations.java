package com.asia.clinic_project_software;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import adabtors.ClinicAdapter;
import adabtors.DateAdapter;
import adabtors.DoctorAdapter;
import adabtors.MyReservationsAdapter;
import classes.ClinicClass;
import classes.DateClass;
import classes.DoctorClass;
import classes.MyReservationsClass;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyReservations extends Fragment {




    private static String clinicId;
    private static String doctorId;
    private static String dayId;
    private static String doctorName;

    private  static ListView myList;

    private static ProgressBar progressBar;
    private   final static  String URL="https://speedless-expiratio.000webhostapp.com/my_reservation.php?Email=";
    public MyReservations() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_my_reservations, container, false);

        progressBar=view.findViewById(R.id.progressBar);
        myList=view.findViewById(R.id.myList);
        getAllData();

        return view;
    }

    private   void getAllData() {
        progressBar.setVisibility(View.VISIBLE);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                URL+getEmail(),
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // create arrayList
                            ArrayList<MyReservationsClass> myArray = new ArrayList<MyReservationsClass>();
                            // get all categories from server
                            JSONArray array = response.getJSONArray("data");
                            for(int index=0; index<array.length(); index++){
                                // get category
                                JSONObject myobject = array.getJSONObject(index);
                                // set category object into categories arrayList
                              myArray.add(new MyReservationsClass(myobject.getInt("doctor_id"),
                                      myobject.getString("doctorName"),myobject.getInt("clinic_id"),
                                      myobject.getString("clinicName"),
                                      myobject.getInt("dayId"),
                                      myobject.getString("day"),
                                      myobject.getString("start"),
                                      myobject.getString("end") ));



                            }
                            MyReservationsAdapter mydiabets = new MyReservationsAdapter(getContext(), R.layout.all_my_reservation, myArray);
                            // set adapter to list view
                            myList.setAdapter(mydiabets);

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


    private String getEmail(){

        SharedPreferences share = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        String email=share.getString("Email", "");
        editor.apply();

        return email;
    }


}
/* */