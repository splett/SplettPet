package splett.foto.dao;

import java.util.List;

import splett.dao.Dao;
import splett.foto.Foto;

public interface FotoDao extends Dao<Foto>{

	public List<Foto> listFotosPublicas(int id);
	public List<Foto> listAllFotos(int id);

}
