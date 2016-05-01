package splett.avaliacao.dao;

import java.util.List;

import splett.avaliacao.Avaliacao;
import splett.dao.Dao;

public interface AvaliacaoDao extends Dao<Avaliacao>{
	
	public List<Avaliacao> listAvaliacoes(int id);

}
