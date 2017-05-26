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
import Entity.Quoter;
import java.util.Date;
import java.util.List;

public interface QuoterKQT extends GenericKQT<Quoter, Integer>{
    public Quoter login(String email, String password);
    public boolean register(String Qname, String Qemail, String password);
    public boolean updatePassword(Integer personId, String oldPassword, String newPassword, String passwordConfirmation);
    public boolean updatePersonInformation(Integer personId, String Qname, String Qemail);
    public List<Quoter> unansweredFRequestFor(Integer personId);
    public List<Quoter> friendsFor(Integer personId);
    public Quoter findById(Integer personId);
    public List<Quoter> nonFriendsFor(Integer personId);
    public void emailConfirmation(Quoter quoter);
    public Quoter findByPassword(String passwword);
    public List<Quoter> findAllSortedByName(boolean isAscending);
    public List<Quoter> findAllsortedByDateOfBirth(boolean isAscending);
    public List<Quoter> findAllSortedByPlace(boolean isAscending);
    public List<Entity.Quoter> friendSortedByName(Integer personId, boolean isAscending);
}
