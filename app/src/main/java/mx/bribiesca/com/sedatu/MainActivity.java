package mx.bribiesca.com.sedatu;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import mx.bribiesca.com.sedatu.dto.Evento;
import mx.bribiesca.com.sedatu.dto.EventoAdapter;
import mx.bribiesca.com.sedatu.util.Parser;

public class MainActivity extends AppCompatActivity {

    HttpURLConnection con;
    ListView eventos;
    DrawerLayout dl;
    NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.left_menu_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /* Habilitamos el menu en la parte superior izquierda */
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_36dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dl = (DrawerLayout)findViewById(R.id.drawer_layout);
        nv = (NavigationView)findViewById(R.id.nav_view);
        enabledActionsLeftMenu(nv);

        /* Lista de eventos */
        eventos = (ListView)findViewById(R.id.listEventos);

        // compruebo que halla conexión a internet en el dispositivo
        try{
            ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni = cm.getActiveNetworkInfo();
            if(ni != null && ni.isConnected()){
                // hace una peticion web a la siguiente direccion
                new Task().execute(new URL("http://sedatu.mx/webServices/getEvent.php"));
            }else{
                Toast.makeText(this, "Error de conexion", Toast.LENGTH_LONG).show();
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Si no se inicio el menu lateral deslizante infla el menu del ActionBar*/
        if(!dl.isDrawerOpen(GravityCompat.START)){
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id){
            /* Icono lado superior izquierdo */
            case android.R.id.home:
                dl.openDrawer(GravityCompat.START);
                return true;
            /* Menu parte superior derecha */
            case R.id.action_settings:
                Intent i = new Intent(this, pestanias.class);
                startActivity(i);
                return true;
            default: return true;
        }
    }

    public void enabledActionsLeftMenu(NavigationView nv){
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                switch (item.getItemId()){
                    case R.id.left_menu_productos:
                        return throwMessage("Pushaste Productos");
                    case R.id.left_menu_orden:
                        return throwMessage("Pushaste Orden");
                    case R.id.left_menu_home:
                        return throwMessage("Pushaste Inicio");
                    case R.id.left_menu_nube:
                        return throwMessage("Pushaste Mi Nube");
                    case R.id.left_menu_sd:
                        return throwMessage("Pushaste Mi Micro SD");
                    case R.id.left_menu_logout:
                        return throwMessage("Pushaste Cerrar Sesión");
                    default: return true;
                }
            }
        });
    }

    public boolean throwMessage(String mensaje){
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
        dl.closeDrawers();
        return true;
    }

    /* Clase que hace la tarea asincrona para la lista */
    public class Task extends AsyncTask<URL, Void, List<Evento>>{

        @Override
        protected List<Evento> doInBackground(URL... urls) {

            List<Evento> eventos = null;

            try{
                con = (HttpURLConnection)urls[0].openConnection();
                int status = con.getResponseCode();
                if(status != 200){
                    // Evento por default en la lista en caso de que no halla valor de retorno
                    eventos = new ArrayList<Evento>();
                    Evento e = new Evento();
                    e.setNombre("No hay Eventos");
                    e.setFechaInicio("No hay Eventos");
                    e.setImg(0);
                    eventos.add(e);
                    return eventos;
                }else{
                    // Recojo el flujo de datos que viene en formato JSON
                    InputStream is = new BufferedInputStream(con.getInputStream());
                    Parser p = new Parser();
                    // Obtengo la lista de objetos de tipo Evento
                    eventos = p.readJSONStream(is);
                }
            }catch(IOException e){
                e.printStackTrace();
            }finally {
                con.disconnect();
            }
            return eventos;
        }

        @Override
        protected void onPostExecute(List<Evento> result) {
            // Creo un objeto de la clase sobreescrita para el adaptador
            EventoAdapter ea = new EventoAdapter(getBaseContext(), result);
            // Si fuese un adaptador <String> en su correspondiente xml solo debe ir un elemento TextView
            eventos.setAdapter(ea);
            // agregamos un listener a la lista del adaptador para poder manejar cada objeto por separado
            eventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    EventoAdapter ea = (EventoAdapter) parent.getAdapter();
                    Evento e = ea.getItem(position);
                    String msj = "Escogiste el evento con ID: " + e.getIdEvento();
                    Log.d("Toast", msj);
                    Toast.makeText(getApplicationContext(), msj,Toast.LENGTH_LONG).show();
                }
            });
        }
    }
    /* Clase que hace la tarea asincrona para la lista */
}


