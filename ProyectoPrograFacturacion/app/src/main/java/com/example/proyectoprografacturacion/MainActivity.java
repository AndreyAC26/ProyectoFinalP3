package com.example.proyectoprografacturacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private int idCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_mainregresar, menu);
        return true;
    }

    public void Agregar(View v){
        Intent start = new Intent(this,AgregarClienteActivity.class);
        startActivity(start);
    }
    public void miLista(View v){
        Intent start = new Intent(this, ListaDeClientesActivity.class);
        startActivity(start);
    }
    public void miListaPagos(View v){

        Intent start = new Intent(this, PagosRealizadosActivity.class);
        start.putExtra("IdClienteFac",idCliente);
        startActivity(start);
    }

}