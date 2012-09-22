package com.xeiam.proprioceptron.actuators;

import com.xeiam.proprioceptron.states.AngleState;
import com.xeiam.proprioceptron.states.AngularVelocityState;

public class AngularVelocityActuator implements Actuator {

  AngularVelocityState angularvelocities;
  AngleState angles;

  public void setDomain(AngularVelocityState angularvelocities) {

    this.angularvelocities = angularvelocities;
  }

  public void setRange(AngleState angles) {

    this.angles = angles;
  }

  @Override
  public void actuate() {

    for (int i = 0; i < angularvelocities.angularvelocities.length; i++) {
      angles.angles[i].setVar(angles.angles[i].getVar() + angularvelocities.angularvelocities[i].getVar());
    }
  }
}