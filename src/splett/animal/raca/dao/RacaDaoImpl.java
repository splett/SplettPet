package splett.animal.raca.dao;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import splett.amizade.Status;
import splett.animal.raca.Raca;
import splett.dao.GenericDao;

@ManagedBean(name = "racaDao")
@ApplicationScoped
public class RacaDaoImpl extends GenericDao<Raca> implements RacaDao {
	private static final long serialVersionUID = 1L;

	public RacaDaoImpl() {
		super(Raca.class);
	}

	@SuppressWarnings("unchecked")
	public List<Raca> pesquisarPorNome(String nome) {
		EntityManager em = emf.createEntityManager();
		Query q = em
				.createQuery("select u from Raca u where lower(u.nome) like concat('%', :nome, '%') and u.status = "
						+ Status.class.getName() + ".ACEITO");
		q.setParameter("nome", nome);
		q.setMaxResults(50);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Raca> listSolicitacoes() {
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("select r from Raca r where r.status = "
				+ Status.class.getName() + ".ESPERA");
		q.setMaxResults(50);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Raca> list() {
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("select r from Raca r where r.status  = "
				+ Status.class.getName() + ".ACEITO");
		q.setMaxResults(50);
		return q.getResultList();
	}
}
