package splett.animal.dao;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import splett.animal.Animal;
import splett.dao.GenericDao;
import splett.usuario.Usuario;

@ManagedBean(name = "animalDao")
@ApplicationScoped
public class AnimalDaoImpl extends GenericDao<Animal>implements AnimalDao {
    private static final long serialVersionUID = 1L;

    public AnimalDaoImpl() {
	super(Animal.class);
    }

    @SuppressWarnings("unchecked")
    public List<Animal> pesquisarPorNome(String nome) {
	EntityManager em = emf.createEntityManager();
	Query q = em.createQuery(
		"select a from Animal a where lower(a.nome) like concat('%', :nome, '%')");
	q.setParameter("nome", nome);
	q.setMaxResults(50);
	return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Animal> getAnimaisUsuario(Usuario usuario) {
	EntityManager em = emf.createEntityManager();
	Query q = em.createQuery("select a from Animal a where dono_id = :id");
	q.setParameter("id", usuario.getId());
	q.setMaxResults(50);
	List<Animal> lista = q.getResultList();
	return lista;
    }
}
