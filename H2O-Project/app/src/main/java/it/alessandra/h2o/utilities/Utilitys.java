package it.alessandra.h2o.utilities;

public class Utilitys {

    public static Float getPercOfTotal(Float totalTarget, Float amount){
        return ((totalTarget-amount)/totalTarget)*100;
    }
}
