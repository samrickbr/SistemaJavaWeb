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
  ativo boolean,
  fotobase64 character varying,
  fotobase64miniatura character varying,
  contenttype character varying(255),
  curriculobase64 character varying,
  contenttypecurriculo character varying,
  CONSTRAINT user_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE produto_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE produto_id_seq
    OWNER TO postgres;

CREATE TABLE IF NOT EXISTS produto
(
	barras bigint,
    id bigint NOT NULL DEFAULT nextval('produto_id_seq'::regclass),
    ativo boolean,
    nome character varying(255) COLLATE pg_catalog."default",
    preco double precision,
    estoque double precision,
    CONSTRAINT prod_pkey PRIMARY KEY (id)
);

ALTER TABLE public.produto
    OWNER to postgres;

CREATE TABLE telefone(
  id BIGSERIAL NOT NULL,
  numero character varying(20),
  tipo character varying(20),
  usuario BIGINT,
  CONSTRAINT tel_pkey PRIMARY KEY (id)
);