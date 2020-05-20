package com.example.jeromotosapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jeromotosapp.Model.Moto;
import com.example.jeromotosapp.R;

import java.util.ArrayList;

public class AdapterListaMotos extends BaseAdapter {

  private  Context context;
  private ArrayList<Moto> arrayList;

  public  AdapterListaMotos(Context context, ArrayList<Moto> arrayList) {
    this.context = context;
    this.arrayList = arrayList;
  }


  @Override
  public int getCount() {
    return arrayList.size() ;
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
      LayoutInflater layoutInflater = (LayoutInflater)  context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
      convertView = layoutInflater.inflate(R.layout.item_lista_motos, null);

      TextView placa = (TextView) convertView.findViewById(R.id.placa);
      TextView referencia = (TextView) convertView.findViewById(R.id.referencia);
      ImageView imageMarca = (ImageView) convertView.findViewById(R.id.imageMarca);

      placa.setText(arrayList.get(position).getPlaca());
      referencia.setText(arrayList.get(position).getTipoMotoReferencia());
      imageMarca.setImageResource(arrayList.get(position).getSrcImageMarca());

    }

    return convertView;
  }
}
