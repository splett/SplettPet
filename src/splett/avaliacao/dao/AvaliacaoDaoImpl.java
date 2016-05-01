package splett.avaliacao.dao;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import splett.avaliacao.Avaliacao;
import splett.dao.GenericDao;

@ManagedBean(name = "avaliacaoDao")
@ApplicationScoped
public class AvaliacaoDaoImpl extends GenericDao<Avaliacao> implements
		AvaliacaoDao {

	private static final long serialVersionUID = 1L;

	public AvaliacaoDaoImpl() {
		super(Avaliacao.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Avaliacao> listAvaliacoes(int id) {
		EntityManager em = emf.createEntityManager();
		Query q = em
				.createQuery("select a from Avaliacao a where a.avaliado.id = :id order by a.id DESC");
		q.setParameter("id", id);
		q.setMaxResults(50);
		return q.getResultList();
	}

}
