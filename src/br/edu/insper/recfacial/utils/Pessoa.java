package br.edu.insper.recfacial.utils;

import java.sql.Blob;
import java.util.ArrayList;

public class Pessoa {
	
	private String name;
	private ArrayList<Blob> fotos;
	
	public Pessoa(String name,ArrayList<Blob> fotos){
		this.name = name;
		this.fotos = fotos;	
	}

}
