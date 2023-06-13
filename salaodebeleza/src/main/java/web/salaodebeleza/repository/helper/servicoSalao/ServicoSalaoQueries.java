package web.salaodebeleza.repository.helper.servicoSalao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import web.salaodebeleza.filter.ServicoSalaoFilter;
import web.salaodebeleza.model.ServicoSalao;

public interface ServicoSalaoQueries {
    
    Page<ServicoSalao> filtrar (ServicoSalaoFilter filtro, Pageable pageable);

    ServicoSalao buscarComFuncionarios (Long codigoServico);

}
