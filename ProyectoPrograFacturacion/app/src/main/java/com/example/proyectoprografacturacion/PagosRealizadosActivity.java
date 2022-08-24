package com.example.proyectoprografacturacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.proyectoprografacturacion.clases.AdapterPagos;
import com.example.proyectoprografacturacion.clases.clsFuncionesPago;
import com.example.proyectoprografacturacion.clases.clsPagosFacturas;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class PagosRealizadosActivity extends AppCompatActivity {


    ListView mListaPagos;
    private AdapterPagos adapter;
    private int idCliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagos_realizados);
        mListaPagos = findViewById(R.id.ListViewPagos);
        Bundle mibundle = getIntent().getExtras();
        assert mibundle != null;
        idCliente = mibundle.getInt("IdClienteFac");
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().name("CXC").schemaVersion(1).build();
        Realm.setDefaultConfiguration(realmConfiguration);
        crearListaFacturas();

    }

    @Override
    public void onResume() {
        super.onResume();
        crearListaFacturas();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_pagos, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id==R.id.menu_volverAtras){

            Intent ma = new Intent(this, MainActivity.class);
            startActivity(ma);
        }
        if(id==R.id.menu_eliminarPago){
            clsFuncionesPago funcionesPagos = new clsFuncionesPago(Realm.getDefaultInstance());
            funcionesPagos.ListaDePagosEliminar();

            onResume();

        }

        return super.onOptionsItemSelected(item);
    }

    private void crearListaFacturas(){
        clsFuncionesPago funcionesPagos = new clsFuncionesPago(Realm.getDefaultInstance());
        final List<clsPagosFacturas> mServCliente = funcionesPagos.ListaDePagos();
        adapter = new AdapterPagos(this,mServCliente,R.layout.adapt_pagos);
        mListaPagos.setAdapter(adapter);
        mListaPagos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clsPagosFacturas mdato = mServCliente.get(position);
                Intent actual = new Intent(PagosRealizadosActivity.this, MostradorDeFacturasActivity.class);

                startActivity(actual);
            }
        });
    }
}