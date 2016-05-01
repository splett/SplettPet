package splett.mensagem.dao;

import java.util.List;

import splett.dao.Dao;
import splett.mensagem.Mensagem;

public interface MensagemDao extends Dao<Mensagem> {
	
	public List<Mensagem> listMensagens(int id);

}
