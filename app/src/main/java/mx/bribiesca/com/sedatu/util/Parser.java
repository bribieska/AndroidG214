package mx.bribiesca.com.sedatu.util;

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import mx.bribiesca.com.sedatu.dto.Evento;

/**
 * Created by Bribiesca on 17/11/15.
 */
public class Parser {
    public List<Evento> readJSONStream(InputStream is) throws IOException {
        JsonReader jr = new JsonReader(new InputStreamReader(is, "UTF-8"));

        try{
            return readEventsArray(jr);
        } finally {
            jr.close();
        }
    }

    public List<Evento> readEventsArray(JsonReader jr) throws IOException {
        List<Evento> eventos = new ArrayList<>();

        jr.beginArray();
        while(jr.hasNext()){
            eventos.add(readEvent(jr));
        }
        jr.endArray();
        return eventos;
    }

    public Evento readEvent(JsonReader jr) throws IOException {
        //String nombreEvento = null;
        Evento e = new Evento();
        jr.beginObject();
        while(jr.hasNext()){
           String name = jr.nextName();
            if(name.equals("nombre")){
                e.setNombre(jr.nextString());
                //nombreEvento = jr.nextString();
            }else if(name.equals("fechaInicio")){
                e.setFechaInicio(jr.nextString());
            }else if(name.equals("img")){
                e.setImg(Integer.parseInt(jr.nextString()));
            }else if(name.equals("idEvento")){
                e.setIdEvento(Integer.parseInt(jr.nextString()));
            }else{
                jr.skipValue();
            }
        }
        jr.endObject();
        return e;
    }
}
