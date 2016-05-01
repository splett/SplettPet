package splett.postagem.comentario.dao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import splett.dao.GenericDao;
import splett.postagem.comentario.Comentario;

@ManagedBean(name = "comentarioDao")
@ViewScoped
public class ComentarioDaoImpl extends GenericDao<Comentario>implements ComentarioDao{

	private static final long serialVersionUID = 1L;

	public ComentarioDaoImpl() {
		super(Comentario.class);
	}
}