package splett.disponibilidade.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import splett.dao.GenericDao;
import splett.disponibilidade.Disponibilidade;
import splett.usuario.Usuario;
import splett.usuarioDisponibilidade.UsuarioDisponibilidade;
import splett.usuarioDisponibilidade.dao.UsuarioDisponibilidadeDao;

@ManagedBean(name = "disponibilidadeDao")
@ApplicationScoped
public class DisponibilidadeDaoImpl extends GenericDao<Disponibilidade>
	implements DisponibilidadeDao {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{usuarioDisponibilidadeDao}")
    private UsuarioDisponibilidadeDao usuarioDisponibilidadeDao;

    public DisponibilidadeDaoImpl() {
	super(Disponibilidade.class);
    }

    public Disponibilidade findByData(Date data) {
	EntityManager em = emf.createEntityManager();
	Query q = em.createQuery("select d from Disponibilidade d where d.data = :data order by d.data asc");
	q.setParameter("data", data);

	Disponibilidade retorno;
	try {
	    retorno = (Disponibilidade) q.getSingleResult();
	} catch (NoResultException e) {
	    return null;
	}
	return retorno;
    }

    public List<Disponibilidade> list(Usuario usuario) {
	List<Disponibilidade> disponibilidades = new ArrayList<>();
	for (UsuarioDisponibilidade usuarioDisponibilidade : usuarioDisponibilidadeDao
		.list(usuario)) {
	    disponibilidades.add(usuarioDisponibilidade.getDisponibilidade());
	}

	return disponibilidades;
    }
    
    public boolean verificarExistenciaDisponibilidade(Usuario usuario, Date data){
    	EntityManager em = emf.createEntityManager();
    	Query q = em.createQuery("select ud from UsuarioDisponibilidade ud inner join ud.disponibilidade d where d.data = :data");
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	String dataString = df.format(data);
    	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    	try {
			q.setParameter("data", sdf1.parse(dataString));
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	if(q.getResultList().isEmpty()){
    		return true;
    	}
    	else{
    		return false;
    	}
    }

    public UsuarioDisponibilidadeDao getUsuarioDisponibilidadeDao() {
        return usuarioDisponibilidadeDao;
    }

    public void setUsuarioDisponibilidadeDao(UsuarioDisponibilidadeDao usuarioDisponibilidadeDao) {
        this.usuarioDisponibilidadeDao = usuarioDisponibilidadeDao;
    }
}
