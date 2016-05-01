package splett.disponibilidade.dao;

import java.util.Date;
import java.util.List;

import splett.dao.Dao;
import splett.disponibilidade.Disponibilidade;
import splett.usuario.Usuario;

public interface DisponibilidadeDao extends Dao<Disponibilidade> {

    public Disponibilidade findByData(Date data);
    
    public List<Disponibilidade> list(Usuario usuario);
    
    public boolean verificarExistenciaDisponibilidade(Usuario usuario, Date data);

}
