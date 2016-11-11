package br.edu.insper.recfacial.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Docker {

	private boolean connected = false;

	public Docker() {
		connected = false;
		try {
			this.startConnection();
		} catch (DockerAlreadyConnectedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void exitDocker() throws DockerNotConnectedException {
		// Closes connection to the docker CLI
		executeCommand("exit");
	}

	private String executeCommand(String command) throws DockerNotConnectedException {
		// Executa um dado comando na CLI do docker
		if (connected) {
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
		} else {
			throw new DockerNotConnectedException();
		}
	}


	public void startConnection() throws DockerAlreadyConnectedException {
		// Starts connection to the docker CLI
		if (!connected) {
			connected = true;
			try {
				executeCommand(Constants.START_CONNECTION);
			} catch (DockerNotConnectedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			throw new DockerAlreadyConnectedException();
		}
	}

	public void trainDatabase() throws DockerNotConnectedException {
		// Treina a base de dados à partir das imagens já salvas no docker
		String command = Constants.TRAIN_DATA;
		
		String output = this.executeCommand(command);

		System.out.println(output);
	}

	public void testImage(String path) throws DockerNotConnectedException{
		// Tests if a image at a given path corresponds to a known person
		String command = Constants.TEST_DATA + " " + path + " && exit";

		String output = executeCommand(command);

		System.out.println(output);
	}
	
	public int mkdir(String dirName) {
		// Returns 0 if successful, 1 if not
		if (connected) {
			try {
				String command = "mkdir " + Constants.RAW_PICTURES_DIR_NODOCKER + "/" + dirName;
				String output = executeCommand(command);
				return 0;
			} catch (DockerNotConnectedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 1;
	}


}
