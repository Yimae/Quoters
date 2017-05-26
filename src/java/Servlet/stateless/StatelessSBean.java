/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.stateless;

/**
 *
 * @author Kva
 */

import javax.ejb.Stateless;

@Stateless

public class StatelessSBean {
    public String sayHello(String name){
        return "Hello" + name + "!\n";
    }
}
