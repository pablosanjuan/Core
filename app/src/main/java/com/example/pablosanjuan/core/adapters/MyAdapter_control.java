package com.example.pablosanjuan.core.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pablosanjuan.core.R;
import com.example.pablosanjuan.core.db.DbManager_control;
import com.example.pablosanjuan.core.vo.ControlVO;
import com.example.pablosanjuan.core.vo.InventarioVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebastian on 05/06/15.
 */
public class MyAdapter_control extends BaseAdapter {


    private List<ControlVO> listaControlVOs;
    private LayoutInflater layoutInflater;
    private List<InventarioVO> listaInventarioVOs;
    Activity a;
    public MyAdapter_control
            (Activity a, List<ControlVO> listaControlVOs) {
        super();
        this.listaControlVOs = new ArrayList<ControlVO>();
        if (listaControlVOs != null){
            this.listaControlVOs = listaControlVOs;
        }
        layoutInflater = a.getLayoutInflater();
        this.a=a;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listaControlVOs.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return listaControlVOs.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        // TODO Auto-generated method stub

        view = layoutInflater.inflate(R.layout.elemento_control, null);

        String id = listaControlVOs.get(position).getId();
      
        //String nombre = listaInventarioVOs.get(position).getNombre();
        String nombre = listaControlVOs.get(position).getNombre();
        String fecha = listaControlVOs.get(position).getFecha();
        String evento = listaControlVOs.get(position).getEvento();


        TextView tv_registro1 = (TextView) view.findViewById(R.id.registrocontrol1);
        TextView tv_registro2 = (TextView) view.findViewById(R.id.registrocontrol2);
        TextView tv_registro3 = (TextView) view.findViewById(R.id.registrocontrol3);
        TextView tv_eventos = (TextView) view.findViewById(R.id.eventos);

        tv_registro1.setText("Id: "+id);
        tv_registro2.setText(nombre);

        tv_registro3.setText(fecha);
        try{
            List<ControlVO> eventos=new DbManager_control(a).getRegistrosByIdBovino(id);

            if(eventos!=null){
                for(int i=0;i<eventos.size();i++){
                    ControlVO eve=eventos.get(i);

                    tv_eventos.setText(tv_eventos.getText()+eve.getEvento()+"\n");

                }

            }

        }catch (Exception e){}


        return view;
    }




}
