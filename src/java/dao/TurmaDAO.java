/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Turma;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

/**
 *
 * @author teste
 */
public class TurmaDAO extends GenericDAO<Turma> {

    // CRUD  - Retrive (Seleção)

    public Turma getPorId(Integer id) {
        try {
            return (Turma) getSessao().get(Turma.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Turma> listarTodos() {
        Criteria c = getSessao().createCriteria(Turma.class);
        c.addOrder(Order.asc("sala"));
        return (List<Turma>) c.list();
    }

    public void salvar(Turma obj) {
        Turma existe = getPorId(obj.getId());

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
