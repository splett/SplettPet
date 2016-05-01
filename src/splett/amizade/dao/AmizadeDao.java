package splett.amizade.dao;

import java.util.List;

import splett.amizade.Amizade;
import splett.dao.Dao;
import splett.usuario.Usuario;

public interface AmizadeDao extends Dao<Amizade> {

	public List<Usuario> listAmigos(Usuario usuario);

	public List<Amizade> listSolicitacoes(Usuario usuario);

	public boolean isAmigo(Usuario u1, Usuario u2);
	
	public boolean isSolicitado(Usuario u1, Usuario u2);
	
	public boolean isSolicitacaoEnviada(Usuario receptor, Usuario emissor);
	
	public Amizade getAmizade(Usuario u1, Usuario u2);
}
