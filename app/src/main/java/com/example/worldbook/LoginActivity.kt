package com.example.worldbook

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    lateinit var etNombreUsuario: EditText
    lateinit var etPassword: EditText
    lateinit var cbRecordarUsuario: CheckBox
    lateinit var btnIniciarSesion: Button
    lateinit var btnCrearUsuario: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        etNombreUsuario=findViewById(R.id.etNombreUsuario)
        etPassword=findViewById(R.id.etPassword)
        cbRecordarUsuario=findViewById(R.id.cbRecordarUsuario)
        btnIniciarSesion=findViewById(R.id.btnIniciarSesion)
        btnCrearUsuario=findViewById(R.id.btnCrearUsuario)

        var preferencias =
            getSharedPreferences(resources.getString(R.string.sp_credenciales), MODE_PRIVATE)
        var usuarioGuardado =
            preferencias.getString(resources.getString(R.string.nombre_usuario), "")
        var passwordGuardado =
            preferencias.getString(resources.getString(R.string.password_usuario), "")

        if (usuarioGuardado != "" && passwordGuardado != "") {
            if (usuarioGuardado != null) {
                startHomeActivity(usuarioGuardado)
            }
        }

        btnIniciarSesion.setOnClickListener {
            var usuario=etNombreUsuario.text.toString()
            var password = etPassword.text.toString()

            if (usuario.isEmpty() || password.isEmpty()){
                var mensaje = " Faltan completar campos"
                Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show()
            } else {
                if (cbRecordarUsuario.isChecked) {
                    var preferencias = getSharedPreferences(resources.getString(R.string.sp_credenciales), MODE_PRIVATE)
                    preferencias.edit().putString(resources.getString(R.string.nombre_usuario), usuario).apply()
                    preferencias.edit().putString(resources.getString(R.string.password_usuario), password).apply()
                }
                startHomeActivity(usuario)
            }
        }

        btnCrearUsuario.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun startHomeActivity(usuario: String) {
        val intent = Intent(this,HomeActivity::class.java)
        intent.putExtra(resources.getString(R.string.nombre_usuario),usuario)
        startActivity(intent)
        finish()
    }
}