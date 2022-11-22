package com.example.proyecto_examen_complexivo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.proyecto_examen_complexivo.Fragments.Fragment_updatePerson;
import com.example.proyecto_examen_complexivo.Fragments.ProductosFragment;
import com.example.proyecto_examen_complexivo.Fragments.ServiciosFragment;
import com.example.proyecto_examen_complexivo.Fragments.detalle_compras;
import com.google.android.material.navigation.NavigationView;

import java.sql.SQLOutput;


public class Navegacion extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

        DrawerLayout drawerLayout;
        NavigationView navigationView;
        Toolbar toolbar;
        ActionBarDrawerToggle toggle;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            //Referencias UI
            drawerLayout = findViewById(R.id.contenidoPrincipal);
            navigationView = findViewById(R.id.nav_view_bar);
            toolbar = findViewById(R.id.toolBar);

            //Configurar Fragment por defecto (El que aparece al principio)
            getSupportFragmentManager().beginTransaction().add(R.id.contentFrame, new ProductosFragment()).commit();
            setTitle("Productos");

            //Configuracion de ToolBars
            setSupportActionBar(toolbar);

            toggle = setDrawerToggle();
            drawerLayout.addDrawerListener(toggle);

            //Dar acciones a los items del menu
            navigationView.setNavigationItemSelectedListener(this);


        }

        private ActionBarDrawerToggle setDrawerToggle() {
            return new ActionBarDrawerToggle(this,
                    drawerLayout,
                    toolbar,
                    R.string.drawer_open,
                    R.string.drawer_close
            );
        }

        @Override
        protected void onPostCreate(@Nullable Bundle savedInstanceState) {
            super.onPostCreate(savedInstanceState);
            toggle.syncState();
        }

        @Override
        public void onConfigurationChanged(@NonNull Configuration newConfig) {
            super.onConfigurationChanged(newConfig);
            toggle.onConfigurationChanged(newConfig);
        }

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            listerSelectedItem(item);
            return true;
        }

        private void listerSelectedItem(MenuItem item) {

            FragmentManager fm= getSupportFragmentManager();
            FragmentTransaction ft= fm.beginTransaction();



            switch (item.getItemId()){

                case R.id.nav_home:
                    ft.replace(R.id.contentFrame, new ProductosFragment()).commit();
                    break;
                case R.id.nav_servicios:
                    ft.replace(R.id.contentFrame, new ServiciosFragment()).commit();
                    break;
                case R.id.nav_carrito:
                    //ft.replace(R.id.contentFrame, new ServiciosFragment()).commit();
                    ft.replace(R.id.contentFrame, new detalle_compras()).commit();
                    break;

                case R.id.nav_perfil:
                    ft.replace(R.id.contentFrame, new Fragment_updatePerson()).commit();
                    break;

            }
            setTitle(item.getTitle());
            drawerLayout.closeDrawers();
        }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            if (toggle.onOptionsItemSelected(item)) {
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuItem menuItem= menu.findItem(R.id.search_button);
            return super.onCreateOptionsMenu(menu);
        }

    }
