package com.example.proyecto_examen_complexivo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.proyecto_examen_complexivo.adapter.LoginAdapter;
import com.example.proyecto_examen_complexivo.base_temp.DbHelper;
import com.example.proyecto_examen_complexivo.modelo.Persona;
import com.example.proyecto_examen_complexivo.modelo.Producto;
import com.example.proyecto_examen_complexivo.network.Constantes;
import com.example.proyecto_examen_complexivo.service.Apis;
import com.example.proyecto_examen_complexivo.service.UsuarioService;
import com.example.proyecto_examen_complexivo.modelo.Usuario;
import com.example.proyecto_examen_complexivo.service.Validacion_user;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class Inicio_Login extends AppCompatActivity implements Validacion_user {

    public static ArrayList<Usuario> arrayDatos = new ArrayList<Usuario>();
    private Usuario usuario=new Usuario();
    private Persona persona=new Persona();


    private ProgressBar progressBar;
    private Button btn_ingresa;
    private TextView txtUsuario, txtClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_ingresa = findViewById(R.id.btn_ingresar);
        txtUsuario = findViewById(R.id.txt_usuario);
        txtClave = findViewById(R.id.txt_contraseña);
        progressBar = findViewById(R.id.progressBar);

        btn_ingresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario u=new Usuario();
                u.setUsuusuario(txtUsuario.getText().toString());
                u.setUsu_contrasena(txtClave.getText().toString());
                obtener(txtUsuario.getText().toString());
                validar(u);
                DbHelper dbHelper = new DbHelper(Inicio_Login.this, "basetemp", null, 2);
                SQLiteDatabase db = dbHelper.getWritableDatabase();



            }
        });
    }

    public void validar(Usuario u){

        UsuarioService usuarioService = Apis.getUsuarioService();
        Call<Integer> call = usuarioService.validar_login(u);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, retrofit2.Response<Integer> response) {
                if (response.isSuccessful()) {
                    int  val = response.body();
                    new LoginAdapter(Inicio_Login.this).execute(val, 3000);
                }
            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                t.printStackTrace();
            }
        });



    }


    @Override
    public void toggleProgressBar(boolean status) {
        if (status) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void lanzarActividad(Class<?> tipoActividad) {
        Intent intent = new Intent(this, tipoActividad);
        startActivity(intent);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


    public void obtener(String username){
        //
        Constantes constantes=new Constantes();
        Call<Usuario> res=constantes.getApiService().getusuario(username);
        res.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                usuario= (Usuario) response.body();
                persona=response.body().getIdpersona();
                Toast.makeText(Inicio_Login.this, ""+persona.getNombre(), Toast.LENGTH_SHORT).show();

                DbHelper dbhelper = new DbHelper(Inicio_Login.this, "basetemp", null, 2);
                String nsql = "INSERT INTO usuario (ud_id, usuusuario, usu_contrasena, cedula, nombre,apellido,direccion, telefono, correo, persona_id) "+
                        "VALUES ('"+usuario.getUsu_id()+"','"+usuario.getUsuusuario()+"','"+usuario.getUsu_contrasena()+"','"+persona.getCedula()+"','"+persona.getNombre()+"','"+persona.getApellido()+"','"+persona.getDireccion()+"','"+persona.getCelular()+"','"+persona.getCorreo()+"','"+persona.getIdpersona()+"')";
                dbhelper.noQuery(nsql);
                dbhelper.close();
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(Inicio_Login.this, "ERROR", Toast.LENGTH_SHORT).show();

            }
        });


    }
}