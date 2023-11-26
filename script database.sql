CREATE DATABASE db_molar_dental_care;

USE db_molar_dental_care;

CREATE TABLE odontologos(
  id_odontologo INT NOT NULL AUTO_INCREMENT,
  apellido VARCHAR(50) NOT NULL,
  nombre VARCHAR(50) NOT NULL,
  dni VARCHAR(8) NOT NULL,
  email VARCHAR(30) NOT NULL,
  direccion VARCHAR(30) NOT NULL,
  telefono VARCHAR(9) NOT NULL,
  fecha_de_nacimiento DATE NOT NULL,
  estado BIT(1) NOT NULL,
  PRIMARY KEY (id_odontologo)
  );

CREATE TABLE rol(
  id_rol INT NOT NULL AUTO_INCREMENT,
  descripcion VARCHAR(20) NOT NULL,
  PRIMARY KEY (id_rol)
  );

CREATE TABLE usuarios (
  id_usuario INT NOT NULL AUTO_INCREMENT,
  id_rol INT NOT NULL,
  apellido VARCHAR(50) NOT NULL,
  nombre VARCHAR(50) NOT NULL,
  dni VARCHAR(8) NOT NULL,
  email VARCHAR(30) NOT NULL,
  telefono VARCHAR(30) NOT NULL,
  usuario VARCHAR(10) NOT NULL,
  password VARCHAR(10) NOT NULL,
  estado BIT(1) NOT NULL,
  PRIMARY KEY (id_usuario),
  FOREIGN KEY (id_rol) REFERENCES rol (id_rol) ON DELETE CASCADE
  );

CREATE TABLE horarios (
  id_horario INT NOT NULL AUTO_INCREMENT,
  id_odontologo INT NOT NULL,
  id_usuario INT NOT NULL,
  fecha DATE NOT NULL,
  hora_inicio VARCHAR(5) NOT NULL,
  hora_fin VARCHAR(5) NOT NULL,
  estado BIT(1) NOT NULL,
  PRIMARY KEY (id_horario),
  FOREIGN KEY (id_odontologo) REFERENCES odontologos(id_odontologo) ON DELETE CASCADE,
  FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE
  );

CREATE TABLE pacientes (
  id_paciente INT NOT NULL AUTO_INCREMENT,
  apellido VARCHAR(50) NOT NULL,
  nombre VARCHAR(50) NOT NULL,
  dni VARCHAR(8) NOT NULL,
  email VARCHAR(30) NOT NULL,
  direccion VARCHAR(30) NOT NULL,
  telefono VARCHAR(9) NOT NULL,
  fecha_de_nacimiento DATE NOT NULL,
  PRIMARY KEY (id_paciente)
  );

CREATE TABLE citas (
  id_cita INT NOT NULL AUTO_INCREMENT,
  id_horario INT NOT NULL,
  id_paciente INT NOT NULL,
  id_usuario INT NOT NULL,
  estado VARCHAR(20) NOT NULL,
  PRIMARY KEY (id_cita),
  FOREIGN KEY (id_horario) REFERENCES horarios(id_horario) ON DELETE CASCADE,
  FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE,
  FOREIGN KEY (id_paciente) REFERENCES pacientes(id_paciente) ON DELETE CASCADE
  );
  
  CREATE TABLE formaspago(
	id_formaPago INT NOT NULL AUTO_INCREMENT,
    descripcion VARCHAR(20) NOT NULL,
    PRIMARY KEY (id_formaPago)
  );
  
  CREATE TABLE pagos(
	id_pago INT NOT NULL AUTO_INCREMENT,
    id_cita INT NOT NULL,
    id_formaPago INT NOT NULL,
    fecha_pago DATE,
    monto_total DECIMAL(9,2) NOT NULL,
    estado BIT(1) NOT NULL,
    PRIMARY KEY (id_pago),
    FOREIGN KEY (id_cita) REFERENCES citas(id_cita) ON DELETE CASCADE,
    FOREIGN KEY (id_formaPago) REFERENCES formaspago(id_formaPago) ON DELETE CASCADE
  );

