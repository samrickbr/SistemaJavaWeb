CREATE TABLE usuario(
  id BIGSERIAL NOT NULL,
  login character varying(255),
  senha character varying(255),
  nome character varying(255),
  email character varying(255),
  cep character varying(255),
  rua character varying(255),
  bairro character varying(255),
  cidade character varying(255),
  uf character varying(255),
  fotobase64 character varying,
  fotobase64miniatura character varying,
  contenttype character varying(255),
  curriculobase64 character varying(255),
  contenttypecurriculo character varying,
  CONSTRAINT user_pkey PRIMARY KEY (id)
);

CREATE TABLE produto(
codigo BIGSERIAL NOT NULL,
barras BIGINT,
nome character varying(255),
preco FLOAT,
estoque FLOAT,
CONSTRAINT prod_pkey PRIMARY KEY (codigo)
);

CREATE TABLE telefone(
  id BIGSERIAL NOT NULL,
  numero character varying(20),
  tipo character varying(20),
  usuario BIGINT,
  CONSTRAINT tel_pkey PRIMARY KEY (id)
);