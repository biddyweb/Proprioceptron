package com.xeiam.proprioceptron.states;

import com.xeiam.proprioceptron.FreeVar;

public class AngularVelocityState implements State {

  public FreeVar[] angularvelocities;

  @Override
  public FreeVar[] toVector() {

    return angularvelocities;
  }

  @Override
  public String[] vectorDoc() {

    return new String[] { "angular velocities" }; // + id
  }

  @Override
  public void addVars(FreeVar[] angularvelocities) {

    this.angularvelocities = angularvelocities;

  }
}