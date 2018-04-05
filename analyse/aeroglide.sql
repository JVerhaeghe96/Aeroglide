-- *********************************************
-- * SQL PostgreSQL generation                 
-- *--------------------------------------------
-- * DB-MAIN version: 10.0.3              
-- * Generator date: Aug 17 2017              
-- * Generation date: Thu Apr  5 10:03:22 2018 
-- * LUN file: C:\Users\Marine\Desktop\glide08\analyse\aeroglide.lun 
-- * Schema: logique/2 
-- ********************************************* 


-- Database Section
-- ________________ 



-- Tables Section
-- _____________ 

create table Pilotes (
     idPilote serial not null,
     nom varchar(20) not null,
     prenom varchar(20) not null,
     email varchar(150),
     rue varchar(20) not null,
     numero varchar(5) not null,
     localite varchar(20) not null,
     codePostal numeric not null,
     noGsm varchar(20),
     solde real not null,
     constraint ID_Pilotes primary key (idPilote));

create table TypePlaneur (
     planeur varchar(20) not null,
     tarifHoraire real not null,
     coutRemorquage real not null,
     constraint ID_TypePlaneur primary key (planeur));

create table Vols (
     noVol serial not null,
     duree numeric not null,
     date date not null,
     cout real not null,
     idPilote serial not null,
     planeur varchar(20) not null,
     constraint ID_Vols primary key (noVol));


-- Constraints Section
-- ___________________ 

alter table Vols add constraint FKeffectuer
     foreign key (idPilote)
     references Pilotes;

alter table Vols add constraint FKcouter
     foreign key (planeur)
     references TypePlaneur;


-- Index Section
-- _____________ 

