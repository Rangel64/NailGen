package web.salaodebeleza.repository.helper.servicoSalao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import web.salaodebeleza.filter.ServicoSalaoFilter;
import web.salaodebeleza.model.Funcionario;
import web.salaodebeleza.model.ServicoSalao;
import web.salaodebeleza.model.Status;
import web.salaodebeleza.repository.pagination.PaginacaoUtil;

public class ServicoSalaoQueriesImpl implements ServicoSalaoQueries{
    
    @PersistenceContext
    private EntityManager manager;

    public Page<ServicoSalao> filtrar(ServicoSalaoFilter filtro, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<ServicoSalao> criteriaQuery = builder.createQuery(ServicoSalao.class);
        Root<ServicoSalao> servicoSalaoRoot = criteriaQuery.from(ServicoSalao.class);
        TypedQuery<ServicoSalao> typedQuery;
        List<Predicate> predicateList = new ArrayList<>();
    
        if (filtro.getCodigo() != null) {
            predicateList.add(builder.equal(servicoSalaoRoot.<Long>get("codigo"), filtro.getCodigo()));
        }
    
        if (filtro.getNome() != null) {
            predicateList.add(builder.greaterThanOrEqualTo(
                    servicoSalaoRoot.<String>get("nome"), filtro.getNome()));
        }
    
        if (filtro.getDescricao() != null) {
            predicateList.add(builder.lessThanOrEqualTo(
                    servicoSalaoRoot.<String>get("descricao"), filtro.getDescricao()));
        }
    
        Join<ServicoSalao, List<Funcionario>> funcionarioJoin = servicoSalaoRoot.join("funcionarios", JoinType.INNER);
    
        if (filtro.getFuncionarios() != null && !filtro.getFuncionarios().isEmpty()) {
            predicateList.add(funcionarioJoin.get("codigo").in(getFuncionariosCodigos(filtro.getFuncionarios())));
        }
    
        predicateList.add(builder.equal(servicoSalaoRoot.<Status>get("status"), Status.ATIVO));
    
        Predicate[] predArray = new Predicate[predicateList.size()];
        predicateList.toArray(predArray);
    
        criteriaQuery.select(servicoSalaoRoot).distinct(true).where(predArray);
    
        PaginacaoUtil.prepararOrdem(servicoSalaoRoot, criteriaQuery, builder, pageable);
    
        typedQuery = manager.createQuery(criteriaQuery);
    
        PaginacaoUtil.prepararIntervalo(typedQuery, pageable);
    
        List<ServicoSalao> servicos = typedQuery.getResultList();
    
        long totalServicos = getTotalServicos(filtro);
        return new PageImpl<>(servicos, pageable, totalServicos);
    }
    
    private List<Long> getFuncionariosCodigos(List<Funcionario> funcionarios) {
        List<Long> codigos = new ArrayList<>();
        for (Funcionario funcionario : funcionarios) {
            codigos.add(funcionario.getCodigo());
        }
        return codigos;
    }
    
    private Long getTotalServicos(ServicoSalaoFilter filtro) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<ServicoSalao> servicoSalaoRoot = criteriaQuery.from(ServicoSalao.class);
        List<Predicate> predicateList = new ArrayList<>();
    
        if (filtro.getCodigo() != null) {
            predicateList.add(builder.equal(servicoSalaoRoot.<Long>get("codigo"), filtro.getCodigo()));
        }
    
        if (filtro.getNome() != null) {
            predicateList.add(builder.greaterThanOrEqualTo(
                    servicoSalaoRoot.<String>get("nome"), filtro.getNome()));
        }
    
        if (filtro.getDescricao() != null) {
            predicateList.add(builder.lessThanOrEqualTo(
                    servicoSalaoRoot.<String>get("descricao"), filtro.getDescricao()));
        }
    
        if (filtro.getFuncionarios() != null && !filtro.getFuncionarios().isEmpty()) {
            Join<ServicoSalao, Funcionario> funcionariosJoin = servicoSalaoRoot.join("funcionarios");
            predicateList.add(funcionariosJoin.get("codigo").in(getFuncionariosCodigos(filtro.getFuncionarios())));
        }
    
        predicateList.add(builder.equal(servicoSalaoRoot.<Status>get("status"), Status.ATIVO));
    
        Predicate[] predArray = new Predicate[predicateList.size()];
        predicateList.toArray(predArray);
    
        criteriaQuery.select(builder.countDistinct(servicoSalaoRoot)).where(predArray);
    
        return manager.createQuery(criteriaQuery).getSingleResult();
    }
    
    @Override
    public ServicoSalao buscarComFuncionarios(Long codigoServico) {
        String jpql = "select distinct l from ServicoSalao l join fetch l.funcionarios where l.codigo = :codigo";
        TypedQuery<ServicoSalao> query = manager.createQuery(jpql, ServicoSalao.class);
        query.setParameter("codigo", codigoServico);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    
}





