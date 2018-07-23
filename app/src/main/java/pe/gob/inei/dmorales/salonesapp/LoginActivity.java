package pe.gob.inei.dmorales.salonesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button btningresar;
    EditText edtUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsuario = (EditText) findViewById(R.id.usuario);
        btningresar = (Button) findViewById(R.id.btnIngresar);

        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int user =  Integer.parseInt(edtUsuario.getText().toString());
                if(user == Usuarios.USUARIO1 || user == Usuarios.USUARIO2 || user == Usuarios.USUARIO3){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("user",user);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "DEBE INGRESAR UN USUARIO CORRECTO", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
