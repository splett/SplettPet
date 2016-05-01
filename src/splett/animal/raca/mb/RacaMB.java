package splett.animal.raca.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import splett.amizade.Status;
import splett.animal.raca.Raca;
import splett.animal.raca.dao.RacaDao;
import splett.animal.tipo.TipoAnimal;

@ManagedBean(name = "racaMB")
@ViewScoped
public class RacaMB {

	@ManagedProperty(value = "#{racaDao}")
	private RacaDao racaDao;

	@ManagedProperty(value = "#{racaLazyDataModel}")
	private RacaLazyDataModel racaLazyDataModel;

	@ManagedProperty(value = "#{solicitacaoRacaLazyDataModel}")
	private SolicitacaoRacaLazyDataModel solicitacaoRacaLazyDataModel;

	private List<Raca> racasFiltered;

	private Raca raca;

	public RacaMB() {
		racasFiltered = new ArrayList<Raca>();
	}

	public void criar() {
		raca = new Raca();
		raca.setTipoAnimal(new TipoAnimal());
	}

	public void salvar() {
		if (raca.getId() != null)
			racaDao.update(raca);
		else
			racaDao.salvar(raca);

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Sucesso!", "Raça salva com sucesso!");
		FacesContext.getCurrentInstance().addMessage("Sucesso!", message);

	}

	public void aceitar() {
		raca.setStatus(Status.ACEITO);
		racaDao.update(raca);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Sucesso!", "Solicitação aceita com sucesso!");
		FacesContext.getCurrentInstance().addMessage("Sucesso!", message);
	}

	public void remover() {
		racaDao.remover(raca);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Sucesso!", "Raça removida com sucesso!");
		FacesContext.getCurrentInstance().addMessage("Sucesso!", message);
	}

	public void cancelar() {
		raca = null;
	}

	public RacaDao getRacaDao() {
		return racaDao;
	}

	public SolicitacaoRacaLazyDataModel getSolicitacaoRacaLazyDataModel() {
		return solicitacaoRacaLazyDataModel;
	}

	public void setSolicitacaoRacaLazyDataModel(
			SolicitacaoRacaLazyDataModel solicitacaoRacaLazyDataModel) {
		this.solicitacaoRacaLazyDataModel = solicitacaoRacaLazyDataModel;
	}

	public void setRacaDao(RacaDao racaDao) {
		this.racaDao = racaDao;
	}

	public RacaLazyDataModel getRacaLazyDataModel() {
		return racaLazyDataModel;
	}

	public void setRacaLazyDataModel(RacaLazyDataModel racaLazyDataModel) {
		this.racaLazyDataModel = racaLazyDataModel;
	}

	public List<Raca> getRacasFiltered() {
		return racasFiltered;
	}

	public void setRacasFiltered(List<Raca> racasFiltered) {
		this.racasFiltered = racasFiltered;
	}

	public Raca getRaca() {
		return raca;
	}

	public void setRaca(Raca raca) {
		this.raca = raca;
	}

}
