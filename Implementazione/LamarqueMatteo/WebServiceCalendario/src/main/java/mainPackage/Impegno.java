/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainPackage;

/**
 *
 * @author Mark
 */
public class Impegno {
    String nome, data, materia, tipologia;
    int durata, importanza;
    
    /**
     * @brief constr with parameters
     * @param nome
     * @param data
     * @param mat
     * @param tip
     * @param dur
     * @param imp 
     */
    public Impegno(String nome, String data, String mat, String tip, int dur, int imp){
        this.nome = nome;
        this.data = data;
        this.materia = mat;
        this.tipologia = tip;
        this.durata = dur;
        this.importanza = imp;
    }
    
    public String myToString(){
        return "<nome>" + nome + "</nome><data>" + data + "</data><durata>" + durata + "</durata><importanza>" + importanza + "</importanza><materia>" + materia + "</materia><tipologia>" + tipologia + "</tipologia>";
    }
}

