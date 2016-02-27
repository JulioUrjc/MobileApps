package es.july.toast1;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

	// Variables de la notificacion
    NotificationManager nm;
    Notification notif;
    static String ns = Context.NOTIFICATION_SERVICE;
     
//Defino los iconos de la notificacion en la barra de notificacion
    int icono_v = R.drawable.ic_launcher;
    int icono_r = R.drawable.ic_launcher;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		//Mostramos Toast
		Toast.makeText(getApplicationContext(),
		getResources().getString(R.string.hola), Toast.LENGTH_LONG).show();
		not();
	}
	
	@Override
	public void onBackPressed() {
		// Quitamos notificaciones
        nm.cancel(1);
	    super.onBackPressed();
	}
	
	private void not(){
		// Inicio el servicio de notificaciones accediendo al servicio
	    nm = (NotificationManager) getSystemService(ns);
	 
	    // Realizo una notificacion por medio de un metodo hecho por mi
	    notificacion(icono_r, "titulo contenido", "texto contenido", "texto extendido");
	 
	    // Lanzo la notificacion creada en el paso anterior
	    nm.notify(1, notif);	
	}
	
	public void notificacion(int icon, CharSequence textoEstado, CharSequence titulo, CharSequence texto) {
        // Capturo la hora del evento
        long hora = System.currentTimeMillis();
         
        // Definimos la accion de la pulsacion sobre la notificacion (esto es opcional)
        Context context = getApplicationContext();
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
 
        // Defino la notificacion, icono, texto y hora
        notif = new Notification(icon, textoEstado, hora);
        notif.setLatestEventInfo(context, titulo, texto, contentIntent);
         
        //Defino que la notificacion sea permamente
        notif.flags = Notification.FLAG_ONGOING_EVENT;
    }

}
