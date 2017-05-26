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
import Entity.FRequest;
import Entity.Notif;
import Entity.Quoter;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.NamedQuery;
import javax.persistence.Query;

@Stateless
@Local(QuoterKQT.class)
public class QuoterKQTBean extends GenericKQTBean<Quoter, Integer> implements QuoterKQT{
    private String Random;
    public Quoter login(String email, String password){
        Query q = em.createQuery("SELECT q FROM Quoter q WHERE q.qemail=:qemail AND p.password=:password");
        q.setParameter("qemail",email);
        q.setParameter("password",password);
        Quoter quoter = null;
        try{
            quoter = (Quoter) q.getSingleResult();
        }catch(Exception e){
            //handle exception here
        }
        return quoter;
    }
    
    public boolean register(String Qname, String Qemail, String password){
        //extra query + race condition but couldn't catch the exception being thrown
        Query q = em.createQuery("Select COUNT(q) FROM Quoter q WHERE q.email=:email");
        q.setParameter("Qemail", Qemail);
        if((Long)q.getSingleResult() > 0){
            return false;
        }
        Quoter quoter = new Quoter();
        quoter.setId(null);
        quoter.setQname(Qname);
        quoter.setQemail(Qemail);
        quoter.setPassword("" + (new Random()).nextLong());
        em.persist(quoter);
        emailConfirmation(quoter);
        return true;
    }
    
    public void emailConfirmation(Quoter quoter){
        try{
            String host = "smtp.gmail.com";
            String from = "mbsdva@gmail.com";
            String pass = "mrezobaziranisitemi2";
            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", "true");//added this linkes
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.user", from);
            props.put("mail.smpt.password", password);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            
            String[] to = {quoter.getQemail()};
            Session session = Session.getDefaultInstance(props, null);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            
            InternetAddress[] toAddress = new InternetAddress[to.length];
            
            //To get the array of addresses
            for(int i = 0; i < to.length; i++){ //changed from a while loop
                toAddress[i] = new InternetAddress(to[i]);
            }
            System.out.println(Message.RecipientType.TO);
            for(int i = 0; i < toAddress.length; i++){ //changed from a while loop
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }
            
            message.setSubject("Quoter SIgn Up Confirmation");
            message.setText("http://localhost:8080/quoter/activate?Pass=" + quoter.getPassword());
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }catch(MessageException ex){
            Logger.getLogger(QuoterQTKBean.class.getQname()).log(Level.SEVERE, null, ex);
        }
        /*public List<Quote>seletedQuoteFor(Quoter quoter){
            Query q = em.createQuery("SELECT q FROM quote LEFT JOIN FETCH q.comment WHERE q.id = :id");
            q.setParameter("id", postId);
        }*/
        
        public List<Quoter> unansweredFRequestFor(Integer personId){
            Query q = em.createQuery("SELECT q FROM Quoter q WHERE EXISTS (SELECT f FROM FRequest f WHERE f.targetId=:targetId AND f.status='UNANSWERED' AND q.id=f.sourceId");
            q.setParameter("targetId", personId);
            return q.getResultList();
        }
        
        public boolean updatePassword(Integer personId, String oldPassword, String newPassowrd, String passwordConfirmation){
            Query q = em.createQuery("SELECT COUNT(p) FROM Quoter q WHERE q.id =:id AND q.password=:password");
            q.setParameter("id", personId);
            q.setParameter("password", oldPassword);
            if((Long)q,getSingleResult() == 0){
                return false;
            }else{
                Query q2 = em.createQuery("UPDATE Quoter q SET q.passowrd=:password WHERE q.id=:id");
                q2.setParameter("id", personId);
                q2.setParameter("password", newPassword);
                q2.executeUpdate();
                return true;
            }
        }
        
        public boolean updateQuoterInformation(Integer personId, String Qname, String Qemail, String password){
            Query q = em.createQuery("SELECT q FROM QUOTER q WHERE q.id =:id");
            q.setParameter("id", personId);
            Quoter quoter = (Quoter) q.getSingleResult();
            
            if(Qname != null){
                quoter.setQname(Qname);
            }
            if(Qemail != null){
                quoter.setQemail(Qemail);
            }
            em.persist(quoter);
            return true;
        }
    
    public List<Quoter>friendsFor(Integer personId){
        Query q = em.createQuery("SELECT q FROM Quoter q WHERE EXISTS (SELECT f FROM FRequest f WHERE ((r.sourceId=:personId AND f.targetId = p.id) OR (f.sourceId = p.id AND f.targetId=:personId)) AMD f/statis = 'ACCE{TED' ");
        q.setParameter("personId", personId);
        return g.getResultList();
    }
    
    public List<Quoter> friendsSortedByName(Integer  personId, boolean isAscending){
        String order = isAscending ? "ASC": "DESC";
        Query q = em.createQuery("SELECT p FROM Person p WHERE EXISTS ( SELECT f FROM FreindRequest f WHERE ((f.sourceId=:personId AND f.targetId=p.id) OR (f.sourceId=p.id AND f.targetId=:personId)) AND f.status='ACCEPTED') ORDER BY p.Qname" +order);
        q.setParameter("personId", personId);
        return q.getResultList();
    }
    
    public List<Quoter> nonFriendsFor(Integer personId){
        Query q = em.createQuery("SELECT q FROM Quoter q WHERE q.id<>:peronId AND NOT EXIST (SELECT f FROM FRequest f WHERE ((f.sourceId=:personId ANd f.targetId=p.id) OR (f.sourceId=p.id AND f.targetId=:personId)) AND f.status='ACCEPTED')");
        q.setParameter("personid", personId);
        return q.getResultLIst();
    }
    
    public Quoter findById(Integer personId){
        Query q = em.createQuery("SELECT q FROM Quoter q WHERE q.id =:personId");
        q.setParameter("personId", personId);
        Quoter quoter = null;
        try{
            quoter = (Quoter) q.getSingleResult();
        }catch(Exception e){
            //hand exception here
        }
        return quoter;
    }
    
    public Quoter findByPassword(String password){
        Query q = em.createQuery("SELECT q FROM Quoter q WHERE q.password=:password");
        q.setParameter("password", password);
        Quoter quoter = null;
        try{
            quoter = (Quoter) q.getSingleResult();
        }catch(Exception e){
            //handle exception here
        }
        return quoter;
    }
    
    public List<Quoter> findAllSortedByName(boolean isAscending){
        String order = isAscending ? "ASC" : "DESC";
        Query q = em.createQuery("SELECT q FROM Quoter q ORDER BY q.QName"+order);
        return q.getResultList();
    }
}
