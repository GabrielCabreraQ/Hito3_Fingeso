
insert into usuario (nombre_usuario,correo_usuario,contrasenia_usuario,
		telefono_usuario,fecha_nacimiento,tipo_usuario,departamento)
values
	('Isaac Espinoza','piscolix@gmail.com','cc34','975956424','1991-04-09','administrador','Valdivia'),
	('Francisco Riquelme','francisco.riquelme@usach.cl','1234','972253424','2000-04-03','administrador','Santiago'),
	('Cristobal Briceño','cristobal@gmail.com','1234','975556424','1996-09-19','arrendatario',null),
	('Rodrigo Vasquez','altoyoyo@gmail.com','1234','956956424','1990-09-19','arrendatario',null),
	('Esteban Paredes','esteban@gmail.com','1234','975955224','2002-02-23','arrendatario',null),
	('Juan Manuel','juan@gmail.com','1234','975956477','1900-05-20','administrador','Santiago'),
	('Camila Vallejos','cami.va@gob.cl','1234','947563381','1988-04-28','administrador','Santiago');

insert into arrendatario_documentos (arrendatario_id_usuario,documentos)
values (3,'Cedulo Identidad');

insert into arrendatario_tipo_licencia (arrendatario_id_usuario,tipo_licencia)
values (3,'B'), (3,'C');

insert into vehiculo (marca,modelo,anio,tipo_transmision,categoria,tipo_cuerpo,combustibleac,devuelto)
values
	('Toyota', 'Aygo', 2023, 'M', 'M', 'B', 'R', true),
	('BMW', 'Mini Cooper', 2022, 'N', 'N', 'C', 'N', true),
	('Ford', 'Fiesta', 2021, 'C', 'E', 'D', 'D', true),
	('Volkswagen', 'Golf Variant', 2023, 'A', 'H', 'W', 'Q', true),
	('Honda', 'Civic', 2020, 'B', 'C', 'V', 'H', true),
	('Tesla', 'Model 3', 2023, 'D', 'D', 'L', 'I', true),
	('Chevrolet', 'Malibu', 2019, 'M', 'I', 'S', 'E', true),
	('Mazda', 'MX-5', 2021, 'A', 'J', 'T', 'E', true),
	('Toyota', 'RAV4', 2020, 'D', 'S', 'F', 'L', true),
	('Jeep', 'Wrangler', 2023, 'M', 'R', 'J', 'S', true),
	('Hyundai', 'Santa Fe', 2021, 'D', 'F', 'X', 'A', true),
	('Ford', 'Ranger', 2022, 'M', 'G', 'P', 'B', true),
	('Audi', 'Q7', 2023, 'D', 'P', 'Q', 'F', true),
	('Porsche', 'Cayenne', 2023, 'B', 'U', 'Z', 'F', true),
	('Mercedes-Benz', 'S-Class', 2023, 'D', 'L', 'E', 'V', true),
	('Tesla', 'Model X', 2023, 'B', 'W', 'M', 'Z', true),
	('Winnebago', 'Vista', 2022, 'D', 'O', 'R', 'U', true),
	('Ford', 'Transit', 2021, 'B', 'X', 'H', 'X', true),
	('Suzuki', 'Celerio', 2018, 'M','C','C', 'N', true);

insert into catalogo (departamento)
values
	('Valdivia'),
	('Santigo'),
	('Puerto Varas');

insert into publicacion (id_vehiculo,codigoacriss,precio_normal,visibilidad,id_catalogo)
values
    (1, 'MMBR', 30000, true, 1),
    (2, 'NNCN', 50000, true, 1),
    (3, 'CEDD', 50000, true, 1),
    (4, 'AHWQ', 45000, true, 1);


insert into arriendo (cliente_id,vehiculo_id,precio,fecha_inicio,fecha_fin,conformidad,cancelado)
values
	(3,1,200000,'2024-12-24','2024-12-25',true,true),
	(4,2,190000,'2025-01-02','2025-01-20',true,true);

insert into vehiculo_disponibilidad (vehiculo_id_vehiculo,disponibilidad)
values
	(1,'2024-12-24'),
	(1,'2024-12-25'),
	(2,'2024-12-26');