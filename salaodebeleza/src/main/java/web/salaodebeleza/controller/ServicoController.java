package web.salaodebeleza.controller;

import java.util.List;

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
import web.salaodebeleza.ajax.NotificacaoAlertify;
import web.salaodebeleza.ajax.TipoNotificaoAlertify;
import web.salaodebeleza.filter.ServicoSalaoFilter;
import web.salaodebeleza.model.Funcionario;
import web.salaodebeleza.model.ServicoSalao;
import web.salaodebeleza.model.Status;
import web.salaodebeleza.pagination.PageWrapper;
import web.salaodebeleza.repository.FuncionarioRepository;
import web.salaodebeleza.repository.ServicoRepository;
import web.salaodebeleza.service.ServicoService;

@Controller
@RequestMapping("/servicos")
public class ServicoController {

    private static final Logger logger = LoggerFactory.getLogger(PessoaController.class);

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ServicoService servicoService;

    @Autowired
    private ServicoRepository servicoRepository;

    @GetMapping("/cadastrar")
    public String abrirCadastro(ServicoSalao servico,Model model){
        List<Funcionario> funcionarios = funcionarioRepository.findByStatus(Status.ATIVO);
        logger.info("funcionarios buscados no BD: {}", funcionarios);
        model.addAttribute("funcionarios", funcionarios);
        return "servico/cadastrar";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(ServicoSalao servico, Model model){
        if(servico!=null){
            servicoService.salvar(servico);
        }
        return "redirect:/servicos/mostrarmensagemcadastrook";
    }

    @GetMapping("/mostrarmensagemcadastrook")
    public String mostrarMensagemCadastroOK(ServicoSalao servico,Model model) {
        NotificacaoAlertify notificacao = new NotificacaoAlertify("Servico inserido com sucesso!",TipoNotificaoAlertify.SUCESSO);
        model.addAttribute("notificacao", notificacao);
        List<Funcionario> funcionarios = funcionarioRepository.findByStatus(Status.ATIVO);
        model.addAttribute("funcionarios", funcionarios);
        return "servico/cadastrar";
    }

    @GetMapping("/abrirpesquisar")
    public String abrirPesquisar(Model model) {
        List<Funcionario> funcionarios = funcionarioRepository.findByStatus(Status.ATIVO);
        model.addAttribute("funcionarios", funcionarios);
        model.addAttribute("url", "/servicos/pesquisar");
        model.addAttribute("uso", "servicos");
        return "servico/pesquisar";
    }

    @GetMapping("/pesquisar")
    public String pesquisar(ServicoSalaoFilter filtro, Model model,
            @PageableDefault(size = 5) @SortDefault(sort = "codigo", direction = Sort.Direction.ASC) Pageable pageable,
            HttpServletRequest request) {
        Page<ServicoSalao> pagina = servicoRepository.filtrar(filtro, pageable);
        PageWrapper<ServicoSalao> paginaWrapper = new PageWrapper<>(pagina, request);
        logger.info("servicos buscados no BD: {}", paginaWrapper.getConteudo());
        model.addAttribute("pagina", paginaWrapper);
        model.addAttribute("uso", "servicos");
        return "servico/mostrartodos";
    }

    @PostMapping("/abriralterar")
    public String abrirAlterar(Long codigo, Model model){
        ServicoSalao servico_aux = servicoRepository.buscarComFuncionarios(codigo);
        model.addAttribute("servicoSalao", servico_aux);
        List<Funcionario> funcionarios = funcionarioRepository.findByStatus(Status.ATIVO);
        model.addAttribute("funcionarios", funcionarios);
        
        return "servico/alterar";
    }

    @PostMapping("/alterar")
    public String alterar(ServicoSalao servico, Model model){
        servicoService.salvar(servico);
        return "redirect:/servicos/mostrarmensagemalterarok";
    }

    @GetMapping("/mostrarmensagemalterarok")
    public String mostrarMensagemAlterarOK(ServicoSalao servico,Model model) {
        NotificacaoAlertify notificacao = new NotificacaoAlertify("Servico atualizado com sucesso!",TipoNotificaoAlertify.SUCESSO);
        model.addAttribute("notificacao", notificacao);
        List<Funcionario> funcionarios = funcionarioRepository.findByStatus(Status.ATIVO);
        model.addAttribute("funcionarios", funcionarios);
        model.addAttribute("url", "/servicos/pesquisar");
        model.addAttribute("uso", "servicos");
        return "servico/pesquisar";
    }

    @PostMapping("/remover")
    public String remover(Long codigo, Model model){
        ServicoSalao servico = servicoRepository.buscarComFuncionarios(codigo);
        servico.setStatus(Status.INATIVO);
        servicoService.alterar(servico);
        return "redirect:/servicos/mostrarmensagemremoverok";
    }

    @GetMapping("/mostrarmensagemremoverok")
    public String mostrarMensagemRemoverOK(ServicoSalao servico,Model model) {
        NotificacaoAlertify notificacao = new NotificacaoAlertify("Servico removico com sucesso!",TipoNotificaoAlertify.SUCESSO);
        model.addAttribute("notificacao", notificacao);
        List<Funcionario> funcionarios = funcionarioRepository.findByStatus(Status.ATIVO);
        model.addAttribute("funcionarios", funcionarios);
        model.addAttribute("url", "/servicos/pesquisar");
        model.addAttribute("uso", "servicos");
        return "servico/pesquisar";
    }

}
