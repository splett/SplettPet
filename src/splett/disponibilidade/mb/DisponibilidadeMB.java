package splett.disponibilidade.mb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import splett.disponibilidade.Disponibilidade;
import splett.disponibilidade.dao.DisponibilidadeDao;
import splett.perfil.mb.PerfilMB;
import splett.session.SessionMB;
import splett.usuario.Usuario;
import splett.usuarioDisponibilidade.UsuarioDisponibilidade;
import splett.usuarioDisponibilidade.dao.UsuarioDisponibilidadeDao;

@ManagedBean(name = "disponibilidadeMB")
@ViewScoped
public class DisponibilidadeMB {

	@ManagedProperty(value = "#{disponibilidadeDao}")
	private DisponibilidadeDao disponibilidadeDao;

	@ManagedProperty(value = "#{usuarioDisponibilidadeDao}")
	private UsuarioDisponibilidadeDao usuarioDisponibilidadeDao;

	@ManagedProperty(value = "#{sessionMB}")
	private SessionMB sessionMB;

	@ManagedProperty(value = "#{perfilMB}")
	private PerfilMB perfilMB;

	private List<Disponibilidade> periodoDisponivel;

	private List<Disponibilidade> disponibilidadesUsuarioVisualizado;

	private String datasDesabilitar;

	private Date dataInicio;

	private Date dataFinal;

	private Date dataHoje;
	
	private Disponibilidade disponibilidade;

	@PostConstruct
	public void init() {
		disponibilidadesUsuarioVisualizado = new ArrayList<>();

	}

	public void criarPeriodoDisponivel() {
		periodoDisponivel = new ArrayList<>();
	}
	
	public String remover(){
		disponibilidadeDao.remover(disponibilidade);
		return "perfil";
	}

	public void cancelar() {
		periodoDisponivel = null;
		disponibilidade = null;
	}

	public void getDatasPeriodo() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dataInicio);
		for (Date dt = dataInicio; dt.compareTo(dataFinal) <= 0;) {
			periodoDisponivel.add(getDisponibilidadeData(dt));
			cal.add(Calendar.DATE, +1);
			dt = cal.getTime();
		}
	}

	private Disponibilidade getDisponibilidadeData(Date data) {
		Disponibilidade d;
		return ((d = (disponibilidadeDao.findByData(data))) != null) ? d
				: criarDisponibilidade(data);
	}

	private Disponibilidade criarDisponibilidade(Date data) {
		Disponibilidade nova = new Disponibilidade();
		nova.setData(data);
		salvarDisponibilidade(nova);

		return nova;
	}

	public String salvarPeriodo() {
		Usuario usuario = perfilMB.getUsuarioVisualizado();
		getDatasPeriodo();
		for (Disponibilidade disponibilidade : periodoDisponivel) {
			UsuarioDisponibilidade usuarioDisponibilidade = new UsuarioDisponibilidade();
			if (disponibilidadeDao.verificarExistenciaDisponibilidade(usuario, disponibilidade.getData()))
			{
				usuarioDisponibilidade.setDisponibilidade(disponibilidade);
				usuarioDisponibilidade.setUsuario(usuario);
				salvarUsuarioDisponibilidade(usuarioDisponibilidade);
			}
		}
		return "perfil";
	}
	
	public String deletarPeriodo(){
		Usuario usuario = perfilMB.getUsuarioVisualizado();
		getDatasPeriodo();
		for (Disponibilidade disponibilidade : periodoDisponivel) {
			UsuarioDisponibilidade usuarioDisponibilidade = new UsuarioDisponibilidade();
			usuarioDisponibilidade.setDisponibilidade(disponibilidade);
			usuarioDisponibilidade.setUsuario(usuario);
			disponibilidadeDao.remover(disponibilidade);
		}
		return "perfil";
	}

	public void salvarDisponibilidade(Disponibilidade disponibilidade) {
		if (disponibilidade.getId() != null)
			disponibilidadeDao.update(disponibilidade);
		else
			disponibilidadeDao.salvar(disponibilidade);
	}

	public void salvarUsuarioDisponibilidade(
			UsuarioDisponibilidade usuarioDisponibilidade) {
		if (usuarioDisponibilidade.getId() != null)
			usuarioDisponibilidadeDao.update(usuarioDisponibilidade);
		else
			usuarioDisponibilidadeDao.salvar(usuarioDisponibilidade);
	}

	public DisponibilidadeDao getDisponibilidadeDao() {
		return disponibilidadeDao;
	}

	public void setDisponibilidadeDao(DisponibilidadeDao disponibilidadeDao) {
		this.disponibilidadeDao = disponibilidadeDao;
	}

	public UsuarioDisponibilidadeDao getUsuarioDisponibilidadeDao() {
		return usuarioDisponibilidadeDao;
	}

	public void setUsuarioDisponibilidadeDao(
			UsuarioDisponibilidadeDao usuarioDisponibilidadeDao) {
		this.usuarioDisponibilidadeDao = usuarioDisponibilidadeDao;
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

	public List<Disponibilidade> getPeriodoDisponivel() {
		return periodoDisponivel;
	}

	public void setPeriodoDisponivel(List<Disponibilidade> periodoDisponivel) {
		this.periodoDisponivel = periodoDisponivel;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public List<Disponibilidade> getDisponibilidadesUsuarioVisualizado() {
		disponibilidadesUsuarioVisualizado = disponibilidadeDao.list(perfilMB
				.getUsuarioVisualizado());
		return disponibilidadesUsuarioVisualizado;
	}

	public void setDisponibilidadesUsuarioVisualizado(
			List<Disponibilidade> disponibilidadesUsuarioVisualizado) {
		this.disponibilidadesUsuarioVisualizado = disponibilidadesUsuarioVisualizado;
	}

	public Date getDataHoje() {
		Calendar c = Calendar.getInstance();
		dataHoje = c.getTime();
		return dataHoje;
	}

	public void setDataHoje(Date dataHoje) {
		this.dataHoje = dataHoje;
	}

	public String getDatasDesabilitar() {
		disponibilidadesUsuarioVisualizado = disponibilidadeDao.list(perfilMB
				.getUsuarioVisualizado());
		DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
		StringBuffer sb = new StringBuffer();
		sb.append("[");	
		for (int i = 0; i < disponibilidadesUsuarioVisualizado.size(); i++) {
			sb.append("\'").append(df.format(disponibilidadesUsuarioVisualizado.get(i).getData())).append("\'");
	        if(i+1 < disponibilidadesUsuarioVisualizado.size()){
	            sb.append(",");
	        }
		}
		sb.append("]");
		datasDesabilitar = sb.toString();
		return datasDesabilitar;
	}

	public void setDatasDesabilitar(String datasDesabilitar) {
		this.datasDesabilitar = datasDesabilitar;
	}

	public Disponibilidade getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(Disponibilidade disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

}