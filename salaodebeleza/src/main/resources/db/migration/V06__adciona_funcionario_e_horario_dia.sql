ALTER TABLE public.dia
    ADD FOREIGN KEY (codigo_funcionario)
    REFERENCES funcionario (codigo)
    NOT VALID;

