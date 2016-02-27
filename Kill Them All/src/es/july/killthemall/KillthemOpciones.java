package es.july.killthemall;

public class KillthemOpciones {

	private static KillthemOpciones opciones = null;
	private boolean sonido;
	private boolean vibracion;
	private boolean acelerometro;
	
	private KillthemOpciones() {
		sonido = true;
		vibracion = true;
		acelerometro=false;
	}
	
	public static synchronized KillthemOpciones getInstance() {
		if(opciones == null)
			opciones = new KillthemOpciones();
		return opciones;
	}

	public void toggleSound() {
		sonido = !sonido;
	}

	public void toggleVibration() {
		vibracion = !vibracion;
	}

	public boolean soundEnabled() {
		return sonido;
	}

	public boolean vibrationEnabled() {
		return vibracion;
	}
	
	public void toggleAcelerometro() {
		acelerometro = !acelerometro;
	}

	public boolean accelerometerEnabled() {
		return acelerometro;
	}
	
}
