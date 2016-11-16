package br.edu.insper.recfacial.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class GetPhoto  {

	public static void main(String[] args) throws ClientProtocolException, IOException{

		File myFile = new File("compare.jpeg");
	
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
	}
}


