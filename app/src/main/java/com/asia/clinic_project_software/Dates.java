package com.asia.clinic_project_software;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

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

import adabtors.DateAdapter;
import classes.DateClass;


/**
 * A simple {@link Fragment} subclass.
 */
public class Dates extends Fragment {
    // public  final static  String URL="http://10.0.2.2/sw/date.php";
    public  final static  String URL="https://speedless-expiratio.000webhostapp.com/date.php";

    TextView start;
    TextView end;
    TextView day;
    TextView number;
    ListView dateList;
    String clinicId;
    String doctorId;
String doctorName;
    public Dates() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_dates, container, false);

        day=view.findViewById(R.id.day);
        start=view.findViewById(R.id.start);
        end=view.findViewById(R.id.end);
        number=view.findViewById(R.id.number);

        dateList=view.findViewById(R.id.dateList);


        Bundle bundle_get =getArguments();
        if(bundle_get !=null){
            doctorId=bundle_get.getString("doctorId");
            clinicId=bundle_get.getString("clinicId");
            doctorName=bundle_get.getString("doctorName");
        }

        getAllData(clinicId,doctorId);


        dateList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                TextView dayId= view.findViewById(R.id.dayId);
                String MyId =dayId.getText().toString();

        //set data to Reservation
                // create object form reservation
                Reservation reservation =new Reservation();
                // set data to bundle_set
                Bundle bundle_set =new Bundle();
                bundle_set.putString("clinicId",clinicId);
                bundle_set.putString("doctorId",doctorId);
                bundle_set.putString("doctorName",doctorName);
                bundle_set.putString("dayId",MyId);
                bundle_set.putInt("date",position);
                // set data to bundle_set(data) to Reservation
               reservation.setArguments(bundle_set);
               // go to Reservation
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment,reservation )
                        .addToBackStack(null)
                        .commit();


            }
        });
        return view;
    }
    public  void getAllData(final String clinicId, final String doctorId) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                URL+"?doctorId="+ doctorId+"&clinicId="+clinicId,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // create arrayList
                            ArrayList<DateClass> myArray = new ArrayList<DateClass>();
                            // get all date from server
                            JSONArray array = response.getJSONArray("data");
                            for(int index=0; index<array.length(); index++){
                                // get date
                                JSONObject myobject = array.getJSONObject(index);
                                // set date object into dates arrayList
//                            day.setText(myobject.getString("day"));
//                            start.setText(myobject.getString("start"));
//                            end.setText(myobject.getString("end"));

                                myArray.add(new DateClass(myobject.getString("day"),
                                        myobject.getString("start"),
                                        myobject.getString("end"),
                                        myobject.getInt("number")));
                            }
                            DateAdapter adapter=new DateAdapter(getContext(),R.layout.all_date,myArray);
                            dateList.setAdapter(adapter);


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

}
