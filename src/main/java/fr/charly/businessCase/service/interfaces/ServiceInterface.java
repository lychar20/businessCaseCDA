package fr.charly.businessCase.service.interfaces;

public interface ServiceInterface <T, ID, C, U>  {

    T create(C o);

    T update(U o, ID id);

    Boolean delete(ID id);

    T findOneById(ID id);

}
