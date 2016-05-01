package splett.amizade.mb;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import splett.amizade.Amizade;
import splett.amizade.Status;
import splett.amizade.dao.AmizadeDao;
import splett.perfil.mb.PerfilMB;
import splett.session.SessionMB;
import splett.usuario.Usuario;
import splett.usuario.dao.UsuarioDao;

@ManagedBean(name = "amizadeMB")
@ViewScoped
public class AmizadeMB {
    @ManagedProperty(value = "#{sessionMB}")
    private SessionMB sessionMB;

    @ManagedProperty(value = "#{perfilMB}")
    private PerfilMB perfilMB;

    @ManagedProperty(value = "#{amizadeDao}")
    private AmizadeDao amizadeDao;

    @ManagedProperty(value = "#{usuarioDao}")
    private UsuarioDao usuarioDao;

    private List<Usuario> amigosUsuarioVisualizado;

    private List<Amizade> solicitacoesAmizadeUsuarioVisualizado;

    private Usuario usuarioSolicitacao;

    private Usuario usuarioSelecionado;

    public void addFriend() {
	Amizade amizade = new Amizade();

	amizade.setUsuarioOrigem(sessionMB.getUsuarioLogado());
	amizade.setUsuarioDestino(perfilMB.getUsuarioVisualizado());

	amizadeDao.salvar(amizade);

	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!",
		"Solicitação enviada com sucesso!");
	FacesContext.getCurrentInstance().addMessage("Sucesso!", message);
    }

    public void aceitarSolicitacao() {
	for (Amizade amizade : solicitacoesAmizadeUsuarioVisualizado) {
	    if (usuarioSolicitacao.equals(amizade.getUsuarioOrigem())) {
		amizade.setStatus(Status.ACEITO);
		salvar(amizade);
		break;
	    }
	}
    }

    public void recusarSolicitacao() {
	for (Amizade amizade : solicitacoesAmizadeUsuarioVisualizado) {
	    if (usuarioSolicitacao.equals(amizade.getUsuarioOrigem())) {
		amizade.setStatus(Status.RECUSADO);
		salvar(amizade);
		break;
	    }
	}
    }

    public void desfazerAmizade() {
	Amizade amizade = amizadeDao.getAmizade(perfilMB.getUsuarioVisualizado(),
		usuarioSelecionado);
	amizade.setStatus(Status.RECUSADO);
	salvar(amizade);
    }

    public void salvar(Amizade amizade) {
	if (amizade.getId() != null) {
	    amizadeDao.update(amizade);
	} else {
	    amizadeDao.salvar(amizade);
	}
    }

    public AmizadeDao getAmizadeDao() {
	return amizadeDao;
    }

    public void setAmizadeDao(AmizadeDao amizadeDao) {
	this.amizadeDao = amizadeDao;
    }

    public SessionMB getSessionMB() {
	return sessionMB;
    }

    public void setSessionMB(SessionMB sessionMB) {
	this.sessionMB = sessionMB;
    }

    public PerfilMB getPerfilMB() {
	return perfilMB;
    }

    public void setPerfilMB(PerfilMB perfilMB) {
	this.perfilMB = perfilMB;
    }

    public UsuarioDao getUsuarioDao() {
	return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
	this.usuarioDao = usuarioDao;
    }

    public List<Usuario> getAmigosUsuarioVisualizado() {
	amigosUsuarioVisualizado = amizadeDao.listAmigos(perfilMB.getUsuarioVisualizado());
	return amigosUsuarioVisualizado;
    }

    public void setAmigosUsuarioVisualizado(List<Usuario> amigosUsuarioVisualizado) {
	this.amigosUsuarioVisualizado = amigosUsuarioVisualizado;
    }

    public List<Amizade> getSolicitacoesAmizadeUsuarioVisualizado() {
	solicitacoesAmizadeUsuarioVisualizado = amizadeDao
		.listSolicitacoes(perfilMB.getUsuarioVisualizado());
	return solicitacoesAmizadeUsuarioVisualizado;
    }

    public void setSolicitacoesAmizadeUsuarioVisualizado(
	    List<Amizade> solicitacoesAmizadeUsuarioVisualizado) {
	this.solicitacoesAmizadeUsuarioVisualizado = solicitacoesAmizadeUsuarioVisualizado;
    }

    public Usuario getUsuarioSolicitacao() {
	return usuarioSolicitacao;
    }

    public void setUsuarioSolicitacao(Usuario usuarioSolicitacao) {
	this.usuarioSolicitacao = usuarioSolicitacao;
    }

    public Usuario getUsuarioSelecionado() {
	return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
	this.usuarioSelecionado = usuarioSelecionado;
    }
}
