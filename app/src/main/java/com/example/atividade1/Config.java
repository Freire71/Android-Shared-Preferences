package com.example.atividade1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Config extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText inputUsuario;
    EditText inputSenha;
    EditText inputConfirmarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        setTitle("Configurações");
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences("Store", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        inputUsuario = (EditText) findViewById(R.id.inputUsuario);
        inputSenha = (EditText) findViewById(R.id.inputSenha);
        inputConfirmarSenha = (EditText) findViewById(R.id.inputConfirmarSenha);
    }

    @Override
    public boolean onSupportNavigateUp(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
        return true;
    }

    public void confirmar(View view) {
        String usuarioDigitado = inputUsuario.getText().toString().trim();
        String senha = inputSenha.getText().toString().trim();
        String confirmarSenha = inputConfirmarSenha.getText().toString().trim();
        if(usuarioDigitado == "" || senha == "" || confirmarSenha == "") {
            Toast.makeText(this,"Não é possível inserir valores vazios", Toast.LENGTH_SHORT).show();
        } else if (!senha.equals(confirmarSenha)) {
            inputSenha.setText("");
            inputConfirmarSenha.setText("");
            Toast.makeText(this,"As senhas digitadas precisam ser iguais", Toast.LENGTH_SHORT).show();
        } else {
            editor.putString("usuario", usuarioDigitado).apply();
            editor.putString("senha", senha).apply();
            Toast.makeText(this,"Usuário e senha atualizados com sucesso!", Toast.LENGTH_SHORT).show();
            limparCampos(view);
        }

    }

    public void limparCampos(View view) {
        inputUsuario.setText("");
        inputSenha.setText("");
        inputConfirmarSenha.setText("");
        return;
    }


}
