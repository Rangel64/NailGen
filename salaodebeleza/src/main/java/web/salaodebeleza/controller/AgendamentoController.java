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
import web.salaodebeleza.ajax.NotificacaoAlertify;
import web.salaodebeleza.ajax.TipoNotificaoAlertify;
import web.salaodebeleza.filter.PessoaFilter;
import web.salaodebeleza.filter.ServicoSalaoFilter;
import web.salaodebeleza.model.Agendamento;
import web.salaodebeleza.model.Dia;
import web.salaodebeleza.model.Funcionario;
import web.salaodebeleza.model.Pessoa;
import web.salaodebeleza.model.ServicoSalao;
import web.salaodebeleza.model.Status;
import web.salaodebeleza.pagination.PageWrapper;
import web.salaodebeleza.repository.DiaRepository;
import web.salaodebeleza.repository.FuncionarioRepository;
import web.salaodebeleza.repository.PessoaRepository;
import web.salaodebeleza.repository.ServicoRepository;
import web.salaodebeleza.service.AgendamentoService;
import web.salaodebeleza.service.DiaService;

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

    @Autowired 
    DiaRepository diaRepository;

    @Autowired
    AgendamentoService agendamentoService;

    @Autowired
    DiaService diaService;



    @RequestMapping("/abrircadastrar")
    public String abrirCadastrar(HttpSession sessao){
        Agendamento agendamento = new Agendamento();
        sessao.setAttribute("agendamento", agendamento);
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
    public String definirServico(Long codigo, Model model, HttpSession sessao, Dia dia, Funcionario funcionario) {
        ServicoSalao servico = servicoRepository.buscarComFuncionarios(codigo);
        System.out.println(servico);
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

    @GetMapping("/abrirescolherhorario")
    public String abrirEscolhaHorario(Model model, HttpSession sessao,Dia dia, Funcionario funcionario){
        logger.info("teste:{}", dia.getDataAgendamento());
        Funcionario funcionarioDia = funcionarioRepository.findByCodigo(funcionario.getCodigo());
        sessao.setAttribute("funcionarioEscolhido", funcionarioDia);
        Dia diaHorario = diaRepository.findByDataAgendamentoAndFuncionario(dia.getDataAgendamento(), funcionario);
        if(diaHorario==null){
            logger.info("testediaHorario null");
            Dia diaAgendamento = new Dia();
            diaAgendamento.setDataAgendamento(dia.getDataAgendamento());
            diaAgendamento.setFuncionario(funcionarioDia);
            model.addAttribute("diaAgendamento", diaAgendamento);

            //colocar na sessao
            return "agendamento/escolherHorario";
        }
        else{
            logger.info("testediaHorario notnull");
            diaHorario.setFuncionario(funcionarioDia);
            model.addAttribute("diaAgendamento", diaHorario);
            return "agendamento/escolherHorario";
        }
    }

    @PostMapping("/escolherhorario")
    public String escolhaHorario(Dia dia, HttpSession sessao){
        // comparar esse dia com o da sesdsao se existir
        // se nao existir na sessao pegar o horario que ele marcou
        // se existir pegar a diferencagit c
        // e colocar num attributo
        // model.addAttribute("horario", VALOR);
        Agendamento agendamento = (Agendamento)sessao.getAttribute("agendamento");
        Funcionario funcionario = (Funcionario)sessao.getAttribute("funcionarioEscolhido");
        dia.setFuncionario(funcionario);
        agendamento.setFuncionario(funcionario);
        agendamento.setCodigo_dia_agendamento(dia);
        // logger.info("testediaHorario {}",dia);
        return "agendamento/cadastrar";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(HttpSession sessao, Model model){
        Agendamento agendamento = (Agendamento)sessao.getAttribute("agendamento");
        if(agendamento!=null){
            if(agendamento.getCliente()!=null && agendamento.getFuncionario()!=null && agendamento.getServico()!=null && agendamento.getCodigo_dia_agendamento()!=null){
                diaService.salvar(agendamento.getCodigo_dia_agendamento());
                agendamentoService.salvar(agendamento);
                sessao.removeAttribute("agendamento");
                sessao.removeAttribute("funcionarioEscolhido");
                return "redirect:/agendamentos/mostrarmensagemcadastrorok";
            }
            else{
                NotificacaoAlertify notificacao = new NotificacaoAlertify("Agendamento cadastrado com falhou!",TipoNotificaoAlertify.ERRO);
                model.addAttribute("notificacao", notificacao);
                return"agendamento/cadastrar";
            }
        }
        else{
            return"agendamento/abrircadastrar";
        }

    }

   
    @GetMapping("/mostrarmensagemcadastrorok")
    public String mostrarMensagemCadastroOK(Model model) {
        NotificacaoAlertify notificacao = new NotificacaoAlertify("Agendamento cadastrado com sucesso!",TipoNotificaoAlertify.SUCESSO);
        model.addAttribute("notificacao", notificacao);

        return "forward:/agendamentos/abrircadastrar";
    }
}
