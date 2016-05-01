package splett.animal.tipo.dao;

import java.util.List;

import splett.animal.raca.Raca;
import splett.animal.tipo.TipoAnimal;
import splett.dao.Dao;

public interface TipoAnimalDao extends Dao<TipoAnimal> {

	public List<TipoAnimal> pesquisarPorNome(String nome);
	public List<Raca> listRacas(int id);
	public List<TipoAnimal> listSolicitacoes();
	public List<TipoAnimal> list();
}
