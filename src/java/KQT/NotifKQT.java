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
import java.util.List;

public interface NotifKQT extends GenericKQT<Notif, Integer>{
    public boolean createNotif(String text, String url, Integer personId);
    public List<Notif> notiffor(Integer personId);
}
