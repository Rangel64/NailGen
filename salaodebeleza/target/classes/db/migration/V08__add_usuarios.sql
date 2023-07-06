INSERT INTO usuario (nome, email, senha, nome_usuario, ativo, data_nascimento) VALUES
('Rangel', 'rangel@seilah.com', '{noop}12345', 'rangel', true, '2000-06-06');


INSERT INTO papel (codigo, nome) VALUES 
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USUARIO');

INSERT INTO usuario_papel (codigo_usuario, codigo_papel) VALUES
(1, 1);

