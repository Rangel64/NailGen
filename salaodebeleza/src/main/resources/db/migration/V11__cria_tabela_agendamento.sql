CREATE TABLE public.agentamento(
    codigo serial NOT NULL,
    codigo_dia_agendamento integer,
    codigo_funcionario integer,
    codigo_servico integer,
    PRIMARY KEY (codigo)
);

ALTER TABLE public.agentamento ADD COLUMN status text DEFAULT 'ATIVO';

ALTER TABLE public.agentamento
    ADD FOREIGN KEY (codigo_dia_agendamento)
    REFERENCES dia (codigo)
    NOT VALID;

ALTER TABLE public.agentamento
    ADD FOREIGN KEY (codigo_funcionario)
    REFERENCES funcionario (codigo)
    NOT VALID;

ALTER TABLE public.agentamento
    ADD FOREIGN KEY (codigo_servico)
    REFERENCES servico (codigo)
    NOT VALID;