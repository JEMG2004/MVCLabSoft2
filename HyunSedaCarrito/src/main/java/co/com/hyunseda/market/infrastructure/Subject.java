package co.com.hyunseda.market.infrastructure;

import java.util.ArrayList;


/**
 *
 * @author USUARIO
 */
public abstract class Subject {
    ArrayList<Observer> observers;

    public void Subject() {

    }

    /**
     * Agrega un observador
     *
     * @param obs
     */
    public void addObserver(Observer obs) {
        if (observers == null) {
            observers = new ArrayList<>();
        }
        observers.add(obs);
    }

    /**
     * Notifica a todos los observadores que hubo un cambio en el modelo
     */
    public void notifyAllObserves() {
        for (Observer each : observers) {
            each.update(this);
        }
    }
}
