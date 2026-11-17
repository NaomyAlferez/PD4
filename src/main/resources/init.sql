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
INSERT INTO Stands VALUES (101, 9.0, 13500.0, 'OCUPADO');
INSERT INTO Stands VALUES (102, 12.0, 18000.0, 'RESERVADO');
INSERT INTO Stands VALUES (103, 6.0, 9000.0, 'DISPONIBLE');
INSERT INTO Stands VALUES (104, 15.0, 22500.0, 'BLOQUEADO');

-- Datos iniciales de Editoriales
INSERT INTO Editorial VALUES (1, 'Editorial Planeta', 'contacto@planeta.mx', '9991234567');
INSERT INTO Editorial VALUES (2, 'Penguin Random House', 'ventas@penguin.mx', '9997654321');
INSERT INTO Editorial VALUES (3, 'Fondo de Cultura Economica', 'info@fce.com.mx', '9991112233');

-- Datos iniciales de Reservaciones
INSERT INTO Reservacion VALUES (1, 101, 1, '2026-11-01', '2026-11-20', 8000.0, TRUE);
INSERT INTO Reservacion VALUES (2, 102, 2, '2026-11-10', '2026-11-18', 2000.0, TRUE);