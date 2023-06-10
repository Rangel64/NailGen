package web.salaodebeleza.controller;

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
import web.salaodebeleza.filter.FuncionarioFilter;
import web.salaodebeleza.model.Dia;
import web.salaodebeleza.model.Funcionario;
import web.salaodebeleza.pagination.PageWrapper;
import web.salaodebeleza.repository.FuncionarioRepository;
import web.salaodebeleza.service.DiaService;
import web.salaodebeleza.service.FuncionarioService;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {
    
    private static final Logger logger = LoggerFactory.getLogger(PessoaController.class);
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private DiaService diaService;

    @GetMapping("/cadastrar")
    public String abrirCadastro(Funcionario funcionario) {
        return "funcionario/cadastrar";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(Funcionario funcionario) {
        
        
        logger.info("codigo:",funcionario.toString());

        if(funcionario!=null){
            String semana[] = {"segunda","terca","quarta","quinta","sexta"};
            Dia dia;
            funcionarioService.salvar(funcionario);
            for (int i = 0; i < 5; i++) {
                dia = new Dia();
                dia.setFuncionario(funcionario);
                dia.setNome_dia(semana[i]);
                logger.info("codigo:",dia.getFuncionario().getCodigo());
                diaService.salvar(dia);
            }
        }

        return "redirect:/funcionarios/mostrarmensagemcadastrook";
    }

    @GetMapping("/mostrarmensagemcadastrook")
    public String mostrarMensagemCadastroOK(Funcionario funcionario,Model model) {
        
        NotificacaoAlertify notificacao = new NotificacaoAlertify("Funcionario inserido com sucesso!",TipoNotificaoAlertify.SUCESSO);
        model.addAttribute("notificacao", notificacao);

        return "funcionario/cadastrar";
    }

    @GetMapping("/abrirpesquisar")
    public String abrirPesquisar(Model model) {
        model.addAttribute("url", "/funcionarios/pesquisar");
        model.addAttribute("uso", "funcionarios");
        return "funcionario/pesquisar";
    }

    @GetMapping("/pesquisar")
    public String pesquisar(FuncionarioFilter filtro, Model model,
            @PageableDefault(size = 5)
            @SortDefault(sort = "codigo", direction = Sort.Direction.ASC) 
            Pageable pageable,
            HttpServletRequest request) {
        Page<Funcionario> pagina = funcionarioRepository.filtrar(filtro, pageable);
        PageWrapper<Funcionario> paginaWrapper = new PageWrapper<>(pagina, request);
        logger.info("Funcionarios buscados no BD: {}", paginaWrapper.getConteudo());
        model.addAttribute("pagina", paginaWrapper);
        model.addAttribute("uso", "funcionarios");
        return "funcionario/mostrartodas";
    }

}
