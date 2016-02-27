package es.july.listaadaptador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class ListaActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		
		Adaptador adaptador = new Adaptador(this);
		ListView lstOpciones = (ListView) findViewById(R.id.ListaOpciones);
		lstOpciones.setAdapter(adaptador);
		
		lstOpciones.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position, long id ){
				switch(position){
					case 0:
						File dir =new File(Environment.getExternalStorageDirectory()+"/.CONT/");
						
						if (!dir.exists()){
							System.out.println("Creando directorio: CONT");
							dir.mkdir();
						}
						try{
							Context c = getApplicationContext();
							InputStream ins = c.getResources().openRawResource(R.raw.termo_k);							
							byte [] buffer = new byte[ins.available()];
							ins.read(buffer);
							ins.close();
							
							String filename =Environment.getExternalStorageDirectory().toString()+"/.CONT/termo_k.jpg";
							FileOutputStream fos = new FileOutputStream(filename);
							fos.write(buffer);
							fos.close();
							
						}catch(IOException e){e.printStackTrace();}
						
						File open = new File (Environment.getExternalStorageDirectory().toString()+"/.CONT/termo_k.jpg");
						
						Intent i1 = new Intent();
						i1.setAction(Intent.ACTION_VIEW);
						i1.setDataAndType(Uri.fromFile(open), "image/*");
						startActivity(i1);

						Log.e("CREADOOOOO","Directorio creado");
						break;
					case 1:
						Intent i = new Intent();
						i.setClass(getApplicationContext(), ConversorActivity.class);
						startActivity(i);
						break;
					case 2:
						Intent i3 = new Intent();
						i3.setClass(getApplicationContext(), ContadorActivity.class);
						startActivity(i3);
						break;
					default:
						Toast.makeText(getApplicationContext(), "pos: "+position+" id: "+id+" View: "+v,
								Toast.LENGTH_SHORT).show();
						break;
				}		
			}
		});
	}
}
