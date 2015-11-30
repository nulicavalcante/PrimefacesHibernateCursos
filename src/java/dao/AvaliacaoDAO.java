/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Avaliacao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

/**
 *
 * @author teste
 */
public class AvaliacaoDAO extends GenericDAO<Avaliacao> {

    // CRUD  - Retrive (Seleção)

    public Avaliacao getPorId(Integer id) {
        try {
            return (Avaliacao) getSessao().get(Avaliacao.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Avaliacao> listarTodos() {
        Criteria c = getSessao().createCriteria(Avaliacao.class);
        c.addOrder(Order.asc("dataAval"));
        return (List<Avaliacao>) c.list();
    }

    public void salvar(Avaliacao obj) {
        Avaliacao existe = getPorId(obj.getId());
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
