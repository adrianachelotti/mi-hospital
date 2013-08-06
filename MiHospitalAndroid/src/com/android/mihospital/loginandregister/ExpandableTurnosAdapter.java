package com.android.mihospital.loginandregister;

import java.util.ArrayList;

import com.android.mihospital.loginandregister.HistorialTurnosExpandibleActivity.Parent;
import com.android.mihospital.turnos.Turno;
import com.android.mihospital.utils.SucursalesList;
import com.android.mihospital.utils.Utils;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class ExpandableTurnosAdapter extends BaseExpandableListAdapter {
 
 
    private LayoutInflater inflater;
    private ArrayList<Parent> mParent;
 
    public ExpandableTurnosAdapter(Context context, ArrayList<Parent> parent){
        mParent = parent;
        inflater = LayoutInflater.from(context);
    }
 
 
    
    //counts the number of group/parent items so the list knows how many times calls getGroupView() method
    public int getGroupCount() {
        return mParent.size();
    }
 
    
    //counts the number of children items so the list knows how many times calls getChildView() method
    public int getChildrenCount(int i) {
        return mParent.get(i).getArrayChildren().size();
    }
 
    
    //gets the title of each parent/group
    public Object getGroup(int i) {
        return mParent.get(i).getTitle();
    }
 
    
    //gets the name of each item
    public Object getChild(int i, int i1) {
        return mParent.get(i).getArrayChildren().get(i1);
    }
 
    
    public long getGroupId(int i) {
        return i;
    }
 
    
    public long getChildId(int i, int i1) {
        return i1;
    }
 
    
    public boolean hasStableIds() {
        return true;
    }
 
    
    //in this method you must set the text to see the parent/group on the list
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
 
        if (view == null) {
            view = inflater.inflate(R.layout.list_paren_item, viewGroup,false);
        }
 
        TextView textView = (TextView) view.findViewById(R.id.list_item_text_view);
        //"i" is the position of the parent/group in the list
        textView.setText(getGroup(i).toString());
 
        //return the entire view
        return view;
    }
 
    
    //in this method you must set the text to see the children on the list
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
    	View v = view;
		if (v == null) {			
			v = inflater.inflate(R.layout.historial_turno_item, null);
		}

		Turno turno = mParent.get(i).getArrayChildren().get(i1);
		if (turno != null) {
			TextView nombeCompleto = (TextView) v
					.findViewById(R.id.nombreMedico);
			TextView especialidad = (TextView) v
					.findViewById(R.id.especialidad);
			TextView diaFecha = (TextView) v.findViewById(R.id.fechaHorario);
			TextView sucursal = (TextView) v.findViewById(R.id.sucursal);

			if (nombeCompleto != null) {
				nombeCompleto.setText(turno.getNombreMedico());

			}
			if (especialidad != null) {
				especialidad.setText(turno.getEspecialidad());
			}

			if (diaFecha != null) {
				diaFecha.setText(turno.getDia());
			}
			if (sucursal != null) {
				sucursal.setText(SucursalesList.sucursales[turno.getSucursal()]);
			}

			TextView cancelado = (TextView) v
					.findViewById(R.id.textViewCancelado);
			if (turno.getEstado().equals(Utils.CANCELADO_MEDICO)) {

				cancelado.setVisibility(View.VISIBLE);
			} else {
				cancelado.setVisibility(View.GONE);
			}

		}
		return v;
    }
 
    
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
 
    
    public void registerDataSetObserver(DataSetObserver observer) {
        /* used to make the notifyDataSetChanged() method work */
        super.registerDataSetObserver(observer);
    }
}