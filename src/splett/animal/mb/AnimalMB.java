package splett.animal.mb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import splett.animal.Animal;
import splett.animal.dao.AnimalDao;
import splett.animal.raca.Raca;
import splett.animal.tipo.TipoAnimal;
import splett.animal.tipo.dao.TipoAnimalDao;
import splett.perfil.mb.PerfilMB;

@ManagedBean(name = "animalMB")
@ViewScoped
public class AnimalMB {

	@ManagedProperty(value = "#{animalDao}")
	private AnimalDao animalDao;

	@ManagedProperty(value = "#{tipoAnimalDao}")
	private TipoAnimalDao tipoAnimalDao;

	@ManagedProperty(value = "#{animalLazyDataModel}")
	private AnimalLazyDataModel animalLazyDataModel;

	@ManagedProperty(value = "#{perfilMB}")
	private PerfilMB perfilMB;

	private List<Animal> animaisFiltered;

	private List<Animal> animaisUsuarioVisualizado;

	private Animal animal;

	private List<Raca> racas;

	private UploadedFile fileUp;

	private String destino = "c:\\temp\\";

	public AnimalMB() {
		animaisFiltered = new ArrayList<Animal>();
	}

	public void criar() {
		racas = new ArrayList<Raca>();
		animal = new Animal();
		Raca r = new Raca();
		r.setTipoAnimal(new TipoAnimal());
		animal.setRaca(r);
	}

	public void transferirArquivo(String caminho, String nome, InputStream in) {
		try {
			File file = new File(caminho);
			file.mkdir();
			File f = new File(caminho + nome);

			f.createNewFile();
			OutputStream out = new FileOutputStream(f);
			int reader = 0;
			byte[] bytes = new byte[(int) getFileUp().getSize()];

			while ((reader = in.read(bytes)) != -1) {
				out.write(bytes, 0, reader);
			}
			in.close();
			out.flush();
			out.close();
		} catch (IOException exception) {

		}
	}

	public void doUpload() {
		Date data = new Date();
		String nome = data.getTime() + fileUp.getFileName();
		if (fileUp != null) {

			try {
				transferirArquivo(destino, nome, getFileUp().getInputstream());
			} catch (IOException exception) {

			}

			animal.setFotoCaminho(destino + nome);
			animal.setFotoContentType(fileUp.getContentType());
			animal.setFotoNome(nome);
			salvar();
		} else {
			salvar();
		}
	}

	public void salvar() {
		if (animal.getDono() == null)
			animal.setDono(perfilMB.getUsuarioVisualizado());
		if (animal.getId() != null)
			animalDao.update(animal);
		else
			animalDao.salvar(animal);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!",
			    "Animal salvo com sucesso!");
		FacesContext.getCurrentInstance().addMessage("Sucesso!", message);
	}

	public void listarRacas() {
		racas = tipoAnimalDao.listRacas(this.animal.getRaca().getTipoAnimal()
				.getId());
	}

	public void remover() {
		animalDao.remover(animal);
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!",
			    "Animal removido com sucesso!");
		FacesContext.getCurrentInstance().addMessage("Sucesso!", message);
	}

	public AnimalLazyDataModel getAnimalLazyDataModel() {
		return animalLazyDataModel;
	}

	public void setAnimalLazyDataModel(AnimalLazyDataModel animalLazyDataModel) {
		this.animalLazyDataModel = animalLazyDataModel;
	}

	public UploadedFile getFileUp() {
		return fileUp;
	}

	public void setFileUp(UploadedFile fileUp) {
		this.fileUp = fileUp;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public void cancelar() {
		animal = null;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public List<Animal> getAnimaisFiltered() {
		return animaisFiltered;
	}

	public void setAnimaisFiltered(List<Animal> animaisFiltered) {
		this.animaisFiltered = animaisFiltered;
	}

	public AnimalDao getAnimalDao() {
		return animalDao;
	}

	public void setAnimalDao(AnimalDao animalDao) {
		this.animalDao = animalDao;
	}

	public TipoAnimalDao getTipoAnimalDao() {
		return tipoAnimalDao;
	}

	public void setTipoAnimalDao(TipoAnimalDao tipoAnimalDao) {
		this.tipoAnimalDao = tipoAnimalDao;
	}

	public List<Raca> getRacas() {
		return racas;
	}

	public void setRacas(List<Raca> racas) {
		this.racas = racas;
	}

	public List<Animal> getAnimaisUsuarioVisualizado() {
		animaisUsuarioVisualizado = animalDao.getAnimaisUsuario(perfilMB
				.getUsuarioVisualizado());
		return animaisUsuarioVisualizado;
	}

	public void setAnimaisUsuarioVisualizado(
			List<Animal> animaisUsuarioVisualizado) {
		this.animaisUsuarioVisualizado = animaisUsuarioVisualizado;
	}

	public PerfilMB getPerfilMB() {
		return perfilMB;
	}

	public void setPerfilMB(PerfilMB perfilMB) {
		this.perfilMB = perfilMB;
	}

}
