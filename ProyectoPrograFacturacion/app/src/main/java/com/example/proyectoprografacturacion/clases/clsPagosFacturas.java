package com.example.proyectoprografacturacion.clases;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class clsPagosFacturas extends RealmObject {

    @PrimaryKey
    private int NumeroDePago;
    private int IdClientePago;
    private String NumFact;
    private String FechaPago;
    private String MontoFacturaPago;
    private String Abono;
    private String SaldoFact;

    public clsPagosFacturas(int numeroDePago, int idClientePago, String numFact, String fechaPago, String montoFacturaPago, String abono, String saldoFact) {
        NumeroDePago = numeroDePago;
        IdClientePago = idClientePago;
        NumFact = numFact;
        FechaPago = fechaPago;
        MontoFacturaPago = montoFacturaPago;
        Abono = abono;
        SaldoFact = saldoFact;
    }

    public clsPagosFacturas() {
        NumeroDePago = 0;
        IdClientePago = 0;
        NumFact = "";
        FechaPago = "";
        MontoFacturaPago = "";
        Abono = "";
        SaldoFact = "";

    }



    public int getNumeroDePago() {
        return NumeroDePago;
    }

    public void setNumeroDePago(int numeroDePago) {
        NumeroDePago = numeroDePago;
    }

    public int getIdClientePago() {
        return IdClientePago;
    }

    public void setIdClientePago(int idClientePago) {
        IdClientePago = idClientePago;
    }

    public String getNumFact() {
        return NumFact;
    }

    public void setNumFact(String numFact) {
        NumFact = numFact;
    }

    public String getFechaPago() {
        return FechaPago;
    }

    public void setFechaPago(String fechaPago) {
        FechaPago = fechaPago;
    }

    public String getMontoFacturaPago() {
        return MontoFacturaPago;
    }

    public void setMontoFacturaPago(String montoFacturaPago) {
        MontoFacturaPago = montoFacturaPago;
    }

    public String getAbono() {
        return Abono;
    }

    public void setAbono(String abono) {
        Abono = abono;
    }

    public String getSaldoFact() {
        return SaldoFact;
    }

    public void setSaldoFact(String saldoFact) {
        SaldoFact = saldoFact;
    }

    @Override
    public String toString() {
        return "clsPagosFacturas{" +
                "NumeroDePago=" + NumeroDePago +
                ", IdClientePago=" + IdClientePago +
                ", NumFact='" + NumFact + '\'' +
                ", FechaPago='" + FechaPago + '\'' +
                ", MontoFacturaPago='" + MontoFacturaPago + '\'' +
                ", Abono='" + Abono + '\'' +
                ", SaldoFact='" + SaldoFact + '\'' +
                '}';
    }
}
