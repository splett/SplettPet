package splett.avaliacao.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import splett.avaliacao.Avaliacao;
import splett.avaliacao.dao.AvaliacaoDao;
import splett.perfil.mb.PerfilMB;
import splett.session.SessionMB;
import splett.usuario.Usuario;

@ManagedBean(name = "avaliacaoMB")
@ViewScoped
public class AvaliacaoMB {

	@ManagedProperty(value = "#{avaliacaoDao}")
	private AvaliacaoDao avaliacaoDao;

	@ManagedProperty(value = "#{avaliacaoLazyDataModel}")
	private AvaliacaoLazyDataModel avaliacaoLazyDataModel;

	@ManagedProperty(value = "#{sessionMB}")
	private SessionMB sessionMB;

	@ManagedProperty(value = "#{perfilMB}")
	private PerfilMB perfilMB;

	private List<Avaliacao> avaliacoesFiltered;

	private Avaliacao avaliacao;

	public AvaliacaoMB() {
		avaliacoesFiltered = new ArrayList<Avaliacao>();
	}

	public void criar() {
		avaliacao = new Avaliacao();
	}

	public void salvar() {
		if (avaliacao.getId() != null) {
			avaliacaoDao.update(avaliacao);
		} else {
			avaliacao.setAvaliado(new Usuario());
			avaliacao.setAvaliador(new Usuario());
			avaliacao.setAvaliador(sessionMB.getUsuarioLogado());
			avaliacao.setAvaliado(perfilMB.getUsuarioVisualizado());
			avaliacaoDao.salvar(avaliacao);
			avaliacao = null;
		}

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Sucesso!", "Avaliação salva com sucesso!");
		FacesContext.getCurrentInstance().addMessage("Sucesso!", message);
	}

	public PerfilMB getPerfilMB() {
		return perfilMB;
	}

	public void setPerfilMB(PerfilMB perfilMB) {
		this.perfilMB = perfilMB;
	}

	public void remover() {
		avaliacaoDao.remover(avaliacao);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Sucesso!", "Avaliação removida com sucesso!");
		FacesContext.getCurrentInstance().addMessage("Sucesso!", message);
	}

	public void cancelar() {
		avaliacao = null;
	}

	public AvaliacaoDao getAvaliacaoDao() {
		return avaliacaoDao;
	}

	public void setAvaliacaoDao(AvaliacaoDao avaliacaoDao) {
		this.avaliacaoDao = avaliacaoDao;
	}

	public SessionMB getSessionMB() {
		return sessionMB;
	}

	public AvaliacaoLazyDataModel getAvaliacaoLazyDataModel() {
		return avaliacaoLazyDataModel;
	}

	public void setAvaliacaoLazyDataModel(
			AvaliacaoLazyDataModel avaliacaoLazyDataModel) {
		this.avaliacaoLazyDataModel = avaliacaoLazyDataModel;
	}

	public void setSessionMB(SessionMB sessionMB) {
		this.sessionMB = sessionMB;
	}

	public List<Avaliacao> getAvaliacoesFiltered() {
		return avaliacoesFiltered;
	}

	public void setAvaliacoesFiltered(List<Avaliacao> avaliacoesFiltered) {
		this.avaliacoesFiltered = avaliacoesFiltered;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

}
