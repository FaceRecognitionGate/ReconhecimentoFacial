package br.edu.insper.recfacial.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Docker {

	private boolean connected = false;

	public Docker() {
		connected = false;
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

	private void trainDatabase() throws DockerNotConnectedException {
		// Treina a base de dados à partir das imagens já salvas no docker


		String command = "cd /root/openface && for N in {1..8}; do ./util/align-dlib.py <path-to-raw-data> "
				+ "align outerEyesAndNose <path-to-aligned-data> --size 96 & done && ./batch-represent/main.lua "
				+ "-outDir <feature-directory> -data <path-to-aligned-data> && ./demos/classifier.py train <feature-directory> "
				+ "&& exit";

		String output = this.executeCommand(command);

		System.out.println(output);
	}

}
