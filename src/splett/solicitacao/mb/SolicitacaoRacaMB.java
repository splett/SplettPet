package splett.solicitacao.mb;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import splett.amizade.Status;
import splett.animal.raca.Raca;
import splett.animal.raca.dao.RacaDao;

@ManagedBean(name = "solicitacaoRacaMB")
@ViewScoped
public class SolicitacaoRacaMB {

	@ManagedProperty(value = "#{racaDao}")
	private RacaDao racaDao;

	private Raca raca;

	public void criar() {
		raca = new Raca();
	}

	public void salvar() {
		raca.setStatus(Status.ESPERA);
		racaDao.salvar(raca);
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Sucesso!", "Solicitação salva com sucesso!");
		FacesContext.getCurrentInstance().addMessage("Sucesso!", message);
	}

	public void cancelar() {
		raca = null;
	}

	public RacaDao getRacaDao() {
		return racaDao;
	}

	public void setRacaDao(RacaDao racaDao) {
		this.racaDao = racaDao;
	}

	public Raca getRaca() {
		return raca;
	}

	public void setRaca(Raca raca) {
		this.raca = raca;
	}
	
	

}
