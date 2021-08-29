// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoDefault extends CommandBase {
  /** Creates a new AutoDefault. */
  public AutoDefault() {
    // Use addRequirements() here to declare subsystem dependencies.
    System.out.println("AutoDefault constructor");
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("AutoDefault initialize");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("AutoDefault execute");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("AutoDefault end");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    System.out.println("AutoDefault isFinished");
    return true;
  }
}
