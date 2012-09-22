/**
 * Copyright 2012 MANC LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xeiam.proprioceptron.states;

import com.xeiam.proprioceptron.FreeVar;
import com.xeiam.proprioceptron.VarType;
import com.xeiam.proprioceptron.Vector;

/**
 * @author zackkenyon
 * @create Aug 21, 2012
 */
public class JointState implements State {

  // There will be another class of variables called fixed variables.
  protected static int DEGREES_OF_FREEDOM = 10;// listed below
  public FreeVar angle;
  public FreeVar angularvelocity;
  public FreeVar torque;
  public FreeVar position;
  public FreeVar tension;
  public FreeVar distance;// from goal
  public FreeVar energy;// will count total energy used
  public FreeVar direction;

  // this section is for joint descriptors, they are constant, and therefore do not contribute to the dimension of the state.
  public FreeVar length;// determines relationship between angle and posx, posy.
  public FreeVar density;// assumed to be constant throughout length of joint, dummy variable in almost every actuator, seems wrong to ignore it.
  JointState in;// determines position
  JointState out;

  public JointState(double d, double e, JointState in) {

    // this section initializes the type of the free variables.
    this.angle = new FreeVar(0, VarType.ANGLE);
    this.angularvelocity = new FreeVar(0, VarType.ANGULARVELOCITY);
    this.position = new FreeVar(new Vector(0.0, 0.0), VarType.POSITION);
    this.tension = new FreeVar(0, VarType.TENSION);
    this.distance = new FreeVar(0, VarType.DISTANCEFROMGOAL);
    this.energy = new FreeVar(0, VarType.ENERGY);
    this.direction = new FreeVar(new Vector(0, 0), VarType.DIRECTION);
    this.torque = new FreeVar(0, VarType.TORQUE);
    this.length = new FreeVar(d, VarType.LENGTH);
    this.density = new FreeVar(e, VarType.DENSITY);

    // this section specifies the unique characteristics of the joint.

    if (in != null) {
      this.in = in;
      this.in.out = this; // hax
    }
  }

  public void initialize(double d, double e) {// the system is completely determined at this point, and the actuator chain will handle the

    // rest of the initialization logic.
    this.angularvelocity.setVar(d);
    this.angle.setVar(e);
  }
  @Override
  public FreeVar[] toVector() {

    return new FreeVar[] { angle, angularvelocity, torque, position, tension, distance, energy };
  }

  @Override
  public String[] vectorDoc() {

    return new String[] { "Joint " };
  }

  @Override
  public void addVars(FreeVar[] vec) {

    if (vec.length != DEGREES_OF_FREEDOM) {
      // TODO obsolete
      System.out.println("All Free Variables must be specified in JointState.addVars");
      System.exit(1);
    }
    angle = vec[0];
    angularvelocity = vec[1];
    torque = vec[2];
    position = vec[3];
    tension = vec[4];
    distance = vec[5];
    energy = vec[6];
    direction = vec[7];
    length = vec[8];
    density = vec[9];
  }
}
