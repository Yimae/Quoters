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
@Table(name = "notif")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "notif.findAll", query = "SELECT n FROM notif n"),
    @NamedQuery(name = "notif.findById", query = "SELECT n FROM notif n WHERE n.id = :id"),
    @NamedQuery(name = "notif.findByText", query = "SELECT n FROM notif n WHERE n.text = :text"),
    @NamedQuery(name = "notif.findByUrl", query = "SELECT n FROM notif n WHERE n.url = :url"),
    @NamedQuery(name = "notif.findByDate", query = "SELECT n FROM notif n WHERE n.date = :date"),
    @NamedQuery(name = "notif.findByPersonId", query="SELECT n FROM notif n WHERE n.personId = :personid")
})

public class Notif implements Serializable{
    private static final long serialVerisonUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max=255)
    @Column(name = "text")
    private String text;
    @Size(max = 255)
    @Column(name = "url")
    private String url;
    @Column(name= "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "personId")
    private int personId;
    
    public Notif(){
        
    }
    
    public Notif(Integer id){
        this.id = id;
    }
    
    public Notif(Integer id, String text, int personId){
        this.id = id;
        this.text = text;
        this.personId = personId;
    }
    
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
    
    public String getUrl(){
        return url;
    }
    
    public void setUrl(String url){
        this.url = url;
    }
    
    public Date getDate(){
        return date;
    }
    
    public void setDate(Date date){
        this.date = date;
    }
    
    public int getPersonId(){
        return personId;
    }
    
    public void setPersonId(int personId){
        this.personId = personId;
    }
    
    @Override
    public int hashCode(){
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object){
        if(!(object instanceof Notif)){
            return false;
        }
        Notif other = (Notif) object;
        if((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))){
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        return "Entity.Notif[ id ="+id+"]";
   }
}
    

