// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Intake extends SubsystemBase {

  WPI_TalonSRX motor = new WPI_TalonSRX(RobotMap.INTAKE_ARM);
  WPI_VictorSPX spinner = new WPI_VictorSPX(RobotMap.SPINNER);

  static DigitalInput bottomLimitSwitch = new DigitalInput(RobotMap.ARM_BOTTOM_LIMIT);
  static DigitalInput topLimitSwitch = new DigitalInput(RobotMap.ARM_TOP_LIMIT);

  private int holdDirection = 0;
  public static final int UP = 1;
  public static final int DOWN = -UP;
  public static final int HOLD = 0;

  public static final int IN = -1;
  public static final int OUT = -IN;

  private double holdSpeed = .1;

  /** Creates a new Intake. */
  public Intake() {
    addChild("Arm", motor);
    addChild("Intake", spinner);
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean( "Bottom", Intake.atBottom());
    SmartDashboard.putBoolean( "Top", Intake.atTop());
  }

  public void moveArm(double speed) {
    motor.set(ControlMode.PercentOutput, speed);
  }

  public void armUp() {
    if (!atTop()) {
      moveArm(UP * .60);
      holdDirection = UP;
      }
    else  {
        this.hold();
    }
  }

  public void armDown() {

    if (!atBottom()) {
      moveArm(DOWN * .50);
      holdDirection = DOWN;
    }
    else {
      this.hold();
    }
  }

  public void hold() {
    motor.set(ControlMode.PercentOutput, holdSpeed * holdDirection);
  }

  public void intakeSpinner(double speed) {
    spinner.set(ControlMode.PercentOutput, speed * 2);
  }

  public static boolean atBottom() {
    return !bottomLimitSwitch.get();
  }

  public static boolean atTop() {
    return !topLimitSwitch.get();
  }

  public void fullStop() {
    holdDirection = HOLD;
  }

}
