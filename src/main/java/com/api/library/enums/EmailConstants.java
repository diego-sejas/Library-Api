package com.api.library.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmailConstants {
    NEW_USER_WELCOME("Te damos la bienvenida a BIBLIOTECA UNLa!"),
    NEW_CONTACT_WELCOME("Formulario de contacto recibido"),
    NEW_USER_BODY("Agradecemos que quieras formar parte de nuestra biblioteca, tu usuario fue registrado con éxito en nuestra plataforma. Ante cualquier consulta, no dudes en contactarnos."),
    NEW_CONTACT_BODY("Muchas gracias por escribirnos! BILBIOTECA UNLa se contactará con usted muy pronto.");

    private final String msg;
}
