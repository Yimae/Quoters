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
import Entity.Comm;
import Entity.Quoter;
import Entity.Quote;
import java.beans.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.ejb.Local;

@Stateless
@Local(CommKQT.class)

public class CommKQTBean extends GenericKQTBean<Comm, Integer> implements CommKQT{
    @EJB
    private NotifKQT notifKQT;
    
    @EJB
    private QuoteKQT quoteKQT;
    
    @EJB
    private QuoterKQT quoterKQT;
    
    public boolean createComm(String text, Integer personId, Integer postId){
        Comm comm = new Comm();
        comm.setDate(new Date());
        //comm.setPersonId(personId);
        comm.setCommtr(quoterKQT.findById(personId));
        comm.setPostId(postId);
        comm.setText(text);
        em.persist(comm);
        
        //dropping to native query so the dateabse takes care of race conditions; parameter is safe to pass;
        Quote quote = quoteKQT.findById(postId);
        Query q = em.createNativeQuery("update posts set popularity = (popularity + 1) where id =" + Quote.getId()+";");
        q.executeUpdate();
        
        Integer ownerId = quote.getOwnerId();
        Integer posterId = quote.getPoster().getId();
        Quoter commtr = QuoterKQT.findById(personId);
        if(ownerId != personId)
            notifKQT.createNotif(commtr.getQname() + " Commented on the post on your wall", "wall", ownerId);
        if(posterId != personId)
            notifKQT.createNotif(commtr.getQname() + "Commented on the post you have written", "wall?ownerId=" + ownerId.toString(), posterId);
        return true;
        
        public List<Comm> CommFor(Integer postId){
            Query q = em.createQuery("SELECT c FROM Comm c JOIN FETCH c.commtr WHERE c.postId=:postId ORDER BY c.id DESC");
            q.setParameter("postId", postId);
            return q.getResultList();
        }
    }
}
