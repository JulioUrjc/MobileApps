package es.july.killthemall;

//import android.annotation.SuppressLint;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.view.MotionEvent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;

public class OpcionesActivity extends Activity {
		//private KillthemOpciones view;
		//private int[] coordenadasDedo1 = {-1, -1};
		//private int[] coordenadasDedo2 = {-1, -1};
		//private double distancia;
		//private double gesto = 0;
	
	   @Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      requestWindowFeature(Window.FEATURE_NO_TITLE);
	      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	      setContentView(R.layout.opciones);
	   
	      CheckBox sonido = (CheckBox) findViewById(R.id.checkBoxSonido);
		   sonido.setOnClickListener(new OnClickListener() {
		   		@Override
		   		public void onClick(View v) {
		   			KillthemOpciones.getInstance().toggleSound();
		   		}
		   });

		   CheckBox vibracion = (CheckBox) findViewById(R.id.checkBoxVibracion);
		   vibracion.setOnClickListener(new OnClickListener() {
		   		@Override
		   		public void onClick(View v) {
		   			KillthemOpciones.getInstance().toggleVibration();
		   		}
		   });
		   
		   CheckBox acelerometro = (CheckBox) findViewById(R.id.checkBoxAccel);
		   acelerometro.setOnClickListener(new OnClickListener() {
		   	@Override
		   	public void onClick(View v) {
		   		KillthemOpciones.getInstance().toggleAcelerometro();
		   	}
		   });

		   sonido.setChecked(KillthemOpciones.getInstance().soundEnabled());
		   vibracion.setChecked(KillthemOpciones.getInstance().vibrationEnabled());	   
		   acelerometro.setChecked(KillthemOpciones.getInstance().accelerometerEnabled());
	   }
	   
	   
		/*protected void onDraw() {
			Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

			if(gesto == 0)
				paint.setColor(Color.RED);
			if(gesto > 0)
				paint.setColor(Color.GREEN);
			if(gesto < 0)
				paint.setColor(Color.BLUE);

			//canvas.drawColor(Color.BLACK);
		}
		
		@SuppressLint("WrongCall")
	    @SuppressWarnings("deprecation")
		public boolean onTouchEvent(MotionEvent event) {
		   	int pointerID = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) 
		   			>> MotionEvent.ACTION_POINTER_ID_SHIFT;

		   	switch(event.getAction()) {
		   	case MotionEvent.ACTION_DOWN:
		   	case MotionEvent.ACTION_POINTER_DOWN:
		   	case MotionEvent.ACTION_POINTER_2_DOWN:
		   	case MotionEvent.ACTION_MOVE:
		   		if(pointerID == 0) {
		   			coordenadasDedo1[0] = (int) event.getX(pointerID);
		   			coordenadasDedo1[1] = (int) event.getY(pointerID);
		   		} else {
		   			coordenadasDedo2[0] = (int) event.getX(pointerID);
		   			coordenadasDedo2[1] = (int) event.getY(pointerID);
		   		}
		   		break;
		   	case MotionEvent.ACTION_POINTER_UP:
		   	case MotionEvent.ACTION_POINTER_2_UP:
		   		if(pointerID == 0) {
		   			coordenadasDedo1[0] = -1;
		   			coordenadasDedo1[1] = -1;
		   		} else {
		   			coordenadasDedo2[0] = -1;
		   			coordenadasDedo2[1] = -1;
		   		}
		   		gesto = 0;
		   		break;
		   	case MotionEvent.ACTION_UP:
		   		coordenadasDedo1[0] = -1;
		   		coordenadasDedo1[1] = -1;
		   		coordenadasDedo2[0] = -1;
		   		coordenadasDedo2[1] = -1;
		   		break;
		   	}

		   	if(event.getPointerCount() == 2) {
		   		int x, y;

		   		switch(event.getAction()) {
		   		case MotionEvent.ACTION_POINTER_DOWN:
		   		case MotionEvent.ACTION_POINTER_2_DOWN:
		   			x = coordenadasDedo2[0] - coordenadasDedo1[0];
		   			y = coordenadasDedo2[1] - coordenadasDedo1[1];
		   			distancia = Math.sqrt(x*x + y*y);
		   			break;
		   		case MotionEvent.ACTION_MOVE:
		   			x = coordenadasDedo2[0] - coordenadasDedo1[0];
		   			y = coordenadasDedo2[1] - coordenadasDedo1[1];
		   			gesto = Math.sqrt(x*x + y*y) - distancia;
		   		}
		   	}

		   	return true;
		}*/
}
