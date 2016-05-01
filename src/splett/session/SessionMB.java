package splett.session;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import splett.usuario.TipoUsuario;
import splett.usuario.Usuario;
import splett.usuario.dao.UsuarioDao;

@ManagedBean(name = "sessionMB")
@SessionScoped
public class SessionMB {

	@ManagedProperty(value = "#{usuarioLogado}")
	private Usuario usuarioLogado;

	@ManagedProperty(value = "#{usuarioDao}")
	private UsuarioDao usuarioDao;

	@PostConstruct
	public void init() {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		usuarioLogado = usuarioDao.pesquisarPorEmail(user.getUsername());
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public boolean isLogado() {
		if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
			return false;
		}
		return true;
	}

	public boolean isUserAdm() {
		return usuarioLogado.getTipo().equals(TipoUsuario.ROLE_ADMIN);
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

}
