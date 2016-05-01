package splett.criptografia;

import java.io.Serializable;
import java.security.MessageDigest;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="criptografia")
@ViewScoped
public class Criptografia implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	public String criptografar(String senha) {
		String retorno = "";
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(senha.getBytes());
			retorno = Conversion.byteArrayToHexString(md.digest());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return retorno;
	}
}
