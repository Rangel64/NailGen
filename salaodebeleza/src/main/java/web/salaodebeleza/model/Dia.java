package web.salaodebeleza.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "dia")
public class Dia {
    
    @Id
	@SequenceGenerator(name="gerador1", sequenceName="dia_codigo_seq", allocationSize=1)
	@GeneratedValue(generator="gerador1", strategy=GenerationType.SEQUENCE)

    private Long codigo;
    private String nome_dia;

    private Boolean h_7 = false;
    private Boolean h_8 = false;
    private Boolean h_9 = false;
    private Boolean h_10 = false;
    private Boolean h_11 = false;
    private Boolean h_12 = false;
    private Boolean h_13 = false;
    private Boolean h_14 = false;
    private Boolean h_15 = false;
    private Boolean h_16 = false;
    private Boolean h_17 = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="codigo_funcionario")
    private Funcionario funcionario;

   
    
    public Long getCodigo() {
        return codigo;
    }
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    public String getNome_dia() {
        return nome_dia;
    }
    public void setNome_dia(String nome_dia) {
        this.nome_dia = nome_dia;
    }

    public Boolean getH_7() {
        return h_7;
    }
    public void setH_7(Boolean h_7) {
        this.h_7 = h_7;
    }
    public Boolean getH_8() {
        return h_8;
    }
    public void setH_8(Boolean h_8) {
        this.h_8 = h_8;
    }
    public Boolean getH_9() {
        return h_9;
    }
    public void setH_9(Boolean h_9) {
        this.h_9 = h_9;
    }
    public Boolean getH_10() {
        return h_10;
    }
    public void setH_10(Boolean h_10) {
        this.h_10 = h_10;
    }
    public Boolean getH_11() {
        return h_11;
    }
    public void setH_11(Boolean h_11) {
        this.h_11 = h_11;
    }
    public Boolean getH_12() {
        return h_12;
    }
    public void setH_12(Boolean h_12) {
        this.h_12 = h_12;
    }
    public Boolean getH_13() {
        return h_13;
    }
    public void setH_13(Boolean h_13) {
        this.h_13 = h_13;
    }
    public Boolean getH_14() {
        return h_14;
    }
    public void setH_14(Boolean h_14) {
        this.h_14 = h_14;
    }
    public Boolean getH_15() {
        return h_15;
    }
    public void setH_15(Boolean h_15) {
        this.h_15 = h_15;
    }
    public Boolean getH_16() {
        return h_16;
    }
    public void setH_16(Boolean h_16) {
        this.h_16 = h_16;
    }
    public Boolean getH_17() {
        return h_17;
    }
    public void setH_17(Boolean h_17) {
        this.h_17 = h_17;
    }
    @Override
    public String toString() {
        return "Dia [codigo=" + codigo + ", nome_dia=" + nome_dia + ", h_7=" + h_7 + ", h_8=" + h_8 + ", h_9=" + h_9
                + ", h_10=" + h_10 + ", h_11=" + h_11 + ", h_12=" + h_12 + ", h_13=" + h_13 + ", h_14=" + h_14
                + ", h_15=" + h_15 + ", h_16=" + h_16 + ", h_17=" + h_17 + "]";
    }
    public Funcionario getFuncionario() {
        return funcionario;
    }
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    

}
