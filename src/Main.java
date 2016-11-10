import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import br.edu.insper.recfacial.utils.Constants;
import br.edu.insper.recfacial.utils.TestProcessImages;

public class Main {
	public static void main (String[] args) throws SerialException, SQLException, JSONException, ClientProtocolException, IOException {
		TestProcessImages teste = new TestProcessImages("/home/marcelo/Documents/Insper/4_Semestre/Tecnologias_Web/fotos/raw/mc_carol", "teste");
		teste.postJson(teste.getJson());
	}
}
