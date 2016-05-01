package splett.video.dao;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import splett.dao.GenericDao;
import splett.video.Video;

@ManagedBean(name = "videoDao")
@ApplicationScoped
public class VideoDaoImpl extends GenericDao<Video> implements VideoDao {

	private static final long serialVersionUID = 1L;

	public VideoDaoImpl() {
		super(Video.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Video> listVideosPublicos(int id) {
		EntityManager em = emf.createEntityManager();
		Query q = em
				.createQuery("select v from Video v where v.usuario.id = :id and v.publico = true order by v.id DESC");
		q.setParameter("id", id);
		q.setMaxResults(50);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Video> listAllVideos(int id) {
		EntityManager em = emf.createEntityManager();
		Query q = em
				.createQuery("select v from Video v where v.usuario.id = :id order by v.id DESC");
		q.setParameter("id", id);
		q.setMaxResults(50);
		return q.getResultList();
	}
}

