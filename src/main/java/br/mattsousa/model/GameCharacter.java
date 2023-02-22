package br.mattsousa.model;

import br.mattsousa.enums.CharacterStatus;

public class GameCharacter {
  private Short lifePoints;
  private Short attackPoints;
  private Integer experiencePoints;
  private Byte staminaPoints;
  private String name;
  private CharacterStatus status;
  
  public GameCharacter(String name) {
    lifePoints = 0;
    attackPoints = 0;
    experiencePoints = 0;
    staminaPoints = 100;
    this.name = name;
    this.status = CharacterStatus.NORMAL;
  }

  public GameCharacter(String name, Short lifePoints, Short attackPoints) {
    this(name);
    this.lifePoints = lifePoints;
    this.attackPoints = attackPoints;
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

  public Byte getStaminaPoints() {
    return staminaPoints;
  }

  public void setStaminaPoints(Byte staminaPoints) {
    this.staminaPoints = staminaPoints;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CharacterStatus getStatus() {
    return status;
  }

  public void setStatus(CharacterStatus status) {
    this.status = status;
  }

}
