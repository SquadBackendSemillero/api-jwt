INSERT INTO roles(rol) VALUES ('Administrador');

INSERT INTO personas(altura, dorsal, edad, enabled, peso, fecha_creacion, fecha_modificacion, id_equipo, rol_id, contrasena, correo, nombre) VALUES (NULL, NULL, NULL, 1, NULL, NOW(),NOW(), NULL, (SELECT id_rol FROM roles WHERE rol="Administrador"), "$2a$10$3DAylmUTxVeZeyqz56wrRO0pS06tAl7ryUBiU/fCrUKJ/X9srnAZW", "juan@gmail.com", "Juan Mateus Tovar");

INSERT INTO secret_keys(fecha_creacion, is_valid, secret_key) VALUES (NOW(), 1, "y5HxxnPzNvhgh0n31ML4HnNWHWZr5iwoDCCcuZ77M+vHElaw26H+FmWcVlSmqt8g9DyXgixFrVJ5onDITPrwT/RK5eGR6Gwk8NMPaXlqwwAN08zkMHqu7ya3dHJ7E+13T+SI3dEr+0LKEcr19Uddx8a/Tg015eD15xhF32sCVl0OyS0S9wVU2MAJkfk5r6ICH1oi3D1MImFBGNkpVBMP3fh1ZY6Rw9jpUUtKubIwHBMzpuervcS1AQDKJnto7Sa6fpCSloAg31EXHe94ZmOnPbMOoCP4b/rGxxtyUvMHrXVSjrr/7my86P+7204BwMnnSupTZGNlqCb88SYOIa7PKj2gjP8+dqExV1+FkAClxIe6dHuB/jYgFXxhVS75mSMdhH1muk6ndG2QI9zBlEHDTFIqaBq6t2awp0lN2Ja6C5AgSAZ9ezJiHk3MdOYkllfS5QOte6jdEzC/p1ubCg5pDVioHTeMkWG38hmiWhuBv441ntNGOLUPnQuaWGslWO/2");

INSERT INTO crypto_keys(fecha_creacion, is_valid, crypto_key) VALUES (NOW(), 1, "1Y6Ppnklpi5Z5DhQQWOln8FodOkSRUIXgjSGfrDalJw=");