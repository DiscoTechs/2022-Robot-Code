// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Drive;

public class AutoD2 extends CommandBase {

  private double distance = 60;
  private double kP = 1.0 / 10_000;
  private double kI;
  private double kD;
  private Drive drive;
  private double setpoint;
  private final double iLimit = 1;
  double error = 0;
  double errorSum = 0;
  double errorRate = 0;
  double lastError = 0;
  double lastTimestamp = 0;


  /** Creates a new AutoD2. */
  public AutoD2(Drive drive, double d) {
    // Use addRequirements() here to declare subsystem dependencies.
    distance = d;
    this.drive = drive;
    addRequirements(this.drive);
    
    setpoint =  distance / (6 * Math.PI) * 4096;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    drive.resetEncoders();
    Robot.gyro.reset();
    error = 0;
    errorSum = 0;
    lastError = 0;
    lastTimestamp = Timer.getFPGATimestamp();
    
    kP = SmartDashboard.getNumber("kP", 0);
    kI = SmartDashboard.getNumber("KI", 0);
    kD = SmartDashboard.getNumber("kD", 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    error = setpoint - Drive.getLeftEncoder();

    // calculations
    double dt = Timer.getFPGATimestamp() - lastTimestamp;

    if (Math.abs(error) < iLimit) {
      errorSum += error * dt;
    }

    errorRate = (error - lastError) / dt;

    double outputSpeed = kP * error + kI * errorSum + kD * errorRate;

    double maxSpeed = 0.8;

    if (outputSpeed > maxSpeed) {
      outputSpeed = maxSpeed;
    }

    if (outputSpeed < -maxSpeed) {
      outputSpeed = -maxSpeed;
    }

    double turnError = Robot.gyro.getAngle();
    double kT = 0.1;

    drive.vDriveArcade(-outputSpeed, -turnError * kT);

    // update last- variables
    lastTimestamp = Timer.getFPGATimestamp();
    lastError = error;

    SmartDashboard.putNumber("D2 Error", error);
    SmartDashboard.putNumber("Setpoint", setpoint);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(error) < 100;
  }


}
