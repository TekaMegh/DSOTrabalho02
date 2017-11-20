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
    private final String filename = "cargos.BATATA";
    
    public MapeadorCargo() {
        load();
    }
    
    public Cargo getCargo (Integer idCargo) {
        return cacheCargos.get(idCargo);
    }
    
    public void putCargo (Cargo cargo) {
        cacheCargos.put(cargo.getCodigo(), cargo);
        this.persist();
    }
    
    public void removeCargo(int identificador) {
        this.cacheCargos.remove(identificador);
        this.persist();
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
            
        } catch (Exception e) {
			System.out.println(e);
			persist();
		}
    }
    
    public void load() {
        
        try {
            FileInputStream fin = new FileInputStream(filename);
            ObjectInputStream oi = new ObjectInputStream(fin);
            
            this.cacheCargos = (HashMap<Integer, Cargo>) oi.readObject();
            
            oi.close();
            fin.close();
            
        } catch (ClassNotFoundException e) {
			System.out.println(e);

		} catch (FileNotFoundException e) {
			System.out.println(e);
			persist();

		} catch (IOException e) {
			System.out.println(e);
			persist();
		}  
    }    
}
