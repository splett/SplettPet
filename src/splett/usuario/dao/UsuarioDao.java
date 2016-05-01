package splett.usuario.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

import splett.dao.Dao;
import splett.usuario.Usuario;

public interface UsuarioDao extends Dao<Usuario> {

    public List<Usuario> pesquisarPorNome(String nome);

    public Usuario pesquisarPorEmail(String email);

    public void realizaAutenticacaoAutomatica(HttpServletRequest request, Usuario usuario)
	    throws Exception;

    public Authentication authenticate(Authentication auth);
    
    public List<Usuario> pesquisarUsuarioPorCidade(String cidade, Integer id);
    
    public List<Usuario> pesquisaUsuario(String uf, String cidade, String bairro, Integer id, String dataInicial, String dataFinal, String tipoAnimal);

    public Usuario recoverAuthenticatedUser();

    public List<Usuario> listUsuariosByEmail(String email);
}