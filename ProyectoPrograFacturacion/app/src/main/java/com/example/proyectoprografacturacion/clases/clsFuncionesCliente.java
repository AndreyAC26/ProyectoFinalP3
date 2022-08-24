package com.example.proyectoprografacturacion.clases;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class clsFuncionesCliente {

    private Realm realm;

    public clsFuncionesCliente(Realm _realm)
    {
        this.realm = _realm;
    }

    public clsFuncionesCliente() {

    }

    //********************************************************************************
    private int CalcularId()
    {
        Realm mRealm = Realm.getDefaultInstance();
        Number idActual = mRealm.where(Clientes.class).max("IdCliente");
        int siguienteid;
        if(idActual==null){
            siguienteid = 1;
        }else{
            siguienteid = idActual.intValue() + 1;
        }
        return siguienteid;
    }
    //*********************************************************************************
    public void CrearCliente(String _Nombre, String _Cedula, String _TelefonoCel, String _Correo, String _Saldo){

        int mIdCliente = CalcularId();
        realm.beginTransaction();
        Clientes agregar = realm.createObject(Clientes.class,mIdCliente);
        agregar.setNombre(_Nombre);
        agregar.setCedula(_Cedula);
        agregar.setTelefonoCelular(_TelefonoCel);
        agregar.setCorreo(_Correo);
        agregar.setSaldo(_Saldo);
        realm.commitTransaction();
    }
    //*********************************************************************************
    public List<Clientes> ListaClientes(){
        RealmResults<Clientes> mresultado = realm.where(Clientes.class).findAll();
        return realm.copyFromRealm(mresultado);
    }

    //**************************************************************************************************
    public void actualizardatos(String _Nombre, String _Cedula, String _TelefonoCel, String _Correo,
                                Clientes cliente){
        realm.beginTransaction();
        cliente.setNombre(_Nombre);
        cliente.setCedula(_Cedula);
        cliente.setTelefonoCelular(_TelefonoCel);
        cliente.setCorreo(_Correo);


        realm.commitTransaction();
    }
    //**************************************************************************************************
    public  Clientes obtener(int id){
        Clientes cliente1 = realm.where(Clientes.class).equalTo("IdCliente",id).findFirst();
        return cliente1;
    }
    //**************************************************************************************************
    public void eliminar(int id){

        Clientes cl = obtener(id);
        realm.beginTransaction();
        cl.deleteFromRealm();
        realm.commitTransaction();
    }
}
