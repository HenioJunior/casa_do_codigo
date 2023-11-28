create table tb_autor(
id bigint not null auto_increment,
nome varchar(50) not null,
email varchar(50) not null,
descricao varchar(400) not null,
data_criacao datetime not null,
primary key(id)
);
