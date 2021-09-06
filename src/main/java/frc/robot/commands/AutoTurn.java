// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoTurn extends PIDCommand {

  DriveSubsystem m_drive;
  /** Creates a new AutoTurn. */
  public AutoTurn(DriveSubsystem drive) {
    super(
        // The controller that the command will use
        drive.m_turnPIDController,
        // This should return the measurement
        () -> drive.getAngleK(),
        // This should return the setpoint (can also be a constant)
        () -> drive.getDashboardAngle(),
        // This uses the output
        output -> {
          drive.arcadeDrive(0, output);
        });

    m_drive = drive;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
    // Configure additional PID options by calling `getController` here.
  }

  @Override
  public void initialize() {
    // TODO Auto-generated method stub
    super.initialize();
    //m_drive.setDashboardAngle();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
