ALTER TABLE dia
    ADD FOREIGN KEY (codigo_funcionario)
    REFERENCES funcionario (codigo);

ALTER TABLE horarios
    ADD FOREIGN KEY (codigo_dia)
    REFERENCES dia (codigo); 