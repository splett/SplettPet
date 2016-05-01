package splett.usuarioDisponibilidade.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import splett.dao.GenericDao;
import splett.usuario.Usuario;
import splett.usuarioDisponibilidade.UsuarioDisponibilidade;

@ManagedBean(name = "usuarioDisponibilidadeDao")
@ApplicationScoped
public class UsuarioDisponibilidadeDaoImpl extends GenericDao<UsuarioDisponibilidade>
	implements UsuarioDisponibilidadeDao {

    private static final long serialVersionUID = 1L;

    public UsuarioDisponibilidadeDaoImpl() {
	super(UsuarioDisponibilidade.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UsuarioDisponibilidade> list(Usuario usuario) {
	EntityManager em = emf.createEntityManager();
	Date dataHoje;
	Calendar c = Calendar.getInstance();
	dataHoje = c.getTime();
	Query q = em.createQuery("select ud from UsuarioDisponibilidade ud inner join ud.disponibilidade d where ud.usuario.id = :id and d.data > :dataHoje order by d.data");
	q.setParameter("id", usuario.getId());
	q.setParameter("dataHoje", dataHoje);
	return q.getResultList();
    }
}
