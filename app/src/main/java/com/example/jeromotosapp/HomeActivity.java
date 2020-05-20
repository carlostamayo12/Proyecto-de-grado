package com.example.jeromotosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
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
import com.example.jeromotosapp.Adapter.AdapterListaMotos;
import com.example.jeromotosapp.Model.Moto;
import com.example.jeromotosapp.Adapter.AdapterListaMotos;
import com.example.jeromotosapp.Model.Moto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class HomeActivity extends AppCompatActivity {

  private TextView text;
  private AdapterListaMotos adapter;
  private ListView listView;
  private  String uidFb;

  @Override
  protected void onRestart() {
    super.onRestart();
    //Toast.makeText(HomeActivity.this, "Restart Home", Toast.LENGTH_LONG).show();
    //Intent intent = new Intent(HomeActivity.this, MainActivity.class);
    //startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_home);
    listView = (ListView) findViewById(R.id.listView);
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    uidFb = user.getUid();
    //text = (TextView) findViewById(R.id.textView);
    listarPlacas();
  }

  @Override
  public void onStart() {
    super.onStart();
    // Toast.makeText(this, "onStart", Toast.LENGTH_LONG).show();
  }



  private void listarPlacas() {

    //text = (TextView) findViewById(R.id.textView);

    String dominio = "https://jeromotos24app.000webhostapp.com/";
    String URL = dominio + "Conexion/listar_motos.php?uid="+uidFb;

    StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        try {

          JSONObject jsonObject = new JSONObject(response);
          String success = jsonObject.getString("success");

          //if(success == "1"){
          JSONArray arrayPersona = jsonObject.getJSONArray("persona");
          JSONObject obj = arrayPersona.getJSONObject(0);

          TextView nombre = (TextView) findViewById(R.id.nombre);
          nombre.setText(obj.getString("nombre"));

          JSONArray arrayMotos = jsonObject.getJSONArray("moto");
          ArrayList <Moto> listaMotos = new ArrayList<>();

          for (int i =0; i < arrayMotos.length(); i++){

            JSONObject ob = arrayMotos.getJSONObject(i);
            Moto moto = new Moto( ob.getString("id"), ob.getString("placa"), ob.getInt("tipoMotoId"), ob.getString("tipoMotoReferencia"), ob.getString("marcaNombre"));
            moto.setSrcImageMarca(Moto.img(ob.getString("marcaNombre")));
            //Moto moto = new Moto(ob);

            listaMotos.add(moto);

          }

          adapter = new AdapterListaMotos(HomeActivity.this, listaMotos);
          listView.setAdapter(adapter);

          listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              try{
                Moto m = (Moto) adapter.getItem(position);
                String motoId = m.getId();
                String tipoMotoId = String.valueOf(m.getTipoMotoId());

                Intent intent = new Intent(HomeActivity.this, TabActivity.class);
                intent.putExtra("tipoMotoId", tipoMotoId);
                intent.putExtra("motoId", motoId);
                startActivity(intent);
              }catch (Exception e){
                e.printStackTrace();
              }
            }
          });
        } catch (JSONException e) {
          e.printStackTrace();
          Toast.makeText(HomeActivity.this,"JSON Exception HomeActivity" + e.toString(), Toast.LENGTH_LONG).show();
        }
      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        //text.setText("onErrorResponse " + error.toString());
        //Toast.makeText(HomeActivity.this,"Error de Red", Toast.LENGTH_LONG).show();
        Toast.makeText(HomeActivity.this,error.toString(), Toast.LENGTH_LONG).show();
      }
    }){
      @Override protected Map<String, String> getParams() throws AuthFailureError {
        Map <String, String > params = new HashMap<>();
        //params.put("uid", "QmxIp8cRtWZEoV0mk4CS2Xw1CBL2");
        //params.put("uid", uidFb);
        return super.getParams();
      }
    };
    RequestQueue requestQueue = Volley.newRequestQueue(this);
    requestQueue.add(stringRequest);
  }

}
