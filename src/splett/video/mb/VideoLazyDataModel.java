package splett.video.mb;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import splett.perfil.mb.PerfilMB;
import splett.usuario.Usuario;
import splett.video.Video;
import splett.video.dao.VideoDao;

@ManagedBean(name = "videoLazyDataModel")
@ViewScoped
public class VideoLazyDataModel extends LazyDataModel<Video> {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{videoDao}")
    private VideoDao videoDao;

    @ManagedProperty(value = "#{perfilMB}")
    private PerfilMB perfilMB;
    
    @Override
    public List<Video> load(int first, int pageSize, String sortField, SortOrder sortOrder,
	    Map<String, Object> filters) {
	List<Video> source = null;
	Usuario u = new Usuario();

	u = perfilMB.getUsuarioVisualizado();
	
	
	if(perfilMB.isFriend() || perfilMB.isManagementAllowed()){

		source = videoDao.listAllVideos(u.getId());
	}else{
		source = videoDao.listVideosPublicos(u.getId());
	}


	// sort
	if (sortField != null) {
	    Collections.sort(source, new LazyVideoSorter(sortField, sortOrder));
	}

	// rowCount
	this.setRowCount(videoDao.getRowCount());

	return source;
    }

    @Override
    public Video getRowData(String rowKey) {
	return videoDao.findById(Integer.parseInt(rowKey));
    }

    @Override
    public Object getRowKey(Video video) {
	return video.getId();
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    public VideoDao getVideoDao() {
	return videoDao;
    }

    public void setVideoDao(VideoDao videoDao) {
	this.videoDao = videoDao;
    }

    public PerfilMB getPerfilMB() {
	return perfilMB;
    }

    public void setPerfilMB(PerfilMB perfilMB) {
	this.perfilMB = perfilMB;
    }

}
