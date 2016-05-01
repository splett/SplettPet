package splett.foto.dao;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import splett.dao.GenericDao;
import splett.foto.Foto;

@ManagedBean(name = "fotoDao")
@ApplicationScoped
public class FotoDaoImpl extends GenericDao<Foto> implements FotoDao {

	private static final long serialVersionUID = 1L;

	public FotoDaoImpl() {
		super(Foto.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Foto> listFotosPublicas(int id) {
		EntityManager em = emf.createEntityManager();
		Query q = em
				.createQuery("select f from Foto f where f.usuario.id = :id and f.publico = true order by f.id DESC");
		q.setParameter("id", id);
		q.setMaxResults(50);
		return q.getResultList();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Foto> listAllFotos(int id) {
		EntityManager em = emf.createEntityManager();
		Query q = em
				.createQuery("select f from Foto f where f.usuario.id = :id order by f.id DESC");
		q.setParameter("id", id);
		q.setMaxResults(50);
		return q.getResultList();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
