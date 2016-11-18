package br.edu.insper.recfacial.servlets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import br.edu.insper.recfacial.utils.Docker;

public class Task extends Thread implements HttpSessionBindingListener {

	private static final int RANGE = 75;

	@Override
	public void run() {
		while (true) {
			System.out.println("Hello World");
			String path = "/opt/data/examples/compare.jpeg";
			File myFile = new File(path);
			CloseableHttpClient client = HttpClients.createDefault();
			try (CloseableHttpResponse response = client.execute(
					new HttpGet("http://10.91.18.15:8080/FRG/photo"))) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					try (FileOutputStream outstream = new FileOutputStream(myFile)) {
					   entity.writeTo(outstream);
					} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
				}
				if (Docker.testImage() > RANGE) {
					HttpClient client2 = HttpClients.createDefault();
					HttpResponse response2 = client2.execute(new HttpGet("http://10.91.18.15:8080/Servo_Catraca/open"));
					Thread.sleep(15000);
				}
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		start();
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		interrupt();
	}

}
