-- gerando_tabelas

CREATE TABLE IF NOT EXISTS categoria(
	id BIGINT PRIMARY KEY NOT NULL,
	nome VARCHAR(15) NOT NULL
);

CREATE TABLE IF NOT EXISTS tag(
	id BIGINT PRIMARY KEY NOT NULL,
	nome VARCHAR(15) NOT NULL
);
	CREATE TABLE IF NOT EXISTS endereco(
	id BIGINT PRIMARY KEY NOT NULL,
	cep VARCHAR(8) NOT NULL,
	numero VARCHAR(10) NOT NULL,
	rua VARCHAR(50) NOT NULL,
	bairro VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS
	estabelecimento (
		id BIGINT PRIMARY KEY NOT NULL,
		cnpj VARCHAR(14) UNIQUE NOT NULL,
		nome_fantasia VARCHAR (50) NOT NULL,
		razao_social VARCHAR (50) NOT NULL,
		email VARCHAR(35) NOT NULL,
		senha VARCHAR(50) Not NULL,
		telefone1 VARCHAR(14) NOT NULL,
		telefone2 VARCHAR(14),
		logo VARCHAR(240),
		id_endereco BIGINT NOT NULL,
			CONSTRAINT fk_endereco
		 		FOREIGN KEY (id_endereco)
					REFERENCES ENDERECO(id)
	);

CREATE TABLE IF NOT EXISTS evento(
	id BIGINT PRIMARY KEY NOT NULL,
	id_estabelecimento BIGINT NOT NULL,
	CONSTRAINT fk_estabelecimento
		FOREIGN KEY(id_estabelecimento)
			REFERENCES estabelecimento(id),

	titulo VARCHAR(120) NOT NULL,
	data TIMESTAMP NOT NULL,
	descricao VARCHAR(250),
	faixa_precos INT,
	poster VARCHAR(250),
	atracoes VARCHAR (120),
	recorrente BOOLEAN NOT NULL DEFAULT FALSE,
	cobra_couvert BOOLEAN NOT NULL DEFAULT TRUE,
	cobra_entrada BOOLEAN NOT NULL DEFAULT FALSE,
	valor_entrada DECIMAL(3, 2) NOT NULL,
	valor_couvert DECIMAL(3, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS tag_evento(
	id_tag BIGINT NOT NULL,
	CONSTRAINT fk_tag
		FOREIGN KEY (id_tag)
			REFERENCES tag (id),
	
	id_evento BIGINT NOT NULL,
	CONSTRAINT fk_evento
		FOREIGN KEY (id_evento)
			REFERENCES evento (id)
);

CREATE TABLE IF NOT EXISTS estabelecimento_categoria(
	id_estabelecimento BIGINT NOT NULL,
	CONSTRAINT fk_estabelecimento
		FOREIGN KEY (id_estabelecimento)
			REFERENCES estabelecimento(id),
	
	id_categoria BIGINT NOT NULL,
	CONSTRAINT fk_categoria
		FOREIGN KEY (id_categoria)
			REFERENCES categoria(id)
);



