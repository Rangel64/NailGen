CREATE TABLE public.diaCliente
(   
    codigo serial NOT NULL,
    data_agendamento date,
    codigo_cliente integer,

    h_7 boolean,
    h_8 boolean,
    h_9 boolean,
    h_10 boolean,
    h_11 boolean,
    h_12 boolean,
    h_13 boolean,
    h_14 boolean,
    h_15 boolean,
    h_16 boolean,
    h_17 boolean,

    PRIMARY KEY (codigo)
);

ALTER TABLE public.agendamento ADD COLUMN codigo_dia_agendamento_cliente integer ;

ALTER TABLE public.agendamento      
    ADD FOREIGN KEY (codigo_dia_agendamento_cliente)
    REFERENCES diaCliente (codigo)
    NOT VALID;

ALTER TABLE public.diaCliente
    ADD FOREIGN KEY (codigo_cliente)
    REFERENCES pessoa (codigo)
    NOT VALID;

