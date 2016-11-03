package br.edu.insper.recfacial.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import br.edu.insper.recfacial.utils.Docker;
import br.edu.insper.recfacial.utils.DockerAlreadyConnectedException;
import br.edu.insper.recfacial.utils.Pessoa;

/**
 * Servlet implementation class ProcessImages
 */
@WebServlet("/ProcessImages")
public class ProcessImages extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ProcessImages() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		JSONObject jObj = null;
		try {
			jObj = new JSONObject(request.getParameter("mydata"));
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // this parses the json
		Iterator it = jObj.keys(); //gets all the keys
		while(it.hasNext()) {
		    String nome = (String) it.next(); // get key
		    ArrayList<Blob> fotos = null;
			try {
				fotos = (ArrayList<Blob>) jObj.get(nome);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // get value
		    Pessoa pessoa = new Pessoa(nome, fotos);
		}
	}

}
