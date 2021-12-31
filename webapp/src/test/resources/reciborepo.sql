DROP TABLE IF EXISTS encabezadoRecibo; 
CREATE TABLE encabezadoRecibo ( 
	id Integer,
	nroAgencia varchar(255),
	titularAgencia varchar(255),
	fecha timestamp WITHOUT time zone,
	nroSubagente Integer,
	subagente varchar(255),
	domicilio varchar(255),
	telefono varchar(255),
	total double PRECISION,
	tituloSubagente varchar(255),
	nroRecibo Integer,
	tipoComprobante varchar(255),
	nombreComprobante varchar(255),
	PRIMARY KEY(id)
);

DROP TABLE IF EXISTS renglonesRecibo;
CREATE TABLE renglonesRecibo (
	recibo_id Integer,
	texto varchar(255),
	importe double precision
);

DROP TABLE IF EXISTS extraRecibo;
CREATE TABLE extraRecibo (
	recibo_id Integer,
	texto varchar(255)
);
