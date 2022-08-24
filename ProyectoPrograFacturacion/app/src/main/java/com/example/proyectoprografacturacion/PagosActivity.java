package com.example.proyectoprografacturacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoprografacturacion.clases.FuncionesFacturas;
import com.example.proyectoprografacturacion.clases.clsFacturas;
import com.example.proyectoprografacturacion.clases.clsFuncionesPago;
import com.example.proyectoprografacturacion.clases.clsPagosFacturas;

import io.realm.Realm;

public class PagosActivity extends AppCompatActivity {
    private int idCliente,mid;
    TextView mNumeroPago,mNumeroCliente,mNumerofactura,mSaldo,mMonto;
    EditText mFecha,mAbono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagos);
        mNumeroPago = findViewById(R.id.txtfactNumerodePagoActivity);
        mNumeroCliente = findViewById(R.id.txtNumClienteActivity);
        mNumerofactura = findViewById(R.id.txtFactIdNumFactActivity);
        mFecha = findViewById(R.id.txtFactFechaActivity);
        mMonto = findViewById(R.id.txtFactMontoFactActivity);
        mAbono = findViewById(R.id.txtFactPagofactActivity);
        mSaldo = findViewById(R.id.txtFactSaldoActivity);
        getCliente();

    }
    private void getCliente() {
        Bundle mibundle = getIntent().getExtras();
        if (mibundle != null) {
            idCliente = mibundle.getInt("IdClienteFac");
            mid = mibundle.getInt("IdClienteFac");
            clsFuncionesPago pago1 = new clsFuncionesPago(Realm.getDefaultInstance());
            clsPagosFacturas pago2 = pago1.obtener(idCliente);
            FuncionesFacturas pago = new FuncionesFacturas(Realm.getDefaultInstance());
            clsFacturas facturas = pago.obtener(idCliente);
            try {
                mNumeroPago.setText(Integer.toString(pago1.CalcularNumeroPago()));
                mNumeroCliente.setText(Integer.toString(facturas.getIdCliente()));
                mNumerofactura.setText(facturas.getNumFact());
                mSaldo.setText(facturas.getSaldoFact());
                mMonto.setText(facturas.getMontoFactura());

            } catch (Exception e) {
                Log.d("error",e.getMessage());
            }
        } else {
            Toast.makeText(PagosActivity.this, "No hay datos", Toast.LENGTH_LONG).show();
        }
    }
    public void cancelarSaldo(View V){

        Bundle mibundle = getIntent().getExtras();
        assert mibundle != null;
        idCliente = mibundle.getInt("IdClienteFac");
        mid = mibundle.getInt("IdClientes");
        try {
            int _NumCliente = Integer.parseInt(mNumeroCliente.getText().toString());
            String _NumFact = mNumerofactura.getText().toString();
            String _Fecha = mFecha.getText().toString();
            int _Monto = Integer.parseInt(mMonto.getText().toString());
            int _Abono = Integer.parseInt(mAbono.getText().toString());
            int _Saldo = Integer.parseInt(mSaldo.getText().toString());
            clsFuncionesPago crear = new clsFuncionesPago(Realm.getDefaultInstance());
            crear.CrearPago(mid,idCliente, _NumCliente, _NumFact, _Fecha, _Monto, _Abono, _Saldo);
            crear.eliminarFact(idCliente);
            Intent pagos = new Intent(this, ListaDeFacturasActivity.class);
            pagos.putExtra("IdClienteFac",idCliente);
            Toast.makeText(PagosActivity.this, "Saldo Cancelado",Toast.LENGTH_LONG).show();
            startActivity(pagos);
        }catch (Exception e){
            Log.d("error",e.getMessage());
        }
    }
    public void ACSalir(View v) {
        finish();
}}