// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Elevator extends SubsystemBase {

  WPI_VictorSPX elevator = new WPI_VictorSPX(RobotMap.ELEVATOR);
  WPI_VictorSPX winch = new WPI_VictorSPX(RobotMap.WINCH);


  public final static int UP = -1;
  public final static int DOWN = 1;

  /** Creates a new Elevator. */
  public Elevator() {
    addChild("Elevator", elevator);
    addChild("Winch", winch);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void down() {
    elevator.set(ControlMode.PercentOutput, DOWN * 1.0);
  }

  public void up() {
    elevator.set(ControlMode.PercentOutput, UP * .8);
  }
  
  public void stop() {
    elevator.set(ControlMode.PercentOutput, 0.0);
  }

  public void winch(double speed) {
    winch.set(ControlMode.PercentOutput, speed);
  }

}
