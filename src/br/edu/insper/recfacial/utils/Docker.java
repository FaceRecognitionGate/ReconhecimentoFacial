package br.edu.insper.recfacial.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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


	public static void trainDatabase(){
		// Treina a base de dados à partir das imagens já salvas no docker
		String command = Constants.TRAIN_DATA;
		
		String output = Docker.executeCommand(command);

		System.out.println(output);
	}

	public static void testImage(String path){
		// Tests if a image at a given path corresponds to a known person
		String command = Constants.TEST_DATA + " " + path + " && exit";

		String output = executeCommand(command);

		System.out.println(output);
	}
	
	public static int mkdir(String dirName){
		// Returns 0 if successful, 1 if not
			String command = "mkdir " + Constants.RAW_PICTURES_DIR_NODOCKER + "/" + dirName;
			String output = executeCommand(command);
			return 0;
	}


}
