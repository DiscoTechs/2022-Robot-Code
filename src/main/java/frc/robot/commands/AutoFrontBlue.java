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
public class AutoFrontBlue extends SequentialCommandGroup {
  /** Creates a new AutoTest. */
  public AutoFrontBlue(Drive drive, Intake intake) {

    // Eject Cargo and back up (3 secs)
    addCommands(new SimpleScore(drive, intake));

    // set rotation for FRONT hub 
    addCommands(new AutoTurn(drive, 110).withTimeout(1.5));
    addCommands(new AutoArm(intake, AutoArm.DOWN).withTimeout(2));

    addCommands(new ParallelCommandGroup(
        new AutoDrive(drive, 63),
        new AutoSpinner(intake, AutoSpinner.IN))
      .withTimeout(1.75));     

    addCommands(new AutoArm(intake, AutoArm.UP).withTimeout(1.75));
    addCommands(new AutoDrive(drive, -78).withTimeout(1.75));
    addCommands(new AutoTurn(drive, -110).withTimeout(1.75));
    addCommands(new AutoDrive(drive, 92, .6).withTimeout(2));
    addCommands(new AutoSpinner(intake, AutoSpinner.OUT).withTimeout(2));

      
    //addCommands(new AutoSpinner(intake, AutoSpinner.IN).withTimeout(1));
    //addCommands(new AutoDrive(drive, ));
    

    //addCommands(new AutoDrive(drive, 72));
    //addCommands(new AutoArm(intake, AutoArm.DOWN).withTimeout(2));
    //addCommands(new AutoDrive(drive, -72).withTimeout(2));
    //addCommands(new AutoArm(intake, AutoArm.UP).withTimeout(2));
    //addCommands(new AutoTurn(drive, 90));

    // SCORE!!!
    //addCommands(new AutoSpinner(intake, AutoSpinner.OUT).withTimeout(1));

    // Get out !!
    //addCommands(new AutoDrive(drive, -80, .6));

    // turn
    //addCommands(new AutoTurn(drive, 2400).withTimeout(2));
    
  }
  
}
