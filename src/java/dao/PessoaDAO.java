/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Pessoa;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author teste
 */
public class PessoaDAO extends GenericDAO<Pessoa> {
    // CRUD  - Retrive (Seleção)

    public Pessoa getPorId(Integer id) {
        try {
            return (Pessoa) getSessao().get(Pessoa.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public Pessoa getPorEmail(String email) {
        try {
            return (Pessoa) getSessao().get(Pessoa.class, email);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Pessoa> listarTodos() {
        Criteria c = getSessao().createCriteria(Pessoa.class);
        c.addOrder(Order.asc("nome"));
        return (List<Pessoa>) c.list();
    }
    public Pessoa login(String email,String senha) {
        Criteria c = getSessao().createCriteria(Pessoa.class);
        
        c.add(Restrictions.eq("email", email));
        c.add(Restrictions.eq("pwd",senha));
        
        return (Pessoa) c.uniqueResult();
    }
    
    public void salvar(Pessoa obj) {
        Pessoa existe = getPorId(obj.getId());
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
