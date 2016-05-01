package splett.solicitacao.mb;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import splett.amizade.Status;
import splett.animal.tipo.TipoAnimal;
import splett.animal.tipo.dao.TipoAnimalDao;

@ManagedBean(name = "solicitacaoTipoMB")
@ViewScoped
public class SolicitacaoTipoMB {

	@ManagedProperty(value = "#{tipoAnimalDao}")
	private TipoAnimalDao tipoAnimalDao;

	private TipoAnimal tipoAnimal;

	public void criar() {
		tipoAnimal = new TipoAnimal();
	}

	public void salvar() {
		tipoAnimal.setStatus(Status.ESPERA);
		tipoAnimalDao.salvar(tipoAnimal);

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Sucesso!", "Solicitação salva com sucesso!");
		FacesContext.getCurrentInstance().addMessage("Sucesso!", message);
	}

	public void cancelar() {
		tipoAnimal = null;
	}

	public TipoAnimalDao getTipoAnimalDao() {
		return tipoAnimalDao;
	}

	public void setTipoAnimalDao(TipoAnimalDao tipoAnimalDao) {
		this.tipoAnimalDao = tipoAnimalDao;
	}

	public TipoAnimal getTipoAnimal() {
		return tipoAnimal;
	}

	public void setTipoAnimal(TipoAnimal tipoAnimal) {
		this.tipoAnimal = tipoAnimal;
	}
}
