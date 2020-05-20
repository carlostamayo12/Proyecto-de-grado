package com.example.jeromotosapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jeromotosapp.Model.OrdenView;
import com.example.jeromotosapp.R;

import java.util.ArrayList;

public class AdapterListaOrden extends BaseAdapter {

  private Context context;
  private ArrayList<OrdenView> arrayList;

  public AdapterListaOrden(Context context, ArrayList<OrdenView> arrayList) {
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
      convertView = layoutInflater.inflate(R.layout.item_lista_servicios, null);
    }

    TextView servicioTaller = (TextView) convertView.findViewById(R.id.servicioTaller);
    TextView strSolicitado = (TextView) convertView.findViewById(R.id.solicitado);
    TextView strRealizado = (TextView) convertView.findViewById(R.id.realizado);
    //TextView proximoServicio = (TextView) convertView.findViewById(R.id.proximoServicio);

    servicioTaller.setText(arrayList.get(position).getOrden().getServicioNombre());
    strSolicitado.setText(arrayList.get(position).getStrSolicitado());
    strRealizado.setText(arrayList.get(position).getStrRealizado());

    return convertView;
  }
}
