package br.edu.insper.recfacial.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

public class TestProcessImages {
	private ArrayList<Blob> fotos;
	private String nome;
	private JSONObject json;
	
	public TestProcessImages(String path, String nome) throws SerialException, SQLException, JSONException, IOException{
		this.nome = nome;
		
		fotos = new ArrayList<Blob>();
		
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		    	FileInputStream fileinput = new FileInputStream(file); 
	    	    byte[] bytearray = IOUtils.toByteArray(fileinput);
	    	    fileinput.read(bytearray);
	    	    fileinput.close();
	    	    Blob blob = new javax.sql.rowset.serial.SerialBlob(bytearray);
	    	    System.out.println(blob.toString());
	    	    fotos.add(blob);
		    }
		}
		json = new JSONObject();
		json.put(nome,fotos);
	}
	
	public void postJson(JSONObject json) throws ClientProtocolException, IOException{
		
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://localhost:8080/ReconhecimentoFacial/ProcessImages");
		
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("mydata", json.toString()));
		
        httppost.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse resp = httpclient.execute(httppost);
	}

	public JSONObject getJson() {
		return json;
	}
	
	
}
