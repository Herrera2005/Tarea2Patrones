/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Observer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
public class GestorReservas {
    private List<Observador> observaciones;
    
    public GestorReservas() {
        this.observaciones = new ArrayList();
    }
    
    public void addObserver(Observador obs){
        observaciones.add(obs);
    }
    
    public void removeObserver(Observador obs){
        observaciones.remove(obs);
    }
    
    public void notifyObservers(){
        
    }
}
