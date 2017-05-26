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
import Entity.Quoter;
import Entity.Quote;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.NamedQuery;
import javax.persistence.Query;

@Stateless
@Local(QuoteKQT.class)
public class QuoteKQTBean extends GenericKQTBean<Quote, Integer> implements QuoteKQT{
    @EJB
    private NotifKQT notif;
    
    @EJB
    private QuoterKQT quoterKQT;
    
    @EJB
    private LinkKQT linkKQT;
    
    public boolean createQuote(String title, String text, Integer posterId, Integer ownerId, String link){
        Quote quote = new Quote();
        quote.setTitle(title);
        quote.setText(text);
        //quote.setPosterId(posterId);
        quote.setQuoter(QuoterKQT.findById(posterId));
        quote.setOwnerId(ownerId);
        quote.setDate(new Date());
        quote.setPopularity(0);
        //em.persist(post);
        
        //quote = findByAttributes(quote.getTitle(), quote.getText(), quote.getOwnerId(), quote.getDate());
        
        if(link != null && !link.equals("")){
            //linkKQT.createLink(link, post.getId());
            Set<Link> links = new HashSet<Link>();
            Link linkEntity = new Link();
            linkEntity.setLink(link);
            linkEntity.setQuote(quote);
            links.add(linkEntity);
            quote.setLinks(links);
        }
        em.persist(quote);
        
        Quoter quoter = quoterKQT.findById(posterId);
        NotifKQT.createNotif(quoter.getQname(), "wall", ownerId);
        return true;
    }
    
    public Quote findByAttribute(String title, String text, Integer ownerId, Date date){
        Query q = em.createQuery("SELECT q FROM Quote q WHERE q.title = title AND q.text =:text AND q.ownerId=:ownerId AND q.date=:date");
        q.setParameter("title", title);
        q.setParameter("text", text);
        q.setParameter("ownerId", ownerId);
        q.setParameter("date", date);
        return (Quote)q.getSingleResult();
    }
}
