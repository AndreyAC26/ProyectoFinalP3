package com.example.proyectoprografacturacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectoprografacturacion.clases.Clientes;
import com.example.proyectoprografacturacion.clases.clsFuncionesCliente;

import io.realm.Realm;

public class ActualizarDatosActivity extends AppCompatActivity {

    EditText mNombreAct,mCedula,mTelefono,mCorreo;
    int mid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
        mNombreAct = findViewById(R.id.txtAgregarNombreaPel);
        mCedula = findViewById(R.id.txtAgregaredulas);
        mTelefono = findViewById(R.id.txtAgregarTelefono);
        mCorreo = findViewById(R.id.txtAgregarCorreooo);
        getCliente();

    }
    private void getCliente(){
        Bundle mibundle = getIntent().getExtras();
        if(mibundle != null){
            int idCliente = mibundle.getInt("IdClientes");
            mid = mibundle.getInt("IdClientes");
            clsFuncionesCliente mservicio = new clsFuncionesCliente(Realm.getDefaultInstance());
            Clientes cliente = mservicio.obtener(idCliente);
            try {
                mNombreAct.requestFocus();
                mNombreAct.setText(cliente.getNombre());
                mCedula.setText(cliente.getCedula());
                mTelefono.setText(cliente.getTelefonoCelular());
                mCorreo.setText(cliente.getCorreo());

            }catch(Exception e){
                Toast.makeText(ActualizarDatosActivity.this,e.getMessage().toString(),Toast.LENGTH_LONG).show();

            }
        }else{
            Toast.makeText(ActualizarDatosActivity.this,"No hay datos",Toast.LENGTH_LONG).show();
        }
    }

    public void actualizar(View v){
        clsFuncionesCliente actualizar = new clsFuncionesCliente(Realm.getDefaultInstance());
        Clientes cliente = actualizar.obtener(mid);
        mNombreAct.requestFocus();
        String nombre = mNombreAct.getText().toString();
        String cedula = mCedula.getText().toString();
        String telefono = mTelefono.getText().toString();
        String correo = mCorreo.getText().toString();

        actualizar.actualizardatos(nombre,cedula,telefono,correo,cliente);
        Intent mp = new Intent(this, ListaDeClientesActivity.class);
        startActivity(mp);
    }
    public void limpiar(View v){
        mNombreAct.setText("");
        mCorreo.setText("");
        mCedula.setText("");
        mTelefono.setText("");
    }
}
