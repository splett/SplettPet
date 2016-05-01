package splett.perfil.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import splett.amizade.dao.AmizadeDao;
import splett.session.SessionMB;
import splett.usuario.Usuario;
import splett.usuario.dao.UsuarioDao;

@ManagedBean(name = "perfilMB")
@SessionScoped
public class PerfilMB {
    @ManagedProperty(value = "#{sessionMB}")
    private SessionMB sessionMB;

    @ManagedProperty(value = "#{usuarioDao}")
    private UsuarioDao usuarioDao;

    @ManagedProperty(value = "#{usuarioVisualizado}")
    private Usuario usuarioVisualizado;

    @ManagedProperty(value = "#{amizadeDao}")
    private AmizadeDao amizadeDao;

    @PostConstruct
    public void init() {
	usuarioVisualizado = sessionMB.getUsuarioLogado();
    }

    public boolean isSelfProfile() {
	return usuarioVisualizado == sessionMB.getUsuarioLogado();
    }

    public boolean isEvaluationAllowed() {
	return !isSelfProfile() || sessionMB.isUserAdm();
    }

    public SessionMB getSessionMB() {
	return sessionMB;
    }

    public void setSessionMB(SessionMB sessionMB) {
	this.sessionMB = sessionMB;
    }

    public UsuarioDao getUsuarioDao() {
	return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
	this.usuarioDao = usuarioDao;
    }

    public boolean isManagementAllowed() {
	return isSelfProfile() || sessionMB.isUserAdm();
    }

    public boolean isFriendshipRequestAllowed() {
	return !isSelfProfile() && !isFriend() && !isFriendshipRequestReceived()
		&& !isFriendshipRequestSent();
    }

    public boolean hasProfilePic() {
	return usuarioVisualizado.getFotoNome() != null
		&& !usuarioVisualizado.getFotoNome().equals("");
    }

    public boolean isFriend() {
	return amizadeDao.isAmigo(usuarioVisualizado, sessionMB.getUsuarioLogado());
    }

    public boolean isFriendshipRequestReceived() {
	return amizadeDao.isSolicitacaoEnviada(sessionMB.getUsuarioLogado(), usuarioVisualizado);
    }

    public boolean isFriendshipRequestSent() {
	return amizadeDao.isSolicitacaoEnviada(usuarioVisualizado, sessionMB.getUsuarioLogado());
    }

    private boolean mostrarInformacaoPrivada() {
	return isSelfProfile() || isFriend() || sessionMB.isUserAdm();
    }

    public boolean mostrarDataNascimento() {
	return usuarioVisualizado.isDataNascimento_isPublico() || mostrarInformacaoPrivada();
    }

    public boolean mostrarEmail() {
	return usuarioVisualizado.isEmail_isPublico() || mostrarInformacaoPrivada();
    }

    public boolean mostrarGenero() {
	return usuarioVisualizado.isGenero_isPublico() || mostrarInformacaoPrivada();
    }

    public boolean mostrarEndereco() {
	return usuarioVisualizado.isEndereco_isPublico() || mostrarInformacaoPrivada();
    }

    public boolean mostrarTelefoneCelular() {
	return usuarioVisualizado.isTelefoneCelular_isPublico() || mostrarInformacaoPrivada();
    }

    public boolean mostrarTelefoneFixo() {
	return usuarioVisualizado.isTelefoneFixo_isPublico() || mostrarInformacaoPrivada();
    }

    public Usuario getUsuarioVisualizado() {
	return usuarioVisualizado;
    }

    public void setUsuarioVisualizado(Usuario usuarioVisualizado) {
	this.usuarioVisualizado = usuarioVisualizado;
    }

    public AmizadeDao getAmizadeDao() {
	return amizadeDao;
    }

    public void setAmizadeDao(AmizadeDao amizadeDao) {
	this.amizadeDao = amizadeDao;
    }

}
