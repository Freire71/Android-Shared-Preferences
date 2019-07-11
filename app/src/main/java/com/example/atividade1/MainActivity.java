package com.example.atividade1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String usuario;
    String senha;
    int tentativas;
    EditText inputUsuario;
    EditText inputSenha;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Login");
        sharedPreferences = getSharedPreferences("Store", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        usuario = sharedPreferences.getString("usuario", "admin");
        senha = sharedPreferences.getString("senha", "admin");
        tentativas = sharedPreferences.getInt("tentativas", 0);
        inputUsuario = findViewById(R.id.inputUsuarioLogin);
        inputSenha = findViewById(R.id.inputSenhaLogin);

//        editor.putString("usuario", "admin").apply();
//        editor.putString("senha", "admin").apply();



    }

    public void entrar(View view) {
        String usuarioDigitado = inputUsuario.getText().toString();
        String senhaDigitada = inputSenha.getText().toString();

        if (usuarioDigitado.equals(usuario) && senhaDigitada.equals(senha)) {
            Log.println(4, "acertou login", "");

            Toast.makeText(this, "Seja bem-vindo!", Toast.LENGTH_SHORT).show();
            editor.putInt("tentativas", 0).apply();
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        } else {
            Log.println(4, "errou login", "");
            if (tentativas == 3) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Você foi bloqueado!");
                builder.setMessage("Você errou o login mais de 3x e por isso foi bloqueado");
                dialog = builder.create();
                dialog.show();
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

                    @Override
                    public void onDismiss(DialogInterface arg0) {
                        finish();
                    }
                });

            } else {
                tentativas += 1;
                editor.putInt("tentativas", tentativas).apply();
                Toast.makeText(this,"Usuário ou senha incorretos!", Toast.LENGTH_SHORT).show();
            }

        }


    }

    public void cancelar(View view) {

    }

}

