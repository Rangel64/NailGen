CREATE TABLE dia
(   
    codigo serial NOT NULL,
    nome_dia text,
    codigo_funcionario serial,
    PRIMARY KEY (codigo)
);

CREATE TABLE horarios
(   
    codigo serial NOT NULL,
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
    codigo_dia serial,
    PRIMARY KEY (codigo)
);