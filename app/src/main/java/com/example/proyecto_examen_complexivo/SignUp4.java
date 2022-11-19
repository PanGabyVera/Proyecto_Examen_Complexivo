package com.example.proyecto_examen_complexivo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.airbnb.lottie.LottieAnimationView;
import com.example.proyecto_examen_complexivo.modelo.Usuario;

import com.example.proyecto_examen_complexivo.service.UsuarioService;
import com.google.android.material.textfield.TextInputLayout;

public class SignUp4 extends AppCompatActivity {

    private ImageView backView;
    private LottieAnimationView imgSignUp;
    private Button btnRegistrarme;
    private EditText txt_usu, txt_contra, txt_confir_contra;
    private TextInputLayout labelUsuario, labelContraseña, labelConfirmContraseña;
    private ConstraintLayout layoutInputDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up4);
        imgSignUp= findViewById(R.id.imgRegistir);
        labelUsuario=findViewById(R.id.labelUsuarioSignup);
        labelContraseña=findViewById(R.id.labelContraseñaSignUp);
        labelConfirmContraseña=findViewById(R.id.labelCofirmContraseñaSignUp);
        layoutInputDatos= findViewById(R.id.layoutSignUp);
       // setAnimation();
        txt_usu=findViewById(R.id.txt_usuarioSignUp);
        txt_contra=findViewById(R.id.txt_contraseñaSignUp);
        txt_confir_contra=findViewById(R.id.txt_contraseñaConfirmarSignUp);
        backView= findViewById(R.id.btnBackView);

        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginBack= new Intent(SignUp4.this, Inicio_Login.class);
                startActivity(loginBack);
                overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
                finish();
            }
        });

        btnRegistrarme= findViewById(R.id.btnRegistrarme);
        btnRegistrarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txt_usu.getText().toString().isEmpty()&&txt_contra.getText().toString().isEmpty()&&txt_confir_contra.getText().toString().isEmpty()){
                    Toast.makeText(SignUp4.this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
                }else{

                    if(txt_contra.getText().toString().equals(txt_confir_contra.getText().toString())){
                        Usuario usuario= new Usuario();
                        usuario.setUsuusuario(txt_usu.getText().toString());
                      //  usuario.setTipoUsuario("cliente");
                        usuario.setUsu_contrasena(txt_contra.getText().toString());
                      //  addUsuario(usuario);
                        limpiarCampos();
                        Intent home= new Intent(SignUp4.this, MainActivity.class);
                        startActivity(home);
                        finish();
                    }else{
                        Toast.makeText(SignUp4.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    UsuarioService usuarioService;

/*
    public void addUsuario(Usuario usuario) {
        usuarioService = Apis.getUsuarioService();
        Call<JSONArray> call = usuarioService.addUsuario(usuario);
        call.enqueue(new Callback<JSONArray>() {
            @Override
            public void onResponse(Call<Usuario> call, retrofit2.Response<Usuario> response) {
                if (response.isSuccessful()) {
                    //Toast.makeText(SignUp4.this, "Usuario agregado", Toast.LENGTH_SHORT).show();
                    //GUARDAMOS EL USUARIO EN LA BASE TEMP PARA VALIDAR EL INGRESO A LA APP
                    SQLiteOpenHelper  bd1 = new SQLiteOpenHelper(SignUp4.this);
                    boolean bandera =bd1.agregarUsuario(txt_usu.getText().toString(), "cliente", txt_contra.getText().toString(), "registrado");
                    if(bandera!=false){
                        Toast.makeText(SignUp4.this, "Usuario agregado", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(SignUp4.this, "Error al agregar usuario 234234", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(SignUp4.this, "Error al agregar usuario", Toast.LENGTH_SHORT).show();
            }
        });
    }*/

    public  void limpiarCampos(){
        txt_usu.setText("");
        txt_contra.setText("");
        txt_confir_contra.setText("");
    }

    private void setAnimation(){
        Animation desplazamientoIzquierdaDerecha= AnimationUtils.loadAnimation(this,R.anim.desplazamiento_izquierda_derecha);
        labelUsuario.setAnimation(desplazamientoIzquierdaDerecha);
        labelContraseña.setAnimation(desplazamientoIzquierdaDerecha);
        labelConfirmContraseña.setAnimation(desplazamientoIzquierdaDerecha);
        Animation desplazamientoAbajo= AnimationUtils.loadAnimation(this,R.anim.desplazamiento_arriba);
        layoutInputDatos.setAnimation(desplazamientoAbajo);
        ///AMIMACION DE IMAGEN ENCABEZADO DE ACTIVITY SIGN UP
        imgSignUp.setAnimation(desplazamientoIzquierdaDerecha);
        imgSignUp.setAnimation(R.raw.effect_sigup_img);
        imgSignUp.playAnimation();
        imgSignUp.setRepeatCount(2000);
    }
}