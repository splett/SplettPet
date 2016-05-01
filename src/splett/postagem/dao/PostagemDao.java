package splett.postagem.dao;

import java.util.List;

import splett.dao.Dao;
import splett.postagem.Postagem;
import splett.postagem.comentario.Comentario;

public interface PostagemDao extends Dao<Postagem>{
	
    public List<Postagem> listarPostagens(Integer id);

    public List<Comentario> listarComentariosPostagem(Integer id);
}