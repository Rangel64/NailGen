CREATE TABLE public.dia
(   
    codigo serial NOT NULL,
    codigo_funcionario integer,
    data_agendamento date,

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
