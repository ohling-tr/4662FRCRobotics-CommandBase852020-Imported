// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoTurn extends PIDCommand {

  private final DriveSubsystem m_drive;
  private final PIDController m_turnController;
  
  /** Creates a new AutoTurn. */
  public AutoTurn(PIDController turnController, DoubleSupplier currentAngle, DoubleSupplier targetAngle, DriveSubsystem drive) {
    super(
        // The controller that the command will use
        //drive.m_turnPIDController,
        turnController,
        // This should return the measurement
        () -> drive.getYaw(),
        // This should return the setpoint (can also be a constant)
        targetAngle,
        // This uses the output
        output -> {
          drive.arcadeDrive(0, output);
        });

    m_drive = drive;
    m_turnController = turnController;
    //m_startAngleSupplier = currentAngle;
    //m_startAngle = m_startAngleSupplier.getAsDouble();
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
    // Configure additional PID options by calling `getController` here.
  }

  public AutoTurn(PIDController turnController, DoubleSupplier currentAngle, double targetAngle, DriveSubsystem drive) {
    this(turnController, currentAngle, () -> targetAngle, drive);
  }

  @Override
  public void initialize() {
    // TODO Auto-generated method stub
    super.initialize();
    m_drive.resetYaw();
  }

  @Override
  public void execute() {
    // TODO Auto-generated method stub
    super.execute();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_turnController.atSetpoint();
  }
}
