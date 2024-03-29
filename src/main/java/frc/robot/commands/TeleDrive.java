// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Smoother;

public class TeleDrive extends CommandBase {

    private final Drive drivetrain;
    private final GenericHID left;
    private final GenericHID right;

    private int lAxis = 0;
    private int rAxis = 0;

    private double smoothTime = 0.4;
    private Smoother leftStick = new Smoother ((int)(50*smoothTime));
    private Smoother rightStick = new Smoother((int)(50*smoothTime));
    private boolean smoothToggle = false;

    /**
     * Creates a new TankDrive command.
     *
     * @param left The control input for the left side of the drive
     * @param right The control input for the right sight of the drive
     * @param drivetrain The drivetrain subsystem to drive
     */
    public TeleDrive(GenericHID left, int lAxis, GenericHID right, int rAxis, Drive drive) {

      drivetrain = drive;
      this.left = left;
      this.right = right;

      this.lAxis = lAxis;
      this.rAxis = rAxis;

      addRequirements(drivetrain);
    }
  
    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
      double lSpeed = left.getRawAxis(lAxis);
      double rSpeed = right.getRawAxis(rAxis);
      boolean buttonClicked = left.getRawButtonPressed(RobotMap.RIGHT_BUMPER);
  //    System.out.println(leftStick.returnArray());
   //   System.out.println(rightStick.returnArray());

      if (buttonClicked && !smoothToggle) 
      {
        smoothToggle = true;
        leftStick.setSpeed(lSpeed);
        rightStick.setSpeed(rSpeed);
        
      } else if (buttonClicked && smoothToggle) {
        smoothToggle = false;
        
      }

      if (smoothToggle) 
      {
        lSpeed = leftStick.updateList(lSpeed);
        rSpeed = rightStick.updateList(rSpeed);
      }
      else if (!smoothToggle)
      {
        lSpeed = left.getRawAxis(lAxis);
        rSpeed = right.getRawAxis(rAxis);
      }
        
      drivetrain.vDriveArcade(lSpeed, -rSpeed);
      
    }
  
    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
      return false; // Runs until interrupted
    }
  
    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
      drivetrain.vDriveTank(0, 0);
    }
  }