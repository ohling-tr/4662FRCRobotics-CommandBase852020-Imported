// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
//import frc.robot.RobotContainer;
import frc.robot.Constants.DriveConstants;
import frc.robot.libraries.ConsoleJoystick;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.commands.*;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.Vision;

public class AutoLeft extends CommandBase {
  /** Creates a new AutoLeft. */

  DriveSubsystem m_drive;
  HopperSubsystem m_hopper;
  IntakeSubsystem m_intake;
  Vision m_vision;
  ConsoleJoystick m_console;

  private static AutoTurn2 m_autoTurn1;
  private static AutoTurn2 m_autoTurn2;

  private static IntakeOn m_intake1;
  private static RevVisionLightOn m_revVision1;
  private static Wait m_wait1;
  private static Wait m_wait2;
  private int m_waitCount;

  private static ParallelRaceGroup m_camera1;
  private static ParallelRaceGroup m_waitIntake1;

  Command m_currentCommand;
  
  public AutoLeft(ConsoleJoystick console, DriveSubsystem drive, HopperSubsystem hopper, IntakeSubsystem intake, Vision vision) {
    System.out.println("AutoLeft constructor");
    m_drive = drive;
    m_hopper = hopper;
    m_intake = intake;
    m_vision = vision;
    m_console = console;
    // Use addRequirements() here to declare subsystem dependencies
    addRequirements(m_drive, m_hopper, m_intake, m_vision);

    m_autoTurn1 = new AutoTurn2(DriveConstants.kTURN_LEFT_AUTO1, m_drive);

    m_wait1 = new Wait(1);
    m_wait2 = new Wait(2);

    m_revVision1 = new RevVisionLightOn(m_vision);

    m_camera1 = new ParallelRaceGroup(m_revVision1, m_wait1);

    m_intake1 = new IntakeOn(m_intake, m_hopper);

    m_waitIntake1 = new ParallelRaceGroup(m_intake1, m_wait2);
    
  }

  private void dashboardCmd(String cmdName) {
    SmartDashboard.putString("Auto Cmd Step", cmdName);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //System.out.println("AutoLeft init");
    m_currentCommand = m_autoTurn1;
    dashboardCmd("1-autoturn1");
    m_currentCommand.initialize();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //System.out.println("AutoLeft execute");
    m_currentCommand.execute();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //System.out.println("AutoLeft end");
    m_currentCommand.end(interrupted);
    dashboardCmd("n-Auto Done");
  }

  private void switchCommand(final Command cmd) {
    m_currentCommand.end(false);
    m_currentCommand = cmd;
    m_currentCommand.initialize();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    //System.out.println("AutoLeft isFinished");
    //boolean returnIsFinished = false;
    //returnIsFinished = m_currentCommand.isFinished();

    if (m_currentCommand.isFinished() == false) {
      return false;
    }

    if (m_currentCommand == m_autoTurn1) {
      if (m_console.getROT_SW_1() > 0) {
        dashboardCmd("2-wait1");
        switchCommand(m_wait1);
        m_waitCount = 1;
      } else {
        dashboardCmd("4-camera1");
        switchCommand(m_camera1);
      }
      return false;
    }

    if (m_currentCommand == m_wait1) {
      if (m_waitCount >= m_console.getROT_SW_1()) {
        dashboardCmd("4-camera1");
        switchCommand(m_camera1);
      } else {
        dashboardCmd("3-wait1");
        switchCommand(m_wait1);
      }
      m_waitCount++;
      return false;
    }

    if (m_currentCommand == m_camera1) {
      dashboardCmd("5-intake1");
      switchCommand(m_waitIntake1);
      return false;
    }

    // last command returns true if isFinished
    if (m_currentCommand == m_waitIntake1) {
      m_currentCommand.end(false);
      return true;
    }

    // if it gets here, then end it to prevent runaway
    return true;
  }
}
