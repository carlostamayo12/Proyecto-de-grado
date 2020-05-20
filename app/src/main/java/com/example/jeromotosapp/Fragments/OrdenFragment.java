package com.example.jeromotosapp.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jeromotosapp.Adapter.AdapterListaOrden;
import com.example.jeromotosapp.Model.Moto;
import com.example.jeromotosapp.Model.Orden;
import com.example.jeromotosapp.Model.OrdenView;
import com.example.jeromotosapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrdenFragment extends Fragment {


  //Controles
  private View v;
  private Spinner spinnerOrden;
  private TextView ordenSubTitulo;

  private TextView estado;
  private TextView placa;
  private TextView km;
  private TextView costoServicio;
  private TextView costoRepuestos;
  private TextView costoTotal;
  private TextView tecnicoNombre;

  private TextView fechaEntrada;
  private TextView fechaSalida;
  private TextView solicitudes;
  private TextView observaciones;


  private ListView listViewServicios;


  //ArrayList
  ArrayList<String> listaSpinnerOrdenIndex;
  ArrayList<OrdenView> listaOrdenEnvio;
  ArrayList<Orden> listaOrden;

  //Adapter
  private AdapterListaOrden adapterListaOrden;
  private ArrayAdapter<String> adapter;

  String motoId;
  String tipoMotoId;
  String placaStr;

  public OrdenFragment(){}

  public OrdenFragment(String motoId, String tipoMotoId) {

    this.motoId = motoId;
    this.tipoMotoId = tipoMotoId;
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    v = inflater.inflate(R.layout.fragment_orden, container, false);

    //Instancia Controles
    listViewServicios = (ListView) v.findViewById(R.id.listaServicios);
    ordenSubTitulo = (TextView) v.findViewById(R.id.ordenSubtitulo);
    listaOrdenEnvio = new ArrayList<>();
    solicitudes = (TextView) v.findViewById(R.id.solicitudes);
    observaciones = (TextView) v.findViewById(R.id.observaciones);

    estado = (TextView) v.findViewById(R.id.estado);
    placa = (TextView) v.findViewById(R.id.placa);
    km = (TextView) v.findViewById(R.id.km);
    costoServicio = (TextView) v.findViewById(R.id.costoServicio);
    costoRepuestos = (TextView) v.findViewById(R.id.costoRepuestos);
    costoTotal = (TextView) v.findViewById(R.id.costoTotal);
    tecnicoNombre = (TextView) v.findViewById(R.id.tecnicoNombre);
    fechaEntrada = (TextView) v.findViewById(R.id.fechaEntrada);
    fechaSalida = (TextView) v.findViewById(R.id.fechaSalida);

    placaStr = "";

    this.consultar();
    return  v;
  }



  public void consultar(){

    String dominio = "https://jeromotos24app.000webhostapp.com/";
    //String dominio = "http://192.168.1.55/";
    String URL = dominio + "Conexion/tabla.php?motoId=" + motoId + "&tipoMotoId=" + tipoMotoId;


    StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        try {

          JSONObject jsonObject = new JSONObject(response);
          //String success = jsonObject.getString("success");

          //Moto
          JSONArray arrayMoto = jsonObject.getJSONArray("moto");
          JSONObject objectMoto = arrayMoto.getJSONObject(0);
          Moto moto = new Moto(objectMoto);
          placaStr = moto.getPlaca();

          //Array Orden
          JSONArray arrayOrden = jsonObject.getJSONArray("orden");
          listaOrden = new ArrayList<>();
          for (int i = 0; i < arrayOrden.length(); i++){
            JSONObject object = arrayOrden.getJSONObject(i);
            Orden orden = new Orden(object);
            listaOrden.add(orden);
          }

          // Listas Spinner
          listaSpinnerOrdenIndex = new ArrayList<>();
          if(listaOrden.size() > 0){
            listaSpinnerOrdenIndex = Orden.extractIndex(listaOrden);
            String index = listaSpinnerOrdenIndex.get(0);
            for (Orden o:listaOrden) {
              if(o.getId() == Integer.valueOf(index)){

                estado.setText(o.getEstado());
                placa.setText(placaStr);
                km.setText(String.valueOf(o.getKilometraje()));
                costoServicio.setText("Servicio:  $ " + String.valueOf(o.getCostoServicio()));
                costoRepuestos.setText("Repuestos: $ " + String.valueOf(o.getCostoRepuestos()));
                costoTotal.setText("Total:     $ " + String.valueOf(o.getCostoServicio()+ o.getCostoRepuestos()));
                tecnicoNombre.setText(o.getTecnicoNombre());

                if(o.getFechaIngreso() > 0){
                  long m = o.getFechaIngreso()*24*60*60*1000L;
                  Date utilDate = new Date(m);
                  SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                  fechaEntrada.setText(format.format(utilDate));
                }
                else{
                  fechaEntrada.setText("No Registra Fecha Ingreso");
                }

                if(o.getFechaSalida() > 0){
                  long m = o.getFechaSalida()*24*60*60*1000L;
                  Date utilDate = new Date(m);
                  SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                  fechaSalida.setText(format.format(utilDate));
                }
                else{
                  fechaSalida.setText("No Registra");
                }

                if(String.valueOf(o.getSolicitudes()).equals("")){
                  solicitudes.setText("No hay solicitudes");
                }else{
                  solicitudes.setText(o.getSolicitudes());
                }

                if(o.getObservaciones().equals("")){
                  observaciones.setText("No hay observaciones");
                }else{
                  observaciones.setText(o.getObservaciones());
                }


                String servicioSolicitado = o.getServiciosSolicitados() == 1 ? "X" : "";
                String servicioRealizado = o.getServiciosRealizados() == 1 ? "X" : "";
                listaOrdenEnvio.add(new OrdenView(o,servicioSolicitado,servicioRealizado) );
              }
            }
            adapterListaOrden = new AdapterListaOrden(getActivity(),listaOrdenEnvio);
            listViewServicios.setAdapter(adapterListaOrden);
          }else{
            ordenSubTitulo.setText("No existen ordenes de servicio ");
          }


          //Spinner Orden
          spinnerOrden = (Spinner) v.findViewById(R.id.spinnerOrden);

          adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, listaSpinnerOrdenIndex);
          spinnerOrden.setAdapter(adapter);

          spinnerOrden.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              String index = listaSpinnerOrdenIndex.get(position);
              listaOrdenEnvio.clear();
              for (Orden o:listaOrden) {
                if(o.getId() == Integer.valueOf(index)){

                  estado.setText(o.getEstado());
                  placa.setText(placaStr);
                  km.setText(String.valueOf(o.getKilometraje()));
                  costoServicio.setText("Servicio:  $ " + String.valueOf(o.getCostoServicio()));
                  costoRepuestos.setText("Repuestos: $ " + String.valueOf(o.getCostoRepuestos()));
                  costoTotal.setText("Total:     $ " + String.valueOf(o.getCostoServicio()+ o.getCostoRepuestos()));
                  tecnicoNombre.setText(o.getTecnicoNombre());

                  if(o.getFechaIngreso() > 0){
                    long m = o.getFechaIngreso()*24*60*60*1000L;
                    Date utilDate = new Date(m);
                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                    fechaEntrada.setText(format.format(utilDate));
                  }
                  else{
                    fechaEntrada.setText("No Registra Fecha Ingreso");
                  }

                  if(o.getFechaSalida() > 0){
                    long m = o.getFechaSalida()*24*60*60*1000L;
                    Date utilDate = new Date(m);
                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                    fechaSalida.setText(format.format(utilDate));
                  }
                  else{
                    fechaSalida.setText("No Registra");
                  }

                  if(o.getSolicitudes().isEmpty()){
                    solicitudes.setText("No hay solicitudes");
                  }else{
                    solicitudes.setText(o.getSolicitudes());
                  }

                  if(o.getObservaciones().isEmpty()){
                    observaciones.setText("No hay observaciones");
                  }else{
                    observaciones.setText(o.getObservaciones());
                  }

                  //
                  //observaciones.setText(o.getObservaciones());

                  String servicioSolicitado = o.getServiciosSolicitados() == 1 ? "X" : "";
                  String servicioRealizado = o.getServiciosRealizados() == 1 ? "X" : "";
                  listaOrdenEnvio.add(new OrdenView(o,servicioSolicitado,servicioRealizado) );
                }
              }
              adapterListaOrden = new AdapterListaOrden(getActivity(),listaOrdenEnvio);
              listViewServicios.setAdapter(adapterListaOrden);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
              ordenSubTitulo.setText("Hola");
            }
          });
        }catch(JSONException e) {
          e.printStackTrace();
          Toast.makeText(getContext(),"Registro Error Orden" + e.toString(), Toast.LENGTH_LONG).show();
        }
      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        Toast.makeText(getActivity(),"OnError" + error.toString(), Toast.LENGTH_LONG).show();
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




