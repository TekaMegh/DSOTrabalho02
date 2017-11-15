/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02.acessos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author rak_w
 */
public class MapeadorAcesso {
    private HashMap<Integer, Acesso> cacheAcessos = new HashMap<>();
    private final String fileName = "Acessos.BATATA";
     
    
    public MapeadorAcesso() {
        load();
    }
    public void put(Acesso acesso){
        acesso.setIdAcesso(cacheAcessos.size()+1);
        cacheAcessos.put(acesso.getIdAcesso(), acesso);
        persist();
    }
    
    public Acesso get(Integer idAcesso){
        return cacheAcessos.get(idAcesso);
    }
    
    public Collection<Acesso> getList(){
        return cacheAcessos.values();
    }
    
    public void remove(Acesso acesso){
        cacheAcessos.remove(acesso.getIdAcesso());
        persist();
    }
    
    private void persist(){
        try{
            FileOutputStream fOS = new FileOutputStream(fileName);
            ObjectOutputStream oOS = new ObjectOutputStream(fOS);
            
            oOS.writeObject(cacheAcessos);
            
            oOS.flush();
            fOS.flush();
            
            oOS.close();
            fOS.close();
            
        } catch (Exception e){
            persist();
        }
    }
    

    private void load() {
        try {
            FileInputStream fIS = new FileInputStream(fileName);
            ObjectInputStream oIS = new ObjectInputStream(fIS);

            cacheAcessos = (HashMap< Integer, Acesso>) oIS.readObject();

            oIS.close();
            fIS.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
