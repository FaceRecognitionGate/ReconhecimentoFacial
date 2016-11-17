package br.edu.insper.recfacial.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import br.edu.insper.recfacial.utils.Docker;

/**
 * Servlet implementation class Start
 */
@WebServlet("/Start")
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final int RANGE = 75;
	
	public Start(){
		
	}
       
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response1.getWriter().append("Served at: ").append(request.getContextPath());
			while (true){
				String path = "/opt/data/examples/compare.jpeg";
				
				File myFile = new File(path);
				
				CloseableHttpClient client = HttpClients.createDefault();
				try (CloseableHttpResponse response = client.execute(
						new HttpGet("http://10.91.18.15:8080/FRG/photo"))) {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
				        try (FileOutputStream outstream = new FileOutputStream(myFile)) {
				           entity.writeTo(outstream);
				       }
				   }
				}
				try {
					if (Docker.testImage() > RANGE){
						HttpClient client2 = HttpClients.createDefault();
						HttpResponse response = client2.execute(new HttpGet("http://10.91.18.15:8080/Servo_Catraca/open"));
						 Thread.sleep(15000);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
	
