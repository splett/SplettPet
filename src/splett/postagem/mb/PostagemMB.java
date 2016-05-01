package splett.postagem.mb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import splett.perfil.mb.PerfilMB;
import splett.postagem.Postagem;
import splett.postagem.comentario.Comentario;
import splett.postagem.comentario.dao.ComentarioDao;
import splett.postagem.dao.PostagemDao;
import splett.session.SessionMB;

@ManagedBean(name = "postagemMB")
@ViewScoped
public class PostagemMB {

	@ManagedProperty(value = "#{postagemDao}")
	private PostagemDao postagemDao;

	private Postagem postagem;

	private Postagem postagemVisualizar;

	@ManagedProperty(value = "#{sessionMB}")
	private SessionMB sessionMB;

	@ManagedProperty(value = "#{perfilMB}")
	private PerfilMB perfilMB;

	@ManagedProperty(value = "#{comentarioDao}")
	private ComentarioDao comentarioDao;

	private List<Postagem> postagens;

	private List<Comentario> comentarios;
	
	private Comentario comentario;
	


	public PostagemMB() {
		postagens = new ArrayList<Postagem>();
		comentarios = new ArrayList<Comentario>();
		comentario = new Comentario();
	}

	@PostConstruct
	public void init() {
		listarPostagens();
	}

	public String salvarPostagem() {
		postagem.setDataPostagem(new Date());
		postagem.setUsuario(sessionMB.getUsuarioLogado());
		postagemDao.salvar(postagem);
		listarPostagens();
		return "postagens";
	}
	
	public void salvarComentario(){
		comentario.setPostagem(postagemVisualizar);
		comentario.setUsuario(sessionMB.getUsuarioLogado());
		comentarioDao.salvar(comentario);
		listarComentariosPostagem(postagemVisualizar);
		criarComentario();
	}

	public void setarPostagem(Integer id) {
		postagemVisualizar = postagemDao.findByIdInteger(id);
	}

	public void criar() {
		postagem = new Postagem();
	}

	public String remover() {
		postagemDao.remover(postagemVisualizar);
		return "postagens";
	}
	
	public boolean criarComentario() {
		comentario = new Comentario();
		return true;
	}

	public void removerComentario() {
		comentarioDao.remover(comentario);
	}

	public void listarPostagens() {
		postagens = postagemDao.listarPostagens(perfilMB
				.getUsuarioVisualizado().getId());
	}
	
	public String editarPostagem(){
		postagemDao.update(postagemVisualizar);
		return "postagens";
	}

	public void cancelar() {
		postagem = null;
	}
	
	public void cancelarComentario() {
		comentario = null;
	}

	public Postagem getPostagem() {
		return postagem;
	}

	public void setPostagem(Postagem postagem) {
		this.postagem = postagem;
	}

	public PostagemDao getPostagemDao() {
		return postagemDao;
	}

	public void setPostagemDao(PostagemDao postagemDao) {
		this.postagemDao = postagemDao;
	}

	public SessionMB getSession() {
		return sessionMB;
	}

	public void setSession(SessionMB sessionMB) {
		this.sessionMB = sessionMB;
	}

	public List<Postagem> getPostagens() {
		return postagens;
	}

	public void setPostagens(List<Postagem> postagens) {
		this.postagens = postagens;
	}

	public Postagem getPostagemVisualizar() {
		return postagemVisualizar;
	}

	public void setPostagemVisualizar(Postagem postagemVisualizar) {
		this.postagemVisualizar = postagemVisualizar;
		listarComentariosPostagem(postagemVisualizar);
	}

	public void listarComentariosPostagem(Postagem postagem){
		comentarios = postagemDao.listarComentariosPostagem(postagemVisualizar.getId());
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

	public ComentarioDao getComentarioDao() {
		return comentarioDao;
	}

	public void setComentarioDao(ComentarioDao comentarioDao) {
		this.comentarioDao = comentarioDao;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Comentario getComentario() {
		return comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}


}