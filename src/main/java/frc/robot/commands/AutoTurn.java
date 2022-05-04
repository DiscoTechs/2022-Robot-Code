// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Drive;

public class AutoTurn extends CommandBase {

  private final Drive drivetrain;
  
  private int degrees = 90;

  double kP = 0.03;
  double error;

  /** Creates a new AutoTurn. */
  public AutoTurn(Drive drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    drivetrain = drive;
    addRequirements(drivetrain);
  }

  public AutoTurn(Drive drive, int degrees) {
    // Use addRequirements() here to declare subsystem dependencies.
    drivetrain = drive;
    addRequirements(drivetrain);

    this.degrees = degrees;
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivetrain.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    // CCW is positive
    error = degrees - Robot.gyro.getAngle();

    SmartDashboard.putNumber("error", error);

    double turnSpeed = error * kP;

    if (turnSpeed > .75) {
      turnSpeed = .75;
    }

    if (turnSpeed < -.75) {
      turnSpeed = -.75;
    }
    
    drivetrain.vDriveTank(turnSpeed, -turnSpeed);

    //drivetrain.autoTurn(degrees);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (Math.abs(error) < 3);
  }
}

/*
  public void autoTurn(int degrees) {

    double kP = 0.01;

    // CCW is positive
    double error = degrees - Robot.gyro.getAngle();

    SmartDashboard.putNumber("error", error);

    double turnSpeed = error * kP;

    if (turnSpeed > .75) {
      turnSpeed = .75;
    }

    if (turnSpeed < -.75) {
      turnSpeed = -.75;
    }

    diffDrive.tankDrive(turnSpeed, -turnSpeed);
  }
    */
  
