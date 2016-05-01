package splett.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Funcoes {

    public static String preencheAEsquerda(String valor, char caracter, int tamanho) {
	return String.format("%" + String.valueOf(caracter) + String.valueOf(tamanho) + "d", valor);
    }

    public static String formataDataEmString(Date data) {
	SimpleDateFormat out;
	try {
	    out = new SimpleDateFormat("dd/MM/yyyy");
	    return out.format(data);
	} catch (Exception e) {
	    return "Erro ao formatar a Data";
	}
    }

    public static String formataDataHoraEmString(Date data) {
	SimpleDateFormat out;
	try {
	    out = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    return out.format(data);
	} catch (Exception e) {
	    return "Erro ao formatar a data e hora";
	}
    }

    public static String formataHoraEmString(Date data) {
	SimpleDateFormat out;
	try {
	    out = new SimpleDateFormat("HH:mm:ss");
	    return out.format(data);
	} catch (Exception e) {
	    return "Erro ao formatar a Hora";
	}
    }

    /**
     * Converte uma String para um objeto Date. Caso a String seja vazia ou
     * nula, retorna null - para facilitar em casos onde formulários podem ter
     * campos de datas vazios.
     *
     * @param data
     *            String no formato dd/MM/yyyy a ser formatada
     * @return Date Objeto Date ou null caso receba uma String vazia ou nula
     * @throws Exception
     *             Caso a String esteja no formato errado
     */
    public static Date formataStringEmData(String data) {
	if (data == null || data.equals("")) {
	    return null;
	}
	Date date = null;
	try {
	    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	    date = (java.util.Date) formatter.parse(data);
	} catch (Exception e) {}
	return date;
    }

    public static String concatenaAspas(String valor) {
	return "";
    }

    public static String retornaSistemaOperacional() {
	return System.getProperties().get("os.name").toString();
    }

    public static String removeCaracteresEspeciais(String texto) {
	/*
	 * Remove alguns caracteres especiais da string
	 */
	texto = texto.replaceAll("\\.?", "");
	texto = texto.replaceAll("-?", "");
	texto = texto.replaceAll("\\(?", "");
	texto = texto.replaceAll("\\)?", "");
	texto = texto.replaceAll("\\_?", "");
	texto = texto.replaceAll(" ", "");
	return texto;
    }

    public static boolean ehAlfa(String texto) {
	return texto.matches("[a-zA-Z]*");
    }

    public static Map<String, String> listaCamposComValores(Class<?> classe,
	    Map<String, String> listaParametro) {
	Map<String, String> listaCampos = new HashMap<String, String>();
	try {
	    Field[] campos = classe.getDeclaredFields();
	    Method[] metodos = classe.getDeclaredMethods();
	    for (Field f : campos) {
		f.getAnnotations();
		for (Method m : metodos) {
		    m.setAccessible(true);
		}
	    }
	} catch (Exception ex) {
	    System.out.println("Erro ocorrido: " + ex);
	}
	return listaCampos;
    }

    public static String recuperaNomeUsuario(String email) {

	String nomeUsuario = null;

	if (email != null && !email.isEmpty()) {
	    int posArroba = email.indexOf("@");
	    nomeUsuario = email.substring(0, posArroba).toLowerCase();
	}

	return nomeUsuario;
    }

    public static String primeiroNome(String nome) {
	return nome.trim().split(" ")[0];
    }

}
