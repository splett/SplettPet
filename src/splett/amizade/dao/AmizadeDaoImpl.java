package splett.amizade.dao;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import splett.amizade.Amizade;
import splett.amizade.Status;
import splett.dao.GenericDao;
import splett.usuario.Usuario;

@ManagedBean(name = "amizadeDao")
@ApplicationScoped
public class AmizadeDaoImpl extends GenericDao<Amizade>implements AmizadeDao {
    private static final long serialVersionUID = 1L;

    public AmizadeDaoImpl() {
	super(Amizade.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Usuario> listAmigos(Usuario usuario) {
	List<Usuario> amigos;
	EntityManager em = emf.createEntityManager();
	Query q = em.createQuery(
		"Select ud from Amizade a inner join a.usuarioDestino ud where ud.id != :id and a.status = :status and a.usuarioOrigem.id = :id");
	q.setParameter("id", usuario.getId());
	q.setParameter("status", Status.ACEITO);
	amigos = q.getResultList();
	List<Usuario> amigosOrigem = listarAmigosOrigem(usuario);
	amigos.addAll(amigosOrigem);
	return amigos;
    }

    @SuppressWarnings("unchecked")
    private List<Usuario> listarAmigosOrigem(Usuario usuario) {
	EntityManager em = emf.createEntityManager();
	Query q = em.createQuery(
		"Select uo from Amizade a inner join a.usuarioOrigem uo where uo.id != :id and a.status = :status and a.usuarioDestino.id = :id");
	q.setParameter("id", usuario.getId());
	q.setParameter("status", Status.ACEITO);
	return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Amizade> listSolicitacoes(Usuario usuario) {
	EntityManager em = emf.createEntityManager();
	Query q = em.createQuery(
		"Select a from Amizade a inner join a.usuarioOrigem uo where uo.id != :id and a.status = :status and a.usuarioDestino.id = :id");
	q.setParameter("id", usuario.getId());
	q.setParameter("status", Status.ESPERA);
	return q.getResultList();
    }

    public boolean isAmigo(Usuario u1, Usuario u2) {
	List<Usuario> amigosU1 = listAmigos(u1);

	for (Usuario usuario : amigosU1) {
	    if (usuario.equals(u2))
		return true;
	}

	return false;
    }

    public Amizade getAmizade(Usuario u1, Usuario u2) {
	Amizade amizade;
	EntityManager em = emf.createEntityManager();
	Query q = em.createQuery(
		"Select a from Amizade a where (usuarioOrigem.id = :id1 and usuarioDestino.id = :id2) or (usuarioOrigem.id = :id2 and usuarioDestino.id = :id1)");
	q.setParameter("id1", u1.getId());
	q.setParameter("id2", u2.getId());
	try {
	    amizade = (Amizade) q.getSingleResult();
	} catch (NoResultException e) {
	    return null;
	}
	return amizade;
    }

    public boolean isSolicitado(Usuario u1, Usuario u2) {
	List<Amizade> solicitacoesU1 = listSolicitacoes(u1);

	for (Amizade amizade : solicitacoesU1) {
	    if (amizade.getUsuarioDestino().equals(u2) || amizade.getUsuarioOrigem().equals(u2))
		return true;
	}

	return false;
    }

    public boolean isSolicitacaoEnviada(Usuario receptor, Usuario emissor) {
	List<Amizade> solicitacoesUsuarioReceptor = listSolicitacoes(receptor);

	for (Amizade amizade : solicitacoesUsuarioReceptor) {
	    if (amizade.getUsuarioOrigem().equals(emissor))
		return true;
	}

	return false;
    }
}
