package splett.animal.tipo.dao;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import splett.amizade.Status;
import splett.animal.raca.Raca;
import splett.animal.tipo.TipoAnimal;
import splett.dao.GenericDao;

@ManagedBean(name = "tipoAnimalDao")
@ApplicationScoped
public class TipoAnimalDaoImpl extends GenericDao<TipoAnimal> implements
		TipoAnimalDao {
	private static final long serialVersionUID = 1L;

	public TipoAnimalDaoImpl() {
		super(TipoAnimal.class);
	}

	@SuppressWarnings("unchecked")
	public List<TipoAnimal> pesquisarPorNome(String nome) {
		EntityManager em = emf.createEntityManager();
		Query q = em
				.createQuery("select t from TipoAnimal t where lower(t.nome) like concat('%', :nome, '%') and t.status = "
						+ Status.class.getName() + ".ACEITO");
		q.setParameter("nome", nome);
		q.setMaxResults(50);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Raca> listRacas(int id) {
		EntityManager em = emf.createEntityManager();
		Query q = em
				.createQuery("select r from Raca r where r.tipoAnimal.id = :id and r.status  = "
						+ Status.class.getName() + ".ACEITO");
		q.setParameter("id", id);
		q.setMaxResults(50);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoAnimal> listSolicitacoes() {
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("select t from TipoAnimal t where t.status = "
				+ Status.class.getName() + ".ESPERA");
		q.setMaxResults(50);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoAnimal> list() {
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("select t from TipoAnimal t where t.status = "
				+ Status.class.getName() + ".ACEITO");
		q.setMaxResults(50);
		return q.getResultList();
	}
}
