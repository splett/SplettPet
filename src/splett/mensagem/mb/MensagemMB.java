package splett.mensagem.mb;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import splett.mensagem.Mensagem;
import splett.mensagem.dao.MensagemDao;
import splett.perfil.mb.PerfilMB;
import splett.session.SessionMB;
import splett.usuario.Usuario;

@ManagedBean(name = "mensagemMB")
@ViewScoped
public class MensagemMB {

	@ManagedProperty(value = "#{mensagemDao}")
	private MensagemDao mensagemDao;

	@ManagedProperty(value = "#{mensagemLazyDataModel}")
	private MensagemLazyDataModel mensagemLazyDataModel;

	@ManagedProperty(value = "#{sessionMB}")
	private SessionMB sessionMB;

	@ManagedProperty(value = "#{perfilMB}")
	private PerfilMB perfilMB;

	private Mensagem mensagem;
	private Mensagem resposta;

	public void criar() {
		mensagem = new Mensagem();
	}

	public void criarResposta() {
		resposta = new Mensagem();
	}

	public void salvar() {

		mensagem.setEmissor(new Usuario());
		mensagem.setReceptor(new Usuario());
		mensagem.setEmissor(sessionMB.getUsuarioLogado());
		mensagem.setReceptor(perfilMB.getUsuarioVisualizado());
		mensagem.setData(new Date());
		mensagem.setHora(new Date());
		mensagemDao.salvar(mensagem);
		mensagem = null;
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Sucesso!", "Mensagem enviada com sucesso!");
		FacesContext.getCurrentInstance().addMessage("Sucesso!", message);
	}

	public Mensagem getResposta() {
		return resposta;
	}

	public void setResposta(Mensagem resposta) {
		this.resposta = resposta;
	}

	public void responder() {

		resposta.setEmissor(new Usuario());
		resposta.setReceptor(new Usuario());
		resposta.setEmissor(perfilMB.getUsuarioVisualizado());
		resposta.setReceptor(mensagem.getEmissor());
		resposta.setData(new Date());
		resposta.setHora(new Date());
		mensagemDao.salvar(resposta);
		resposta = null;
	}

	public PerfilMB getPerfilMB() {
		return perfilMB;
	}

	public void setPerfilMB(PerfilMB perfilMB) {
		this.perfilMB = perfilMB;
	}

	public void remover() {
		mensagemDao.remover(mensagem);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Sucesso!", "Mensagem removida com sucesso!");
		FacesContext.getCurrentInstance().addMessage("Sucesso!", message);
	}

	public void cancelar() {
		mensagem = null;
	}

	public MensagemDao getMensagemDao() {
		return mensagemDao;
	}

	public void setMensagemDao(MensagemDao mensagemDao) {
		this.mensagemDao = mensagemDao;
	}

	public SessionMB getSessionMB() {
		return sessionMB;
	}

	public MensagemLazyDataModel getMensagemLazyDataModel() {
		return mensagemLazyDataModel;
	}

	public void setMensagemLazyDataModel(
			MensagemLazyDataModel mensagemLazyDataModel) {
		this.mensagemLazyDataModel = mensagemLazyDataModel;
	}

	public void setSessionMB(SessionMB sessionMB) {
		this.sessionMB = sessionMB;
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

}
