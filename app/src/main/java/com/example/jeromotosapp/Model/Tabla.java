package com.example.jeromotosapp.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class Tabla {

  int id;
  int contadorKm;
  int contadorTime;
  int servicioId;
  int km;
  int tiempo;
  boolean estado;
  String servicioNombre;

  public Tabla(JSONObject object) {
    try {
      this.id = object.getInt("id");;
      this.contadorKm =  object.getInt("contadorKm");
      this.contadorTime = object.getInt("contadorTime");
      this.servicioId = object.getInt("servicioId");
      this.km = object.getInt("kilometraje");
      this.tiempo = object.getInt("tiempo");
      this.estado = object.getBoolean("estado");
      this.servicioNombre = object.getString("servicioNombre");
    }catch (JSONException e){
      e.printStackTrace();
    }


  }

  public int getId() {
    return id;
  }

  public int getContadorKm() {
    return contadorKm;
  }

  public int getContadorTime() {
    return contadorTime;
  }

  public int getServicioId() {
    return servicioId;
  }

  public int getKm() {
    return km;
  }

  public int getTiempo() {
    return tiempo;
  }

  public boolean isEstado() {
    return estado;
  }

  public String getServicioNombre() {
    return servicioNombre;
  }
}