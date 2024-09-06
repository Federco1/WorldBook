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
import com.example.intentologin.RegisterActivity

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

        btnIniciarSesion.setOnClickListener {
            var usuario=etNombreUsuario.text.toString()


            if (usuario.isEmpty() || etPassword.text.toString().isEmpty()){
                var mensaje = " Faltan completar campos"
                Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show()
            }else{

                if(cbRecordarUsuario.isChecked){
                    Log.i("TODO","Funcionalidad de recordar usuario y contraseña")
                }
                val intent = Intent(this,MainActivity::class.java)
                intent.putExtra("NOMBRE",usuario)
                startActivity(intent)
                finish()
            }



        }

        btnCrearUsuario.setOnClickListener {
            Toast.makeText(this,"Crear Usuario ToDo", Toast.LENGTH_SHORT).show()

        }
    }
}