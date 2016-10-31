package br.edu.insper.recfacial;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	private void runDocker(){

		String command = "docker run -p 9000:9000 -p 8000:8000 -t -i bamos/openface /bin/bash";

		String output = this.executeCommand(command);

		System.out.println(output);
	}

	private void trainDatabase(){

		this.runDocker();

		String command = "cd /root/openface && for N in {1..8}; do ./util/align-dlib.py <path-to-raw-data> "
				+ "align outerEyesAndNose <path-to-aligned-data> --size 96 & done && ./batch-represent/main.lua "
				+ "-outDir <feature-directory> -data <path-to-aligned-data> && ./demos/classifier.py train <feature-directory> "
				+ "&& exit";

		String output = this.executeCommand(command);

		System.out.println(output);
	}

	private void testImage(String path){

		this.runDocker();

		String command = "./demos/classifier.py infer /opt/data/features/classifier.pkl " + path + " && exit";

		String output = this.executeCommand(command);

		System.out.println(output);
	}


	private String executeCommand(String command) {

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
}
