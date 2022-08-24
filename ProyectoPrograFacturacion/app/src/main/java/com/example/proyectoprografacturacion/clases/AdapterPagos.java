package com.example.proyectoprografacturacion.clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.proyectoprografacturacion.R;

import java.util.List;

public class AdapterPagos extends BaseAdapter {


    private Context context;
    private List<clsPagosFacturas> lista;
    private int layout;

    public AdapterPagos(Context context, List<clsPagosFacturas> lista, int layout) {
        this.context = context;
        this.lista = lista;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public clsPagosFacturas getItem(int position) {
        return lista.get(position);

    }


    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String _NumPago = "Numero de pago: ";
        String _NumCliente = "Id cliente: ";
        String _NumFact = "Numero de factura: ";
        String _Fecha = "Fecha: : ";
        String _Abono = "Abono: ";
        String _Saldo = "Saldo: ";
        String Monto = "Monto: ";
        AdapterPagos.viewHolder vh;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(layout,null);
            vh = new com.example.proyectoprografacturacion.clases.AdapterPagos.viewHolder();
            vh._NumFact = (TextView) convertView.findViewById(R.id.txtAdapPagoNumFact);
            vh._Abono = (TextView) convertView.findViewById(R.id.txtadapSPagoAbono);
            vh._mFecha = (TextView) convertView.findViewById(R.id.txtAdapPagoFecha);
            vh._mMonto = (TextView) convertView.findViewById(R.id.txtAdapPagoMonto);
            vh._msaldo = (TextView) convertView.findViewById(R.id.txtadapSPagosaldo);
            vh._NumCliente = (TextView) convertView.findViewById(R.id.txtAdapPagoNumCliente);
            vh._NumPago = (TextView) convertView.findViewById(R.id.txtAdappagoNumPago);
            convertView.setTag(vh);

        }else{
            vh = (AdapterPagos.viewHolder) convertView.getTag();
        }

        clsPagosFacturas cliente = lista.get(position);

        vh._NumFact.setText(_NumFact+cliente.getNumFact().toString());
        vh._mFecha.setText(_Fecha+cliente.getFechaPago().toString());
        vh._Abono.setText(_Abono+cliente.getAbono().toString());
        vh._mMonto.setText(Monto+cliente.getMontoFacturaPago().toString());
        vh._NumCliente.setText(_NumCliente+(cliente.getIdClientePago()));
        vh._NumPago.setText(_NumPago+cliente.getSaldoFact().toString());
        vh._msaldo.setText(_Saldo+cliente.getSaldoFact());
        return convertView;

    }


    public class viewHolder{

        TextView _NumPago;
        TextView _NumCliente;
        TextView _mFecha;
        TextView _mMonto;
        TextView _Abono;
        TextView _msaldo;
        TextView _NumFact;

    }
}
