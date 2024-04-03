-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

-- senha padrão: 12345
insert into Usuario (nome, cpf, username, email, senha, perfil) 
values ('Teste', '123456' ,'Testizinho', 'teste@mail.com', 'NuCgY6/GPMQTMdNiush/UNx86FJs4rFVBcCfuzRRIREuEbf42eMqkc+ex10zbq4TK4fvrcJUpNH85V1+nUEcJg==', 2);

INSERT INTO Usuario (nome, cpf, username, email, senha, perfil) 
VALUES ('Ale', '123.456.789-10', 'alezinho', 'ale@gmail.com', 'senha123', 3);


INSERT INTO TipoAnimal (nome) VALUES
('cachorro');

INSERT INTO TipoAnimal (nome) VALUES
('gato');


INSERT INTO Racao (sabor, animal_id, peso, idade) VALUES ('carne', 1, 1, 1);
INSERT INTO Racao (sabor, animal_id, peso, idade) VALUES ('frango', 1, 1,2);
INSERT INTO Racao (sabor, animal_id, peso, idade) VALUES ('frango', 2, 2, 1);
INSERT INTO Racao (sabor, animal_id, peso, idade) VALUES ('atum', 2, 1, 2);
