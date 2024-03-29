// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Auto1Ball extends SequentialCommandGroup {
  /** Creates a new SimpleScore. */
  public Auto1Ball(Drive drive, Intake intake) {

    // push in
    //addCommands(new AutoDrive(drive, 24));

    // SCORE!!!
    addCommands(new AutoSpinner(intake, AutoSpinner.OUT).withTimeout(10));

    // Get out !!
    addCommands(new AutoD2(drive, -80).withTimeout(2));

    // turn
    //addCommands(new AutoTurn(drive, 2400).withTimeout(2));
    

  }
}
