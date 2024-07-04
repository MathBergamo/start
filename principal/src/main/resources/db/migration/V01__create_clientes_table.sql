CREATE TABLE clientes (
  id SERIAL PRIMARY KEY,
  email VARCHAR(100) NOT NULL UNIQUE,
  senha VARCHAR(100) NOT NULL,
  nome VARCHAR(100) NOT NULL,
  data_nascimento DATE NOT NULL,
  salario DECIMAL(15, 2) NOT NULL,
  cpf VARCHAR(11) NOT NULL UNIQUE
);

CREATE INDEX idx_clientes_email ON clientes(email);