package es.july.listaarchivos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

	private int ListaFiles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Intent i = new Intent();
		i.setClass(getApplicationContext(), ListaFilesActivity.class);
		startActivityForResult(i, ListaFiles);
	}

}
