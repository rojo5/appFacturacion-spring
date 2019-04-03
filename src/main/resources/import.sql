/*POPULATE TABLES */
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(1, 'Luis', 'Lorenzo', 'luis@luis.com', '2018-10-01', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(2, 'Selva', 'Blanco', 'selva@selva.com', '2018-09-02', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(3, 'Mario', 'Lorenzo', 'mario@mario.com', '2018-09-03', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(4, 'Guillermo', 'Barajas', 'guillermo@guillermo.com', '2018-09-04', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(5, 'Rosa', 'Cuadrado', 'rosa@rosa.com', '2018-09-05', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(6, 'Paula', 'Blanco', 'paula@paula.com', '2018-09-06', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(7, 'Isa', 'Cuadrado', 'isa@isa.com', '2018-09-07', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(8, 'Mari', 'Lopez', 'mari@mari.com', '2018-09-08', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(9, 'Santi', 'Lopez', 'santi@santi.com', '2018-09-09', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(10, 'David', 'Martin', 'david@david.com', '2018-09-10', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(11, 'Maria', 'Fernandez', 'maria@maria.com', '2018-09-11', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(12, 'Daniel', 'Halcon', 'daniel@daniel.com', '2018-09-12', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(13, 'Alberto', 'Linares', 'alberto@alberto.com', '2018-09-13', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(14, 'Joaquin', 'Precioso', 'joaquin@joaquin.com', '2018-09-14', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(15, 'Jose', 'Mota', 'jose@jose.com', '2018-09-15', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(16, 'Juan', 'Cuadrado', 'juan@juan.com', '2018-09-16', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(17, 'Alba', 'Barajas', 'alba@alba.com', '2018-09-17', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(18, 'Jorge', 'Cisneros', 'jorge@jorge.com', '2018-09-18', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(19, 'Pilar', 'Ocaña', 'pilar@pilar.com', '2018-09-19', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(20, 'Tania', 'Mate', 'tania@tania.com', '2018-09-20', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(21, 'Alicia', 'Jimenez', 'alicia@alicia.com', '2018-09-21', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(22, 'Alvaro', 'Lambea', 'alvaro@alvaro.com', '2018-09-22', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(23, 'Israel', 'Muñoz', 'israel@israel.com', '2018-09-23', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(24, 'Beatriz', 'Maldonado', 'beatriz@beatriz.com', '2018-09-24', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(25, 'Nacho', 'LaMarca', 'nacho@nacho.com', '2018-09-25', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(26, 'Alex', 'Segundo', 'alex@alex.com', '2018-09-26', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(27, 'Eva', 'Hernando', 'eva@eva.com', '2018-09-27', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(28, 'Pepe', 'Lorenzo', 'pepe@pepe.com', '2018-09-28', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(29, 'Raluca', 'Muresan', 'raluca@raluca.com', '2018-09-29', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES(30, 'Raquel', 'Benito', 'raquel@raquel.com', '2018-09-30', '');

INSERT INTO productos (nombre, precio, create_at) VALUES ("Panasonic pantalla LCD", 5000 , NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES ("Sony camara digital  DSC-W320B", 500 , NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES ("Apple iPod Shuffle", 1000 , NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES ("Sony notebook Z110",  951, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES ("Hewlett Packard multifuncional F2280", 657 , NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES ("Bianchi  bicicleta Aro 26", 3158 , NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES ("Mica comoda 5 cajones", 100 , NOW());


INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES ('Facturas equipos de oficina', null, 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES (1,1,1);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES (2,1,4);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES (1,1,5);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES (1,1,7);

INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES ('Factura bicicleta', 'Hello there!!', 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES (3, 2, 6);


INSERT INTO users (username, password, enabled) VALUES ('luis', '$2a$10$s.rdy6eepkLrZ5Gfeqe1Ce4erdEvX2V5UaOaGorc1FLyR6Au7Ogkm', 1);
INSERT INTO users (username, password, enabled) VALUES ('admin', '$2a$10$MzB3zY.TvA24VtoWUO.UCu1GPx5cDSVWVeL5o4IpyefDAJDymuWRa', 1);

INSERT INTO authorities (user_id, authority) VALUES (1, 'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES (2, 'ROLE_ADMIN');
INSERT INTO authorities (user_id, authority) VALUES (2, 'ROLE_USER');