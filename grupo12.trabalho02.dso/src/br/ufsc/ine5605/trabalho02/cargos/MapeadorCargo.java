/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02.cargos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rak_w
 */
public class MapeadorCargo {
    private HashMap<Integer, Cargo> cacheCargos = new HashMap<>();
    private final String filename = "cargos.dat";
    
    public Cargo getCargo (Integer idCargo) {
        return cacheCargos.get(idCargo);
    }
    
    public void putCargo (Cargo cargo) {
        cacheCargos.put(cargo.getCodigo(), cargo);
    }
    
    public Collection<Cargo> getList() {
        return cacheCargos.values();
    }
    
    public void persist() {
        
        try {
            FileOutputStream fout = new FileOutputStream(filename);
            
            ObjectOutputStream oo = new ObjectOutputStream(fout);
            oo.writeObject(cacheCargos);
            
            oo.flush();
            fout.flush();
            
            oo.close();
            fout.close();
            
            oo = null;
            fout = null;
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MapeadorCargo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MapeadorCargo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void load() {
        
        try {
            FileInputStream fin = new FileInputStream(filename);
            ObjectInputStream oi = new ObjectInputStream(fin);
            
            this.cacheCargos = (HashMap<Integer, Cargo>) oi.readObject();
            
            oi.close();
            fin.close();
            oi = null;
            fin = null;
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MapeadorCargo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MapeadorCargo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MapeadorCargo.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
}
