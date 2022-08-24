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

import com.example.proyectoprografacturacion.clases.AdapterClientes;
import com.example.proyectoprografacturacion.clases.Clientes;
import com.example.proyectoprografacturacion.clases.FuncionesFacturas;
import com.example.proyectoprografacturacion.clases.clsFuncionesCliente;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ListaDeClientesActivity extends AppCompatActivity {

    ListView mLista;
    private AdapterClientes adapter;
    int mIdClienteSel;

    @Override
    protected void onResume() {
        super.onResume();
        clsFuncionesCliente mcliente = new clsFuncionesCliente(Realm.getDefaultInstance());
        List<Clientes> mServCliente = mcliente.ListaClientes();

        adapter = new AdapterClientes(this,mServCliente, R.layout.adapt_cliente);
        mLista.setAdapter(adapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_clientes);

        setContentView(R.layout.activity_lista_de_clientes);
        mLista = findViewById(R.id.txtListView);
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().name("CXC").schemaVersion(1).build();
        Realm.setDefaultConfiguration(realmConfiguration);
        clsFuncionesCliente mcliente = new clsFuncionesCliente(Realm.getDefaultInstance());
        final List<Clientes> mServCliente = mcliente.ListaClientes();
        adapter = new AdapterClientes(this,mServCliente,R.layout.adapt_cliente);
        mLista.setAdapter(adapter);

        mLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mLista.setAdapter(adapter);
                Clientes mdato = mServCliente.get(position);
                mIdClienteSel = mdato.getIdCliente();

            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id==R.id.menu_regresar){
            Intent Mainactivity = new Intent(this,MainActivity.class);
            startActivity(Mainactivity);
        }
        if(id==R.id.menu_agregar){
            Intent mc = new Intent(this,AgregarFacturasActivity.class);
            mc.putExtra("IdClientes",mIdClienteSel);
            startActivity(mc);
        }
        if(id==R.id.menu_act){
            Intent mc = new Intent(this,ActualizarDatosActivity.class);
            mc.putExtra("IdClientes",mIdClienteSel);
            startActivity(mc);
        }
        if(id==R.id.menu_ver){
            Intent mc = new Intent(this,MostradorDeDatosActivity.class);
            mc.putExtra("IdClientes",mIdClienteSel);
            startActivity(mc);
        }
        if(id==R.id.menu_eliminarcliente){
            clsFuncionesCliente eliminar = new clsFuncionesCliente(Realm.getDefaultInstance());
            FuncionesFacturas elim = new FuncionesFacturas(Realm.getDefaultInstance());
            elim.ListaDeFacturasEliminar();
            eliminar.eliminar(mIdClienteSel );
            onResume();
        }


        return super.onOptionsItemSelected(item);
    }
}