package com.example.worldbook

import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment

class LogoFragmento : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.logo_fragmento, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val logoImageView: ImageView = view.findViewById(R.id.logo)

        logoImageView.setImageResource(R.mipmap.logo)

        logoImageView.setOnClickListener{
            Toast.makeText(context, "Bienvenido a WorldBook", Toast.LENGTH_SHORT).show()
        }
    }
}