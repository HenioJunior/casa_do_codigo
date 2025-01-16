INSERT INTO categoria(nome) VALUES ('Programacao');
INSERT INTO categoria(nome) VALUES ('Mobile');
INSERT INTO categoria(nome) VALUES ('Infraestrutura');

INSERT INTO autor(nome, email, descricao, instante_criacao) VALUES ('Thiago Leite e Carvalho', 'thiago@email.com', 'Thiago Leite e Carvalho é Mestre em Computação, analista de sistemas sênior da empresa de tecnologia do governo federal.', '2025-01-16');

INSERT INTO livro(titulo, resumo, sumario, preco, numero_paginas, isbn, data_publicacao, autor_id, categoria_id) VALUES ('Estruturas de Dados', 'As estruturas de dados são a base para a construção de algoritmos eficientes...', 'Parte 1: Dentro da Matrix', 49.90, 320,'978-85-5519-338-5', '2026-01-02', 1, 1);
