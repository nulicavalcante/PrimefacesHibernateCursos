package dao;

import java.util.Collection;
import java.util.List;
import modelo.Curso;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

/**
 *
 * @author
 */
public class CursoDAO extends GenericDAO<Curso> {

    // CRUD  - Retrive (Seleção)

    public Curso getPorId(Integer id) {
        try {
            return (Curso) getSessao().get(Curso.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Curso> listarTodos() {
        Criteria c = getSessao().createCriteria(Curso.class);
        c.addOrder(Order.asc("nome"));
        return (List<Curso>) c.list();
    }
    
    public void salvar(Curso obj) {
        Curso existe = getPorId(obj.getId());
        try {
            if (existe != null) {
                this.atualizar(obj);
            } else {
                this.inserir(obj);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
