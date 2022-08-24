package com.example.proyectoprografacturacion.clases;

import java.util.List;

import io.realm.Realm;

public class FuncionesFacturas {
    private Realm realm;


    public FuncionesFacturas(Realm _realm)
    {
        this.realm = _realm;
    }
    //************************************************************************************
    private int CalcularIdFac()
    {
        Realm mRealm = Realm.getDefaultInstance();
        Number idActual = mRealm.where(clsFacturas.class).max("IdClienteFac");
        return  idActual == null ? 1 : idActual.intValue() + 1;
    }
    //************************************************************************************
    public void CrearFactura(int _idCliente, String _NumFact,
                             String _FechaFact, String _FechaVenc,
                             int _Montofact, String _Pagosfact,
                             String _SaldoFact, String _Estadofact){
        int mIdCliente = CalcularIdFac();
        int saldoact;
        Clientes saldo = realm.where(Clientes.class).equalTo("IdCliente",_idCliente).findFirst();
        realm.beginTransaction();
        if(saldo.getSaldo()==null){
            saldo.setSaldo("0");
        }
        saldoact = Integer.parseInt(saldo.getSaldo());
        clsFacturas agregar = realm.createObject(clsFacturas.class,mIdCliente);
        agregar.setIdCliente(_idCliente);
        agregar.setNumFact(_NumFact);
        agregar.setFechaFact(_FechaFact);
        agregar.setFechaVence(_FechaVenc);
        agregar.setMontoFactura(String.valueOf(_Montofact));
        agregar.setPagosFactura(_Pagosfact);
        agregar.setSaldoFact(_SaldoFact);
        agregar.setEstadoFact(_Estadofact);
        saldo.setSaldo(String.valueOf(saldoact+_Montofact));
        realm.commitTransaction();
    }
    //***********************************************************************************************
    public List<clsFacturas> ListaFacturasPorId(int id){
        List<clsFacturas> facturas = realm.where(clsFacturas.class).equalTo("IdCliente",id).findAll();
        return facturas;
    }

    //*****************************************************************************************************
    public  clsFacturas obtener(int id){
        clsFacturas factura = realm.where(clsFacturas.class).equalTo("IdClienteFac",id).findFirst();
        return factura;
    }

    //*********************************************************************************************
    public void ListaDeFacturasEliminar(){
        realm.beginTransaction();
        realm.delete(clsFacturas.class);
        realm.commitTransaction();
    }

}
