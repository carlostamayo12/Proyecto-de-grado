package com.example.jeromotosapp.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Orden {

  private int id;
  private int fechaIngreso;
  private int fechaEntregaEstimada;
  private int fechaSalida;
  private int kilometraje;
  private int kmTotal;
  private String solicitudes;
  private String observaciones;
  private int costoServicio;
  private int costoRepuestos;
  private String estado;
  private int motoId;
  private String tecnicoNombre;
  private int serviciosIndice;
  private int serviciosRealizados;
  private int serviciosSolicitados;
  private int servicioId;
  private String servicioNombre;
  //public Servicios servicios;

  private final int DIAS = 18261;

  public Orden(JSONObject object) {

    try {
      this.id = object.getInt("id");
      this.fechaIngreso = object.getInt("fechaIngreso");
      this.fechaEntregaEstimada = object.getInt("fechaEntregaEstimada");
      this.fechaSalida =  object.getInt("fechaSalida");
      this.kilometraje = object.getInt("kilometraje");
      this.kmTotal = object.getInt("kmTotal");
      this.solicitudes = object.getString("solicitudes");
      this.observaciones =  object.getString("observaciones");
      this.costoServicio =  object.getInt("costoServicio");
      this.costoRepuestos = object.getInt("costoRepuestos");
      this.estado = object.getString("estado");
      this.motoId = object.getInt("motoId");;
      this.tecnicoNombre = object.getString("tecnicoNombre");
      this.serviciosIndice = object.getInt("servicios.id");
      this.serviciosSolicitados = object.getInt("servicios.solicitados");
      this.serviciosRealizados = object.getInt("servicios.realizados");
      this.servicioId = object.getInt("servicios.servicioId");
      this.servicioNombre = object.getString("servicios.servicioNombre");
      //this.servicios = new Servicios(object);

    }catch (JSONException e){
      e.printStackTrace();
    }
  }

  public static boolean existOrden(ArrayList<Orden> listaOrden, String estado){
    for (Orden o:listaOrden) {
      String est = o.getEstado();
      if(est.equals(estado)){
        return true;
      }
    }
    return false;
  }

  public static ArrayList<Orden> listar_orden_estado(ArrayList<Orden> listaOrden, String estado){

    ArrayList<Orden> listaOrdenEnvio = new ArrayList<Orden>();
    for (Orden o:listaOrden) {
      String est = o.getEstado();
      if(est.equals(estado)){
        listaOrdenEnvio.add(o);
      }
    }
    return listaOrdenEnvio;
  }

  public static ArrayList<String> extractIndex(ArrayList<Orden> listaOrden){
    ArrayList<String> listaString = new ArrayList<>();

    int aux = listaOrden.get(0).getId();
    listaString.add(Orden.ordenPad(aux));
    for (Orden o:listaOrden) {
      if(o.getId() == aux){

      }else{
        aux = o.getId();
        Orden.ordenPad(aux);
        listaString.add(Orden.ordenPad(aux));
      }
    }
    return listaString;
  }

  public static String ordenPad(int pad){

    if(pad < 10){
      return  "000"+pad;
    }
    if(pad < 100){
      return  "00"+pad;
    }
    if(pad < 1000){
      return  "0"+pad;
    }
    else{
      return String.valueOf(pad);
    }
  }


  public int getId() {
    return id;
  }

  public int getFechaIngreso() {
    return fechaIngreso;
  }

  public int getFechaEntregaEstimada() {
    return fechaEntregaEstimada;
  }

  public int getFechaSalida() {
    return fechaSalida;
  }

  public int getKilometraje() {
    return kilometraje;
  }

  public int getKmTotal() {
    return kmTotal;
  }

  public String getSolicitudes() {
    return solicitudes;
  }

  public String getObservaciones() {
    return observaciones;
  }

  public int getCostoServicio() {
    return costoServicio;
  }

  public int getCostoRepuestos() {
    return costoRepuestos;
  }

  public String getEstado() {
    return estado;
  }

  public int getMotoId() {
    return motoId;
  }

  public String getTecnicoNombre() {
    return tecnicoNombre;
  }

  public int getServiciosIndice() {
    return serviciosIndice;
  }

  public int getServiciosRealizados() {
    return serviciosRealizados;
  }

  public int getServiciosSolicitados() {
    return serviciosSolicitados;
  }

  public int getServicioId() {
    return servicioId;
  }

  public String getServicioNombre() {
    return servicioNombre;
  }
}

