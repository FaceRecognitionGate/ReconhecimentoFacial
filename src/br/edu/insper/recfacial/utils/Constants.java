package br.edu.insper.recfacial.utils;

public final class Constants {

	// Diretórios
	public static final String SHARED_DIR = "/opt";
	public static final String RAW_PICTURES_DIR = SHARED_DIR + "/raw";
	public static final String RAW_PICTURES_DIR_NODOCKER = "/opt/data/raw";
	public static final String ZIP_DIRECTORY = "/opt/data/zip";
	public static final String ALIGNED_PICTURES_DIR = SHARED_DIR + "/aligned";
	public static final String FEATURES_DIR = SHARED_DIR + "/features";
	public static final String ROOT = " /root";
	public static final String OPENFACE_DIR = ROOT + "/openface";
	public static final String ALIGNED_PICTURES_DIR_NODOCKER = "opt/data/aligned";
	public static final String ZIP_DIR = "/opt/data/zip";

	public static final String PORT_A = "9000:9000";
	public static final String PORT_B = "8000:8000";
	public static final String SHARED_FOLDER = "/opt/data:/opt/data";
	public static final String DOCKER_INFO = "bamos/openface /bin/bash";
	public static final String START_CONNECTION = "docker run -p " + PORT_A
			+ " -p " + PORT_B + " -v " + SHARED_FOLDER + " -it " + DOCKER_INFO;
	public static final String CD_OPENFACE = "cd " + OPENFACE_DIR;
	public static final String ALIGN_DATA = " align outerEyesAndNose";
	
	// Comandos já concatenados
	public static final String ALIGN_COMMAND = "for N in {1..8}; "
			+ "do " + OPENFACE_DIR + "/util/align-dlib.py" + RAW_PICTURES_DIR + ALIGN_DATA
			+ ALIGNED_PICTURES_DIR + " --size 96 & done";
	public static final String FEATURES_COMMAND = OPENFACE_DIR + " /batch-represent/main.lua "
			+ "-outDir" + FEATURES_DIR + " -data" + ALIGNED_PICTURES_DIR;
	public static final String TRAIN_COMMAND = "/demos/classifier.py train"
			+ FEATURES_DIR;
	public static final String TRAIN_DATA = ALIGN_COMMAND + " && "
			+ FEATURES_COMMAND + " && " + TRAIN_COMMAND + " && exit";
	public static final String TEST_DATA = OPENFACE_DIR + "/demos/classifier.py infer /opt/data/features/classifier.pkl";
	
	private Constants(){}

}
