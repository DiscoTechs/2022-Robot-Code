// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.Elevator;

public class ControlElevator extends CommandBase {

  private final XboxController gamepad;
  private final Elevator elevator;

  public static final int UP = 1;
  public static final int DOWN = -1;
  public static final int STOP = 0;
  public int direction = UP;

  /** Creates a new ControlElevator. */
  public ControlElevator(Elevator elevator, XboxController gamepad) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.elevator = elevator;
    this.gamepad = gamepad;
    addRequirements(this.elevator);
  }

  public ControlElevator(Elevator elevator, XboxController gamepad, int direction) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.elevator = elevator;
    this.gamepad = gamepad;
    this.direction = direction;
    addRequirements(this.elevator);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (gamepad.getRawButton(RobotMap.XBX_A)) {
      elevator.down();
    } else if (gamepad.getRawButton(RobotMap.XBX_B)) {
      elevator.up();
    } else {
      elevator.stop();
    }

    if (gamepad.getRawButton(RobotMap.XBX_C)) {
      elevator.winch(-0.2);
    } else if (gamepad.getRawButton(RobotMap.XBX_D)) {
      elevator.winch(0.2);
    } else {
      elevator.winch(0.0);
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
