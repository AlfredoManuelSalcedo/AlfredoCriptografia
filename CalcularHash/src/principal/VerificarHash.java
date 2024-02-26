package principal;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.MessageDigest;

public class VerificarHash {

	public static void main(String[] args) {
		try {
			FileInputStream fileout = new FileInputStream("DATOS.DAT");
			ObjectInputStream dataOS= new ObjectInputStream(fileout);
			Object o = dataOS.readObject();
			//PRIMERA LECTURA; SE OBTIENE EL STRING
			String datos = (String)o;
			System.out.println("Datos: "+ datos);
			//SEGUDNA LECTURA, SE OBTIENE EL RESUMEN
			o=dataOS.readObject();
			byte resumenOriginal[]=(byte[])o;
			
			MessageDigest md = MessageDigest.getInstance("SHA");
			//SE CALCULA EL RESUMEN DEL STRING LEIDO DEL FICHERO
			md.update(datos.getBytes());//TEXTO A RESUMIR
			byte resumenActual[]=md.digest();//SE CALCULA EL RESUMEN
			//SE COMPRUEBAN SI LOS 2 RESUMENES SON IGUALES
			if(MessageDigest.isEqual(resumenActual, resumenOriginal)) {
				System.out.println("\n DATOS VALIDO");
			}else {
				System.out.println("\n DATOS NO VALIDOS");
			}
			dataOS.close();
			fileout.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
