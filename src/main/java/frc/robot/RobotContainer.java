// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.AutoDrive;
import frc.robot.commands.AutoFrontBlue;
import frc.robot.commands.AutoFrontRed;
import frc.robot.commands.AutoSide;
import frc.robot.commands.ControlElevator;
import frc.robot.commands.IntakeCargo;
import frc.robot.commands.OutAndBak;
import frc.robot.commands.SimpleScore;
import frc.robot.commands.TeleDrive;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public final Drive drive = new Drive();
  public final Intake intake = new Intake();
  public final Elevator elevator = new Elevator();

  // private Joystick lstick;
  // private Joystick rstick;

  private XboxController gamepad;
  private XboxController drivepad;

  SendableChooser<Command> autoChooser;
  public SendableChooser<Integer> hubChooser;

  public RobotContainer() {

    // Configure the button bindings
    configureButtonBindings();

    // Why is the auto-select here? Because the container has all of the subsystem
    // objects
    autoChooser = new SendableChooser<Command>();
    autoChooser.addOption("Do Nothing", new AutoDrive(drive, 0));
    autoChooser.addOption("Simple Score", new SimpleScore(drive, intake));
    autoChooser.addOption("Backup", new AutoDrive(drive, -48));
    autoChooser.addOption("OutandBak", new OutAndBak(drive, intake));
    autoChooser.setDefaultOption("AutoFrontBLUE", new AutoFrontBlue(drive, intake));
    autoChooser.addOption("AutoFrontRED", new AutoFrontRed(drive, intake));
    autoChooser.addOption("AutoSide", new AutoSide(drive, intake));


    SmartDashboard.putData("Auto Chooser", autoChooser);

    hubChooser = new SendableChooser<Integer>();
    hubChooser.setDefaultOption("Front",0);
    hubChooser.addOption("Side", 1);
    SmartDashboard.putData("Hub Chooser", hubChooser);

    gamepad = new XboxController(RobotMap.GAMEPAD);
    drivepad = new XboxController(RobotMap.DRIVEPAD);

    drive.setDefaultCommand(new TeleDrive(drivepad, RobotMap.XBX_L_Y, drivepad, RobotMap.XBX_R_X, drive));
    intake.setDefaultCommand(new IntakeCargo(intake, gamepad));
    elevator.setDefaultCommand(new ControlElevator(elevator, gamepad));

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autoChooser.getSelected();
  }

  public int getHubSelected() {

    return hubChooser.getSelected();
  }


}
