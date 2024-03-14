-- Inserção de dados na tabela 'estado'
INSERT INTO estado (dataInclusao, nome, sigla) VALUES ('2023-11-28 17:52:21.615056','Tocantins', 'TO');
INSERT INTO estado (dataInclusao, nome, sigla) VALUES ('2023-11-28 17:52:21.615056','Goiás', 'GO');
INSERT INTO estado (dataInclusao, nome, sigla) VALUES ('2023-11-28 17:52:21.615056','Rio de Janeiro', 'RJ');
INSERT INTO estado (dataInclusao, nome, sigla) VALUES ('2023-11-28 17:52:21.615056','São Paulo', 'SP');

-- Inserção de dados na tabela 'cidade'
INSERT INTO cidade (dataInclusao, id, usuario_estado, nome) VALUES ('2023-11-28 17:52:21.615056', 1, 1, 'Palmas');
INSERT INTO cidade (dataInclusao, id, usuario_estado, nome) VALUES ('2023-11-28 17:52:21.615056', 2, 2, 'Goiania');
INSERT INTO cidade (dataInclusao, id, usuario_estado, nome) VALUES ('2023-11-28 17:52:21.615056', 3, 1, 'Araguaina');

INSERT INTO cidade (dataInclusao, id, usuario_estado, nome) VALUES ('2023-11-29 17:52:21.615056', 4, 1, 'TESTE');

-- Inserção de dados na tabela 'categoria'

INSERT INTO categoria (nome) VALUES ('alimento');
INSERT INTO categoria (nome) VALUES ('acessorios');
INSERT INTO categoria (nome) VALUES ('higiene');
INSERT INTO categoria (nome) VALUES ('saude');
INSERT INTO categoria (nome) VALUES ('Teste');



-- Inserção de dados na tabela 'produto'
INSERT INTO produto (dataInclusao, quantidade_estoque, valor, categoria_produto, descricao, nome) 
VALUES ('2023-11-28 18:03:57.903875', 122, 39.00, 1, 'Ração premium para cães e gatos de todas as idades e portes.', 'Ração Premium para Cães e Gatos');

INSERT INTO produto (dataInclusao, quantidade_estoque, valor, categoria_produto, descricao, nome) 
VALUES ('2023-11-28 18:03:57.903875', 50, 55.00, 1, 'Ração super premium com ingredientes selecionados para nutrição completa de cães e gatos.', 'Ração Super Premium para Cães e Gatos');

INSERT INTO produto (dataInclusao, quantidade_estoque, valor, categoria_produto, descricao, nome) 
VALUES ('2023-11-28 18:03:57.903875', 95, 30.00, 2, 'Areia sanitária para gatos com alto poder de absorção e controle de odores.', 'Areia Sanitária para Gatos');

INSERT INTO produto (dataInclusao, quantidade_estoque, valor, categoria_produto, descricao, nome) 
VALUES ('2023-11-28 18:03:57.903875', 20, 520.00, 2, 'Coleira de nylon resistente para cães de todos os tamanhos.', 'Coleira de Nylon para Cães');

INSERT INTO produto (dataInclusao, quantidade_estoque, valor, categoria_produto, descricao, nome) 
VALUES ('2023-11-28 18:03:57.903875', 35, 25.00, 2, 'Brinquedo interativo de borracha para cães mastigarem e se divertirem.', 'Brinquedo de Borracha para Cães');

INSERT INTO produto (dataInclusao, quantidade_estoque, valor, categoria_produto, descricao, nome) 
VALUES ('2023-11-28 18:03:57.903875', 80, 15.00, 3, 'Shampoo suave e sem lágrimas para filhotes de cães e gatos.', 'Shampoo para Filhotes');

INSERT INTO produto (dataInclusao, quantidade_estoque, valor, categoria_produto, descricao, nome) 
VALUES ('2023-11-28 18:03:57.903875', 10, 35.00, 3, 'Escova de cerdas macias para remoção de pelos soltos e desembaraço da pelagem.', 'Escova para Cães e Gatos');

INSERT INTO produto (dataInclusao, quantidade_estoque, valor, categoria_produto, descricao, nome) 
VALUES ('2023-11-28 18:03:57.903875', 15, 45.00, 4, 'Suplemento vitamínico para fortalecimento do sistema imunológico de cães e gatos.', 'Suplemento Vitamínico para Pets');


-- Inserção de dados na tabela 'avaliacao'
INSERT INTO avaliacao (dataInclusao, nota, comentario, produto_avaliacao) VALUES ('2023-11-28 17:52:21.615056', 5, 'Muito bom', 1);
INSERT INTO avaliacao (dataInclusao, nota, comentario, produto_avaliacao) VALUES ('2023-11-28 17:52:21.615056', 4, 'Gostei, comprarei novamente', 2);
INSERT INTO avaliacao (dataInclusao, nota, comentario, produto_avaliacao) VALUES ('2023-11-28 17:52:21.615056', 1, 'Golpe!!!!!!!!!', 1);

-- Inserção de dados na tabela 'usuario'
INSERT INTO usuario (id, dataInclusao, cpf, email, login, nome, senha) VALUES ('sdas-asdasdsas-aasd23234','2023-11-28 18:03:57.903875', '05415887105', 'joao@gmail.com', 'joaojoao', 'joao', 'yEaSZv1mx2Hf11tomtEAY3HUG2hrQS2ACE17U1PeCoA7PFIhHARbDredPke5UTKwvMVA+jod2rMVKSoDzm8p3Q==');
INSERT INTO usuario (id, dataInclusao, cpf, email, login, nome, senha) VALUES ('sdgg3434-aasd23234','2023-11-28 18:07:39.904241', '12345678901', 'maria@gmail.com', 'mariamaria', 'Maria', 'yEaSZv1mx2Hf11tomtEAY3HUG2hrQS2ACE17U1PeCoA7PFIhHARbDredPke5UTKwvMVA+jod2rMVKSoDzm8p3Q==');
INSERT INTO usuario (id, dataInclusao, cpf, email, login, nome, senha) VALUES ('sdd32hsd23234','2023-11-28 18:07:53.952613', '34567890123', 'ana@gmail.com', 'anaana', 'Ana', 'yEaSZv1mx2Hf11tomtEAY3HUG2hrQS2ACE17U1PeCoA7PFIhHARbDredPke5UTKwvMVA+jod2rMVKSoDzm8p3Q==');
INSERT INTO usuario (id, dataInclusao, cpf, email, login, nome, senha) VALUES ('234342sdsas-afdsfsd23234','2023-11-28 18:07:59.345242', '45678901234', 'pedro@gmail.com', 'pedropedro', 'Pedro', 'yEaSZv1mx2Hf11tomtEAY3HUG2hrQS2ACE17U1PeCoA7PFIhHARbDredPke5UTKwvMVA+jod2rMVKSoDzm8p3Q==');
INSERT INTO usuario (id, dataInclusao, cpf, email, login, nome, senha) VALUES ('kdsafst4322sd23234','2023-11-28 18:08:04.895422', '56789012345', 'carla@gmail.com', 'carlacarla', 'Carla', 'yEaSZv1mx2Hf11tomtEAY3HUG2hrQS2ACE17U1PeCoA7PFIhHARbDredPke5UTKwvMVA+jod2rMVKSoDzm8p3Q==');

-- teste
INSERT INTO usuario (id, dataInclusao, cpf, email, login, nome, senha) VALUES ('testestestesteste','2023-11-28 18:08:04.895422', '56789012345', 'teste@gmail.com', 'testeste', 'Testenome', 'yEaSZv1mx2Hf11tomtEAY3HUG2hrQS2ACE17U1PeCoA7PFIhHARbDredPke5UTKwvMVA+jod2rMVKSoDzm8p3Q==');
INSERT INTO usuario_perfil(perfil, id_usuario) VALUES (2, 'testestestesteste');
INSERT INTO usuario_perfil(perfil, id_usuario) VALUES (1, 'testestestesteste');
INSERT INTO usuario (id, dataInclusao, cpf, email, login, nome, senha) VALUES ('testestestesteste2','2023-11-28 18:08:04.895422', '00000000000', 'teste2@gmail.com', 'testeste2', 'TEstte', 'yEaSZv1mx2Hf11tomtEAY3HUG2hrQS2ACE17U1PeCoA7PFIhHARbDredPke5UTKwvMVA+jod2rMVKSoDzm8p3Q==');
INSERT INTO usuario_perfil(perfil, id_usuario) VALUES (2, 'testestestesteste2');

-- Inserção de dados na tabela 'usuario_perfil'
INSERT INTO usuario_perfil(perfil, id_usuario) VALUES (2, 'sdas-asdasdsas-aasd23234');
INSERT INTO usuario_perfil(perfil, id_usuario) VALUES (1, 'sdas-asdasdsas-aasd23234');
INSERT INTO usuario_perfil(perfil, id_usuario) VALUES (1, 'sdgg3434-aasd23234');
INSERT INTO usuario_perfil(perfil, id_usuario) VALUES (1, 'sdd32hsd23234');
INSERT INTO usuario_perfil(perfil, id_usuario) VALUES (2, 'sdd32hsd23234');
INSERT INTO usuario_perfil(perfil, id_usuario) VALUES (1, '234342sdsas-afdsfsd23234');
INSERT INTO usuario_perfil(perfil, id_usuario) VALUES (1, 'kdsafst4322sd23234');

-- Inserção de dados na tabela 'telefone'
INSERT INTO telefone(dataInclusao, CodigoArea, numero, telefone_usuario) VALUES ('2023-11-28 17:52:21.615056','63', '984255465', 'sdas-asdasdsas-aasd23234');
INSERT INTO telefone(dataInclusao, CodigoArea, numero, telefone_usuario) VALUES ('2023-11-28 17:52:21.615056','63', '984545458', 'sdgg3434-aasd23234');
INSERT INTO telefone(dataInclusao, CodigoArea, numero, telefone_usuario) VALUES ('2023-11-28 17:52:21.615056','94', '992322585', 'sdd32hsd23234');
INSERT INTO telefone(dataInclusao, CodigoArea, numero, telefone_usuario) VALUES ('2023-11-28 17:52:21.615056','63', '999568525', '234342sdsas-afdsfsd23234');

-- Inserção de dados na tabela 'endereco'
INSERT INTO endereco (cep, logradouro, dataInclusao, endereco_cidade, bairro, numero, complemento, usuario_endereco) VALUES ('123123123', 'rua','2023-11-28 17:52:21.615056',1, 'quadra 403', '23', 'casa', 'sdas-asdasdsas-aasd23234');
INSERT INTO endereco (cep, logradouro, dataInclusao, endereco_cidade, bairro, numero, complemento, usuario_endereco) VALUES ('123123123', 'rua','2023-11-28 17:52:21.615056',1, 'quadra 405', '30', 'casa', 'sdgg3434-aasd23234');
INSERT INTO endereco (cep, logradouro, dataInclusao, endereco_cidade, bairro, numero, complemento, usuario_endereco) VALUES ('123123123', 'rua','2023-11-28 17:52:21.615056',1, 'quadra 403', '24', 'servico', 'sdgg3434-aasd23234');
INSERT INTO endereco (cep, logradouro, dataInclusao, endereco_cidade, bairro, numero, complemento, usuario_endereco) VALUES ('123123123', 'rua','2023-11-28 17:52:21.615056',1, 'quadra 403', '244', 'casa', '234342sdsas-afdsfsd23234');

-- teste
INSERT INTO endereco (cep, logradouro, dataInclusao, endereco_cidade, bairro, numero, complemento, usuario_endereco) VALUES ('123123123', 'rua','2023-11-28 17:52:21.615056',1, 'teste', 'tt', 'teste', 'sdas-asdasdsas-aasd23234');

-- Inserção de dados na tabela 'pagamento'
INSERT INTO pagamento (dataInclusao, id, pago, valor, tipo) VALUES ('2023-11-28 17:52:21.615056',1, true, 11000, 'boleto');
INSERT INTO pagamento (dataInclusao, id, pago, valor, tipo) VALUES ('2023-11-28 17:52:21.615056',2, true, 16500, 'pix');
INSERT INTO pagamento (dataInclusao, id, pago, valor, tipo) VALUES ('2023-11-28 17:52:21.615056',3, true, 3900, 'cartao');


-- Inserção de dados nas tabelas 'boletobancario', 'cartaocredito', 'pixpagamento' para **Teste**

INSERT INTO pagamento (dataInclusao, id, pago, valor, tipo) VALUES ('2023-11-28 17:52:21.615056', 4, true, 3900, 'boleto');
INSERT INTO pagamento (dataInclusao, id, pago, valor, tipo) VALUES ('2023-11-28 17:52:21.615056', 5, true, 0000, 'cartao');
INSERT INTO pagamento (dataInclusao, id, pago, valor, tipo) VALUES ('2023-11-28 17:52:21.615056', 6, true, 0000, 'pix');
INSERT INTO pagamento (dataInclusao, id, pago, valor, tipo) VALUES ('2023-11-28 17:52:21.615056', 7, true, 3900, 'teste');
INSERT INTO boletobancario (banco, numeroBoleto, id) VALUES ('TESTE', 'PARA TESTES', 4);
INSERT INTO cartaocredito (bandeiraCartao, cvv, numeroCartao ,id) VALUES ('Test', 'ttt','test test test test', 5);
INSERT INTO pixpagamento (chave, id) VALUES ('tstestestestestestestestestestestestestes', 6);
INSERT INTO boletobancario (banco, numeroBoleto, id) VALUES ('TESTE', 'PARA TESTES', 7);


-- Inserção de dados nas tabelas 'boletobancario', 'cartaocredito', 'pixpagamento'
INSERT INTO boletobancario (banco, numeroBoleto, id) VALUES ('caixa', '23535556535df5d6sf5565d65f6sd5fd5sf', 1);
INSERT INTO cartaocredito (bandeiraCartao, cvv, numeroCartao ,id) VALUES ('VISA', '645','5546 5225 8886 2246', 3);
INSERT INTO pixpagamento (chave, id) VALUES ('asdsdassdasdsdsdawdadawasdhghkihossfdfs', 2);


-- Inserção de dados na tabela 'venda'
INSERT INTO venda (dataInclusao, valorTotal, endereco_venda, venda_pagamento) VALUES ('2023-11-28 17:52:21.615056', 11000, 1, 1);
INSERT INTO venda (dataInclusao, valorTotal, endereco_venda, venda_pagamento) VALUES ('2023-11-28 17:52:21.615056', 16500, 2, 2);
INSERT INTO venda (dataInclusao, valorTotal, endereco_venda, venda_pagamento) VALUES ('2023-11-28 17:52:21.615056', 3900, 2, 3);

-- teste
INSERT INTO venda (valorTotal, endereco_venda, venda_pagamento, dataInclusao) VALUES (3900, 2, 4, '2023-11-28 17:52:21.615056');

-- Inserção de dados na tabela 'itemvenda'
INSERT INTO itemvenda (dataInclusao, quantidade, valorTotal, valorUnitario, produto_itemvenda, venda_itemvenda) VALUES ('2023-11-28 17:52:21.615056', 2, 11000, 5500, 2, 1);
INSERT INTO itemvenda (dataInclusao, quantidade, valorTotal, valorUnitario, produto_itemvenda, venda_itemvenda) VALUES ('2023-11-28 17:52:21.615056', 3, 16500, 5500, 2, 2);
INSERT INTO itemvenda (dataInclusao, quantidade, valorTotal, valorUnitario, produto_itemvenda, venda_itemvenda) VALUES ('2023-11-28 17:52:21.615056', 1,3900, 3900, 1, 3);
-- teste
INSERT INTO itemvenda (dataInclusao, quantidade, valorTotal, valorUnitario, produto_itemvenda, id) VALUES ('2023-11-28 17:52:21.615056', 1,3900, 3900, 1, 4);
INSERT INTO itemvenda (dataInclusao, quantidade, valorTotal, valorUnitario, produto_itemvenda, id) VALUES ('2023-11-28 17:52:21.615056', 1,3900, 3900, 1, 5);