package com.example.jeromotosapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jeromotosapp.Model.TablaView;
import com.example.jeromotosapp.R;

import java.util.ArrayList;

public class AdapterListaTabla extends BaseAdapter {

  private Context context;
  private ArrayList<TablaView> arrayList;

  public AdapterListaTabla(Context context, ArrayList<TablaView> arrayList) {
    this.context = context;
    this.arrayList = arrayList;
  }

  @Override
  public int getCount() {
    return arrayList.size();
  }

  @Override
  public Object getItem(int position) {
    return arrayList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {

    if(convertView == null){
      LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
      convertView = layoutInflater.inflate(R.layout.item_tabla, null);
    }

    TextView servicio = (TextView) convertView.findViewById(R.id.servicio);
    TextView frecuencia = (TextView) convertView.findViewById(R.id.frecuencia);
    TextView ultimoServicio = (TextView) convertView.findViewById(R.id.ultimoServicio);
    TextView proximoServicio = (TextView) convertView.findViewById(R.id.proximoServicio);

    //servicio.setText(arrayList.get(position).getServicioNombre());
    servicio.setText("Servic");
    frecuencia.setText(arrayList.get(position).getFrecuencia());
    ultimoServicio.setText(arrayList.get(position).getUltimo());
    proximoServicio.setText(arrayList.get(position).getProximo());

    return convertView;
  }
}
