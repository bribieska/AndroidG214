package mx.bribiesca.com.sedatu.util;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mx.bribiesca.com.sedatu.R;

/**
 * Created by Bribiesca on 25/11/15.
 */
public class LeftMenuFragment extends Fragment {
    private String tipo;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = null;
        if(getTipo().equals("HOME")){
            v = inflater.inflate(R.layout.fragment_content, container, false);
        }else if(getTipo().equals("PRODUCTOS")){
            v = inflater.inflate(R.layout.fragment_content, container, false);
        }else if(getTipo().equals("ORDEN")){
            v = inflater.inflate(R.layout.fragment_content, container, false);
        }
        return v;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
