// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class AutoDrive extends CommandBase {

  private final Drive drivetrain;
  int distance = 24;
  double speed = .75;
  
  /** Creates a new AutoDrive. */
  public AutoDrive(Drive drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    drivetrain = drive;
    addRequirements(drivetrain);
  }

  public AutoDrive(Drive drive, int d) {
    // Use addRequirements() here to declare subsystem dependencies.
    drivetrain = drive;
    addRequirements(drivetrain);

    distance = d;
    speed = .5;
  }

  public AutoDrive(Drive drive, int d, double s) {
    // Use addRequirements() here to declare subsystem dependencies.
    drivetrain = drive;
    addRequirements(drivetrain);

    distance = d;
    speed = s;
  }
  
  // Called when the command is initially scheduled.
  @Override
  public void initialize(
  ) {
    drivetrain.reset();
    drivetrain.setTarget(distance);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.autoTarget();
  }
  
  public void execute___() {
    if(distance > 0) {
     drivetrain.autoDrive(distance, speed);
    } else { 
      drivetrain.autoReverse(distance, speed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if ( drivetrain.isDone()) {
      drivetrain.resetEncoders();
      return true;
    } else {
      return false;
    }

  }
}
