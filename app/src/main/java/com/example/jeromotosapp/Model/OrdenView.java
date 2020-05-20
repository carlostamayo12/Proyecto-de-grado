package com.example.jeromotosapp.Model;

public class OrdenView {

  private Orden orden;
  private  String strSolicitado;
  private String strRealizado;

  public OrdenView(Orden orden, String strSolicitado, String strRealizado) {
    this.orden = orden;
    this.strSolicitado = strSolicitado;
    this.strRealizado = strRealizado;
  }

  public Orden getOrden() {
    return orden;
  }

  public String getStrSolicitado() {
    return strSolicitado;
  }

  public String getStrRealizado() {
    return strRealizado;
  }
}

