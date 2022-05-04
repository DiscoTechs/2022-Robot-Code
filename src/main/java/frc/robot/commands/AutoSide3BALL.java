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
public class AutoSide3BALL extends SequentialCommandGroup {
  /** Creates a new AutoTest. */
  public AutoSide3BALL(Drive drive, Intake intake) {
    
    // shoot ball
    addCommands(new AutoSpinner(intake, AutoSpinner.OUT).withTimeout(1.5));
    // bakc up
    addCommands(new AutoD2(drive, -24.4).withTimeout(.75));
    // rotate 
    addCommands(new AutoTurn(drive, 110).withTimeout(1.5));
    // arm down
    addCommands(new AutoArm(intake, AutoArm.DOWN).withTimeout(1.75));
    // get two balls!!!!  lol thats sounds wrong
  
    addCommands(new ParallelCommandGroup(
        new AutoD2(drive, 280.2),
        new AutoSpinner(intake, AutoSpinner.IN)).withTimeout(7));     
    // drive bak
    addCommands(new AutoD2(drive, -280.2));
    // arm up
    addCommands(new AutoArm(intake, AutoArm.UP).withTimeout(1.75));
    // rotate 
    addCommands(new AutoTurn(drive, -112).withTimeout(1.5));
    // forawrd!
    addCommands(new AutoD2(drive, 28).withTimeout(2));
    // out
    addCommands(new AutoSpinner(intake, AutoSpinner.OUT).withTimeout(1.5));
    

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
