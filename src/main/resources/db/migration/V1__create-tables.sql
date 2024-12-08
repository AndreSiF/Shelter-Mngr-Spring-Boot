CREATE TABLE desastre(id_desastre SERIAL,
                    uuid_desastre UUID DEFAULT gen_random_uuid(),
                    nome_desastre varchar(100) not null,
                    data date not null,
                    descricao_desastre varchar(200) not null,
                    PRIMARY KEY(id_desastre));

CREATE TABLE abrigo (id_abrigo SERIAL,
                    uuid_abrigo UUID DEFAULT gen_random_uuid(),
                    nome_abrigo varchar(100) not null,
                    endereco varchar(100) not null unique,
                    vagas int not null,
                    cadastros int,
                    PRIMARY KEY(id_abrigo));

CREATE TABLE usuario (id_usuario SERIAL,
                    uuid_usuario UUID DEFAULT gen_random_uuid(),
                    nome varchar(100) not null,
                    cpf char(14) not null unique,
                    data_cad date not null,
                    idade int not null,
                    id_abrigo int not null,
                    PRIMARY KEY(id_usuario),
                    FOREIGN KEY(id_abrigo) REFERENCES abrigo(id_abrigo));

CREATE TABLE vitima (id_vitima SERIAL,
                    id_usuario int not null,
                    ultimo_end varchar(100) not null,
                    presente BOOLEAN not null,
                    descricao varchar(200),
                    PRIMARY KEY(id_vitima),
                    FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario));

CREATE TABLE voluntario(id_volunt SERIAL,
                    id_usuario int not null,
                    endereco varchar(100) not null,
                    PRIMARY KEY(id_volunt),
                    FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario));
