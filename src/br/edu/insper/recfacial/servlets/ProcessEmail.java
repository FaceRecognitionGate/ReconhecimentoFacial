package br.edu.insper.recfacial.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import br.edu.insper.recfacial.utils.Docker;
import br.edu.insper.recfacial.utils.DockerAlreadyConnectedException;


/**
 * Servlet implementation class ProcessImages
 */
@WebServlet("/ProcessEmail")
public class ProcessEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ProcessEmail() {
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
		Object email = null;
		String email_received = request.getParameter((String) email);
		
        HttpClient client = HttpClients.createDefault();
        HttpGet request_http = new HttpGet("https://catracainsper.mybluemix.net/getImages?email=" + email_received);
        
        HttpResponse resp = client.execute(request_http);
		
        if( resp.getStatusLine().getStatusCode() == 200) {
            InputStreamReader stream = new InputStreamReader(resp.getEntity().getContent());
            BufferedReader br = new BufferedReader(stream);
            String line;
            while ( (line = br.readLine()) != null ) {
                System.out.println(line);
            }
        }
	}
	
	

}
