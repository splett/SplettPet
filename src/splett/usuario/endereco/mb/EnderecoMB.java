package splett.usuario.endereco.mb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import splett.animal.tipo.TipoAnimal;
import splett.session.SessionMB;
import splett.usuario.Usuario;
import splett.usuario.dao.UsuarioDao;
import splett.usuario.endereco.Endereco;
import splett.usuario.endereco.dao.EnderecoDao;

@ManagedBean(name = "enderecoMB")
@ViewScoped
public class EnderecoMB {

	@ManagedProperty(value = "#{enderecoDao}")
	private EnderecoDao enderecoDao;

	private Endereco endereco;

	private List<Usuario> usuarios;
	
	private Date dataInicial;
	
	private Date dataFinal;
	
	private TipoAnimal tipoAnimal;
	
	@ManagedProperty(value = "#{usuarioDao}")
	private UsuarioDao usuarioDao;
	
    @ManagedProperty(value = "#{sessionMB}")
    private SessionMB sessionMB;

	public EnderecoMB() {
		usuarios = new ArrayList<Usuario>();
	}

	@PostConstruct
	public void criar() {
		endereco = new Endereco();
		tipoAnimal = new TipoAnimal();
	}

	public void salvar() {
		if (endereco.getId() != null)
			enderecoDao.update(endereco);
		else
			enderecoDao.salvar(endereco);
	}

	public List<Endereco> listarCidades(){
		return enderecoDao.pesquisarPorCidade(this.endereco.getUf());
	}
	
	public List<Endereco> listarBairros(){
		return enderecoDao.pesquisarPorBairro(this.endereco.getCidade());
	}
	
	public void remover() {
		enderecoDao.remover(endereco);
	}

	public void cancelar() {
		endereco = null;
	}

	public EnderecoDao getEnderecoDao() {
		return enderecoDao;
	}

	public void setEnderecoDao(EnderecoDao enderecoDao) {
		this.enderecoDao = enderecoDao;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public void setarUsuarios(){
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String dataIncialS = sdf1.format(dataInicial);
		String dataFinalS = sdf1.format(dataFinal);
		
		usuarios = usuarioDao.pesquisaUsuario(endereco.getUf(), endereco.getCidade(), endereco.getBairro(), sessionMB.getUsuarioLogado().getId(), dataIncialS, dataFinalS, "Cachorro");
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public SessionMB getSessionMB() {
		return sessionMB;
	}

	public void setSessionMB(SessionMB sessionMB) {
		this.sessionMB = sessionMB;
	}
	
	public boolean verficarListaUsuarios(){
		return (usuarios==null);
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	public void criarDatasPesquisa(){
		dataInicial = new Date();
		dataFinal = new Date();
	}

	public TipoAnimal getTipoAnimal() {
		return tipoAnimal;
	}

	public void setTipoAnimal(TipoAnimal tipoAnimal) {
		this.tipoAnimal = tipoAnimal;
	}
	
}