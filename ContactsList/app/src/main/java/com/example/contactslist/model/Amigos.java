package com.example.contactslist.model;

public class Amigos {

    int id;
    String nome, local, dataDoEncontro;
    double numero;

    public Amigos(int id, String nome, String local, String dataencontro, double numero) {
        this.id = id;
        this.nome = nome;
        this.local = local;
        this.dataDoEncontro = dataencontro;
        this.numero = numero;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getLocal() {
        return local;
    }

    public String getDataEncontro() {
        return dataDoEncontro;
    }

    public double getNumero() {
        return numero;
    }

}
