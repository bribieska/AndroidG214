package mx.bribiesca.com.sedatu;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import mx.bribiesca.com.sedatu.util.AppPageAdapter;

public class pestanias extends AppCompatActivity {

    ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pestanias);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        vp = (ViewPager)findViewById(R.id.pager);
        AppPageAdapter apa = new AppPageAdapter(getSupportFragmentManager());
        vp.setAdapter(apa);
    }
}
