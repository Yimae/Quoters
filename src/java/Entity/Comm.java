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
@Table(name= "comm")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comm.findAll", query = "SELECT c FROM Comm c"),
    @NamedQuery(name = "Comm.findById", query = "SELECT c From Comm c WHERE c.id = :id"),
    @NamedQuery(name = "Comm.findByText", query = "SELECT c From Comm c WHERE c.text = :text"),
    @NamedQuery(name = "Comm.findByDate", query = "SELECT c FROM Comm c WHERE c.date = :date"),
    @NamedQuery(name = "Comm.findByPersonId", query = "SELECT c FROM Comm c WHERE c.personId = :personId"),
    @NamedQuery(name = "Comm.findByPostId", query = "SELECT c FROM Comm c WHERE c.postId = :postId")})

public class Comm implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name ="id")
    private Integer id;
    @Size(max = 255)
    @Column(name ="text")
    private String text;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    /*@Basic(optional = false)
    @NotNull
    @Column(name="personId")
    private int personId;
    */
    @Basic(optional = false)
    @NotNull
    @Column(name = "postId")
    private int postId;
    
    public Comm(){
    }
    
    public Comm(Integer id){
        this.id = id;
    }
    
    /*public Comm(Integer id, int personId, int postId){
        this.id = id;
        this.personId = personId;
        this.postId = postId;
    }*/
    
    public Integer getId(){
        return id;
    }
    
    public void setId(Integer id){
        this.id = id;
    }
    
    public String getText(){
        return text;
    }
    
    public void setText(String text){
        this.text = text;
    }
    
    public Date getDate(){
        return date;
    }
    
    public void setDate(Date date){
        this.date = date;
    }
    
    /*public int getPersonId(){
        return personId;
    }
    
    public void setPersonId(int persondId){
        this.personId = personId;
    }
    */
    
    public int getPostId(){
        return postId;
    }
    
    public void setPostId(int postId){
        this.postId = postId;
    }
    @Override
    public int hashCode(){
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals (Object object){
        //This method wont work in case the id field are not set
        if(!(object instanceof Comm)){
            return false;
        }
        Comm other = (Comm) object;
        if((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))){
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        return "Entity.Comm[ id = " + id +" ]";
    }
    
    @ManyToOne
    //@JoinColumn (name ="personId", updateable = false, insertable = false)
    @JoinColumn (name = "personId")
    private Quoter Commtr;
    
    public Quoter getCommtr(){
        return Commtr;
    }
    
    public void setCommtr(Quoter Commtr){
        this.Commtr = Commtr;
    }
}
