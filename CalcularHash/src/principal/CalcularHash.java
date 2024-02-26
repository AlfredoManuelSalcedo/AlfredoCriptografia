package principal;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CalcularHash {

	public static void main(String[] args) {
		try {
			FileOutputStream fileout = new FileOutputStream("DATOS.DAT");
			ObjectOutputStream dataOS = new ObjectOutputStream(fileout);
			MessageDigest md = MessageDigest.getInstance("SHA");
			String datos = "Episodio IV: Una nueva esperanza.\n"
					+"Hace mucho tiempo en una galaxia muy, muy lejana...\n"
					+"Nos encontramos en  un periodo deguerra civil. Las naves espaciales rebeldes, \n"
					+"atacando desde una base oculta, han logrado su primera victoria contra el \n"
					+"malvado imperio Galaáctico\n";
			byte dataBytes[]=datos.getBytes();
			md.update(dataBytes); //TEXTO A RESUMIR
			byte resumen[]= md.digest();//SE CALCULA EL RESUMEN
			dataOS.writeObject(datos);//SE ESCRIBEN LOS DATOS
			dataOS.writeObject(resumen);//SE ESCRIBE EL RESUMEN
			dataOS.close();
			fileout.close();
			System.out.println("Datos:"+ datos);
			System.out.println("\nResumen creado con exito");
		}catch(IOException e) {
			e.printStackTrace();
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

	}

}
