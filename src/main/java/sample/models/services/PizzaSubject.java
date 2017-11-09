package sample.models.services;

import sample.PizzaData;

public interface PizzaSubject {
    void register(PizzaObserver observer);
    void notifyObservers(PizzaData data);
}
