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

public interface LinkKQT extends GenericKQT<Link, Integer>{
    public String test();
    public Link createLink(String link, Integer PostId);
}
