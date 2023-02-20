package br.mattsousa.model;

public class GameCharacter {
  private Short lifePoints;
  private Short attackPoints;
  private Integer experiencePoints;
  
  public GameCharacter() {
    lifePoints = 0;
    attackPoints = 0;
    experiencePoints = 0;
  }

  public GameCharacter(Short lifePoints, Short attackPoints) {
    this.lifePoints = lifePoints;
    this.attackPoints = attackPoints;
    this.experiencePoints = 0;
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

  public Integer getExperiencePoints() {
    return experiencePoints;
  }

  public void setExperiencePoints(Integer experiencePoints) {
    this.experiencePoints = experiencePoints;
  }
  
}
