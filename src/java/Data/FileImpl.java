package data.Implementation;

import data.model.SocialDevelopDataLayer;
import data.model.File;
import it.univaq.f4i.iw.framework.data.DataLayerException;
import java.util.GregorianCalendar;

/**
 *
 * @author luka
 */
public class FileImpl implements File  
{
    
    private int id;
    private String nome;
    private String localfile;
    private GregorianCalendar caricamento;
    private int size;
    
    protected SocialDevelopDataLayer ownerdatalayer;
    
    public FileImpl(SocialDevelopDataLayer ownerdatalayer) 
    {
         
        this.ownerdatalayer = ownerdatalayer;
          
        id = 0;
        nome = "";
        localfile = "";
        caricamento = null;
        size = 0;
               
    }

    
     
    @Override
     public void copyFrom(File file) throws DataLayerException 
    {
        id = file.getId();
        nome = file.getNome();
        localfile = file.getLocalfile();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getLocalfile() {
        return localfile;
    }

    @Override
    public void setLocalfile(String localfile) {
        this.localfile = localfile;
    }

    @Override
    public GregorianCalendar getCaricamento() {
        return caricamento;
    }

    @Override
    public void setCaricamento(GregorianCalendar caricamento) {
        this.caricamento = caricamento;
    }

    @Override
    public SocialDevelopDataLayer getOwnerdatalayer() {
        return ownerdatalayer;
    }

    @Override
    public void setOwnerdatalayer(SocialDevelopDataLayer ownerdatalayer) {
        this.ownerdatalayer = ownerdatalayer;
    }
    
    
     
    @Override
    public int getSize()
    {
        return size;
    }

    /**
     * @param size the size to set
     */
    @Override
    public void setSize(int size) 
    {
        this.size = size;
    }
     
     
     
    
}
