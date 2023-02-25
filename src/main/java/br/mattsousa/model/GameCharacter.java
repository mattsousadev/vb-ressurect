package br.mattsousa.model;

import java.util.ArrayList;

import br.mattsousa.enums.CharacterStatus;

public class GameCharacter {

  public static final int AVAILABLE_ATTACKS = 3;

  private String name;
  private Short lifePoints;
  private Byte staminaPoints;
  private Integer experiencePoints;
  private CharacterStatus status;
  private ArrayList<Attack> attacks;
  
  
  public GameCharacter(String name) {
    lifePoints = 0;
    experiencePoints = 0;
    staminaPoints = 100;
    this.name = name;
    this.status = CharacterStatus.NORMAL;
    this.attacks = new ArrayList<>();
  }

  public GameCharacter(String name, Short lifePoints) {
    this(name);
    this.lifePoints = lifePoints;
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

  public ArrayList<Attack> getAttacks() {
    return attacks;
  }

  public void setAttacks(ArrayList<Attack> attacks) {
    this.attacks = attacks;
  }

}
