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

import com.example.proyectoprografacturacion.clases.Clientes;
import com.example.proyectoprografacturacion.clases.FuncionesFacturas;
import com.example.proyectoprografacturacion.clases.clsFuncionesCliente;

import io.realm.Realm;

public class MostradorDeDatosActivity extends AppCompatActivity {

    TextView mCedula,mNombreMos,mTelefono,mCorreo;

    int mid;
    int idCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrador);

        mTelefono = findViewById(R.id.txtMostrarTelefono);
        mNombreMos = findViewById(R.id.txvMostrarNombre);
        mCedula = findViewById(R.id.txtMostrarCed);
        mCorreo = findViewById(R.id.txtCampoCorreo);
        getCliente();

    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_misclientes, menu);

        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id==R.id.menu_eliminar){
            FuncionesFacturas fac = new FuncionesFacturas(Realm.getDefaultInstance());
            clsFuncionesCliente borrar = new clsFuncionesCliente(Realm.getDefaultInstance());
            fac.ListaDeFacturasEliminar();
            borrar.eliminar(mid);
            Toast.makeText(MostradorDeDatosActivity.this,"Se ha borrado con exito",Toast.LENGTH_LONG).show();
            Intent mpa = new Intent(this, ListaDeClientesActivity.class);
            startActivity(mpa);
        }
        if(id==R.id.menu_volver){
            Intent mpa = new Intent(this, ListaDeClientesActivity.class);
            startActivity(mpa);
        }
        return super.onOptionsItemSelected(item);
    }
    private void getCliente() {
        Bundle mibundle = getIntent().getExtras();
        if (mibundle != null) {
            idCliente = mibundle.getInt("IdClientes");
            mid = mibundle.getInt("IdClientes");
            clsFuncionesCliente mservicio = new clsFuncionesCliente(Realm.getDefaultInstance());
            Clientes cliente = mservicio.obtener(idCliente);
            try {
                mNombreMos.requestFocus();
                mNombreMos.setText(cliente.getNombre());
                mCedula.setText(cliente.getCedula());
                mTelefono.setText(cliente.getTelefonoCelular());
                mCorreo.setText(cliente.getCorreo());


            } catch (Exception e) {
                Toast.makeText(MostradorDeDatosActivity.this, e.getMessage().toString(), Toast.LENGTH_LONG).show();

            }
        } else {
            Toast.makeText(MostradorDeDatosActivity.this, "No hay datos", Toast.LENGTH_LONG).show();
        }
    }
    public void Editar(View v){
        Intent ac = new Intent(this, ActualizarDatosActivity.class);
        ac.putExtra("IdClientes",idCliente);
        startActivity(ac);
    }

    public void AgregarFactura(View v){
        Intent ac = new Intent(this,AgregarFacturasActivity.class);
        ac.putExtra("IdClientes",idCliente);
        startActivity(ac);
    }
    public void verFacturas(View v){
        Intent ac = new Intent(this,ListaDeFacturasActivity.class);
        ac.putExtra("IdClientes",idCliente);
        startActivity(ac);
    }
}