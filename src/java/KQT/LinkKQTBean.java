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
import Entity.Link;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.NamedQuery;
import javax.persistence.Query;

@Stateless
@Local(LinkKQT.class)
public class LinkKQTBean extends GenericKQTBean<Link, Integer> implements LinkKQT{
    @EJB
    private QuoteKQT quoteKQT;
    
    public Link createLink(String link, Integer postId){
        if(link == null || link.equals("")) return null;
        Link linkEntity = new Link();
        linkEntity.setLink(link);
        //linkEntity.setPostId(postId)
        linkEntity.setQuote(quoteKQT.findById(postId));
        em.persist(linkEntity);
        return linkEntity;
    }
    
    @SuppressWarnings("unchecked")
    public String test(){
        Query q = em.createQuery("SELECT l FROM Link l");
        Link result = (Link)q.getSingleResult();
        return result.getLink();
        //return result.getLink();
    }
    
    /*public Admin login(String username, String password){
        Query q = em.createNamedQuery("loginAdmin");
    q.setParameter("username", username);
    q.setParameter("password", password);
    Admin a = (Admin)q.getSingleResult();
    return a;
    }*/
}
