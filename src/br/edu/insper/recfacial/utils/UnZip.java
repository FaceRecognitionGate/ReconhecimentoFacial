package br.edu.insper.recfacial.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

import net.lingala.zip4j.core.ZipFile;

public class UnZip {

	public static void unzip(String source) throws net.lingala.zip4j.exception.ZipException{
		
		source = Constants.ZIP_DIRECTORY + "/" + source;
	    ZipFile zipFile = new ZipFile(source);
	    zipFile.extractAll(Constants.RAW_PICTURES_DIR_NODOCKER);
	}
}