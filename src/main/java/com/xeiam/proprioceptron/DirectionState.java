package com.xeiam.proprioceptron;

class DirectionState implements State {

  public FreeVar[] directions;

  @Override
  public String[] vectorDoc() {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public FreeVar[] toVector() {

    return directions;
  }

  @Override
  public void addVars(FreeVar[] vars) {

    directions = vars;

  }

}