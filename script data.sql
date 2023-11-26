USE db_molar_dental_care;

-- Roles
INSERT INTO rol(descripcion) VALUES('Administrador'), ('Asistente');

-- Formas de pago
INSERT INTO formaspago(descripcion) VALUES('Efectivo'), ('Tarjeta');

-- pacientes
INSERT INTO pacientes(apellido, nombre, dni, email, direccion, telefono, fecha_de_nacimiento) 
VALUES('Perez', 'Andres', '23094013', 'andres@gmail.com', 'av. las palmeras', '983405193', '2000-03-12'),
('Torres', 'Ariana', '73930192', 'ariana@gmail.com', 'los jardines 203', '904832758', '2001-05-20');

-- Odontologos
INSERT INTO odontologos(apellido, nombre, dni, email, direccion, telefono, fecha_de_nacimiento, estado) 
VALUES('Vega', 'Miguel', '74283707', 'miguel@gmail.com', 'montufar 1024', '931030257', '2001-03-24', 0),
('Perez', 'Soffia', '72839405', 'soffia@gmail.com', 'los jardines 203', '904832252', '2000-10-14', 1);

-- Usuarios
INSERT INTO usuarios(id_rol, apellido, nombre, dni, email, telefono, usuario, password, estado) 
VALUES(1, 'Vasquez', 'Leon', '74203217', 'miguel@gmail.com', '931030257','admin', '1234', 1),
(2, 'Torres', 'Fernando', '71214559', 'fernando@gmail.com', '912050214','fernando', '1234', 0);

-- Horarios de atención
INSERT INTO horarios(id_odontologo, id_usuario,fecha, hora_inicio, hora_fin, estado) 
VALUES(1, 1, '2023-11-24' ,'12:00', '13:00', 1),
(1, 2, '2023-11-28','13:00', '14:00', 1);

-- Citas 
INSERT INTO citas(id_horario, id_paciente, id_usuario, estado)
VALUES(1, 2, 1, 'pendiente'),
(2, 1, 1, 'atendida');

-- Pagos
INSERT INTO pagos(id_cita, id_formaPago, monto_total, estado)
VALUES(1, 1, 30.0, 0);
INSERT INTO pagos(id_cita, id_formaPago, fecha_pago ,monto_total, estado)
VALUES(2, 2, '2023-11-24', 35.0, 1);

-- Select a todas las tablas 
SELECT * FROM rol;
SELECT * FROM formaspago;
SELECT * FROM pacientes;
SELECT * FROM odontologos;
SELECT * FROM usuarios;
SELECT * FROM horarios;
SELECT * FROM citas;
SELECT * FROM pagos;
