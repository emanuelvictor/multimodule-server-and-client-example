drop table if exists individual cascade;
create table individual (signature TEXT not null, mutant boolean not null, primary key (signature));