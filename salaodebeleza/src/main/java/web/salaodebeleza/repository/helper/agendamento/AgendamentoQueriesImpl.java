package web.salaodebeleza.repository.helper.agendamento;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import web.salaodebeleza.model.Agendamento;
import web.salaodebeleza.repository.pagination.PaginacaoUtil;

@Transactional

public class AgendamentoQueriesImpl implements AgendamentoQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Agendamento> buscarAgendamentosComObjetos(Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Agendamento> criteriaQuery = builder.createQuery(Agendamento.class);
        Root<Agendamento> agendamentoRoot = criteriaQuery.from(Agendamento.class);
        
        agendamentoRoot.fetch("cliente", JoinType.INNER);
        agendamentoRoot.fetch("codigo_dia_agendamento", JoinType.INNER);
        agendamentoRoot.fetch("codigo_dia_agendamento_cliente", JoinType.INNER);
        agendamentoRoot.fetch("funcionario", JoinType.INNER);
        agendamentoRoot.fetch("servico", JoinType.INNER);
        
        criteriaQuery.select(agendamentoRoot);
        
        TypedQuery<Agendamento> typedQuery = manager.createQuery(criteriaQuery);
        
        PaginacaoUtil.prepararIntervalo(typedQuery, pageable);
        
        List<Agendamento> agendamentos = typedQuery.getResultList();
        
        long totalAgendamentos = getTotalAgendamentos();
        
        return new PageImpl<>(agendamentos, pageable, totalAgendamentos);
    }
    
    private Long getTotalAgendamentos() {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Agendamento> agendamentoRoot = criteriaQuery.from(Agendamento.class);
        
        criteriaQuery.select(builder.countDistinct(agendamentoRoot));
        
        return manager.createQuery(criteriaQuery).getSingleResult();
    }
    

}
