package com.example.proyectoprografacturacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoprografacturacion.clases.FuncionesFacturas;
import com.example.proyectoprografacturacion.clases.clsFacturas;

import io.realm.Realm;

public class MostradorDeFacturasActivity extends AppCompatActivity {

    TextView mFactNumeroFact,mFactFecha,mFactFechaVence,mFactMonto,mFactPago,mFactSaldo,mFactEstado;

    private int idCliente,mid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrador_de_facturas);
        mFactNumeroFact = findViewById(R.id.txtCampoFactNumeroFact);
        mFactFecha = findViewById(R.id.txtCampoMosFactFecha);
        mFactFechaVence = findViewById(R.id.txtCampoMosFactFechaVence);
        mFactMonto = findViewById(R.id.txtCampoMosFactMonto);
        mFactPago = findViewById(R.id.txtCampoMosFactPago);
        mFactSaldo = findViewById(R.id.txtCampoMosFactSaldo);
        mFactEstado = findViewById(R.id.txtCampoFactEstado);
        getCliente();

    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_regresar, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id==R.id.menu_atras){

            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void getCliente() {
        Bundle mibundle = getIntent().getExtras();
        if (mibundle != null) {
            idCliente = mibundle.getInt("IdClienteFac");
            mid = mibundle.getInt("IdClientes");
            FuncionesFacturas mservicio = new FuncionesFacturas(Realm.getDefaultInstance());
            clsFacturas facturas = mservicio.obtener(idCliente);
            try {
                mFactNumeroFact.requestFocus();
                mFactNumeroFact.setText(facturas.getNumFact());
                mFactFecha.setText(facturas.getFechaFact());
                mFactFechaVence.setText(facturas.getFechaVence());
                mFactMonto.setText(facturas.getMontoFactura());
                mFactPago.setText(facturas.getPagosFactura());
                mFactSaldo.setText(facturas.getSaldoFact());
                mFactEstado.setText(facturas.getEstadoFact());


            } catch (Exception e) {
                Toast.makeText(MostradorDeFacturasActivity.this, e.getMessage().toString(), Toast.LENGTH_LONG).show();

            }
        } else {
            Toast.makeText(MostradorDeFacturasActivity.this, "No hay datos", Toast.LENGTH_LONG).show();
        }
    }
    public void PantallaCancelarSaldo(View v){
        Intent pantalla = new Intent(this,PagosActivity.class);

        pantalla.putExtra("IdClienteFac",idCliente);
        pantalla.putExtra("IdClientes",mid);
        startActivity(pantalla);
    }

}