package fr.charly.businessCase.service.interfaces;

import java.security.Principal;
import java.util.List;

public interface ServicePrincipalInterface<T, ID, C, U> {

    List<?> list();

    T create(C o, Principal principal);

    T update(U o, ID id, Principal principal);

    Boolean delete(ID id);

    T findOneById(ID id);

}
