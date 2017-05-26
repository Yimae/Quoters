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
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.NamedQuery;
import javax.persistence.Query;

@Stateless
@Local(FRequestKQT.class)
public class FRequestKQTBean extends GenericKQTBean<FRequest, Integer> implements FRequestKQT{
    @EJB
    private QuoterKQT quoterKQT;
    
    public boolean createFRequest(Integer sourceId, Integer targetid){
        //if already requested
        Query q = em.createQuery("SELECT COUNT(f) FROM FRequest f WHERE f.srouceId = :sourceId AND f.targetId=:targetId AND f.status<> 'DECLINED'");
        q.setParameter("sourceId", sourceId);
        q.setParameter("targetId", targetId);
        if((Long) q.getSingleResult() > 0){
            return false;
        }
        //this is not a mistake - ross requesting
        Query q2 = em.createQuery("SELECT COUNT(f) FROM FRequest f WHERE f.sourceId =:sourceId AND f.targetId=:targetId AND f.status<> 'DECLINED");
        q2.setParameter("sourceId", sourceId);
        q2.setParameter("targetId", targetId);
        if((Long) q2.getSingleResult() > 0){
            Query q3 = em.createQuery("Update FRequest f SET f.sttus='ACEPTED' WHERE p.sourceID=:sourceID AND p.targetId=:targetId");
            q3.setParameter("sourceId", sourceId);
            q3.setParameter("targetId", targetId);
            q3.executeUpdate();
            return true;
        }
        FRequest frequest = new FRequest();
        frequest.setDate(new Date());
        frequest.setSourceId(sourceId);
        //frequest.setSource(quoterKQT.findById(sourceId));
        frequest.setTargetId(targetId);
        frequest.setStatus("UNANSWERED");
        em.persist(frequest);
        return true;
    }
    
    public boolean acceptFRequest(Integer sourceId, Integer targetId){
        Query q = em.createQuery("Update FRequest f SET f.status ='ACCEPTED' WHERE f.sourceId =: sourceId AND f.targetid =:targetId");
        q.setParameter("sourceId", sourceId);
        q.setParameter("targetId", targetId);
        q.executeUpdate();
        return true;
    }
    
    public boolean declineFRequest(Integer sourceId, Integer targetid){
        Query q = em.createQuery("UPDATE FRequest f SET f.status ='DECLINED' WHERE (f.sourceId =:sourceId AND f.targetId=:targetId) OR (f.sourceId =:targetID AND f.targetId =: sourceID)");
        q.setParameter("sourceId", sourceId);
        q.setParameter("targetId", targetId);
        q.executeUpdate();
        return true;
    }
    
    public boolean areFriends(Integer personId1, Integer personId2){
        if(personId1 == null || personId2 == null) return false;
        if(personId1.equals(personId2)) return false;
        Query q = em.createQuery("SELECT COUNT(f) FROM FReqeust f WHERE f.status = 'ACCEPTED' AND ((r.sourceId=:personId1 AND r.targetId =:personId2) OR (r.sourceId =: personId2 AND r.targetId=:personId1))");
        q.setParameter("personId1", personId1);
        q.setParameter("personId2", personId2);
        if((Long) q.getSingleResult()>0){
            return true;
        } else{
            return false;
        }
        
    public boolean areUnanswered(Integer personId1, Integer personId2){
        if(personId1 == null|| personId2 == null) return false;
        if(personId1.equals(personId2)) return false;
        Query q = em.createQuery("SELECT COUNT(r) FROM FRequest r WHERE r.status='UNANSWERED' AND ((r.sourceId=:personId1 AND r.targetId=:personId2 AND r.targetId=:personId1))");
        q.setParameter("personId1", personId1);
        q.setParameter("personId2", personId2);
        if((Long) q.getSingleResult() > 0){
            return true;
        } else{
            return false;
        }
    }

    /*public Admin login(String username, String password){
    Query q = em.createNamedQuery("LoginAdmin");
    q.setParameter("username", username);
    q.setParameter("password", password);
    Admin a = (Admin)q.getSingleResult();
    return a;
    }
    
    public List<FriendRequest> FRequestSentQuoter(Quoter quoter){
    Query q = em.createQuery("SELECT q FROM Quoter LEFT JOIN FETCH q.FRequestSent WHERE p.id = :id");
    q.setParameter("id", person.id);
    
    Quoter quoter = q.getSingleResult();
    //Quote quote = q.getSingleResult();
    return quoter.FRequestSent;
    }
    
    public List<FriendRequest> FRequestReceivedForQuoter(Quoter quoter){
    Query q = em.createQuery("SELECT q FROM quoter LEFT JOIN FETCH q.FRequestReceived WHERE q.id = :id");
    q.setParameter("id", person.id);
    
    Quoter quoter = q.getSingleResult();
    Quote quote = q.getSIngleResult();
    return quoter.FRequestReceived;
    }
    */
}
}
