package com.example.proyectoprografacturacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoprografacturacion.clases.FuncionesFacturas;
import com.example.proyectoprografacturacion.clases.clsFuncionesCliente;

import io.realm.Realm;

public class AgregarFacturasActivity extends AppCompatActivity {
    EditText mNumFact, mFecha, mFechaVence, mMonto, mPago,msaldo;
    TextView mEstado;
    int IdCliente;
    Button btAgreegar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_facturas);

        mNumFact = findViewById(R.id.txtfactNumeroFact);
        mFecha = findViewById(R.id.txtFactFechaFact);
        mFechaVence = findViewById(R.id.txtFactFechVence);
        mMonto = findViewById(R.id.txtFactMontoFact);
        mPago = findViewById(R.id.txtFactPagofact);
        msaldo = findViewById(R.id.txtFactSaldo);
        mEstado = findViewById(R.id.txtFactEstado);
        btAgreegar = findViewById(R.id.btAgregarFact);
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

            Intent ma = new Intent(this, MostradorDeDatosActivity.class);
            ma.putExtra("IdClientes",IdCliente);
            startActivity(ma);
        }
        return super.onOptionsItemSelected(item);
    }
    private void getCliente(){
        Bundle mibundle = getIntent().getExtras();
        if(mibundle != null){
            IdCliente = mibundle.getInt("IdClientes");
            try {
                btAgreegar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final clsFuncionesCliente mservicio = new clsFuncionesCliente(Realm.getDefaultInstance());
                        mservicio.obtener(IdCliente);
                        agregarFact(mservicio);
                    }
                });
            }catch(Exception e){
                Toast.makeText(AgregarFacturasActivity.this,e.getMessage().toString(),Toast.LENGTH_LONG).show();

            }
        }else{
            Toast.makeText(AgregarFacturasActivity.this,"No hay datos",Toast.LENGTH_LONG).show();
        }
    }
    public void agregarFact(clsFuncionesCliente c) {
        try {
            Bundle mibundle = getIntent().getExtras();
            assert mibundle != null;
            IdCliente = mibundle.getInt("IdClientes");
            String _NumFact = mNumFact.getText().toString();
            String _Fecha = mFecha.getText().toString();
            String _FechaVence = mFechaVence.getText().toString();
            int _Monto = Integer.parseInt(mMonto.getText().toString());
            String _Pago = mPago.getText().toString();
            String _Saldo = msaldo.getText().toString();
            String _Estado = mEstado.getText().toString();
            FuncionesFacturas facturas = new FuncionesFacturas(Realm.getDefaultInstance());
            facturas.CrearFactura(IdCliente,_NumFact,_Fecha,_FechaVence,_Monto,_Pago,_Saldo,_Estado);
            Toast.makeText(this,"Se ha guardado con exito",Toast.LENGTH_LONG).show();
            Intent mc = new Intent(this,ListaDeFacturasActivity.class);
            mc.putExtra("IdClientes",IdCliente);
            startActivity(mc);
        } catch (Exception e) {
            Log.d("error",e.getMessage());
        }
        Borrarceldas();
    }
    public void corregir(View v){
        Borrarceldas();
    }

    private void Borrarceldas() {
        mNumFact.setText("");
        mFecha.setText("");
        mFechaVence.setText("");
        mMonto.setText("");
        mPago.setText("");
        msaldo.setText("");
    }

}