// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Drive extends SubsystemBase {

  final static WPI_TalonSRX leftDrive = new WPI_TalonSRX(RobotMap.LEFT_MOTOR);
  final static WPI_TalonSRX leftFollow = new WPI_TalonSRX(RobotMap.LEFT_FOLLOWER);
  
  final static WPI_TalonSRX rightDrive = new WPI_TalonSRX(RobotMap.RIGHT_MOTOR);
  final static WPI_TalonSRX rightFollow = new WPI_TalonSRX(RobotMap.RIGHT_FOLLOWER);

  int rEncoder = 0;
  int lEncoder = 0;

  double target = 0;

  MotorControllerGroup lGroup = null;
  MotorControllerGroup rGroup = null;
  DifferentialDrive diffDrive = null;

  boolean isDone = false;
  
  /** Creates a new Drive. */
  public Drive() {
    leftDrive.setInverted(true);
    leftFollow.setInverted(true);
    leftFollow.follow(leftDrive);

    rightDrive.setInverted(false);
    rightFollow.setInverted(false);
    rightFollow.follow(rightDrive);

    //leftDrive.configOpenloopRamp(.1);
    //rightDrive.configOpenloopRamp(.1);
    
    resetEncoders();

    diffDrive = new DifferentialDrive(leftDrive, rightDrive);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Auto Target", target);
  }

  
  public void vDriveTank(double left, double right){
    diffDrive.tankDrive(left, right, true);
    
  }

  public void vDriveArcade (double left, double right) {
    diffDrive.arcadeDrive(left, right, true);
  }

  public void resetEncoders(){
    leftDrive.setSelectedSensorPosition(0);
    rightDrive.setSelectedSensorPosition(0);
  }

  public void autoDrive(int inches, double speed) {

    speed = -speed;
    double target = inches / (6 * Math.PI) * 4000;
    SmartDashboard.putNumber("Target Encoder Value", target);

    if ( getLeftEncoder() < target ) {
      leftDrive.set(ControlMode.PercentOutput, speed);

      if(getRightEncoder() < getLeftEncoder()) {
       rightDrive.set(ControlMode.PercentOutput, speed * 1.20);
      } else if (getRightEncoder() > getLeftEncoder() ){
        rightDrive.set(ControlMode.PercentOutput, speed);
      } else {
        rightDrive.set(ControlMode.PercentOutput, speed);
      }

      isDone = false;

    } else {
      leftDrive.set(ControlMode.PercentOutput, 0.0);
      rightDrive.set(ControlMode.PercentOutput, 0.0);
  
      isDone = true;
    }

  }

  double kP = 1.0 / 30_000;
  double kT = 1.0 / 10_000;
  double error = 0;

  public void autoTarget() {
    
    error = target - getLeftEncoder();
    double turnError = getLeftEncoder() - getRightEncoder();
    SmartDashboard.putNumber("Drive error", error);
    
    diffDrive.arcadeDrive(-error * kP, turnError * kT);

    if (Math.abs(error) < 50) {
      isDone = true;
    } else {
      isDone = false;
    }

  }

  public void autoReverse(int inches, double speed) {

    //speed = speed;
    double target = inches / (6 * Math.PI) * 3600;
    SmartDashboard.putNumber("Target Encoder Value", target);

    if ( getLeftEncoder() > target ) {
      leftDrive.set(ControlMode.PercentOutput, speed);

      if(getRightEncoder() < getLeftEncoder()) {
       rightDrive.set(ControlMode.PercentOutput, speed * 1.20);
      } else if (getRightEncoder() > getLeftEncoder() ){
        rightDrive.set(ControlMode.PercentOutput, speed);
      } else {
        rightDrive.set(ControlMode.PercentOutput, speed);
      }

      isDone = false;

    } else {
      leftDrive.set(ControlMode.PercentOutput, 0.0);
      rightDrive.set(ControlMode.PercentOutput, 0.0);
  
      isDone = true;
    }

  }

  public static double getLeftEncoder() {
    return leftDrive.getSelectedSensorPosition();
  }

  public static double getRightEncoder() {
    return rightDrive.getSelectedSensorPosition();
  }

  public void autoTurn(int degrees) {

    double kP = 0.012;

    // CCW is positive
    double error = degrees - Robot.gyro.getAngle();

    SmartDashboard.putNumber("error", error);

    if (Math.abs(error) > 5) {
      diffDrive.tankDrive(error * kP, -error * kP);
      
    } else {
      isDone = true;
      diffDrive.tankDrive(0.0, 0.0);
    }
  }

  public void autoTurn_worked(int degrees) {

    // CCW is positive

    if (Robot.gyro.getAngle() < degrees) {
      diffDrive.arcadeDrive(0.0, .5);
      
    } else {
      isDone = true;
      diffDrive.arcadeDrive(0.0, 0.0);
    }
  }

  public boolean isDone() {
    return isDone;
  }

  public void reset() {
    isDone = false;
    Robot.gyro.reset();
    resetEncoders();
  }

  public void setTarget(int inches) {
     target = inches / (6 * Math.PI) * 4096;
  }

  public static void setRamp(double t) {
    leftDrive.configOpenloopRamp(t);
    rightDrive.configOpenloopRamp(t);
  }

}
