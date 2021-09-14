// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveSubsystem;

public class AutoLeft extends CommandBase {
  /** Creates a new AutoLeft. */

  DriveSubsystem m_drive;

  AutoTurn m_autoTurn1;
  AutoTurn m_autoTurn2;

  Command m_currentCommand;
  
  public AutoLeft(DriveSubsystem drive) {
    System.out.println("AutoLeft constructor");
    m_drive = drive;
    // Use addRequirements() here to declare subsystem dependencies
    m_autoTurn1 = new AutoTurn(m_drive.m_turnPIDController, () -> m_drive.getYaw(), DriveConstants.kTURN_LEFT_AUTO1, m_drive);
    
  }

  private void dashboardCmd(String cmdName) {
    SmartDashboard.putString("Auto Cmd Step", cmdName);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("AutoLeft init");
    m_currentCommand = m_autoTurn1;
    dashboardCmd("autoturn1");
    m_currentCommand.initialize();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("AutoLeft execute");
    m_currentCommand.execute();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("AutoLeft end");
    m_currentCommand.end(interrupted);
    dashboardCmd("Auto Done");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    System.out.println("AutoLeft isFinished");
    boolean returnIsFinished = false;
    returnIsFinished = m_currentCommand.isFinished();
    return returnIsFinished;
  }
}
