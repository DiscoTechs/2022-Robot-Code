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
public class OutAndBak extends SequentialCommandGroup {
  /** Creates a new OutAndBak. */
  public OutAndBak(Drive drive, Intake intake) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    

    // SCORE!!!
    addCommands(new AutoSpinner(intake, AutoSpinner.OUT).withTimeout(1));

    // Get out !!
    addCommands(new AutoDrive(drive, -48).withTimeout(1));

    // turn
    addCommands(new AutoTurn(drive, 160).withTimeout(2));

    // drop the arm
    addCommands(new AutoArm(intake, AutoArm.DOWN).withTimeout(2));

    // forward to the cargo!!!!
    addCommands(new ParallelCommandGroup(
             new AutoDrive(drive, 36, .25),
             new AutoSpinner(intake, AutoSpinner.IN)).withTimeout(2));

    // back up
    addCommands(new AutoDrive(drive, -36).withTimeout(2));

    // raise the arm
    addCommands(new AutoArm(intake, AutoArm.UP).withTimeout(2));

    // turn back
    addCommands(new AutoTurn(drive, 160).withTimeout(2));

    // back to the hub
    addCommands(new AutoDrive(drive, 24).withTimeout(2));

    // SCORE!!!
    addCommands(new AutoSpinner(intake, AutoSpinner.OUT).withTimeout(1));
     
  }
}



    /* OLD OUT AND BAK

    // SCORE!!!
    addCommands(new AutoSpinner(intake, AutoSpinner.OUT).withTimeout(2));

    // out
    addCommands(new AutoDrive(drive, -48));
    addCommands(new AutoArm(intake, AutoArm.DOWN));
    addCommands(new AutoTurn(drive, 6500));
    
    // out a little more, with the intake going
    addCommands( new ParallelCommandGroup(
        new AutoSpinner(intake, AutoSpinner.IN),
        new AutoDrive(drive, 48)));

    // turn around
    addCommands(new AutoTurn(drive, 9800));

    // back to the hub
    addCommands(new AutoDrive(drive, 130));

    //SCORE!!!
    addCommands(new AutoSpinner(intake, AutoSpinner.OUT).withTimeout(2));

    */
