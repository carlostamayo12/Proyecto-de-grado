package com.example.jeromotosapp.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jeromotosapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class MotoFragment extends Fragment {

  private String motoId;
  private String tipoMotoId;
  private String URL;
  View v;

  public MotoFragment(){}

  public MotoFragment(String motoId, String tipoMotoId) {
    this.motoId = motoId;
    this.tipoMotoId = tipoMotoId;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    v = inflater.inflate(R.layout.fragment_moto, container, false);
    listar();
    return  v;

  }

  public void listar(){

    String dominio = "https://jeromotos24app.000webhostapp.com/";

    //String dominio = "http://192.168.1.55/";
    String URL = dominio + "Conexion/moto.php?motoId=" + motoId;


    //String URL = "http://192.168.1.55/Conexion/moto.php?motoId=" + motoId;
    //https://jeromotos24app.000webhostapp.com/Conexion/moto.php?motoId=1
    StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        try {

          JSONObject jsonObject = new JSONObject(response);
          String success = jsonObject.getString("success");

          //if(success == "1"){
          JSONArray arrayMoto = jsonObject.getJSONArray("moto");
          JSONObject obj = arrayMoto.getJSONObject(0);


          TextView placa = (TextView) v.findViewById(R.id.placa);
          TextView color = (TextView) v.findViewById(R.id.color);
          TextView kmPromedio = (TextView) v.findViewById(R.id.kmPromedio);
          TextView referencia = (TextView) v.findViewById(R.id.referencia);
          TextView marca = (TextView) v.findViewById(R.id.marca);

          placa.setText(obj.getString("placa"));
          color.setText(obj.getString("color"));
          kmPromedio.setText(obj.getString("km_promedio")+ " Km/dia ");
          referencia.setText(obj.getString("tipoMotoReferencia"));
          marca.setText(obj.getString("marcaNombre"));

        } catch (JSONException e) {
          e.printStackTrace();
          Toast.makeText(getContext(),"Registro Error moto" + e.toString(), Toast.LENGTH_LONG).show();
        }
      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        Toast.makeText(getActivity(),"OnError Moto" + error.toString(), Toast.LENGTH_LONG).show();
      }
    }){
      @Override protected Map<String, String> getParams() throws AuthFailureError {
        Map <String, String > params = new HashMap<>();
        params.put("uid", "QmxIp8cRtWZEoV0mk4CS2Xw1CBL2");
        return super.getParams();
      }
    };
    RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
    requestQueue.add(stringRequest);
  }

}
