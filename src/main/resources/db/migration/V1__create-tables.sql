CREATE TABLE abrigo (id_abrigo SERIAL,
                    uuid_abrigo UUID DEFAULT gen_random_uuid(),
                    nome_abrigo varchar(100) not null,
                    endereco varchar(100) not null,
                    vagas int not null,
                    cadastros int,
                    PRIMARY KEY(id_abrigo));

CREATE TABLE usuario (id_usuario SERIAL,
                    uuid_usuario UUID DEFAULT gen_random_uuid(),
                    nome varchar(100) not null,
                    cpf char(14) not null,
                    data_cad date not null,
                    idade int not null,
                    id_abrigo int not null,
                    PRIMARY KEY(id_usuario),
                    FOREIGN KEY(id_abrigo) REFERENCES abrigo(id_abrigo));

CREATE TABLE vitima (id_vitima SERIAL,
                    uuid_vitima UUID DEFAULT gen_random_uuid(),
                    id_usuario int not null,
                    ultimo_end varchar(100) not null,
                    presente BOOLEAN not null,
                    PRIMARY KEY(id_vitima),
                    FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario));

CREATE TABLE voluntario(id_volunt SERIAL,
                    uuid_volunt UUID DEFAULT gen_random_uuid(),
                    id_usuario int not null,
                    endereco varchar(100) not null,
                    PRIMARY KEY(id_volunt),
                    FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario));

CREATE TABLE riscos(id_riscos SERIAL,
                    uuid_riscos UUID DEFAULT gen_random_uuid(),
                    id_vitima int not null,
                    frio BOOLEAN not null,
                    nutricao BOOLEAN not null,
                    desidrat BOOLEAN not null,
                    machucado BOOLEAN not null,
                    PRIMARY KEY(id_riscos),
                    FOREIGN KEY(id_vitima) REFERENCES vitima(id_vitima));

CREATE TABLE desastre(id_desastre SERIAL,
                    uuid_desastre UUID DEFAULT gen_random_uuid(),
                    id_vitima int not null,
                    nome_desastre varchar(100) not null,
                    data_desastre date not null,
                    PRIMARY KEY(id_desastre),
                    FOREIGN KEY(id_vitima) REFERENCES vitima(id_vitima));

CREATE TABLE perm(id_perm SERIAL,
                    uuid_perm UUID DEFAULT gen_random_uuid(),
                    nome_perm varchar(50) not null,
                    PRIMARY KEY(id_perm));

CREATE TABLE usuario_perm(id_us_perm SERIAL,
                    uuid_us_perm UUID DEFAULT gen_random_uuid(),
                    id_usuario int not null, id_perm int not null,
                    PRIMARY KEY(id_us_perm),
                    FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario),
                    FOREIGN KEY(id_perm) REFERENCES perm(id_perm));
