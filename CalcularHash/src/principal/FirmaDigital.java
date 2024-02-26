package principal;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class FirmaDigital {
	public static void main(String[] args) {
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
			keyGen.initialize(1024);
			
			KeyPair par = keyGen.generateKeyPair();
			PrivateKey clavepriv = par.getPrivate();
			PublicKey clavepub = par.getPublic();
			
			Signature dsa = Signature.getInstance("SHAwithDSA");
			dsa.initSign(clavepriv);
			String mensaje = "Este mensaje va a ser firmado";
			System.out.println("Mensaje:"+mensaje);
			dsa.update(mensaje.getBytes());
			
			byte[] firma =dsa.sign();
			System.out.println("Mensaje firmado");
			Signature verificadsa = Signature.getInstance("SHAwithDSA");
			verificadsa.initVerify(clavepub);
			verificadsa.update(mensaje.getBytes());
			boolean check = verificadsa.verify(firma);
			if(check) {
				System.out.println("Firma verificada con clave publica");
			}else {
				System.out.println("Firma no verificada");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
}
