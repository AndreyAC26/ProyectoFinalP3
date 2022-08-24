package com.example.proyectoprografacturacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoprografacturacion.clases.clsFuncionesCliente;

import io.realm.Realm;

public class AgregarClienteActivity extends AppCompatActivity {

    EditText mNombre,mCedula,mTelefono,mCorreo;
    TextView mSaldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cliente);
        mNombre = findViewById(R.id.txtAgregarNombreaP);
        mCedula = findViewById(R.id.txtAgregaredula);
        mTelefono = findViewById(R.id.txtAgregarTelefonoCel);
        mCorreo = findViewById(R.id.txtAgregarCorreo);
        mSaldo = findViewById(R.id.txtCamposaldo);
        mSaldo.setText("0");

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_regresar, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id==R.id.menu_atras){
            Intent ma = new Intent(this,MainActivity.class);
            startActivity(ma);
        }
        return super.onOptionsItemSelected(item);
    }

    public void CrearCliente(View v){
        try{
            Realm.init(this);
            String _Nombre = mNombre.getText().toString();
            String _Cedula = mCedula.getText().toString();
            String _TelefonoCel = mTelefono.getText().toString();
            String _Correo = mCorreo.getText().toString();
            String _Saldo = mSaldo.getText().toString();
            clsFuncionesCliente cliente = new clsFuncionesCliente(Realm.getDefaultInstance());
            cliente.CrearCliente(_Nombre,_Cedula,_TelefonoCel,_Correo,_Saldo);
            Toast.makeText(this,"Se ha guardado con exito",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this,"Se produjo un error",Toast.LENGTH_LONG).show();
        }
        borrarceldas();
    }

    public void cancelar(View v){
        borrarceldas();
        Toast.makeText(this,"Se ha cancelado la operacion",Toast.LENGTH_LONG).show();
    }

    private void borrarceldas() {
        mNombre.setText("");
        mCedula.setText("");
        mTelefono.setText("");
        mTelefono.setText("");
        mCorreo.setText("");
    }

}