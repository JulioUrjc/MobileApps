package es.july.killthemall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

public class Killthem extends Activity{
	private GameView view;
	private Acelerometro acelerometro;
	private WakeLock wl;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		acelerometro = new Acelerometro(this.getApplicationContext());	      
		
		if(getIntent().getExtras() != null) {
	    	int puntos = getIntent().getExtras().getInt("Puntos");
	    	int malos = getIntent().getExtras().getInt("Malos");
		    if(puntos == Marcador.MAX_PUNT || malos==0)
		    	view=new GameView(this,0,Marcador.MAX_PUNT);
		    else
		    	view=new GameView(this,puntos,malos);
	    } else
	    	view=new GameView(this,0,Marcador.MAX_PUNT);
		
		setContentView(view);
		
		PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
		wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "Killthem-vC");
	}
	
	@Override
	public void onBackPressed() {
		Bundle bundle = new Bundle();
	    bundle.putInt("Puntos", view.getMarcador().getPuntos());
	    bundle.putInt("Malos", view.getSprites());

	    Intent mIntent = new Intent();
	    mIntent.putExtras(bundle);
	    setResult(Activity.RESULT_OK, mIntent);
	    super.onBackPressed();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		if(KillthemOpciones.getInstance().accelerometerEnabled())
			wl.release();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		acelerometro.unregister();
	}

	@Override
	protected void onResume() {
		super.onResume();
		acelerometro.register();
		if(KillthemOpciones.getInstance().accelerometerEnabled())
			wl.acquire();
	}
}
