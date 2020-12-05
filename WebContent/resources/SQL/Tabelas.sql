-- Criar o BD 'curso-jsp'
/*
CREATE DATABASE "curso-jsp"
  WITH ENCODING='UTF8'
       OWNER=postgres
       CONNECTION LIMIT=-1
       TABLESPACE=pg_default;
*/

-- inserir autoincremento para a coluna 'id' na tabela 'usuario' --
CREATE SEQUENCE user_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

-- Criação tabela 'Usuario'
CREATE TABLE usuario(
  id BIGINT NOT NULL DEFAULT nextval('user_id_seq'::regclass),
  login character varying(255),
  senha character varying(255),
  nome character varying(255),
  email character varying(255),
  cep character varying(255),
  rua character varying(255),
  bairro character varying(255),
  cidade character varying(255),
  uf character varying(255),
  fotobase64 character varying(255),
  contenttype character varying(255),
  curriculobase64 character varying(255),
  contenttypecurriculo character varying(255),
  CONSTRAINT user_pkey PRIMARY KEY (id)
);

-- Inserir autoincremento para a tabela produto, campo 'id'
CREATE SEQUENCE prod_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

-- Criar tabela 'produto';

CREATE TABLE public.produto
(
  id bigint NOT NULL DEFAULT nextval('prod_id_seq'::regclass),
  nome character varying(255),
  codigo bigint NOT NULL,
  preco double precision,
  estoque double precision,
  CONSTRAINT prod_pkey PRIMARY KEY (codigo)
);

-- Inserir autoincremento para a tabela telefone, campo 'id'
CREATE SEQUENCE tel_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

-- Criar tabela 'Telefone'
CREATE TABLE telefone(
  id BIGINT NOT NULL DEFAULT nextval('tel_id_seq'::regclass),
  numero character varying(20),
  tipo character varying(20),
  usuario BIGINT,
  CONSTRAINT tel_pkey PRIMARY KEY (id)
);
