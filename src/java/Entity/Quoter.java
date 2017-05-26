/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Kva
 */

import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "quoters")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Quoter.findAll", query = "SELECT q FROM Quoter q"),
    @NamedQuery(name = "Quoter.findById", query = "SELECT q FROM Quoter q WHERE q.id = :id"),
    @NamedQuery(name = "Quoter.findByQname", query = "SELECT q FROM Quoter q WHERE q.Qname = :Qname"),
    @NamedQuery(name = "Quoter.findByQemail", query = "SELECT q FROM Quoter q WHERE q.Qemail = :Qemail"),
    @NamedQuery(name = "Quoter.findByPassword", query = "SELECT q FROM Quoter q WHERE q.password = :password")
})

public class Quoter implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    //@NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "Qname")
    private String Qname;
    @Size(max = 255)
    @Column(name = "Qemail")
    private String Qemail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    
    public Quoter(){
        
    }
    
    public Quoter(Integer id){
        this.id = id;
    }
    
    public Quoter(Integer id, String Qemail, String password){
        this.id = id;
        this.Qemail = Qemail;
        this.password = password;
    }
    
    public Integer getId(){
        return id;
    }
    
    public void setId(Integer id){
        this.id = id;
    }
    
    public String getQname(){
        return Qname;
    }
    
    public void setQname(String Qname){
        this.Qname = Qname;
    }
    
    public String getQemail(){
        return Qemail;
    }
    
    public void setQemail(String Qemail){
        this.Qemail = Qemail;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    @Override
    public int hashCode(){
        int hash = 0;
        hash += (id != null? id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object){
        if(!(object instanceof Quoter)){
            return false;
        }
        Quoter other = (Quoter) object;
        if((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))){
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        return "Entity.Quoter[ id = "+id+"]";
    }
}
