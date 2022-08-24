package com.example.proyectoprografacturacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoprografacturacion.clases.AdapterFacturas;
import com.example.proyectoprografacturacion.clases.Clientes;
import com.example.proyectoprografacturacion.clases.FuncionesFacturas;
import com.example.proyectoprografacturacion.clases.clsFacturas;
import com.example.proyectoprografacturacion.clases.clsFuncionesCliente;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ListaDeFacturasActivity extends AppCompatActivity {
    TextView mSaldoTotal;
    ListView mLista;
    private AdapterFacturas adapter;
    private int idCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_facturas);

        mLista = findViewById(R.id.ListaFacturas);
        mSaldoTotal = findViewById(R.id.SaldoTotalListFactCampo);
        Bundle mibundle = getIntent().getExtras();
        assert mibundle != null;
        idCliente = mibundle.getInt("IdClientes");
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().name("CXC").schemaVersion(1).build();
        Realm.setDefaultConfiguration(realmConfiguration);
        getCliente();
        crearListaFacturas();

    }

    @Override
    public void onResume() {
        super.onResume();
        crearListaFacturas();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_regresar, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id==R.id.menu_atras){

            Intent ma = new Intent(this, MostradorDeDatosActivity.class);
            ma.putExtra("IdClientes",idCliente);
            startActivity(ma);
        }
        return super.onOptionsItemSelected(item);
    }

    private void crearListaFacturas(){
        FuncionesFacturas mcliente = new FuncionesFacturas(Realm.getDefaultInstance());
        final List<clsFacturas> mServCliente = mcliente.ListaFacturasPorId(idCliente);
        adapter = new AdapterFacturas(this,mServCliente,R.layout.adapt_facturas);
        mLista.setAdapter(adapter);
        mLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clsFacturas mdato = mServCliente.get(position);
                Intent actual = new Intent(ListaDeFacturasActivity.this, MostradorDeFacturasActivity.class);
                actual.putExtra("IdClienteFac",mdato.getIdClienteFac());
                actual.putExtra("IdClientes",mdato.getIdCliente());
                startActivity(actual);
            }
        });

    }
    private void getCliente() {
        Bundle mibundle = getIntent().getExtras();
        if (mibundle != null) {
            idCliente = mibundle.getInt("IdClientes");
            clsFuncionesCliente saldo = new clsFuncionesCliente(Realm.getDefaultInstance());
            Clientes clientes = saldo.obtener(idCliente);
            try {
                mSaldoTotal.setText(clientes.getSaldo());
            } catch (Exception e) {
                Log.d("error",e.getMessage());
            }
        } else {
            Toast.makeText(ListaDeFacturasActivity.this, "No hay datos", Toast.LENGTH_LONG).show();
        }
    }


}