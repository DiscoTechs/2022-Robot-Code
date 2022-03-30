// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class AutoArm extends CommandBase {
  /** Creates a new AutoArm. */

  public static final int UP = -1;
  public static final int DOWN = -UP;

  private Intake intake;
  private int direction = UP;

  public AutoArm(Intake intake) {

    this.intake = intake;
    this.direction = UP;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake);
  }

  public AutoArm(Intake intake, int direction) {

    this.intake = intake;
    this.direction = direction;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (direction == DOWN ) {
      intake.moveArm(Intake.DOWN * .5);
    } else {
      intake.moveArm(Intake.UP * .5);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    if (direction == UP ) {
      return Intake.atTop();
    } else {
      return Intake.atBottom();
    }

  }
}
