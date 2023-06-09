package web.salaodebeleza.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import web.salaodebeleza.ajax.NotificacaoAlertify;
import web.salaodebeleza.ajax.TipoNotificaoAlertify;
import web.salaodebeleza.model.Funcionario;
import web.salaodebeleza.repository.FuncionarioRepository;
import web.salaodebeleza.service.FuncionarioService;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {
    
    private static final Logger logger = LoggerFactory.getLogger(PessoaController.class);
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("/cadastrar")
    public String abrirCadastro(Funcionario funcionario) {
        return "funcionario/cadastrar";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(Funcionario pessoa) {
        funcionarioService.salvar(pessoa);
        return "redirect:/funcionarios/mostrarmensagemcadastrook";
    }

    @GetMapping("/mostrarmensagemcadastrook")
    public String mostrarMensagemCadastroOK(Funcionario pessoa,Model model) {
        
        NotificacaoAlertify notificacao = new NotificacaoAlertify("Funcionario inserido com sucesso!",TipoNotificaoAlertify.SUCESSO);
        model.addAttribute("notificacao", notificacao);

        return "funcionario/cadastrar";
    }

}
