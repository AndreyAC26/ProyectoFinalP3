package com.example.proyectoprografacturacion.clases;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class clsFacturas extends  RealmObject{

    @PrimaryKey
    private int IdClienteFac;
    private int IdCliente;
    private String NumFact;
    private String FechaFact;
    private String FechaVence;
    private String MontoFactura;
    private String PagosFactura;
    private String SaldoFact;
    private String EstadoFact;

    public clsFacturas(int idClienteFac, int idCliente, String numFact, String fechaFact, String fechaVence,
                       String montoFactura, String pagosFactura, String saldoFact, String estadofact) {
        IdClienteFac = idClienteFac;
        NumFact = numFact;
        FechaFact = fechaFact;
        FechaVence = fechaVence;
        MontoFactura = montoFactura;
        PagosFactura = pagosFactura;
        SaldoFact = saldoFact;
        IdCliente = idCliente;
        EstadoFact = estadofact;
    }

    public clsFacturas(){
        IdClienteFac = 0;
        IdCliente = 0;
        NumFact = "";
        FechaFact = "";
        FechaVence = "";
        MontoFactura = "";
        PagosFactura = "";
        SaldoFact = "";
        EstadoFact = "";
    }

    public int getIdClienteFac() {
        return IdClienteFac;
    }

    public void setIdClienteFac(int idClienteFac) {
        IdClienteFac = idClienteFac;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int idCliente) {
        IdCliente = idCliente;
    }

    public String getNumFact() {
        return NumFact;
    }

    public void setNumFact(String numFact) {
        NumFact = numFact;
    }

    public String getFechaFact() {
        return FechaFact;
    }

    public void setFechaFact(String fechaFact) {
        FechaFact = fechaFact;
    }

    public String getFechaVence() {
        return FechaVence;
    }

    public void setFechaVence(String fechaVence) {
        FechaVence = fechaVence;
    }

    public String getMontoFactura() {
        return MontoFactura;
    }

    public void setMontoFactura(String montoFactura) {
        MontoFactura = montoFactura;
    }

    public String getPagosFactura() {
        return PagosFactura;
    }

    public void setPagosFactura(String pagosFactura) {
        PagosFactura = pagosFactura;
    }

    public String getSaldoFact() {
        return SaldoFact;
    }

    public void setSaldoFact(String saldoFact) {
        SaldoFact = saldoFact;
    }

    public String getEstadoFact() {
        return EstadoFact;
    }

    public void setEstadoFact(String estadoFact) {
        EstadoFact = estadoFact;
    }

    @Override
    public String toString() {
        return "clsFacturas{" +
                "IdClienteFac=" + IdClienteFac +
                ", NumFact='" + NumFact + '\'' +
                ", FechaFact='" + FechaFact + '\'' +
                ", FechaVence='" + FechaVence + '\'' +
                ", MontoFactura='" + MontoFactura + '\'' +
                ", PagosFactura='" + PagosFactura + '\'' +
                ", SaldoFact='" + SaldoFact + '\'' +
                ", IdCliente'" + IdCliente + '\'' +
                ", Estado'" + EstadoFact + '\'' +
                '}';
    }
}

