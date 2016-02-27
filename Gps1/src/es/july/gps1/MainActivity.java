package es.july.gps1;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	public TextView nsatelites, app_info, pos_info;
	static LocationManager lm;
	static MiLocationListener mlistener;

	// GPS
	public static String lat, lon, loc;
	static Location location_A;
	static Location location_B;
	int nsat, msat, alt, error;
	double vel;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Localizamos TextView
		pos_info = (TextView) findViewById(R.id.pos_info);
		nsatelites = (TextView) findViewById(R.id.nsatelites);

		// Iniciamos los controles para el GPS
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		mlistener = new MiLocationListener();
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5,
				mlistener);

		// Escribimos los TextView el valor inicial
		loc = "Buscando posicion...";
		pos_info.setText("Buscando direccion...");
		String tsat = (String) (getResources().getString(R.string.sat) + " "
				+ nsat + " / " + msat + "\n" + loc + "\n"
				+ getResources().getString(R.string.alt) + " " + alt + " m"
				+ "\n" + getResources().getString(R.string.prec) + " " + error
				+ " m" + "\n" + getResources().getString(R.string.vel) + " "
				+ vel + " Km/h");

		nsatelites.setText(tsat);
	}
	
	
	@Override
	//Codigo que se ejecuta cuando cerramos nuestra aplicacion
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		lm.removeUpdates(mlistener);	
	}


	//Clase para nuestro listener del GPS
	public class MiLocationListener implements LocationListener {

		public MiLocationListener() {
			// TODO Auto-generated constructor stub
		}

		public synchronized void onLocationChanged(Location location) {

			DecimalFormat f = new DecimalFormat("###.#####");

			lat = f.format(location.getLatitude()).replace(",", ".");
			lon = f.format(location.getLongitude()).replace(",", ".");
			alt = (int) location.getAltitude();
			error = (int) location.getAccuracy();
			int vel_mt = (int) location.getSpeed();

			vel = (((double) vel_mt * 60) * 60) / 1000;
			loc = "Lat: " + lat + ", Lon: " + lon;

			// escribimos Datos del GPS
			String tsat = (String) (getResources().getString(R.string.sat)
					+ " " + nsat + " / " + msat + "\n" + loc + "\n"
					+ getResources().getString(R.string.alt) + " " + alt + " m"
					+ "\n" + getResources().getString(R.string.prec) + " "
					+ error + " m" + "\n"
					+ getResources().getString(R.string.vel) + " " + vel + " Km/h");

			nsatelites.setText(tsat);

		}

		public synchronized void onProviderDisabled(String provider) {
			// gps_flag.setText("Desactivado");
			// Toast.makeText(main.this,
			// "GPS desactivado, por favor activa el GPS",
			// Toast.LENGTH_LONG).show();
		}

		public synchronized void onProviderEnabled(String provider) {
			// gps_flag.setText("Activado");
			// Toast.makeText(main.this, "GPS activado",
			// Toast.LENGTH_LONG).show();
		}

		public synchronized void onStatusChanged(String provider, int status,
				Bundle extras) {
			// TODO Auto-generated method stub
		}
	}


}
