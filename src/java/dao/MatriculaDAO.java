/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Matricula;
import modelo.Pessoa;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author teste
 */
public class MatriculaDAO extends GenericDAO<Matricula> {
    // CRUD  - Retrive (Seleção)

    public Matricula getPorId(Integer id) {
        try {
            return (Matricula) getSessao().get(Matricula.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Matricula> listarTodos() {
        Criteria c = getSessao().createCriteria(Matricula.class);
        c.addOrder(Order.asc("dataInicio"));
        return (List<Matricula>) c.list();
    }
    public List<Matricula> listarTodosPorAluno(Pessoa pessoa) {
        Criteria c = getSessao().createCriteria(Matricula.class);
        c.add(Restrictions.eq("pessoa.id",pessoa.getId()));
        
        return (List<Matricula>) c.list();
    }
    public void salvar(Matricula obj) {
        Matricula existe = getPorId(obj.getId());
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
