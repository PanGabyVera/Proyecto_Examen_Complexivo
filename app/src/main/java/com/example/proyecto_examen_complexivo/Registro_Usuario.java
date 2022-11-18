package com.example.proyecto_examen_complexivo;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import com.example.proyecto_examen_complexivo.modelo.Persona;
import com.example.proyecto_examen_complexivo.modelo.Rol;
import com.example.proyecto_examen_complexivo.modelo.Usuario;
import com.example.proyecto_examen_complexivo.service.Apis;
import com.example.proyecto_examen_complexivo.service.PersonaService;
import com.example.proyecto_examen_complexivo.service.UsuarioService;
import com.google.gson.JsonArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;

import java.io.IOException;

public class Registro_Usuario extends AppCompatActivity {
    private EditText usuario, contra, repetir;
    private Button btnregistra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);
        usuario = findViewById(R.id.txt_usuario1);
        contra = findViewById(R.id.txt_contra_1);
        repetir = findViewById(R.id.txt_rep_1);
        btnregistra = findViewById(R.id.btn_registrar_1);

        btnregistra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usuario.getText().toString().isEmpty()||contra.getText().toString().isEmpty()||repetir.getText().toString().isEmpty()){
                   Toast.makeText(Registro_Usuario.this, "Llene todos los campos", Toast.LENGTH_LONG).show();
                }else{
                    Persona p = new Persona(1);
                    Rol r= new Rol(1);
                    Usuario u=new Usuario(usuario.getText().toString(),contra.getText().toString(),p,r);
                    addUsuario(u);
                    Toast.makeText(Registro_Usuario.this, "Persona Agregada", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    UsuarioService usuarioService;
    public void addUsuario(Usuario usuario) {

        usuarioService = Apis.getUsuarioService();
        Call<Usuario> call = usuarioService.addUsuario(usuario);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, retrofit2.Response<Usuario> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Registro_Usuario.this, "Datos Guardados", Toast.LENGTH_LONG).show();
                    Intent home = new Intent(Registro_Usuario.this, Navegacion.class);
                    startActivity(home);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                System.out.println(t.getMessage());
                System.out.println("Error");
            }

        });

    }
}

