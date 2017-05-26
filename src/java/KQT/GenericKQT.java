/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KQT;

/**
 *
 * @author Kva
 */
import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;

public interface GenericKQT<T, ID extends Serializable> {
    public Class<T> getEntityType();
    public T findById(ID id);
    public List<T> findAll();
    public List<T> findBy(String query);
    public T persist(T entity);
    public T merge(T entity);
    public void remove(T entity);
    public void flush();
    public void claer();
}
