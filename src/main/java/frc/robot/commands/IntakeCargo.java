// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.Intake;

public class IntakeCargo extends CommandBase {

  private final Intake intake;
  private final XboxController gamepad;

  /** Creates a new IntakeCargo. */
  public IntakeCargo(Intake intake, XboxController gamepad) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.intake = intake;
    this.gamepad = gamepad;
    addRequirements(this.intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    if (gamepad.getRawAxis(RobotMap.XBX_L_TRIG) > 0.05) {
      intake.intakeSpinner(gamepad.getRawAxis(RobotMap.XBX_L_TRIG) * Intake.IN);
    } else if (gamepad.getRawAxis(RobotMap.XBX_R_TRIG) > 0.05) {
      intake.intakeSpinner(1.1 * Intake.OUT);
    } else {
      intake.intakeSpinner(0.0);
    }
      
    if (gamepad.getRawButton(RobotMap.LEFT_BUMPER) && gamepad.getRawButton(RobotMap.RIGHT_BUMPER)) {
      intake.fullStop();
    } else if (gamepad.getRawButton(RobotMap.LEFT_BUMPER)) {
      intake.armUp();
    } else if (gamepad.getRawButton(RobotMap.RIGHT_BUMPER)) {
      intake.armDown();
    } else {
      intake.hold();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
