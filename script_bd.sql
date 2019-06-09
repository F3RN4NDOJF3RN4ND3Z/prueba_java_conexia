create database conexia_test;
create table CLIENTE(
	ID_CLIENTE SERIAL primary key,
	NOMBRE VARCHAR(255) not null,
	APELLIDO1 VARCHAR(255) null,
	APELLIDO2 VARCHAR(255) null,
	OBSERVACIONES VARCHAR(255) null
);

create table MESA(
	ID_MESA SERIAL primary key,
	NUM_MAX_COMENSALES numeric not null,
	UBICACION VARCHAR(255) null
	
);

create table COCINERO(
	ID_COCINERO SERIAL primary key,
	NOMBRE VARCHAR(255) not null,
	APELLIDO1 VARCHAR(255) null,
	APELLIDO2 VARCHAR(255) null
	
);

create table CAMARERO(
	ID_CAMARERO SERIAL primary key,
	NOMBRE VARCHAR(255) not null,
	APELLIDO1 VARCHAR(255) null,
	APELLIDO2 VARCHAR(255) null
	
);


create table FACTURA(
	ID_FACTURA SERIAL primary key,
	ID_CLIENTE integer not null,
	ID_CAMARERO integer not null,
	ID_MESA integer not null,
	FECHA_FACTURA date not null default CURRENT_DATE,
  CONSTRAINT facturas_cliente FOREIGN KEY (ID_CLIENTE)
      REFERENCES CLIENTE (ID_CLIENTE) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT factura_camarero FOREIGN KEY (ID_CAMARERO)
      REFERENCES CAMARERO (ID_CAMARERO) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO action,
  CONSTRAINT factura_mesa FOREIGN KEY (ID_MESA)
      REFERENCES MESA (ID_MESA) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


create table DETALLE_FACTURA(
	ID_DETALLE_FACTURA SERIAL,
	ID_FACTURA integer not null,
	ID_COCINERO integer not null,
	PLATO varchar(255) not null,
	IMPORTE numeric not null,
  PRIMARY KEY (ID_DETALLE_FACTURA, ID_FACTURA),
  CONSTRAINT detalle_factura_factura FOREIGN KEY (ID_FACTURA)
      REFERENCES FACTURA (ID_FACTURA) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT detalle_factura_cocinero FOREIGN KEY (ID_COCINERO)
      REFERENCES COCINERO (ID_COCINERO) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO action
 
);

insert into cliente (nombre,apellido1,apellido2) values('prueba','prueba1','prueba1');
insert into cliente (nombre,apellido1,apellido2) values('prueba2','prueba2 apellido1','prueba2 apellido2');
insert into cliente (nombre,apellido1,apellido2) values('prueba3','prueba3 apellido1','prueba3 apellido2');

insert into mesa (num_max_comensales,ubicacion) values(2,'ubicacion1');
insert into mesa (num_max_comensales,ubicacion) values(3,'ubicacion1');
insert into mesa (num_max_comensales,ubicacion) values(1,'ubicacion1');
insert into mesa (num_max_comensales,ubicacion) values(4,'ubicacion4');

insert into cocinero (nombre,apellido1,apellido2) values('cocinero_prueba','prueba1','prueba1');
insert into cocinero (nombre,apellido1,apellido2) values('cocinero_prueba2','prueba2 apellido1','prueba2 apellido2');
insert into cocinero (nombre,apellido1,apellido2) values('cocinero_prueba3','prueba3 apellido1','prueba3 apellido2');

insert into camarero (nombre,apellido1,apellido2) values('camarero_prueba','prueba1','prueba1');
insert into camarero (nombre,apellido1,apellido2) values('camarero_prueba2','prueba2 apellido1','prueba2 apellido2');
insert into camarero (nombre,apellido1,apellido2) values('camarero_prueba3','prueba3 apellido1','prueba3 apellido2');

insert into factura (id_cliente,id_camarero,id_mesa) values(1,1,1);
insert into factura (id_cliente,id_camarero,id_mesa) values(2,2,2);
insert into factura (id_cliente,id_camarero,id_mesa) values(3,3,3);
insert into factura (id_cliente,id_camarero,id_mesa) values(3,3,4);

insert into detalle_factura (id_cocinero,id_factura,plato,importe) values(1,1,'plato prueba factura1',250000);
insert into detalle_factura (id_cocinero,id_factura,plato,importe) values(2,2,'plato prueba factura1',80000);
insert into detalle_factura (id_cocinero,id_factura,plato,importe) values(1,3,'plato prueba factura1',250000);
insert into detalle_factura (id_cocinero,id_factura,plato,importe) values(1,4,'plato prueba factura4',150000);


select c.id_camarero,c.nombre,c.apellido1,c.apellido2,SUM (df.importe) total_facturado from camarero c
inner join factura f on f.id_camarero=c.id_camarero
inner join detalle_factura df on df.id_factura=f.id_factura
group BY(c.id_camarero,c.nombre,c.apellido1,c.apellido2);

select c.id_cliente,c.nombre,c.apellido1,c.apellido2,SUM(df.importe) total_gastado from cliente c 
inner join factura f on f.id_cliente=c.id_cliente
inner join detalle_factura df on df.id_factura=f.id_factura

group by (c.id_cliente,c.nombre,c.apellido1,c.apellido2)
having SUM(df.importe) > 100000;


