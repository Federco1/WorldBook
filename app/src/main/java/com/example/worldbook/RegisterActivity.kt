package com.example.worldbook

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.worldbook.R

class RegisterActivity : AppCompatActivity() {

    lateinit var btnCrearUsuario: Button
    lateinit var etNombreUsuario: EditText
    lateinit var etPassword: EditText
    lateinit var etPasswordAgain: EditText
    lateinit var etEmail: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnCrearUsuario = findViewById(R.id.btnCrearUsuario)
        etNombreUsuario = findViewById(R.id.etNombreUsuario)
        etPassword = findViewById(R.id.etPassword)
        etPasswordAgain = findViewById(R.id.etPasswordAgain)
        etEmail = findViewById(R.id.etEmail)


        btnCrearUsuario.setOnClickListener {
            var email = etEmail.text.toString()
            var usuario = etNombreUsuario.text.toString()
            var password = etPassword.text.toString()
            var passwordAgain = etPasswordAgain.text.toString()

            if (email.isEmpty() || usuario.isEmpty() || password.isEmpty() || passwordAgain.isEmpty() ){
                var mensaje = "Complete todos los campos"
                Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show()
            }else{
                if (password != passwordAgain) {
                    Toast.makeText(this, "Contraseña no coincide", Toast.LENGTH_SHORT).show()
                } else {
                    // Verificar si el usuario o el email ya existen en la base de datos usando DatosUsuarioDao
                    val db = DataBase.getDatabase(applicationContext)
                    val userDao = db.DatosUsuarioDao()  // Aquí usas tu DAO llamado DatosUsuarioDao

                    // Comprobar si el email o usuario ya están registrados
                    val usuarioExistente = userDao.getUserByUsername(usuario)
                    val emailExistente = userDao.getUserByEmail(email)

                    if (usuarioExistente != null || emailExistente != null) {
                        Toast.makeText(this, "El usuario o email ya están registrados", Toast.LENGTH_SHORT).show()
                }else{
                    var nuevoUsuario = DatosUsuario(email,usuario,password)
                    DataBase.getDatabase(applicationContext).DatosUsuarioDao().insert(nuevoUsuario)
                    val intent = Intent(this, TerminosYCondicionesActivity::class.java)
                    startActivity(intent)
                    }
                }
            }
        }
    }
}