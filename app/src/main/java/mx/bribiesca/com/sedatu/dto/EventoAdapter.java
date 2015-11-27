package mx.bribiesca.com.sedatu.dto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mx.bribiesca.com.sedatu.R;

/**
 * Created by Bribiesca on 18/11/15.
 */
// La clase debe extender de la clase ArrayAdapter<Objeto de interes>
public class EventoAdapter extends ArrayAdapter<Evento> {

    public EventoAdapter(Context c, List<Evento> objetos){
        super(c, 0, objetos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtengo una instancia del Inflater
        LayoutInflater i = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Me quedo con la referencia de la fila actual
        View item = convertView;

        if(convertView == null){
            // Inflo el Inflater con el xml que representa las filas
            item = i.inflate(R.layout.item_eventos, parent, false);
        }

        TextView titulo = (TextView)item.findViewById(R.id.text1);
        TextView subtitulo = (TextView)item.findViewById(R.id.text2);
        ImageView imagen = (ImageView)item.findViewById(R.id.tipo_imagen);

        // Obtengo el evento actual
        Evento evento = getItem(position);

        // Setteo los elementos
        titulo.setText(evento.getNombre());
        subtitulo.setText(evento.getFechaInicio());
        int tipo = evento.getImg();
        if(tipo == 0){
            imagen.setImageResource(R.drawable.rammstein);
        }else if(tipo == 1){
            imagen.setImageResource(R.drawable.of_mice_and_men);
        }
        return item;
    }
}
