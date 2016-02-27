package es.july.listaadaptador;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ConversorActivity extends Activity {

	// spinner valores para tiempo inactividad y metros
	Spinner spnUnit;
	public ArrayAdapter<CharSequence> unit_adapter;
	DecimalFormat dosdecimales;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// para quitar titulo
		setContentView(R.layout.conversor);

		// Inicializar los Spinner
		spnUnit = (Spinner) findViewById(R.id.spnUnit);
		unit_adapter = ArrayAdapter.createFromResource(this,
				R.array.unit_array, android.R.layout.simple_spinner_item);
		unit_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnUnit.setAdapter(unit_adapter);

	}

	public void back(View v) {
		finish();
	}

	public void calc(View v) {

		EditText zero = (EditText) findViewById(R.id.zero);
		EditText span = (EditText) findViewById(R.id.span);
		TextView resultado = (TextView) findViewById(R.id.resultado);

		// Hacer operaciones sin maximo de tickets
		if ((zero.getText().length() == 0) || (span.getText().length() == 0)) {

			Toast error = Toast.makeText(getApplicationContext(),
					"Por favor rellena todos los campos", Toast.LENGTH_LONG);
			error.show();
		} else {

			Double z = Double.valueOf(zero.getText().toString());
			Double s = Double.valueOf(span.getText().toString());
			Double r = Math.abs((z - s));

			int i = spnUnit.getSelectedItemPosition();
			String u = unit_adapter.getItem(i).toString();


			dosdecimales = new DecimalFormat("###.##");

			String r0 = "    0% |  4mA  | " + dosdecimales.format(z) + u;
			String r25 = "  25% |  8mA  | "
					+ dosdecimales.format(z + (r * 0.25)) + u;
			String r50 = "  50% | 12mA | " + dosdecimales.format(z + (r * 0.5))
					+ u;
			String r75 = "  75% | 16mA | "
					+ dosdecimales.format(z + (r * 0.75)) + u;
			String r100 = "100% | 20mA | " + dosdecimales.format(s) + u;

			resultado.setText(r0 + "\n" + r25 + "\n" + r50 + "\n" + r75 + "\n"
					+ r100);
		}

	}

}
