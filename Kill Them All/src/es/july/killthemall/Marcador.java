package es.july.killthemall;

public class Marcador {

	public static final int MAX_PUNT = 15;

	private int puntos;

	public void initMarcador() {
		puntos = 0;
	}

	public Marcador() {
		initMarcador();
	}
	
	public Marcador(int puntos) {
		this.puntos=puntos;
	}

	public int getPuntos() {
		return puntos;
	}

	public void addPuntos() {
		puntos++;
	}

	public boolean acabado() {
		return puntos == MAX_PUNT;
	}
}