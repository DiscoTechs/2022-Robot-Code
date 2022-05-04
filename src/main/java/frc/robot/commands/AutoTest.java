// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoTest extends SequentialCommandGroup {
  /** Creates a new SimpleScore. */
  public AutoTest(Drive drive, Intake intake) {

    addCommands(new AutoTurn(drive, -90).withTimeout(2));


    /* // Spin!
    addCommands(new AutoTurn(drive, 90).withTimeout(2));
    
    addCommands(new AutoArm(intake, AutoArm.DOWN).withTimeout(1.75));

    // backward/pickup
    addCommands(new ParallelCommandGroup(
      new AutoD2(drive, 48),
      new AutoSpinner(intake, AutoSpinner.IN))
      .withTimeout(2));

    addCommands(new AutoArm(intake, AutoArm.UP).withTimeout(1.75));

    addCommands(new AutoTurn(drive, 90).withTimeout(2));

    addCommands(new AutoD2(drive, 48).withTimeout(2));

    // SCORE!!!
    addCommands(new AutoSpinner(intake, AutoSpinner.OUT).withTimeout(2));


    // turn
    //addCommands(new AutoTurn(drive, 2400).withTimeout(2));

    */
    

  }
}
