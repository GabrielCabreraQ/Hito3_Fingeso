insert into usuario (id_usuario,nombre_usuario,correo_usuario,contrasenia_usuario,
		telefono_usuario,fecha_nacimiento,tipo_usuario,departamento)
values
	(1,'Isaac Espinoza','piscolix@gmail.com','cc34','975956424','1991-04-09','administrador','Valdivia'),
	(2,'Benjamin Vicuña','wachito@gmail.com','estoyverde','972253424','2000-04-03','administrador','Valdivia'),
	(3,'Cristobal Briceño','fothermuckers@gmail.com','googlemija','975556424','1996-09-19','arrendatario',null),
	(4,'Rodrigo Vasquez','altoyoyo@gmail.com','lavidita','956956424','1990-09-19','arrendatario',null),
	(5,'Esteban Paredes','216@gmail.com','robateunatele','975955224','2002-02-23','arrendatario',null),
	(6,'Juan Manuel Zolezzi','zolezzi@gmail.com','triplezeta','975956477','1900-05-20','administrador','Santiago'),
	(7,'Camila Vallejos','cami.va@gob.cl','elcomunismofunciona','947563381','1988-04-28','mecanico','Santiago');

insert into arrendatario_documentos (arrendatario_id_usuario,documentos)
values (3,'Cedulo Identidad');

insert into arrendatario_tipo_licencia (arrendatario_id_usuario,tipo_licencia)
values (3,'B'), (3,'C');

insert into vehiculo (id_vehiculo,marca,modelo,anio,tipo_transmision,categoria,tipo_cuerpo,combustibleac,devuelto)
values
	(1, 'Toyota', 'Aygo', 2023, 'M', 'M', 'B', 'R', true),
	(2, 'BMW', 'Mini Cooper', 2022, 'N', 'N', 'C', 'N', true),
	(3, 'Ford', 'Fiesta', 2021, 'C', 'E', 'D', 'D', true),
	(4, 'Volkswagen', 'Golf Variant', 2023, 'A', 'H', 'W', 'Q', true),
	(5, 'Honda', 'Civic', 2020, 'B', 'C', 'V', 'H', true),
	(6, 'Tesla', 'Model 3', 2023, 'D', 'D', 'L', 'I', true),
	(7, 'Chevrolet', 'Malibu', 2019, 'M', 'I', 'S', 'E', true),
	(8, 'Mazda', 'MX-5', 2021, 'A', 'J', 'T', 'E', true),
	(9, 'Toyota', 'RAV4', 2020, 'D', 'S', 'F', 'L', true),
	(10, 'Jeep', 'Wrangler', 2023, 'M', 'R', 'J', 'S', true),
	(11, 'Hyundai', 'Santa Fe', 2021, 'D', 'F', 'X', 'A', true),
	(12, 'Ford', 'Ranger', 2022, 'M', 'G', 'P', 'B', true),
	(13, 'Audi', 'Q7', 2023, 'D', 'P', 'Q', 'F', true),
	(14, 'Porsche', 'Cayenne', 2023, 'B', 'U', 'Z', 'F', true),
	(15, 'Mercedes-Benz', 'S-Class', 2023, 'D', 'L', 'E', 'V', true),
	(16, 'Tesla', 'Model X', 2023, 'B', 'W', 'M', 'Z', true),
	(17, 'Winnebago', 'Vista', 2022, 'D', 'O', 'R', 'U', true),
	(18, 'Ford', 'Transit', 2021, 'B', 'X', 'H', 'X', true),
	(19, 'Suzuki', 'Celerio', 2018, 'M','C','C', 'N', true);

insert into catalogo (id,departamento)
values
	(1,'Valdivia'),
	(2,'Santigo'),
	(3,'Puerto Varas');

insert into publicacion (id_publicacion,vehiculo_id,codigoacriss,precio_normal,visibilidad,catalogo_id)
values
	(1,1,null,30000, true, 1),
	(2,2,null,50000, true, 1),-- mi idea es que el codigo acrris se genere automaticamente con los datos del auto
	(3,3,null,50000, false, 1),
	(4,4,null,45000, true, 1),
	(5,5,null,35000, true, 2);


insert into arriendo (id,cliente_id,vehiculo_id,precio,fecha_inicio,fecha_fin,conformidad,cancelado)
values
	(1,3,1,200000,'2024-12-24','2024-12-25',true,true),
	(2,4,2,190000,'2025-01-02','2025-01-20',true,true);

insert into vehiculo_disponibilidad (vehiculo_id_vehiculo,disponibilidad)
values
	(1,'2024-12-24'),
	(1,'2024-12-25'),
	(2,'2024-12-26');