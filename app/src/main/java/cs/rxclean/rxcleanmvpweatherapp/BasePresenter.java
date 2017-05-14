package cs.rxclean.rxcleanmvpweatherapp;

/**
 * Base contract to be implemented by all Presenters.
 */

public interface BasePresenter {

    void subscribe();

    void unsubscribe();

}