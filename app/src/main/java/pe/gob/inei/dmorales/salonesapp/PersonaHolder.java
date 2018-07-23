package pe.gob.inei.dmorales.salonesapp;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class PersonaHolder extends RecyclerView.ViewHolder {
    TextView txtDni, txtNombre,txtSalon;
    CardView cv;
    public PersonaHolder(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.item_cv);
        txtDni = (TextView) itemView.findViewById(R.id.item_dni);
        txtNombre = (TextView) itemView.findViewById(R.id.item_nombre);
        txtSalon = (TextView) itemView.findViewById(R.id.item_salon);

    }

    public void setTxtDni(String dni) {
        txtDni.setText(dni);
    }

    public void setTxtNombre(String nombre) {
        txtNombre.setText(nombre);
    }

    public void setTxtSalon(int salon) {
        txtSalon.setText(salon+"");
    }

    public CardView getCv() {
        return cv;
    }

}
