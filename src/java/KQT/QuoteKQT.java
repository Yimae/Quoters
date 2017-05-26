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
import Entity.Quote;
import java.util.List;

public interface QuoteKQT extends GenericKQT<Quote, Integer>{
    public boolean createQuote(String title, String text, Integer posterId, Integer ownerId, String links);
    public List<Quote> topTenFor(Integer personId);
    public java.util.List<Entity.Quote> wallFor(java.lang.Integer personId);
}
