package com.example.worldbook

import android.Manifest
import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class RecordarUsuario : Service() {

    private val CHANNEL_ID = "recordarUsuario"

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }



    @SuppressLint("ForegroundServiceType")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notificacion = crearNotificacion()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            crearChannel()
            startForeground(1, notificacion)
        } else {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
            } else {
                NotificationManagerCompat.from(this).notify(1,notificacion)
            }
        }
        return START_STICKY
    }


    private fun crearChannel() {
        val importance = NotificationManager.IMPORTANCE_LOW
        val channel = NotificationChannel(CHANNEL_ID, "Recordar Usuario", importance)
        channel.description = "Se va a mostrar cuando se marque la casilla"
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun crearNotificacion(): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notificacion)
            .setContentTitle("WorldBook")
            .setContentText("El usuario esta siendo recordado.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setOngoing(true)
            .build()
    }

}
