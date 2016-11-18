package br.edu.insper.recfacial.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

public final class Docker {


	private Docker() {
	}


	public static String executeCommand(String command){
		// Executa um dado comando na CLI do docker
		StringBuffer output = new StringBuffer();
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader =
                            new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output.toString();
	}


	public static void trainDatabase() throws ClientProtocolException, IOException{
		HttpClient client = HttpClients.createDefault();
		HttpResponse response = client.execute(new HttpGet("http://of:8888/new"));
	}

	public static int testImage() throws ClientProtocolException, IOException, InterruptedException{
		// Tests if a image at a given path corresponds to a known person
		HttpClient client = HttpClients.createDefault();

		HttpResponse response = client.execute(new HttpGet("http://of:5000/check"));
		Thread.sleep(500);
		
		File file = new File("/opt/data/examples/compare.jpeg");
		
		String line = (String) FileUtils.readLines(file).get(1);
		
		return Math.round(Float.valueOf(line) * 100);
	}
	
	public static int mkdir(String dirName){
		// Returns 0 if successful, 1 if not
			String command = "mkdir " + Constants.RAW_PICTURES_DIR_NODOCKER + "/" + dirName;
			String output = executeCommand(command);
			return 0;
	}


}
