package model;

public class Vozilo {
	private int id_vozila;
	private String proizvodjac;
	private String kategorija;
	private int godiste;
	private int kubikaza;
	private int cena;
	
	public Vozilo() {
		super();
	}

	public Vozilo(int id_vozila, String proizvodjac, String kategorija, int godiste, int kubikaza, int cena) {
		super();
		this.id_vozila = id_vozila;
		this.proizvodjac = proizvodjac;
		this.kategorija = kategorija;
		this.godiste = godiste;
		this.kubikaza = kubikaza;
		this.cena = cena;
	}

	public int getId_vozila() {
		return id_vozila;
	}

	public void setId_vozila(int id_vozila) {
		this.id_vozila = id_vozila;
	}

	public String getProizvodjac() {
		return proizvodjac;
	}

	public void setProizvodjac(String proizvodjac) {
		this.proizvodjac = proizvodjac;
	}

	public String getKategorija() {
		return kategorija;
	}

	public void setKategorija(String kategorija) {
		this.kategorija = kategorija;
	}

	public int getGodiste() {
		return godiste;
	}

	public void setGodiste(int godiste) {
		this.godiste = godiste;
	}

	public int getKubikaza() {
		return kubikaza;
	}

	public void setKubikaza(int kubikaza) {
		this.kubikaza = kubikaza;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	@Override
	public String toString() {
		return "Vozilo [id_vozila=" + id_vozila + ", proizvodjac=" + proizvodjac + ", kategorija=" + kategorija
				+ ", godiste=" + godiste + ", kubikaza=" + kubikaza + ", cena=" + cena + "]";
	}
}
