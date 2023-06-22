package web.salaodebeleza.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import web.salaodebeleza.filter.PessoaFilter;
import web.salaodebeleza.filter.ServicoSalaoFilter;
import web.salaodebeleza.model.Agendamento;
import web.salaodebeleza.model.Funcionario;
import web.salaodebeleza.model.Pessoa;
import web.salaodebeleza.model.ServicoSalao;
import web.salaodebeleza.model.Status;
import web.salaodebeleza.pagination.PageWrapper;
import web.salaodebeleza.repository.FuncionarioRepository;
import web.salaodebeleza.repository.PessoaRepository;
import web.salaodebeleza.repository.ServicoRepository;


@Controller
@RequestMapping("/agendamentos")
public class AgendamentoController {
    private static final Logger logger = LoggerFactory.getLogger(PessoaController.class);

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    ServicoRepository servicoRepository;

    @RequestMapping("/abrircadastrar")
    public String abrirCadastrar(HttpSession sessao){
        sessao.setAttribute("agendamento", new Agendamento());
        return("agendamento/cadastrar");
    }

    @RequestMapping("escolherpessoa")
    public String abrirEscolhaPessoa(Model model){
        model.addAttribute("url","/agendamentos/pesquisarpessoa");  
        model.addAttribute("uso", "agendamentos");
        return("pessoas/pesquisar");
    }

    @GetMapping("/pesquisarpessoa")
    public String pesquisar(PessoaFilter filtro, Model model,
            @PageableDefault(size = 5)
            @SortDefault(sort = "codigo", direction = Sort.Direction.ASC) 
            Pageable pageable,
            HttpServletRequest request) {
        Page<Pessoa> pagina = pessoaRepository.filtrar(filtro, pageable);
        PageWrapper<Pessoa> paginaWrapper = new PageWrapper<>(pagina, request);
        logger.info("Pessoas buscadas no BD: {}", paginaWrapper.getConteudo());
        model.addAttribute("pagina", paginaWrapper);
        model.addAttribute("uso", "agendamentos");
        return "pessoas/mostrartodas";  
    }

    @PostMapping("/escolherpessoa")
    public String definirPessoa(Long codigo, Model model, HttpSession sessao) {
        Optional<Pessoa> optPessoa = pessoaRepository.findById(codigo);
        if (optPessoa.isPresent()) {
            Agendamento agendamento = (Agendamento) sessao.getAttribute("agendamento");
            agendamento.setCliente(optPessoa.get());
            return "agendamento/cadastrar";
        } else {
            model.addAttribute("opcao", "pessoas");
            model.addAttribute("mensagem", "Não existe pessoa com código: " + codigo);
            return "mostrarmensagem";
        }
    }
    
    @GetMapping("/escolherservico")
    public String abrirEscolhaServico(Model model){
        List<Funcionario> funcionarios = funcionarioRepository.findByStatus(Status.ATIVO);
        model.addAttribute("funcionarios", funcionarios);
        model.addAttribute("url", "/agendamentos/pesquisarservico");
        model.addAttribute("uso", "agendamentos");
        return "servico/pesquisar";
    }

    @GetMapping("/pesquisarservico")
    public String pesquisarLote(ServicoSalaoFilter filtro, Model model,
            @PageableDefault(size = 5) @SortDefault(sort = "codigo", direction = Sort.Direction.ASC) Pageable pageable,
            HttpServletRequest request) {
        Page<ServicoSalao> pagina = servicoRepository.filtrar(filtro, pageable);
        PageWrapper<ServicoSalao> paginaWrapper = new PageWrapper<>(pagina, request);
        logger.info("servicos buscados no BD: {}", paginaWrapper.getConteudo());
        model.addAttribute("pagina", paginaWrapper);
        model.addAttribute("uso", "agendamentos");
        return "servico/mostrartodos";
    }

    @PostMapping("/escolherservico")
    public String definirLote(Long codigo, Model model, HttpSession sessao) {
        ServicoSalao servico = servicoRepository.buscarComFuncionarios(codigo);
        if (servico != null) {
            Agendamento agendamento = (Agendamento) sessao.getAttribute("agendamento");
            agendamento.setServico(servico);
            return "agendamento/cadastrar";
        } else {
            model.addAttribute("opcao", "servicos");
            model.addAttribute("mensagem", "Não existe servico com código: " + codigo);
            return "mostrarmensagem";
        }
    }

}
