package es.july.listaadaptador;

public class Item {

	private String titulo;
	private String subtitulo;
	private int imagen;
	
	public Item(String titulo, String subtitulo, int imagen) {
		super();
		this.titulo = titulo;
		this.subtitulo = subtitulo;
		this.imagen = imagen;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getSubtitulo() {
		return subtitulo;
	}

	public int getImagen() {
		return imagen;
	}

	
}
