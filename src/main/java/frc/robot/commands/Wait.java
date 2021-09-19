// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.WaitCommand;

public class Wait extends WaitCommand {
  /** Creates a new Wait. */
  public Wait(double seconds) {
    // Use addRequirements() here to declare subsystem dependencies.
    super(seconds);
  }

}
