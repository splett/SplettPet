package splett.animal.raca.dao;

import java.util.List;

import splett.animal.raca.Raca;
import splett.dao.Dao;

public interface RacaDao extends Dao<Raca> {

	public List<Raca> pesquisarPorNome(String nome);
	public List<Raca> listSolicitacoes();
	public List<Raca> list();
}
