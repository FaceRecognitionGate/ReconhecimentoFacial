package br.edu.insper.recfacial.utils;

import net.lingala.zip4j.core.ZipFile;

public class Zip {

	public void unZipFile(String zipFileName) {
		// Unzips files from given zipinputstream
		String fullPath = Constants.ZIP_DIR + zipFileName + ".zip"; // TODO: use constants
		String savePath = Constants.ALIGNED_PICTURES_DIR_NODOCKER + "/" + zipFileName;
		try {
			ZipFile zipFile = new ZipFile(fullPath);
			zipFile.extractAll(savePath);
		} catch (net.lingala.zip4j.exception.ZipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
