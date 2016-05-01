package splett.mensagem.mb;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import splett.mensagem.Mensagem;
import splett.mensagem.dao.MensagemDao;
import splett.perfil.mb.PerfilMB;
import splett.usuario.Usuario;

@ManagedBean(name = "mensagemLazyDataModel")
@ViewScoped
public class MensagemLazyDataModel extends LazyDataModel<Mensagem> {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{mensagemDao}")
    private MensagemDao mensagemDao;

    @ManagedProperty(value = "#{perfilMB}")
    private PerfilMB perfilMB;

    @Override
    public List<Mensagem> load(int first, int pageSize, String sortField, SortOrder sortOrder,
	    Map<String, Object> filters) {
	List<Mensagem> source = null;

	Usuario u = new Usuario();
	u = perfilMB.getUsuarioVisualizado();

	source = mensagemDao.listMensagens(u.getId());

	// rowCount
	this.setRowCount(mensagemDao.getRowCount());

	return source;
    }

    @Override
    public Mensagem getRowData(String rowKey) {
	return mensagemDao.findById(Integer.parseInt(rowKey));
    }

    @Override
    public Object getRowKey(Mensagem mensagem) {
	return mensagem.getId();
    }

    public MensagemDao getMensagemDao() {
	return mensagemDao;
    }

    public void setMensagemDao(MensagemDao mensagemDao) {
	this.mensagemDao = mensagemDao;
    }

    public PerfilMB getPerfilMB() {
	return perfilMB;
    }

    public void setPerfilMB(PerfilMB perfilMB) {
	this.perfilMB = perfilMB;
    }

}
