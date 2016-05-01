package splett.mensagem.dao;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import splett.dao.GenericDao;
import splett.mensagem.Mensagem;

@ManagedBean(name = "mensagemDao")
@ApplicationScoped
public class MensagemDaoImpl extends GenericDao<Mensagem> implements MensagemDao{

	private static final long serialVersionUID = 1L;

	public MensagemDaoImpl() {
		super(Mensagem.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Mensagem> listMensagens(int id) {
		EntityManager em = emf.createEntityManager();
		Query q = em
				.createQuery("select m from Mensagem m where m.receptor.id = :id order by m.id DESC");
		q.setParameter("id", id);
		q.setMaxResults(50);
		return q.getResultList();
	}
}
