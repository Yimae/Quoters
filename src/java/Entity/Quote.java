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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.text.SimpleDateFormat;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "quotes")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Quote.findAll", query="SELECT quo FROM Quote quo"),
  @NamedQuery(name = "Quote.findById", query="SELECT quo FROM Quote quo WHERE quo.id = :id"),
  @NamedQuery(name = "Quote.findByTitle", query="SELECT quo FROM Quote quo WHERE quo.title = :title"),
  @NamedQuery(name = "Quote.findByDate", query="SELECT quo FROM Quote quo WHERE quo.date = :date"),
  @NamedQuery(name = "Quote.findByText", query="SELECT quo FROM Quote quo WHERE quo.text = :text"),
  //@NamedQuery(name = "Quote.findByPosterId", query = "SELET quo FROM Quote q WHERE quo.posterId = :posterId"),
  @NamedQuery(name = "Quote.findByOwnerId", query = "SELECT quo FROM Quote quo WHERE quo.ownerId = :ownerId"),
  @NamedQuery(name = "Quote.findByPopularity", query = "SELECT quo FROM Quote quo WHERE quo.popularity = :popularity")
})

public class Quote implements Serializable{
    private static final long serialVerionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "title")
    private String title;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Size(max = 255)
    @Column(name = "text")
    private String text;
    /*
    @Basic(optional = false)
    @NotNull
    @Column(name = "posterId")
    private int posterId;
    */
    @Basic(optional = false)
    @NotNull
    @Column(name = "ownerId")
    private int ownerId;
    @Column(name = "popularity")
    private Integer popularity;
    
    public Quote(){
    }
    
    public Quote(Integer id){
        this.id = id;
    }
    
    /*public Quote(Integer id, int posterId, int ownerId){
        this.id = id;
        this.posterId = posterId;
        this.ownerId = ownerId;
    }*/
    
    public Integer getId(){
        return id;
    }
    
    public void setId(Integer id){
        this.id = id;
    }
    
    public String getTitle(){
        return title;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public Date getDate(){
        return date;
    }
    
    public void setDate(Date date){
        this.date = date;
    }
    
    public String getText(){
        return text;
    }
    
    public void setText(String text){
        this.text = text;
    }
    
    /*public int getPosterId(){
        return posterId;
    }
    
    public void setPosterId(int posterId){
        this.posterId = posterId;
    }*/
    
    public int getOnwerId(){
        return ownerId;
    }
    
    public void setOwnerId(int ownerId){
        this.ownerId = ownerId;
    }
    
    public Integer getPopularity(){
        return popularity;
    }
    
    public void setPopularity(int popularity){
        this.popularity = popularity;
    }
    
    @Override
    public int hashCode(){
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals (Object object){
        if (!(object instanceof Quote)){
            return false;
        }
        Quote other = (Quote) object;
        if((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))){
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        return "Entity.Quote[ id="+id+"]";
    }
    
    @ManyToOne
    @JoinColumn(name = "posterId")
    private Quoter quotemkr;
    
    public Quoter getQuoter(){
        return quotemkr;
    }
    
    public void setQuoter(Quoter quotemkr){
        this.quotemkr = quotemkr;
    }
    
    @OneToMany (mappedBy = "quote", cascade = CascadeType.PERSIST)
    private Set<Link> links = new HashSet<Link>();
    
    public Set<Link> getLinks(){
        return links;
    }
    
    public void setLinks(Set<Link> links){
        this.links = links;
    }
    
    public String getFormattedDate(){
        SimpleDateFormat formatte = new SimpleDateFormat("dd-MM-yyyy");
        return formatte.format(date);
    }
    
}
