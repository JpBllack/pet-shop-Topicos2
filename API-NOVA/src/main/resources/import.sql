-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

-- senha padrão: 12345
insert into Usuario (id, nome, cpf, username, email, senha, perfil) 
values (1,'Teste', '123456' ,'Testizinho', 'teste@mail.com', 'NuCgY6/GPMQTMdNiush/UNx86FJs4rFVBcCfuzRRIREuEbf42eMqkc+ex10zbq4TK4fvrcJUpNH85V1+nUEcJg==', 2);

INSERT INTO Usuario (id, nome, cpf, username, email, senha, perfil) 
VALUES (2, 'Ale', '123.456.789-10', 'alezinho', 'ale@gmail.com', 'senha123', 3);


