package database;

import java.util.List;

public interface CRUD {
    /*
    * Una interfaz es una clase especial que establece un contrato entre ella
    * y la clase que la implementa, donde tiene que establecer sus mismos
    * métodos y en caso, su misma lógica
    */

    //Esta interfaz nos va a servir para cualquier entidad que retornemos

    public Object insert(Object obj);

    public List<Object> findAll();

    public boolean update(Object obj);

    public boolean delete(Object obj);
}
