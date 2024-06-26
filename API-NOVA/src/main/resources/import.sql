-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;


INSERT INTO Estado (nome, sigla) VALUES ('Tocantins', 'TO');
INSERT INTO Estado (nome, sigla) VALUES ('São Paulo', 'SP');
INSERT INTO Estado (nome, sigla) VALUES ('Rio de Janeiro', 'RJ');
INSERT INTO Estado (nome, sigla) VALUES ('Mato Grosso', 'MT');
INSERT INTO Estado (nome, sigla) VALUES ('Para', 'PA');
INSERT INTO Estado (nome, sigla) VALUES ('Piaui', 'PI');
INSERT INTO Estado (nome, sigla) VALUES ('Sergipe', 'SE');
INSERT INTO Estado (nome, sigla) VALUES ('Minas Gerais', 'MG');
INSERT INTO Estado (nome, sigla) VALUES ('Maranhão', 'MA');
INSERT INTO Estado (nome, sigla) VALUES ('Amazonas', 'AM');
INSERT INTO Estado (nome, sigla) VALUES ('Amapa', 'AP');
INSERT INTO Estado (nome, sigla) VALUES ('Acre', 'AC');
INSERT INTO Estado (nome, sigla) VALUES ('Alagoas', 'AL');
INSERT INTO Estado (nome, sigla) VALUES ('Espirito Santo', 'ES');
INSERT INTO Estado (nome, sigla) VALUES ('Mato Grosso', 'MT');
INSERT INTO Estado (nome, sigla) VALUES ('Mato Grosso do Sul', 'MS');


INSERT INTO Municipio(nome , estado_id) VALUES ('Palmas',1);
INSERT INTO Municipio(nome , estado_id) VALUES ('Araguaina',1);
INSERT INTO Municipio(nome , estado_id) VALUES ('Paraiso',1);
INSERT INTO Municipio(nome , estado_id) VALUES ('Araguaina',1);
INSERT INTO Municipio(nome , estado_id) VALUES ('Porto Nacional',1);
INSERT INTO Municipio(nome , estado_id) VALUES ('Augustinopolis',1);
INSERT INTO Municipio(nome , estado_id) VALUES ('Gurupi',1);
INSERT INTO Municipio(nome , estado_id) VALUES ('Teresina',5); 

-- senha padrão: 12345
-- Inserção de um usuário veterinário
INSERT INTO usuario (nome, sobrenome, cpf, username, email, senha, perfil, imagem) 
VALUES ('Teste', 'da Silva', '12345678910', 'Testizinho', 'teste@gmail.com', 'NuCgY6/GPMQTMdNiush/UNx86FJs4rFVBcCfuzRRIREuEbf42eMqkc+ex10zbq4TK4fvrcJUpNH85V1+nUEcJg==', 'ADMIN', '1/0fde6eb1-0c4d-4da4-b9c2-40ded1804877.jpeg');

INSERT INTO usuario (nome, sobrenome, cpf, username, email, senha, perfil, imagem) 
VALUES ('Son Goku', 'Junior', '12345678900', 'goku', 'goku@gmail.com', 'NuCgY6/GPMQTMdNiush/UNx86FJs4rFVBcCfuzRRIREuEbf42eMqkc+ex10zbq4TK4fvrcJUpNH85V1+nUEcJg==', 'ADMIN', '2/4fbcc36e-f464-4ef1-9acc-63ef680800fa.jpeg');

INSERT INTO usuario (nome, sobrenome, cpf, username, email, senha, perfil, imagem) 
VALUES ('Vegeta', 'Almeida', '12345678901', 'vegeta', 'vegeta@gmail.com', 'NuCgY6/GPMQTMdNiush/UNx86FJs4rFVBcCfuzRRIREuEbf42eMqkc+ex10zbq4TK4fvrcJUpNH85V1+nUEcJg==', 'VET', '3/00dbfd74-c18a-4f9a-87ce-97fb6137da74.jpeg');

INSERT INTO usuario (nome, sobrenome, cpf, username, email, senha, perfil, imagem) 
VALUES ('Son Gohan', 'Marques', '12345678902', 'gohan', 'gohan@gmail.com', 'NuCgY6/GPMQTMdNiush/UNx86FJs4rFVBcCfuzRRIREuEbf42eMqkc+ex10zbq4TK4fvrcJUpNH85V1+nUEcJg==', 'VET', '4/83d8476c-e778-418e-b309-abe60b0567f2.jpeg');

INSERT INTO usuario (nome, sobrenome, cpf, username, email, senha, perfil, imagem) 
VALUES ('Piccolo', 'Cunha', '12345678903', 'piccolo', 'piccolo@gmail.com', 'NuCgY6/GPMQTMdNiush/UNx86FJs4rFVBcCfuzRRIREuEbf42eMqkc+ex10zbq4TK4fvrcJUpNH85V1+nUEcJg==', 'USER', '5/0f75c222-8cbc-4a17-b0b1-3cbfde889b9b.jpeg');

INSERT INTO usuario (nome, sobrenome, cpf, username, email, senha, perfil, imagem) 
VALUES ('Trunks', 'Scot', '12345678904', 'trunks', 'trunks@gmail.com', 'NuCgY6/GPMQTMdNiush/UNx86FJs4rFVBcCfuzRRIREuEbf42eMqkc+ex10zbq4TK4fvrcJUpNH85V1+nUEcJg==', 'USER', '6/82201426-3be6-4d76-8fec-8f613dcac32d.jpeg');

INSERT INTO usuario (nome, sobrenome, cpf, username, email, senha, perfil) 
VALUES ('Alisson', 'Lima', '12345687654', 'alisson', 'alisson@gmail.com', 'NuCgY6/GPMQTMdNiush/UNx86FJs4rFVBcCfuzRRIREuEbf42eMqkc+ex10zbq4TK4fvrcJUpNH85V1+nUEcJg==', 'ADMIN');

INSERT INTO usuario (nome, sobrenome, cpf, username, email, senha, perfil) 
VALUES ('João', 'Gomes', '12345616523', 'joao', 'joao@gmail.com', 'NuCgY6/GPMQTMdNiush/UNx86FJs4rFVBcCfuzRRIREuEbf42eMqkc+ex10zbq4TK4fvrcJUpNH85V1+nUEcJg==', 'ADMIN');

insert into endereco(logradouro, numero, complemento, bairro, endereco_cidade, usuario_id, principal, cep) values('Rua dos bobos', 'Casa 1', 'Em frente a passarela', 'Bairro gen', 1, 1, true, '48374836483');
insert into endereco(logradouro, numero, complemento, bairro, endereco_cidade, usuario_id, principal, cep) values('Santana dourado', 'Complexo 2', 'Construção vermelha', 'Plano D', 1, 1, false, '778436836');


INSERT INTO cartao_credito (nome, numero_cartao, codigo_seguranca, validade_mes, validade_ano, usuario_id, principal) VALUES ('fulano de tal','1234123412341234', '123', 12, 2025, 1, true);
INSERT INTO cartao_credito (nome, numero_cartao, codigo_seguranca, validade_mes, validade_ano, usuario_id, principal) VALUES ('cicrano de teo', '1829273635364895', '124', 1, 2028, 1, false);
INSERT INTO cartao_credito (nome, numero_cartao, codigo_seguranca, validade_mes, validade_ano, usuario_id, principal) VALUES ('pessoa random', '9292027263546489', '813', 9, 2025, 2, true);
INSERT INTO cartao_credito (nome, numero_cartao, codigo_seguranca, validade_mes, validade_ano, usuario_id, principal) VALUES ('pessoa teste', '9292920336635242', '721', 10, 2030, 1, false);
INSERT INTO cartao_credito (nome, numero_cartao, codigo_seguranca, validade_mes, validade_ano, usuario_id, principal) VALUES ('fulano teste', '8484736363740902', '147', 5, 2025, 3, true);
INSERT INTO cartao_credito (nome, numero_cartao, codigo_seguranca, validade_mes, validade_ano, usuario_id, principal) VALUES ('cicrano teste', '0292918273263938', '456', 12, 2025, 3, false);
INSERT INTO cartao_credito (nome, numero_cartao, codigo_seguranca, validade_mes, validade_ano, usuario_id, principal) VALUES ('teste de pessoa', '0282636352627838', '333', 9, 2024, 4, true);


INSERT INTO TipoAnimal (nome) VALUES
('Cachorro');

INSERT INTO TipoAnimal (nome) VALUES
('Gato');

INSERT INTO TipoAnimal (nome) VALUES
('Cavalo');

INSERT INTO TipoAnimal (nome) VALUES
('Peixe');

INSERT INTO TipoAnimal (nome) VALUES
('Aves');

INSERT INTO TipoAnimal (nome) VALUES
('Papagaio');

INSERT INTO TipoAnimal (nome) VALUES
('Canário');

INSERT INTO TipoAnimal (nome) VALUES
('Sabiá');

INSERT INTO TipoAnimal (nome) VALUES
('Coelho');

INSERT INTO TipoAnimal (nome) VALUES
('Hamster');

INSERT INTO TipoAnimal (nome) VALUES
('Iguana');

INSERT INTO TipoAnimal (nome) VALUES
('Tartaruga');




-- Inserir um pet do tipo cachorro, macho
INSERT INTO Pet (nome, anoNascimento, TipoAnimalId, usuario_id) 
VALUES ('Rex', 2019, 1,1);

-- Inserir um pet do tipo gato, fêmea
INSERT INTO Pet (nome, anoNascimento, TipoAnimalId,  usuario_id) 
VALUES ('Luna', 2018, 2,2);

-- Inserir um pet do tipo pássaro, macho
INSERT INTO Pet (nome, anoNascimento, TipoAnimalId,  usuario_id) 
VALUES ('Tweety', 2020, 3,1);

INSERT INTO Pet (nome, anoNascimento, TipoAnimalId,  usuario_id) 
VALUES ('Titan', 2022, 1,5);

INSERT INTO Pet (nome, anoNascimento, TipoAnimalId,  usuario_id) 
VALUES ('Sweet', 2024, 2,3);

INSERT INTO Pet (nome, anoNascimento, TipoAnimalId,  usuario_id) 
VALUES ('Todd', 2021, 3,6);

INSERT INTO Pet (nome, anoNascimento, TipoAnimalId,  usuario_id) 
VALUES ('Sib', 2019, 2,7);

INSERT INTO Pet (nome, anoNascimento, TipoAnimalId,  usuario_id) 
VALUES ('Gina', 2023, 1,8);

-- Inserir uma consulta para um pet, agendada com um veterinário
INSERT INTO Consulta (data, motivo, veterinario_id, pet_id) 
VALUES ('2024-04-04', 'Exame de rotina', 3, 1);

-- Inserir outra consulta para um pet, com outro veterinário
INSERT INTO Consulta (data, motivo, veterinario_id, pet_id) 
VALUES ('2024-04-05', 'Vacinação', 4, 2);

-- Inserir uma consulta para outro pet, com um veterinário diferente
INSERT INTO Consulta (data, motivo, veterinario_id, pet_id) 
VALUES ('2024-04-06', 'Tratamento de ferimento', 3, 2);



insert into Marca(nome) values('Whiskas');
insert into Marca(nome) values('Gran Plus');
insert into Marca(nome) values('Friskies');
insert into Marca(nome) values('Pedigree');
insert into Marca(nome) values('Royal Canin');
insert into Marca(nome) values('FinoTrato');
insert into Marca(nome) values('Hills');
insert into Marca(nome) values('NuTropica');
insert into Marca(nome) values('Guabi Natural');
insert into Marca(nome) values('Coveli');
insert into Marca(nome) values('Zee.Dog');
insert into Marca(nome) values('Optimum');
insert into Marca(nome) values('PremieR');


--                                                                                                nome,                        sabor,                      animal_id,              peso,              idade,        marca_id,   preco,     estoque,               imagem
INSERT INTO Racao (nome, sabor, animal_id, peso, idade, marca_id, preco, estoque, imagem) VALUES ('Ração GranPlus Gourmet para Cães Adultos de Médio e Grande Porte Sabor Ovelha e Arroz','Ovelha e arroz', 1, 3, 2, 2, 62.00, 100, '73e31b9a-954d-40b0-a352-c151363fffbf.jpeg');
INSERT INTO Racao (nome, sabor, animal_id, peso, idade, marca_id, preco, estoque, imagem) VALUES ('Ração GranPlus Menu para Cães Adultos de Porte Mini Sabor Carne e Arroz', 'Carne e arroz', 1, 5, 2, 2, 120.00, 100, 'fd5e07f8-82fd-409a-b747-e1a4dd9e8615.jpeg');
INSERT INTO Racao (nome, sabor, animal_id, peso, idade, marca_id, preco, estoque, imagem) VALUES ('Ração Hills Science Diet para Cães Adultos de Grande Porte Sabor Frango', 'Frango', 1, 5, 2, 7, 450.00, 100, 'edb49a53-9379-4337-b70e-a9d323cd99ad.jpeg');
INSERT INTO Racao (nome, sabor, animal_id, peso, idade, marca_id, preco, estoque, imagem) VALUES ('Ração Hills para Cães Filhotes de Mini e Pequeno Porte Sabor Frango', 'Frango', 1, 5, 2, 7, 150.00, 100, 'c6a2d506-8eb6-4ceb-9ab5-7f7828eb9913.jpeg');
INSERT INTO Racao (nome, sabor, animal_id, peso, idade, marca_id, preco, estoque, imagem) VALUES ('Ração GranPlus Choice para Gatos Adultos Sabor Frango e Carne', 'Frango e carne', 2, 5, 2, 2, 147.00, 100, '9cdbfa0d-c95f-4323-8662-facd235a9982.jpeg');
INSERT INTO Racao (nome, sabor, animal_id, peso, idade, marca_id, preco, estoque, imagem) VALUES ('Ração Whiskas para Gatos Adultos Castrados Sabor Carne', 'Carne', 2, 5, 2, 1, 180.00, 100, '0fe3f574-fbe4-44d7-b0a9-56f596171edd.jpeg');
INSERT INTO Racao (nome, sabor, animal_id, peso, idade, marca_id, preco, estoque, imagem) VALUES ('Ração Whiskas para Gatos Adultos Castrados Sabor Carne', 'Carne', 2, 3, 2, 1, 50.00, 100, '0dea9877-cab2-4849-b6a7-851e59d7557b.jpeg');
INSERT INTO Racao (nome, sabor, animal_id, peso, idade, marca_id, preco, estoque, imagem) VALUES ('Ração Seca Royal Canin Veterinary Diet Hepatic para Gatos Adultos com Insuficiência Hepática Crônica', 'Frango', 2, 2, 2, 1, 150.00, 100, 'b4c728b8-4105-4d46-a77a-414c7cf7316c.jpeg');
INSERT INTO Racao (nome, sabor, animal_id, peso, idade, marca_id, preco, estoque, imagem) VALUES ('Ração Úmida Pedigree Sachê Carne ao Molho para Cães Adultos de Raças Pequenas 100 g', 'Carne', 1, 6, 2, 4, 3.50, 200, '0bc58dd7-d1ef-4963-af74-76be9f33e050.jpeg');
INSERT INTO Racao (nome, sabor, animal_id, peso, idade, marca_id, preco, estoque, imagem) VALUES ('Ração Royal Canin Sterilised 12+ para Gatos Adultos Castrados Acima de 12 Anos', 'Carne', 2, 1, 3, 5, 42.00, 80, 'cfe0318a-1cbb-4dd9-bd69-af9fc3b211c8.jpeg');
INSERT INTO Racao (nome, sabor, animal_id, peso, idade, marca_id, preco, estoque, imagem) VALUES ('Alimento Nutrópica Extrusado Natural para Calopsita', 'Não definido', 5, 2, 4, 8, 100.00, 100, '70c78936-3327-432a-90b6-e710f1cea942.jpeg');
INSERT INTO Racao (nome, sabor, animal_id, peso, idade, marca_id, preco, estoque, imagem) VALUES ('Ração Finotrato Prime Light para Cães Adultos de Porte Grande','Natural', 1, 5, 2, 6, 200.00, 100, '73e31b9a-954d-40b0-a352-c151363fffbf.jpeg');
INSERT INTO Racao (nome, sabor, animal_id, peso, idade, marca_id, preco, estoque, imagem) VALUES ('Ração Úmida Royal Canin Sachê Light para Gatos Adultos 85 g', 'Carne', 2, 6, 2, 5, 14.00, 200, 'd47fa0f4-df41-4314-8afd-5cf4cb10e13c.jpeg');
INSERT INTO Racao (nome, sabor, animal_id, peso, idade, marca_id, preco, estoque, imagem) VALUES ('Ração Úmida GranPlus Gourmet Sachê para Gatos Adultos Sabor Atum - 85g', 'Atum', 2, 6, 2, 2, 3.00, 200, '4e047929-a671-48cb-a4c7-0809656efaf8.jpeg');
INSERT INTO Racao (nome, sabor, animal_id, peso, idade, marca_id, preco, estoque, imagem) VALUES ('Ração Guabi Natural para Gatos Adultos Castrados Sabor Frango e Arroz Integral', 'Frango e arroz integral', 2, 5, 2, 9, 300.00, 80, 'fc0163a5-ee2f-41e8-afca-9213159933ed.jpeg');
INSERT INTO Racao (nome, sabor, animal_id, peso, idade, marca_id, preco, estoque, imagem) VALUES ('Ração Royal Canin Sterilised 12+ para Gatos Adultos Castrados Acima de 12 Anos', 'Carne', 1, 5, 1, 5, 420.00, 80, '2d19410f-e601-4a20-a4e5-acf27e4871e6.jpeg');
INSERT INTO Racao (nome, sabor, animal_id, peso, idade, marca_id, preco, estoque, imagem) VALUES ('Alimento Nutrópica Extrusados e Frutas para Papagaio', 'Não definido', 5, 1, 4, 8, 70.00, 50, 'aede1ead-6d56-4b06-b427-b1f733c72d85.jpeg');
INSERT INTO Racao (nome, sabor, animal_id, peso, idade, marca_id, preco, estoque, imagem) VALUES ('Alimento Avitrim Coveli para Papagaios e Araras', 'Não definido', 5, 1, 4, 10, 25.00, 90, '4cc9fdf7-b117-4e0b-b78d-ccb911a0cb85.jpeg');
INSERT INTO Racao (nome, sabor, animal_id, peso, idade, marca_id, preco, estoque, imagem) VALUES ('Ração Nutrópica Seleção Natural para Canário', 'Não definido', 5, 6, 4, 10, 35.00, 40, '0af9c6f7-312a-4252-9201-d4fe68beed36.jpeg');
INSERT INTO Racao (nome, sabor, animal_id, peso, idade, marca_id, preco, estoque, imagem) VALUES ('Refeição Natural Zee.Dog Kitchen Lata para Cães', 'Carne', 1, 6, 2, 11, 25.00, 60, '36787479-60ae-4aeb-be14-316488efceec.jpeg');
INSERT INTO Racao (nome, sabor, animal_id, peso, idade, marca_id, preco, estoque, imagem) VALUES ('Refeição Natural Zee.Dog Kitchen Lata Baixo Fósforo', 'Frango e vegetais', 1, 6, 3, 11, 27.00, 60, '460d3089-adbf-494a-b428-489ea0e1cba3.jpeg');
INSERT INTO Racao (nome, sabor, animal_id, peso, idade, marca_id, preco, estoque, imagem) VALUES ('Ração Optimum para Gatos Adultos Castrados 1+ anos', 'Carne', 2, 5, 2, 12, 150.00, 100, 'fa3a2406-3563-4d82-8e06-f4b9efe77ecc.jpeg');
INSERT INTO Racao (nome, sabor, animal_id, peso, idade, marca_id, preco, estoque, imagem) VALUES ('Ração GranPlus Gourmet para Gatos Filhotes', 'Salmão e Frango', 2, 3, 1, 2, 70.00, 100, 'cd3aa321-6736-4365-afdf-ed0ae8a085f5.jpeg');
INSERT INTO Racao (nome, sabor, animal_id, peso, idade, marca_id, preco, estoque, imagem) VALUES ('Ração Premier Nutrição Clínica Cardio para Cães de Porte Médio e Grande', 'Não definido', 1, 5, 2, 13, 329.00, 1, 'f63316fb-bd8f-42d9-88a8-801fd7814d30.jpeg');


