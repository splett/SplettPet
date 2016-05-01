package splett.configuracoes.mb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import splett.criptografia.Criptografia;
import splett.endereco.Logradouro;
import splett.perfil.mb.PerfilMB;
import splett.usuario.Usuario;
import splett.usuario.dao.UsuarioDao;
import splett.usuario.endereco.dao.EnderecoDao;

@ManagedBean(name = "configuracoesMB")
@ViewScoped
public class ConfiguracoesMB {

    @ManagedProperty(value = "#{usuarioDao}")
    private UsuarioDao usuarioDao;

    @ManagedProperty(value = "#{perfilMB}")
    private PerfilMB perfilMB;

    @ManagedProperty(value = "#{enderecoDao}")
    private EnderecoDao enderecoDao;

    @ManagedProperty(value = "#{criptografia}")
    private Criptografia criptografia;

    private String senha = "";

    private Usuario usuario;

    private UploadedFile fileUp;

    private String destino = "c:\\temp\\";

    @PostConstruct
    public void criar() {
	usuario = new Usuario();
	usuario = perfilMB.getUsuarioVisualizado();
    }

    public void procurarEndereco() {
		List<Logradouro> enderecos = enderecoDao.pesquisarPorCep(usuario.getEndereco().getCep());
		Logradouro l = enderecos.get(0);
		usuario.getEndereco().setLogradouro(l.getNome());
		usuario.getEndereco().setBairro(l.getBairroInicial().getNome());
		usuario.getEndereco().setUf(l.getBairroInicial().getUf());
		usuario.getEndereco().setCidade(l.getLocalidade().getNome());

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
	if (fileUp.getSize() >= 0 || fileUp != null) {
	    try {
		File f = new File(usuario.getFotoCaminho());
		f.delete();
	    } catch (Exception exception) {

	    }

	    try {
		transferirArquivo(destino, nome, getFileUp().getInputstream());
	    } catch (IOException exception) {

	    }

	    usuario.setFotoCaminho(destino + nome);
	    usuario.setFotoContentType(fileUp.getContentType());
	    usuario.setFotoNome(nome);
	    salvar();
	} else {
	    salvar();
	}

	fileUp = null;
    }

    public void salvar() {
	usuarioDao.update(usuario);
	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!",
		"Informações salvas com sucesso!");
	FacesContext.getCurrentInstance().addMessage("Sucesso!", message);
    }

    public void cancelar() {
	usuario = null;
	fileUp = null;
    }

    public UploadedFile getFileUp() {
	return fileUp;
    }

    public void setFileUp(UploadedFile fileUp) {
	this.fileUp = fileUp;
    }

    public UsuarioDao getUsuarioDao() {
	return usuarioDao;
    }

    public EnderecoDao getEnderecoDao() {
	return enderecoDao;
    }

    public void setEnderecoDao(EnderecoDao enderecoDao) {
	this.enderecoDao = enderecoDao;
    }

    public Criptografia getCriptografia() {
	return criptografia;
    }

    public void setCriptografia(Criptografia criptografia) {
	this.criptografia = criptografia;
    }

    public String getSenha() {
	return senha;
    }

    public void setSenha(String senha) {
	this.senha = senha;
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
	this.usuarioDao = usuarioDao;
    }

    public PerfilMB getPerfilMB() {
	return perfilMB;
    }

    public void setPerfilMB(PerfilMB perfilMB) {
	this.perfilMB = perfilMB;
    }

    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

}
