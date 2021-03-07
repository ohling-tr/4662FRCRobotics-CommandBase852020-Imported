// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.Vision;

public class IntakeOn extends CommandBase {

  private IntakeSubsystem m_intake;
  private Vision m_vision;
  
  /** Creates a new IntakeOn. */
  public IntakeOn(IntakeSubsystem intake, Vision vision) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake, vision);
    m_intake = intake;
    m_vision = vision;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_intake.deployIntake();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //if (!interrupted) {
      m_intake.retractIntake();
     //}
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //add dio test for hopper full sensor
    return false;
  }
}
