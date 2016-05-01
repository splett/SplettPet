package splett.video.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import splett.perfil.mb.PerfilMB;
import splett.usuario.Usuario;
import splett.video.Video;
import splett.video.dao.VideoDao;

@ManagedBean(name = "videoMB")
@ViewScoped
public class VideoMB {

	@ManagedProperty(value = "#{videoDao}")
	private VideoDao videoDao;

	@ManagedProperty(value = "#{videoLazyDataModel}")
	private VideoLazyDataModel videoLazyDataModel;

	@ManagedProperty(value = "#{perfilMB}")
	private PerfilMB perfilMB;

	private List<Video> videosFiltered;

	private Video video;

	public VideoMB() {
		videosFiltered = new ArrayList<Video>();
	}

	public void criar() {
		video = new Video();
	}

	public void salvar() {
		String caminho = video.getCaminho().replace("/watch?v=", "/embed/");
		video.setCaminho(caminho);

		if (video.getId() != null) {
			videoDao.update(video);
		} else {
			video.setUsuario(new Usuario());
			video.setUsuario(perfilMB.getUsuarioVisualizado());
			videoDao.salvar(video);
		}
		video = null;
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Sucesso!", "Video salvo com sucesso!");
		FacesContext.getCurrentInstance().addMessage("Sucesso!", message);
	}

	public void remover() {
		videoDao.remover(video);
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Sucesso!", "Video removido com sucesso!");
		FacesContext.getCurrentInstance().addMessage("Sucesso!", message);
	}

	public void cancelar() {
		video = null;
	}

	public VideoDao getVideoDao() {
		return videoDao;
	}

	public void setVideoDao(VideoDao videoDao) {
		this.videoDao = videoDao;
	}

	public VideoLazyDataModel getVideoLazyDataModel() {
		return videoLazyDataModel;
	}

	public void setVideoLazyDataModel(VideoLazyDataModel videoLazyDataModel) {
		this.videoLazyDataModel = videoLazyDataModel;
	}

	public List<Video> getVideosFiltered() {
		return videosFiltered;
	}

	public void setVideosFiltered(List<Video> videosFiltered) {
		this.videosFiltered = videosFiltered;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public PerfilMB getPerfilMB() {
		return perfilMB;
	}

	public void setPerfilMB(PerfilMB perfilMB) {
		this.perfilMB = perfilMB;
	}

}
