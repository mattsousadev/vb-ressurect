package br.mattsousa.model;

public class GameCharacter {
  private Short lifePoints;
  private Short attackPoints;
  
  public GameCharacter() {
    lifePoints = 0;
    attackPoints = 0;
  }

  public GameCharacter(Short lifePoints, Short attackPoints) {
    this.lifePoints = lifePoints;
    this.attackPoints = attackPoints;
  }

  public void attack(GameCharacter target){
    Short damagedLifePoints = Integer.valueOf(target.getLifePoints() - attackPoints).shortValue();
    if (damagedLifePoints < 0){
      damagedLifePoints = 0;
    }
    target.setLifePoints(damagedLifePoints);
  }

  public Boolean isAlive(){
    return lifePoints > 0;
  }

  public Short getLifePoints() {
    return lifePoints;
  }

  public void setLifePoints(Short lifePoints) {
    this.lifePoints = lifePoints;
  }

  public Short getAttackPoints() {
    return attackPoints;
  }

  public void setAttackPoints(Short attackPoints) {
    this.attackPoints = attackPoints;
  }
  
}
