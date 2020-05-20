package com.example.jeromotosapp.Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TablaView {

  private String servicioNombre;
  private String frecuencia;
  private String ultimo;
  private String proximo;

  public TablaView(String servicioNombre, String frecuencia, String ultimo) {
    this.servicioNombre = servicioNombre;
    this.frecuencia = frecuencia;
    this.ultimo = ultimo;
  }

  public TablaView(String servicioNombre, String frecuencia, String ultimo, String proximo) {
    this.servicioNombre = servicioNombre;
    this.frecuencia = frecuencia;
    this.ultimo = ultimo;
    this.proximo = proximo;
  }


  public static String frecuenciaServicio (Tabla tabla){

    int km =  Integer.valueOf(tabla.getKm());
    int tiempo = Integer.valueOf(tabla.getTiempo());

    if(km != 0 && tiempo == 0){
      return  "Realizar cada " + km + " kms";
    }
    else if (km == 0 && tiempo != 0){
      return  "Realizar cada " + tiempo + "meses";
    }
    else if(km != 0 && tiempo != 0) {
      return  "Realizar cada " + km + " kms o " + tiempo + " meses";
    }
    return "No registra";

  }

  public static int ultimaOrdenIndex(ArrayList<Orden> listaOrden ,int servicioId, Tabla tabla){

    int index = 0;
    for (Orden o:listaOrden) {
      String estado = o.getEstado();
      if(o.getServicioId() == tabla.getServicioId() && estado.equals("Finalizado") && o.getServiciosRealizados() == 1 ){
        return index;
      }
      index++;
    }
    return -1;
  }

  public static String convertToFecha(long dias){
    long  m = dias*24*60*60*1000L;
    Date utilDate = new Date(m);
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    return format.format(utilDate);
  }

  public static TablaView view(ArrayList<Orden> listaOrden ,Tabla tabla, int position ){

    String servicioNombre = tabla.servicioNombre;
    String frecuencia = TablaView.frecuenciaServicio(tabla);
    String ultimo = "ultimo";
    String proximo = "proximo";

    int km = 0;
    long dias = 0;
    String fecha = "";


    if(position > -1){

      ultimo = "Realizado a los "+ listaOrden.get(position).getKilometraje() + " Kms el dia " + TablaView.convertToFecha(listaOrden.get(position).getFechaSalida());

      if(tabla.contadorKm > 0 && tabla.contadorTime > 0){

        km = listaOrden.get(position).getKilometraje() + tabla.getKm();
        dias = (long) tabla.contadorKm;
        fecha = TablaView.convertToFecha(dias);
        proximo = "Proximo servicio: " + km + " Kms o " + fecha;

      }
      else if(tabla.contadorKm > 0){

        km = listaOrden.get(position).getKilometraje() + tabla.getKm();
        dias = (long) tabla.contadorKm;
        fecha = TablaView.convertToFecha(dias);
        proximo = "Proximo servicio: " + km + " Kms o " + fecha;
      }
      else if(tabla.contadorTime > 0){

        km = listaOrden.get(position).getKilometraje() + tabla.getKm();
        dias = (long) tabla.contadorTime;
        fecha = TablaView.convertToFecha(dias);
        proximo = "Proximo servicio: " + fecha;
      }
      else{
        km = listaOrden.get(position).getKilometraje() + tabla.getKm();
        dias = (long) tabla.contadorKm;
        fecha = TablaView.convertToFecha(dias);
        //proximo = "Proximo servicio: " + km + " Kms o " + fecha;
        proximo = "Proximo servicio: " + km + " Kms ";
      }
    }else {

      ultimo = "No registra un ultimo servicio";
      proximo = "No registra proximo servicio";

    }

    return new TablaView(servicioNombre,frecuencia, ultimo, proximo);
  }

  public String getServicioNombre() {
    return servicioNombre;
  }

  public String getFrecuencia() {
    return frecuencia;
  }

  public String getUltimo() {
    return ultimo;
  }

  public String getProximo() {
    return proximo;
  }
}

