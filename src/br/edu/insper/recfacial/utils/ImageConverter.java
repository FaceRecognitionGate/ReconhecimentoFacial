package br.edu.insper.recfacial.utils;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

public class ImageConverter {
    /**
      * Take in a BLOB file (specified as an array parameter but we only ever use [0])
      * Read in the binary stream of the BLOB
      * Change the binary stream to jpg
      * Write the binary stream jpg to the BLOB
      * The BLOB parameter is passed in via out - so there is no need to return the BLOB, only edit it
      */
    public static void convertImage(Blob blob, String outputStream) {
       BufferedImage bufferedImage = null;
       File file = new File(Constants.RAW_PICTURES_DIR_NODOCKER + outputStream);
        try {
            bufferedImage = ImageIO.read(blob.getBinaryStream());

            RenderedImage renderedImage = (RenderedImage)bufferedImage;

            ImageIO.write(renderedImage, "JPG", file);

        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch(IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}