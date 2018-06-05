package com.asia.clinic_project_software;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
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

import adabtors.ClinicAdapter;
import classes.ClinicClass;


/**
 * A simple {@link Fragment} subclass.
 */
public class Clinics extends Fragment {
    // public  final static  String URL="http://10.0.2.2/sw/allclinic.php";//local link
    private  final static  String URL="https://speedless-expiratio.000webhostapp.com/allclinic.php";//online link
    private static ListView clinicList;
    private static ProgressBar progressBar;
    public Clinics() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_clinics, container, false);
        clinicList=view.findViewById(R.id.clinicList);
        progressBar=view.findViewById(R.id.progressBar);
        getAllData();
        clinicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView clinicid= view.findViewById(R.id.clinicId);
                String clinicId =clinicid.getText().toString();

                Departments departments =new Departments();

                Bundle bundle =new Bundle();
                bundle.putString("clinicId",clinicId.toString());

                departments.setArguments(bundle);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment, departments)
                        .addToBackStack(null)
                        .commit();



            }
        });


        return view;
    }

    private   void getAllData() {
        progressBar.setVisibility(View.VISIBLE);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,URL , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    // create arrayList
                    ArrayList<ClinicClass> myArray = new ArrayList<ClinicClass>();
                    // get all categories from server
                    JSONArray array = response.getJSONArray("data");
                    for(int index=0; index<array.length(); index++){
                        // get category
                        JSONObject myobject = array.getJSONObject(index);
                        // set category object into categories arrayList
                        myArray.add(new ClinicClass(myobject.getInt("id")
                                , myobject.getString("name")
                                , myobject.getString("street")+" "+myobject.getString("district")+" "+myobject.getString("city")
                                , myobject.getString("phone")));
                    }
                    ClinicAdapter myadabtor = new ClinicAdapter(getContext(), R.layout.all_clinics, myArray);
                    // set adapter to list view
                    clinicList.setAdapter(myadabtor);

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
