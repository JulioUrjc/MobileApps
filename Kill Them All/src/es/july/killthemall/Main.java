package es.july.killthemall;

import android.app.Activity;
import android.content.Intent;
//import android.graphics.Color;
//import android.widget.TextView;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Main extends Activity {

	private int puntos = 0;
	private int malos = 0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.main);
		
			  /*TextView play = (TextView)findViewById(R.id.play_button);
			  play.setTextColor(Color.WHITE);*/
			  ImageButton play = (ImageButton)findViewById(R.id.boton1);
			  play.setOnClickListener(new OnClickListener() {
				  @Override
				  public void onClick(View v) {
					  Toast.makeText(getApplicationContext(),
					             R.string.menu_play, Toast.LENGTH_SHORT).show();
					  empiezaJuego();
				  }
			  });
			 
			  /*TextView options = (TextView)findViewById(R.id.options_button);
			  options.setTextColor(Color.WHITE);*/
			  ImageButton options = (ImageButton)findViewById(R.id.boton2);
			  options.setOnClickListener(new OnClickListener() {
				  @Override
				  public void onClick(View v) {
					  Toast.makeText(getApplicationContext(),
					             R.string.menu_options, Toast.LENGTH_SHORT).show();				  
					  muestraOpciones();					  
				  }
			  });
			 
			  /*TextView exit = (TextView)findViewById(R.id.exit_button);
			  exit.setTextColor(Color.WHITE);*/
			  ImageButton exit = (ImageButton)findViewById(R.id.boton3);
			  exit.setOnClickListener(new OnClickListener() {
				  @Override
				  public void onClick(View v) {
					  Toast.makeText(getApplicationContext(),
			             R.string.menu_exit, Toast.LENGTH_SHORT).show();
					  finish();
				  }
			  });
			 
			  ImageView logo = (ImageView)findViewById(R.id.kill_logo);
			  logo.setOnClickListener(new OnClickListener() {
				  @Override
				  public void onClick(View v) {
					  Intent viewIntent = new Intent("android.intent.action.VIEW",
			             Uri.parse("http://www.google.es"));
					  		startActivity(viewIntent);
			   }
			  });
	}
	
	private void empiezaJuego() {
		Intent juego = new Intent(this, Killthem.class);
		juego.putExtra("Puntos", puntos);
		juego.putExtra("Malos", malos);
		this.startActivityForResult(juego, 1);
		
	}
	
	private void muestraOpciones() {
		Intent opciones = new Intent(this, OpcionesActivity.class);
		this.startActivity(opciones);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode) {
		case 1:
			if (resultCode == Activity.RESULT_OK && data.getExtras() != null) {
				// solo si el codigo devuelto es RESULT_OK, procesamos
				puntos = data.getExtras().getInt("Puntos");
				malos = data.getExtras().getInt("Malos");
			}
			break;
		}
	}

}
