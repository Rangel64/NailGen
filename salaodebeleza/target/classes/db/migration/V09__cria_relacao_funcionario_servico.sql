BEGIN;

CREATE TABLE public.servico
(
    codigo bigserial NOT NULL,
    nome text,
    descricao text,
    PRIMARY KEY (codigo)
);


CREATE TABLE public.servico_funcionario
(
    codigo_servico bigint NOT NULL,
    codigo_funcionario bigint NOT NULL
);

ALTER TABLE public.servico_funcionario
    ADD FOREIGN KEY (codigo_servico)
    REFERENCES public.servico (codigo)
    NOT VALID;

ALTER TABLE public.servico_funcionario
    ADD FOREIGN KEY (codigo_funcionario)
    REFERENCES public.funcionario (codigo)
    NOT VALID;
    
END;