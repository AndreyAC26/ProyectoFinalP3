package com.example.proyectoprografacturacion.clases;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class clsFuncionesPago {

    private Realm realm;

    public clsFuncionesPago(Realm _realm) {
        this.realm = _realm;
    }

    //***********************************************************************************
    public int CalcularNumeroPago()
    {
        Realm mRealm = Realm.getDefaultInstance();
        Number idActual = mRealm.where(clsPagosFacturas.class).max("NumeroDePago");
        int siguienteid;

        if(idActual==null){
            siguienteid = 1;
        }else{
            siguienteid = idActual.intValue() + 1;
        }
        return siguienteid;
    }
    //**********************************************************************************
    public void CrearPago(int _idCliente, int _idClienteFac, int idClientePago,
                          String numFact, String fechaPago, int montoFacturaPago,
                          int abono, int saldoFact){
        int saldoact,saldoactcliente,pago;
        int mIdClientePago = CalcularNumeroPago();
        clsFacturas saldo = realm.where(clsFacturas.class).equalTo("IdClienteFac",_idClienteFac).findFirst();
        Clientes saldocliente = realm.where(Clientes.class).equalTo("IdCliente",_idCliente).findFirst();
        if(saldo.getSaldoFact()==null){
            saldo.setSaldoFact("0");
        }
        saldoact = Integer.parseInt(saldo.getSaldoFact());
        if(saldocliente.getSaldo()==null){
            saldocliente.setSaldo("0");
        }
        saldoactcliente = Integer.parseInt(saldocliente.getSaldo());
        pago = Integer.parseInt(saldo.getPagosFactura());
        realm.beginTransaction();
        clsPagosFacturas agregar = realm.createObject(clsPagosFacturas.class,mIdClientePago);

        agregar.setIdClientePago(idClientePago);
        agregar.setNumFact(numFact);
        agregar.setFechaPago(fechaPago);
        agregar.setMontoFacturaPago(String.valueOf(montoFacturaPago));
        agregar.setAbono(String.valueOf(abono));
        agregar.setSaldoFact(String.valueOf(saldoFact-abono));
        saldo.setSaldoFact(String.valueOf(saldoact-abono));
        saldo.setPagosFactura(String.valueOf(pago+abono));
        saldocliente.setSaldo(String.valueOf(saldoactcliente-abono));
        realm.commitTransaction();
    }
    //********************************************************************************************
    public List<clsPagosFacturas> ListaDePagos(){
        RealmResults<clsPagosFacturas> mresultado = realm.where(clsPagosFacturas.class).findAll();
        return realm.copyFromRealm(mresultado);
    }
    //******************************************************************************************
    public clsPagosFacturas obtener(int id){
        clsPagosFacturas pago = realm.where(clsPagosFacturas.class).equalTo("IdClientePago",id).findFirst();
        return pago;
    }
    //*********************************************************************************************
    public void ListaDePagosEliminar(){
        realm.beginTransaction();
        realm.delete(clsPagosFacturas.class);
        realm.commitTransaction();
    }
    public void eliminarFact(int id){
        clsFacturas saldo = realm.where(clsFacturas.class).equalTo("IdClienteFac",id).findFirst();
        int saldofact = Integer.parseInt(saldo.getSaldoFact());
        if (saldofact==0) {
            clsFacturas facturas = realm.where(clsFacturas.class).equalTo("IdClienteFac", id).findFirst();
            realm.beginTransaction();
            facturas.deleteFromRealm();
            realm.commitTransaction();
        }
    }

}
