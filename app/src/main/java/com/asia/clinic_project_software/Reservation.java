package com.asia.clinic_project_software;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import classes.DoctorClass;


/**
 * A simple {@link Fragment} subclass.
 */
public class Reservation extends Fragment {
    TextView reserved;
    Button accept;
    Button cancel;
    TextView start;
    TextView end;
    TextView number;
    TextView day;


    String clinicId;
    String doctorId;
    String dayId;
    String doctorName;
    String num;
    int date;

    // public static final String URL_RESERVE = "http://10.0.2.2/sw/reservation.php";
    public static final String URL_RESERVE = "https://speedless-expiratio.000webhostapp.com/reservation.php";
    // public static final String URL_CANCEL = "http://10.0.2.2/sw/reservation_cancel.php";
    public static final String URL_CANCEL = "https://speedless-expiratio.000webhostapp.com/reservation_cancel.php";
    // public  final static  String URL="http://10.0.2.2/sw/date.php";
    public  final static  String URL="https://speedless-expiratio.000webhostapp.com/date.php";

    public  final static  String URL_NUMBER="https://speedless-expiratio.000webhostapp.com/number.php";




    public Reservation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_reservation, container, false);
        reserved=view.findViewById(R.id.reserved);
        number=view.findViewById(R.id.number);

        // get data from Dates
        Bundle bundle_get =getArguments();
        if(bundle_get !=null){
            doctorId=bundle_get.getString("doctorId");
            clinicId=bundle_get.getString("clinicId");
            doctorName=bundle_get.getString("doctorName");
            dayId= bundle_get.getString("dayId");
            date=bundle_get.getInt("date");
        }

        getAllData(clinicId,doctorId,date);
        getYourNumber(doctorId,dayId);

        String text ="Thank you for using this application to make an appointment with "+doctorName+" and wish you healing";

        reserved.setText(text);
        accept=view.findViewById(R.id.accept);
        cancel=view.findViewById(R.id.cancel);
        day=view.findViewById(R.id.day);
        start=view.findViewById(R.id.start);
        end=view.findViewById(R.id.end);


        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accept.setVisibility(View.INVISIBLE);
                cancel.setVisibility(View.VISIBLE);
                reserved.setVisibility(View.VISIBLE);


                String email=getEmail();
                reserve(email,doctorId,clinicId,URL_RESERVE);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel.setVisibility(View.INVISIBLE);
                accept.setVisibility(View.VISIBLE);
                reserved.setVisibility(View.INVISIBLE);


                String email=getEmail();

                reserve(email,doctorId,clinicId,URL_CANCEL);


            }
        });


        return view;
    }
    public  void  getYourNumber(String doctorId ,String dayId)
    {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                URL_NUMBER+"?doctorId="+doctorId+"&dayId="+dayId,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // get number of reseave
                            JSONArray array = response.getJSONArray("data");
                            //for(int index=0; index<array.length(); index++){
                            // get category
                            JSONObject myobject = array.getJSONObject(0);
                            // set category object into categories arrayList

                            number.setText(myobject.getString("number"));


                            // }

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
    public  void getAllData(final String clinicId, final String doctorId, final int date)
    {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                URL+"?doctorId="+ doctorId+"&clinicId="+clinicId,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // create arrayList
                            ArrayList<DoctorClass> myArray = new ArrayList<DoctorClass>();
                            // get all categories from server
                            JSONArray array = response.getJSONArray("data");
                            //for(int index=0; index<array.length(); index++){
                            // get category
                            JSONObject myobject = array.getJSONObject(date);
                            // set category object into categories arrayList


                            day.setText(myobject.getString("day"));
                            start.setText(myobject.getString("start"));
                            end.setText(myobject.getString("end"));

                            // }

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

    public void reserve(final String email, final String doctorId, final String clinicId, String MYURL)
    {
        StringRequest request = new StringRequest(Request.Method.POST, MYURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.equals("success")){
                    Toast.makeText(getContext(), "done", Toast.LENGTH_SHORT).show();
                    // firstly save shared preference
                }else{
                    Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("doctorId", doctorId.toString());
                params.put("clinicId", clinicId.toString());
                params.put("patientEmail", email.toString());
                params.put("day", dayId.toString());

                return params;

            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);

    }
private String getEmail(){

    SharedPreferences share = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = share.edit();
    String email=share.getString("Email", "");
    editor.apply();

    return email;
}

}
