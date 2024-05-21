-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

-- senha padrão: 12345
-- Inserção de um usuário veterinário
INSERT INTO Usuario (nome, cpf, username, email, senha, perfil) 
VALUES ('Teste', '123456', 'Testizinho', 'teste@gmail.com', 'NuCgY6/GPMQTMdNiush/UNx86FJs4rFVBcCfuzRRIREuEbf42eMqkc+ex10zbq4TK4fvrcJUpNH85V1+nUEcJg==', 'ADMIN');

INSERT INTO Usuario (nome, cpf, username, email, senha, perfil) 
VALUES ('Son Goku', '12345678900', 'goku', 'goku@gmail.com', 'NuCgY6/GPMQTMdNiush/UNx86FJs4rFVBcCfuzRRIREuEbf42eMqkc+ex10zbq4TK4fvrcJUpNH85V1+nUEcJg==', 'ADMIN');

INSERT INTO Usuario (nome, cpf, username, email, senha, perfil) 
VALUES ('Vegeta', '12345678901', 'vegeta', 'vegeta@gmail.com', 'NuCgY6/GPMQTMdNiush/UNx86FJs4rFVBcCfuzRRIREuEbf42eMqkc+ex10zbq4TK4fvrcJUpNH85V1+nUEcJg==', 'VET');

INSERT INTO Usuario (nome, cpf, username, email, senha, perfil) 
VALUES ('Son Gohan', '12345678902', 'gohan', 'gohan@gmail.com', 'NuCgY6/GPMQTMdNiush/UNx86FJs4rFVBcCfuzRRIREuEbf42eMqkc+ex10zbq4TK4fvrcJUpNH85V1+nUEcJg==', 'VET');

INSERT INTO Usuario (nome, cpf, username, email, senha, perfil) 
VALUES ('Piccolo', '12345678903', 'piccolo', 'piccolo@gmail.com', 'NuCgY6/GPMQTMdNiush/UNx86FJs4rFVBcCfuzRRIREuEbf42eMqkc+ex10zbq4TK4fvrcJUpNH85V1+nUEcJg==', 'USER');

INSERT INTO Usuario (nome, cpf, username, email, senha, perfil) 
VALUES ('Trunks', '12345678904', 'trunks', 'trunks@gmail.com', 'NuCgY6/GPMQTMdNiush/UNx86FJs4rFVBcCfuzRRIREuEbf42eMqkc+ex10zbq4TK4fvrcJUpNH85V1+nUEcJg==', 'USER');





INSERT INTO TipoAnimal (nome) VALUES
('cachorro');

INSERT INTO TipoAnimal (nome) VALUES
('gato');

INSERT INTO TipoAnimal (nome) VALUES
('cavalo');

INSERT INTO TipoAnimal (nome) VALUES
('peixe');

INSERT INTO TipoAnimal (nome) VALUES
('calopsita');





-- Inserir um pet do tipo cachorro, macho
INSERT INTO Pet (nome, anoNascimento, TipoAnimalId, usuario_id) 
VALUES ('Rex', 2019, 1,1);

-- Inserir um pet do tipo gato, fêmea
INSERT INTO Pet (nome, anoNascimento, TipoAnimalId,  usuario_id) 
VALUES ('Luna', 2018, 2,2);

-- Inserir um pet do tipo pássaro, macho
INSERT INTO Pet (nome, anoNascimento, TipoAnimalId,  usuario_id) 
VALUES ('Tweety', 2020, 3,3);




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


INSERT INTO Estado (nome, sigla) VALUES ('Tocantins', 'TO');
INSERT INTO Estado (nome, sigla) VALUES ('Rio de Janeiro', 'RJ');
INSERT INTO Estado (nome, sigla) VALUES ('Mato Grosso', 'MT');
INSERT INTO Estado (nome, sigla) VALUES ('Para', 'PA');
INSERT INTO Estado (nome, sigla) VALUES ('Piaui', 'PI');

INSERT INTO Municipio(nome , estado_id) VALUES ('Palmas',1);
INSERT INTO Municipio(nome , estado_id) VALUES ('Teresina',4); 
