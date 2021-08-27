package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.Vozilo;

@WebServlet("/Vozila")
public class VoziloControler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public VoziloControler() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DAO dao = new DAO();
		String ids = request.getParameter("id");
		try {
			
		
		int id = Integer.parseInt(ids);

		String action = request.getParameter("action");
		
		if (action != null && action.trim().length() > 0) {
			switch (action) {
			case "Delete":
				dao.deleteVozilaBrID(id);
				request.setAttribute("msg", "Uspesan delete");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				break;
			case "Edit":
				request.setAttribute("voziloEdit", dao.selectVoziloByID(id));
				request.getRequestDispatcher("index.jsp").forward(request, response);
				break;
			default:
				break;
			}
		} else {
			request.setAttribute("msg", "Poruka: Pogresna akcija");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		} catch (Exception e) {
			request.setAttribute("msg", "Poruka: Pogresan ID");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action != null && action.trim().length() > 0) {
			switch (action) {
			case "Insert":

				String proizvodjac = request.getParameter("proizvodjac");
				String kategorija = request.getParameter("kategorija");
				String godisteS = request.getParameter("godiste");
				String kubikazaS = request.getParameter("kubikaza");
				String cenaS = request.getParameter("cena");

				if (proizvodjac != null && proizvodjac.trim().length() > 0 && kategorija != null
						&& kategorija.trim().length() > 0 && godisteS != null && godisteS.trim().length() > 0
						&& kubikazaS != null && kubikazaS.trim().length() > 0 && cenaS != null
						&& cenaS.trim().length() > 0) {

					try {
						int godiste = Integer.parseInt(godisteS);
						int kubikaza = Integer.parseInt(kubikazaS);
						int cena = Integer.parseInt(cenaS);

						Vozilo v = new Vozilo(0, proizvodjac, kategorija, godiste, kubikaza, cena);
						DAO dao = new DAO();
						dao.insertVozilaUBazu(v);
						request.setAttribute("msg", "Uspesan unos vozila u bazu");
						request.getRequestDispatcher("index.jsp").forward(request, response);

					} catch (Exception e) {
						request.setAttribute("msg", "Godiste, cena i kubikaza moraju biti brojevi");
						request.getRequestDispatcher("index.jsp").forward(request, response);
					}

				} else {
					request.setAttribute("msg", "Morate popuniti sva polja");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				break;
			case "Edit":
				proizvodjac = request.getParameter("proizvodjac");
				kategorija = request.getParameter("kategorija");
				godisteS = request.getParameter("godiste");
				kubikazaS = request.getParameter("kubikaza");
				cenaS = request.getParameter("cena");
				String idS = request.getParameter("id");

				if (proizvodjac != null && proizvodjac.trim().length() > 0 && kategorija != null
						&& kategorija.trim().length() > 0 && godisteS != null && godisteS.trim().length() > 0
						&& kubikazaS != null && kubikazaS.trim().length() > 0 && cenaS != null
						&& cenaS.trim().length() > 0) {

					try {
						int godiste = Integer.parseInt(godisteS);
						int kubikaza = Integer.parseInt(kubikazaS);
						int cena = Integer.parseInt(cenaS);
						int id = Integer.parseInt(idS);
						Vozilo v = new Vozilo(id, proizvodjac, kategorija, godiste, kubikaza, cena);

						DAO dao = new DAO();
						dao.updateVozilo(v, id);
						request.setAttribute("msg", "Uspesno ste editovali vozilo");
						request.getRequestDispatcher("index.jsp").forward(request, response);

					} catch (Exception e) {
						request.setAttribute("msg", "Godiste, cena i kubikaza moraju biti brojevi");
						request.getRequestDispatcher("index.jsp").forward(request, response);
					}
				} else {
					request.setAttribute("msg", "Morate popuniti sva polja");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
			}
		}
	}

}
