create database NMCNPM
go 
use NMCNPM

create table userpass (id int primary key identity not null,
						email text null,
						pass text null,
						isRemove bit null,
						);	