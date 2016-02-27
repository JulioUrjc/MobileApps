package es.july.listaadaptador;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adaptador extends ArrayAdapter<Object>{
	
	private static Item[] items= new Item[]{
		new Item ("Termopares Tipo K",
				  "Tabla de equivalencia de los termos",
				  R.drawable.termo),
		new Item ("Calculadora",
				  "Sirve para sumar todos los numeros",
				  R.drawable.hart),	
		new Item ("Titulo 3",
				  "Subtitulo largo 3",
				  R.drawable.cv),
		new Item ("Titulo 4",
				  "Subtitulo largo 4",
				  R.drawable.cv)};
	private Activity context;
	
	public Adaptador (Activity context){
		super(context,R.layout.listitem,items);
		this.context=context;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		LayoutInflater inflater= context.getLayoutInflater();
		View item = inflater.inflate(R.layout.listitem,null);
		
		ImageView lbImagen = (ImageView) item.findViewById(R.id.LbImagen);
		lbImagen.setImageResource(items[position].getImagen());
		
		TextView lbTitulo = (TextView) item.findViewById(R.id.LbTitulo);
		lbTitulo.setText(items[position].getTitulo());
		
		TextView lbSubTitulo = (TextView) item.findViewById(R.id.LbSubTitulo);
		lbSubTitulo.setText(items[position].getSubtitulo());	
		
		return (item);
	}
}
