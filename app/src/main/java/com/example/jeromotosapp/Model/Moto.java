package com.example.jeromotosapp.Model;

import com.example.jeromotosapp.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Moto {

  private String id;
  private String placa;
  private String color;
  private int km_promedio;
  private int tipoMotoId;
  private String tipoMotoReferencia;
  private int marcaId;
  private String marcaNombre;
  private int srcImageMarca;

  public Moto(String id, String placa,int tipoMotoId, String tipoMotoReferencia, String marcaNombre) {
    this.id = id;
    this.placa = placa;
    this.tipoMotoId = tipoMotoId;
    this.tipoMotoReferencia = tipoMotoReferencia;
    this.marcaNombre = marcaNombre;

  }

  public Moto(){}

  public Moto(JSONObject object){
    try {
      this.id = object.getString("moto.id");
      this.placa = object.getString("moto.placa");
      this.color =  object.getString("moto.color");
      this.km_promedio = object.getInt("moto.km_promedio");
      //this.tipoMotoId =  object.getInt("moto.tipoMotoId");
      this.tipoMotoReferencia = object.getString("moto.tipoMotoReferencia");
      //this.marcaId = object.getInt("moto.marcaId");
      this.marcaNombre = object.getString("moto.marcaNombre");
    }catch (JSONException e){
      e.printStackTrace();
    }

  }

  public Moto(String id, String placa, String color, int km_promedio, int tipoMotoId, String tipoMotoReferencia, int marcaId, String marcaNombre) {
    this.id = id;
    this.placa = placa;
    this.color = color;
    this.km_promedio = km_promedio;
    this.tipoMotoId = tipoMotoId;
    this.tipoMotoReferencia = tipoMotoReferencia;
    this.marcaId = marcaId;
    this.marcaNombre = marcaNombre;
  }

  public static int img(String marcaNombre){

    String marca = marcaNombre.toLowerCase();
    if(marca.equals("yamaha")){
      return R.drawable.logo_yamaha;
    }
    else if(marca.equals("suzuki")){
      return R.drawable.logo_suzuki;
    }
    else if (marca.equals("honda")){
      return R.drawable.logo_honda;
    }
    return R.drawable.ic_motorcycle_negro_24dp;
  }

  public void setSrcImageMarca(int srcImageMarca) {
    this.srcImageMarca = srcImageMarca;
  }

  public int getSrcImageMarca() {
    return srcImageMarca;
  }

  public String getId() {
    return id;
  }

  public String getPlaca() {
    return placa;
  }

  public String getColor() {
    return color;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setPlaca(String placa) {
    this.placa = placa;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public void setKm_promedio(int km_promedio) {
    this.km_promedio = km_promedio;
  }

  public void setTipoMotoId(int tipoMotoId) {
    this.tipoMotoId = tipoMotoId;
  }

  public void setTipoMotoReferencia(String tipoMotoReferencia) {
    this.tipoMotoReferencia = tipoMotoReferencia;
  }

  public void setMarcaId(int marcaId) {
    this.marcaId = marcaId;
  }

  public void setMarcaNombre(String marcaNombre) {
    this.marcaNombre = marcaNombre;
  }

  public int getKm_promedio() {
    return km_promedio;
  }

  public int getTipoMotoId() {
    return tipoMotoId;
  }

  public String getTipoMotoReferencia() {
    return tipoMotoReferencia;
  }

  public int getMarcaId() {
    return marcaId;
  }

  public String getMarcaNombre() {
    return marcaNombre;
  }
}
