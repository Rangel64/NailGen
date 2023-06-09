CREATE TABLE funcionario
(   
    codigo serial NOT NULL,
    nome text,
    cpf text,
    data_nascimento date,
    profissao text,
    PRIMARY KEY (codigo)
);


