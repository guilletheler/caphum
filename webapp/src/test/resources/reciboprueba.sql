delete from ENCABEZADORECIBO;
INSERT INTO ENCABEZADORECIBO
(NROAGENCIA, TITULARAGENCIA, FECHA, NROSUBAGENTE, SUBAGENTE, DOMICILIO, TELEFONO, TOTAL, TITULOSUBAGENTE, NRORECIBO)
VALUES('8774', 'Stessens María Mónica', '2020-11-05', 2, 'Juan de los Palotes', 'Por ahí', '123', 654.21, 'SUBAGENTE', 1);

delete from public.renglonesRecibo;

INSERT INTO RENGLONESRECIBO
(NRORECIBO, TEXTO, IMPORTE)
VALUES (1, 'RENGLON 1', 500),(1, 'RENGLON 2', 700),(1, 'RENGLON 3', 200);