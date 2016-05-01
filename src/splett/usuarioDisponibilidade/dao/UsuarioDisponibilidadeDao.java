package splett.usuarioDisponibilidade.dao;

import java.util.List;

import splett.dao.Dao;
import splett.usuario.Usuario;
import splett.usuarioDisponibilidade.UsuarioDisponibilidade;

public interface UsuarioDisponibilidadeDao extends Dao<UsuarioDisponibilidade> {
    public List<UsuarioDisponibilidade> list(Usuario usuario);
}
