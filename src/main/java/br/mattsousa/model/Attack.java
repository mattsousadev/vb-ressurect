package br.mattsousa.model;

public class Attack {
    private String name;
    private Short attackPoints;
    private Byte staminaCost;

    public Attack(String name, Short attackPoints, Byte staminaCost) {
        this.name = name;
        this.attackPoints = attackPoints;
        this.staminaCost = staminaCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(Short attackPoints) {
        this.attackPoints = attackPoints;
    }

    public Byte getStaminaCost() {
        return staminaCost;
    }

    public void setStaminaCost(Byte staminaCost) {
        this.staminaCost = staminaCost;
    }

}
