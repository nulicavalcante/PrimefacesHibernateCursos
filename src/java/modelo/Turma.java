/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author 
 */
@Entity
@Table(name = "Turmas")
public class Turma implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 100, nullable = false)
    private String sala;
    @Temporal(TemporalType.TIME)
    private Date horarioInicio;
    @Temporal(TemporalType.TIME)
    private Date horarioFinal;
    @ManyToOne(targetEntity = Curso.class)
    @JoinColumn(name = "idCurso")
    private Curso curso;
    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL)
     private List<Avaliacao> avaliacao;

    public Turma() {
    }

    public Turma(Integer id, String sala, Date horarioInicio, Date horarioFinal, Curso curso, List<Avaliacao> avaliacao) {
        this.id = id;
        this.sala = sala;
        this.horarioInicio = horarioInicio;
        this.horarioFinal = horarioFinal;
        this.curso = curso;
        this.avaliacao = avaliacao;
    }
    

    
    public Integer getId() {
        return id;
    
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public Date getHorarioInicio() throws ParseException {
        return horarioInicio;
    }

    public void setHorarioInicio(Date horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public Date getHorarioFinal() throws ParseException {
        
        return horarioFinal;
    }

    public void setHorarioFinal(Date horarioFinal) {
        
        this.horarioFinal = horarioFinal;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Avaliacao> getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(List<Avaliacao> avaliacao) {
        this.avaliacao = avaliacao;
    }
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Turma other = (Turma) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }    
}
