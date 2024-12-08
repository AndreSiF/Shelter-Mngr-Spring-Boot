CREATE TABLE login(
                    id SERIAL NOT NULL PRIMARY KEY,
                    login varchar(100) UNIQUE NOT NULL,
                    senha varchar(100) NOT NULL,
                    permissao varchar(100)
);