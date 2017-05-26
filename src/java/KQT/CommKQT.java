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
import java.util.List;

public interface CommKQT extends GenericKQT<Comm, Integer>{
    public boolean createComm(String text, Integer personId, Integer postId);
    public List<Comm> commentsFor(Integer postId);
}
