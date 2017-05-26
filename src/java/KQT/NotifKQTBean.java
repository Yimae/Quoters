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
import Entity.Notif;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.NamedQuery;
import javax.persistence.Query;

@Stateless
@Local(NotifKQT.class)
public class NotifKQTBean extends GenericKQTBean<Notif, Integer> implements NotifKQT{
    public boolean createNotif(String text, String url, Integer personid){
        Notif notif = new Notif();
        notif.setText(text);
        notif.setDate(new Date());
        notif.setUrl(url);
        notif.setPersonId(personId);
        em.persist(notif);
        return true;
    }
    
    public List<Notif> notiffor(Integer personId){
        Query q = em.createQuery("SELECT n FROM Notif n WHERE n.personId =:personId ORDER BY n.date DESC");
        q.setParameter("personId", personId);
        return q.getResultList();
    }
}
