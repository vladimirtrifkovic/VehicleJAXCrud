package model;

//vazni importi 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.ArrayList;
public class DAO {
   private DataSource ds;

//DEFINICIJA KONEKCIONIH STRINGOVA
	private static String SELECTVOZILO = "SELECT * FROM vozila";
	private static String INSERTVOZILO = "INSERT INTO `vozila`( `proizvodjac`, `kategorija`, `godiste`, `kubikaza`, `cena`) VALUES (?, ?, ?, ?, ?)";
	private static String DELETEVOZILO = "DELETE FROM `vozila` WHERE id_vozila = ?";
	private static String EDITVOZILA = "UPDATE `vozila` SET `proizvodjac`= ?,`kategorija`= ?,`godiste`= ?,`kubikaza`= ?,`cena`= ? WHERE id_vozila = ?";
	private static String SELECTVOZILAID = "SELECT * FROM `vozila` WHERE id_vozila = ?";
	
	// DEFINICIJA KONSTRUKTORA ZA PODESAVNJE KONEKCIJE – UVEK ISTO
	public DAO(){
	try {
		InitialContext cxt = new InitialContext();
		if ( cxt == null ) { 
		} 
		ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/mysql" ); 
		if ( ds == null ) { 
		} 		
		} catch (NamingException e) {
		}
	}
	// DEFINICIJA METODE 
	public ArrayList<Vozilo> selecSvihVozila(){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
		ArrayList<Vozilo> la = new ArrayList<Vozilo>();
		Vozilo v = null;
				
         try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECTVOZILO);
			pstm.execute();

//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			while(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				
				int id_vozila = rs.getInt("id_vozila");
				String proizvodjac = rs.getString("proizvodjac");
				String kategorija = rs.getString("kategorija");
				int godiste = rs.getInt("godiste");
				int kubikaza = rs.getInt("kubikaza");
				int cena = rs.getInt("cena");
				 v = new Vozilo(id_vozila, proizvodjac, kategorija, godiste, kubikaza, cena);
				la.add(v);
			}
//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return la; 
	}
	
	
	public void insertVozilaUBazu(Vozilo v){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
				
         try {
			con = ds.getConnection();
			pstm = con.prepareStatement(INSERTVOZILO);
			pstm.setString(1, v.getProizvodjac());
			pstm.setString(2, v.getKategorija());
			pstm.setInt(3, v.getGodiste());
			pstm.setInt(4, v.getKubikaza());
			pstm.setInt(5, v.getCena());
			pstm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteVozilaBrID(int id){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
				
         try {
			con = ds.getConnection();
			pstm = con.prepareStatement(DELETEVOZILO);
			pstm.setInt(1, id);
			pstm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateVozilo(Vozilo v, int id){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
				
         try {
			con = ds.getConnection();
			pstm = con.prepareStatement(EDITVOZILA);
			pstm.setString(1, v.getProizvodjac());
			pstm.setString(2, v.getKategorija());
			pstm.setInt(3, v.getGodiste());
			pstm.setInt(4, v.getKubikaza());
			pstm.setInt(5, v.getCena());
			pstm.setInt(6, id);
			pstm.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Vozilo  selectVoziloByID(int id){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU 
	
		Vozilo v = null;
				
         try {
			con = ds.getConnection();
			pstm = con.prepareStatement(SELECTVOZILAID);
			pstm.setInt(1, id);
			pstm.execute();

//****POCETAK AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
			rs = pstm.getResultSet();

			while(rs.next()){ // if UMESTO while AKO UPIT VRACA JEDAN REZULTAT
				
				
				String proizvodjac = rs.getString("proizvodjac");
				String kategorija = rs.getString("kategorija");
				int godiste = rs.getInt("godiste");
				int kubikaza = rs.getInt("kubikaza");
				int cena = rs.getInt("cena");
				 v = new Vozilo(id, proizvodjac, kategorija, godiste, kubikaza, cena);
				
			}
//****KRAJ OBRADE ResultSet-a	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// VRACANJE REZULTATA AKO METODA VRACA REZULTAT
		return v;
	}
	
}

