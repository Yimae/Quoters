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
@Table(name = "FRequest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FRequest.findAll", query = "SELECT f FROM FRequest f"),
    @NamedQuery(name = "FRequest.findById", query = "SELECT f FROM FRequest f WHERE f.id = :id"),
    //@NamedQuery(name = "FRequest.findByStatus", query = "SELECT f FROM FRequest f WHERE f.status = :status"),
    @NamedQuery(name = "FRequest.findByDate", query = "SELECT f FROM FRequest f WHERE f.date = :date"),
    @NamedQuery(name = "FRequest.findBySourceId", query = "SELECT f FROM FRequest f WHERE f.sourceId = :sourcId"),
    @NamedQuery(name = "FRequest.findByTargetId", query = "SELECT f FROM FRequest f WHERE f.targetId = :targetId")
})

public class FRequest implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "status")
    private String status;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sourceId")
    private int sourceId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "targetId")
    private int targetId;
    
    public FRequest(){
    }
    
    public FRequest(Integer id){
        this.id = id;
    }
    
    public FRequest(Integer id, int sourceId, int targetId){
        this.id = id;
        this.sourceId = sourceId;
        this.targetId = targetId;
    }
    
    public Integer getId(){
        return id;
    }
    
    public void setId(Integer id){
        this.id = id;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    
    public Date getDate(){
        return date;
    }
    
    public void setDate(Date date){
        this.date = date;
    }
    
    public int getSourceId(){
        return sourceId;
    }
    
    public void setSourceId(int sourceId){
        this.sourceId = sourceId;
    }
    
    public int getTargetId(){
        return targetId;
    }
    
    public void setTargetId(int targetId){
        this.targetId = targetId;
    }
    
    @Override
    public int hashCode(){
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object){
        if (!(object instanceof FRequest)){
            return false;
        }
        FRequest other = (FRequest) object;
        if((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))){
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        return "Entity.FRequest[ id = "+id+"]";
    }
    
    /*@ManyToOne
    @JoinColumn (name = "sourceId")
    private Quoter source;
    public Quoter getSource(){
        return source;
    }
    public void setSource(Quoter source){
        this.source = source;
    }
    */
}