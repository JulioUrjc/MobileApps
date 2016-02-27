package es.july.contador;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	
	private static int cont=0;
	private Button b_reset,b_increment,b_decrement;
	private TextView vistaContador;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b_reset = (Button)findViewById(R.id.b_reset);
        b_increment = (Button)findViewById(R.id.b_incrementar);
        b_decrement = (Button)findViewById(R.id.b_decrementar);
        vistaContador = (TextView) findViewById(R.id.textView1);
        b_reset.setOnClickListener(this);
        b_increment.setOnClickListener(this);
        b_decrement.setOnClickListener(this);
    }
    
    public void incrementar(){
    	cont++ ;
    	String count=""+cont;
    	vistaContador.setText(count);
    }
    
    public void reset (){
    	cont = 0;
    	String count=""+cont;
    	vistaContador.setText(count);
    }
    
    public void decrementar(){
    	if(cont>0)
    		cont-- ;
      	String count=""+cont;
    	vistaContador.setText(count);
    }

	@Override
	public void onClick(View boton) {
		 
		switch (boton.getId()) {
			case R.id.b_incrementar: {
				incrementar ();
				break;
			}
			case R.id.b_decrementar: {
				decrementar ();
				break;
			}
			case R.id.b_reset: {
				reset ();
				break;
			}
		}
	}
}
