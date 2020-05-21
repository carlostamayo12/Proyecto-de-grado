package com.example.jeromotosapp.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jeromotosapp.Adapter.AdapterListaTabla;
import com.example.jeromotosapp.Model.Moto;
import com.example.jeromotosapp.Model.Orden;
import com.example.jeromotosapp.Model.Tabla;
import com.example.jeromotosapp.Model.TablaView;
import com.example.jeromotosapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class TablaFragment extends Fragment {

  private View v;
  private AdapterListaTabla adapterLista;
  private ListView listTabla;
  private TextView tituloTabla;

  public TablaFragment() {
    // Required empty public constructor
  }


  String motoId;
  String tipoMotoId;
  public TablaFragment(String motoId, String tipoMotoId) {
    this.motoId = motoId;
    this.tipoMotoId = tipoMotoId;
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    //Toast.makeText(getContext(),"TablaFRagment", Toast.LENGTH_LONG).show();
    v = inflater.inflate(R.layout.fragment_tabla, container, false);
    listTabla = (ListView) v.findViewById(R.id.listTabla);
    consultar();
    return v;
  }

  public void consultar(){
    //String URL = "http://192.168.1.55/Conexion/tabla.php?motoId=" + motoId + "&&tipoMotoId=" + tipoMotoId;
    //String URL = "https://jeromotos24app.000webhostapp.com/Conexion/tabla.php?motoId=" + motoId + "&&tipoMotoId=" + tipoMotoId;

    String dominio = "https://jeromotos24app.000webhostapp.com/";
    //String dominio = "http://192.168.1.55/";
    String URL = dominio + "Conexion/tabla.php?motoId=" + motoId + "&tipoMotoId=" + tipoMotoId;


    StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        try {

          JSONObject jsonObject = new JSONObject(response);
          //String success = jsonObject.getString("success");

          //JSONArray arrayOrden = jsonObject.getJSONArray("orden");
          JSONArray arrayMoto = jsonObject.getJSONArray("moto");

          Moto moto = new Moto();
          JSONObject objectMoto = arrayMoto.getJSONObject(0);
          moto.setId(objectMoto.getString("moto.id"));
          moto.setPlaca(objectMoto.getString("moto.placa"));
          moto.setColor(objectMoto.getString("moto.color"));
          moto.setKm_promedio(objectMoto.getInt("moto.km_promedio"));
          moto.setTipoMotoReferencia(objectMoto.getString("moto.tipoMotoReferencia"));
          moto.setMarcaNombre(objectMoto.getString("moto.marcaNombre"));

          tituloTabla = (TextView) v.findViewById(R.id.tituloTabla);
          String titulo = "Tabla Mantenimiento " + moto.getTipoMotoReferencia();
          tituloTabla.setText(titulo);

          //Array Orden
          JSONArray arrayOrden = jsonObject.getJSONArray("orden");
          ArrayList<Orden> listaOrden = new ArrayList<>();
          for (int i = 0; i < arrayOrden.length(); i++){
            JSONObject object = arrayOrden.getJSONObject(i);
            Orden orden = new Orden(object);
            listaOrden.add(orden);
          }

          //Array TablaView Envio
          ArrayList<TablaView> listTablaView = new ArrayList<>();
          //Array Tabla
          JSONArray arrayTabla = jsonObject.getJSONArray("tabla");
          String t = "" +
                  "";
          ArrayList<Tabla> listatabla = new ArrayList<>();
          for (int i = 0; i < arrayTabla.length(); i++){
            JSONObject object = arrayTabla.getJSONObject(i);
            //Tabla tabla = new Tabla(object);


            //Borrar
            Tabla tabla = new Tabla(
                    object.getInt("id"),
                    object.getInt("contadorKm"),
                    object.getInt("contadorTime"),
                    object.getInt("servicioId"),
                    object.getInt("kilometraje"),
                    object.getInt("tiempo"),
                    object.getString("servicioNombre")
                    );


            int position = TablaView.ultimaOrdenIndex(listaOrden, tabla.getServicioId(), tabla);
            listTablaView.add(TablaView.view(listaOrden, tabla, position));
          }
          adapterLista = new AdapterListaTabla(getActivity(),listTablaView);
          listTabla.setAdapter(adapterLista);

        } catch (JSONException e) {
          e.printStackTrace();
          Toast.makeText(getActivity(),"Registro Error Tabla" + e.toString(), Toast.LENGTH_LONG).show();
        }
      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        Toast.makeText(getActivity(),"OnError Tabla" + error.toString(), Toast.LENGTH_LONG).show();
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
