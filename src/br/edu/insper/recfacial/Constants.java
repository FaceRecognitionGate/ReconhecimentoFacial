package br.edu.insper.recfacial;

public final class Constants {

	public static final String PORT_A = "9000:9000";
	public static final String PORT_B = "8000:8000";
	public static final String SHARED_FOLDER = "$HOME/data1:/opt/data";
	public static final String DOCKER_INFO = "bamos/openface /bin/bash";
	public static final String START_CONNECTION = "docker run -p " + PORT_A 
			+ " -p " + PORT_B + " -v " + SHARED_FOLDER + " -it " + DOCKER_INFO;
	
	private Constants(){}

}
