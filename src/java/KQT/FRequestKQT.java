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

public interface FRequestKQT extends GenericKQT<FRequest, Integer>{
    public boolean createFRequest(Integer sourceId, Integer targetId);
    public boolean acceptFRequest(Integer sourceId, Integer targetId);
    public boolean declineFRequest(Integer sourceId, Integer targetId);
    public boolean areFriends(Integer personId1, Integer personId2);
    public boolean areUnanswered(Integer personId1, Integer personId2);
}
