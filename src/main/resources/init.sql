CREATE TABLE IF NOT EXISTS Stands (
    id INT PRIMARY KEY,
    metros_cuadrados DOUBLE NOT NULL,
    precio DOUBLE NOT NULL,
    estado VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS Editorial (
    id INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS Reservacion (
    id INT PRIMARY KEY,
    id_stand INT NOT NULL,
    id_editorial INT NOT NULL,
    fecha_reserva DATE NOT NULL,
    fecha_vencimiento DATE NOT NULL,
    monto_pagado DOUBLE NOT NULL,
    aplica_pronto_pago BOOLEAN NOT NULL,
    FOREIGN KEY (id_stand) REFERENCES Stands(id),
    FOREIGN KEY (id_editorial) REFERENCES Editorial(id)
);

-- Datos iniciales de Stands
MERGE INTO Stands KEY(id) VALUES (101, 9.0, 13500.0, 'OCUPADO');
MERGE INTO Stands KEY(id) VALUES (102, 12.0, 18000.0, 'RESERVADO');
MERGE INTO Stands KEY(id) VALUES (103, 6.0, 9000.0, 'DISPONIBLE');
MERGE INTO Stands KEY(id) VALUES (104, 15.0, 22500.0, 'BLOQUEADO');

-- Datos iniciales de Editoriales
MERGE INTO Editorial KEY(id) VALUES (1, 'Editorial Planeta', 'contacto@planeta.mx', '9991234567');
MERGE INTO Editorial KEY(id) VALUES (2, 'Penguin Random House', 'ventas@penguin.mx', '9997654321');
MERGE INTO Editorial KEY(id) VALUES (3, 'Fondo de Cultura Economica', 'info@fce.com.mx', '9991112233');

-- Datos iniciales de Reservaciones
MERGE INTO Reservacion KEY(id) VALUES (1, 101, 1, '2026-11-01', '2026-11-20', 8000.0, TRUE);
MERGE INTO Reservacion KEY(id) VALUES (2, 102, 2, '2026-11-10', '2026-11-18', 2000.0, TRUE);