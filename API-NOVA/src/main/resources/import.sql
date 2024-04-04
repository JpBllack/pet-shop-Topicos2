-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

-- senha padrão: 12345
-- Inserção de um usuário veterinário
INSERT INTO Usuario (nome, cpf, username, email, senha, perfil) 
VALUES ('Teste', '123456', 'Testizinho', 'teste@mail.com', 'NuCgY6/GPMQTMdNiush/UNx86FJs4rFVBcCfuzRRIREuEbf42eMqkc+ex10zbq4TK4fvrcJUpNH85V1+nUEcJg==', 'ADMIN');

INSERT INTO Usuario (nome, cpf, username, email, senha, perfil) 
VALUES ('Son Goku', '12345678900', 'goku', 'goku@example.com', '12345', 'ADMIN');

INSERT INTO Usuario (nome, cpf, username, email, senha, perfil) 
VALUES ('Vegeta', '12345678901', 'vegeta', 'vegeta@example.com', '12345', 'VET');

INSERT INTO Usuario (nome, cpf, username, email, senha, perfil) 
VALUES ('Son Gohan', '12345678902', 'gohan', 'gohan@example.com', '12345', 'VET');

INSERT INTO Usuario (nome, cpf, username, email, senha, perfil) 
VALUES ('Piccolo', '12345678903', 'piccolo', 'piccolo@example.com', '12345', 'USER');

INSERT INTO Usuario (nome, cpf, username, email, senha, perfil) 
VALUES ('Trunks', '12345678904', 'trunks', 'trunks@example.com', '12345', 'USER');





INSERT INTO TipoAnimal (nome) VALUES
('cachorro');

INSERT INTO TipoAnimal (nome) VALUES
('gato');

INSERT INTO TipoAnimal (nome) VALUES
('cavalo');

INSERT INTO TipoAnimal (nome) VALUES
('peixe');

INSERT INTO TipoAnimal (nome) VALUES
('passaro');




INSERT INTO Racao (sabor, animal_id, peso, idade) VALUES ('carne', 1, 1, 1);
INSERT INTO Racao (sabor, animal_id, peso, idade) VALUES ('frango', 1, 1,2);
INSERT INTO Racao (sabor, animal_id, peso, idade) VALUES ('pistaxe', 5, 2, 2);
INSERT INTO Racao (sabor, animal_id, peso, idade) VALUES ('atum', 2, 1, 2);

-- Inserir um pet do tipo cachorro, macho
INSERT INTO Pet (nome, anoNascimento, tipoAnimal_id, sexo) 
VALUES ('Rex', 2019, 1, 1);

-- Inserir um pet do tipo gato, fêmea
INSERT INTO Pet (nome, anoNascimento, tipoAnimal_id, sexo) 
VALUES ('Luna', 2018, 2, 2);

-- Inserir um pet do tipo pássaro, macho
INSERT INTO Pet (nome, anoNascimento, tipoAnimal_id, sexo) 
VALUES ('Tweety', 2020, 3, 1);




-- Inserir uma consulta para um pet, agendada com um veterinário
INSERT INTO Consulta (data, motivo, veterinario_id, pet_id) 
VALUES ('2024-04-04', 'Exame de rotina', 3, 1);

-- Inserir outra consulta para um pet, com outro veterinário
INSERT INTO Consulta (data, motivo, veterinario_id, pet_id) 
VALUES ('2024-04-05', 'Vacinação', 4, 2);

-- Inserir uma consulta para outro pet, com um veterinário diferente
INSERT INTO Consulta (data, motivo, veterinario_id, pet_id) 
VALUES ('2024-04-06', 'Tratamento de ferimento', 3, 2);




