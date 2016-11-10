package br.edu.insper.recfacial.utils;

import java.io.File;

import net.lingala.zip4j.core.ZipFile;

public class Zip {

	public void unZipFile(String zipFileName) {
		// Unzips files from given zipinputstream
		String fullPath = Constants.ZIP_DIR + zipFileName + ".zip";
		// Create user directory
		String savePath = Constants.RAW_PICTURES_DIR_NODOCKER + "/" + zipFileName;
		boolean dir = mkdir(savePath);
		if (dir) {
			try {
				ZipFile zipFile = new ZipFile(fullPath);
				zipFile.extractAll(savePath);
			} catch (net.lingala.zip4j.exception.ZipException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private boolean mkdir(String dirName) {
		File file = new File(dirName);
		return file.mkdir();
	}

}
