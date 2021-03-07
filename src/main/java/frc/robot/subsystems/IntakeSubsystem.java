// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.graalvm.compiler.lir.alloc.lsra.IntervalWalker;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CommonConstants;
import frc.robot.Constants.IntakeContstants;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new IntakeSubsystem. */

  private DoubleSolenoid intakeLift;

  public IntakeSubsystem() {
    intakeLift = new DoubleSolenoid(CommonConstants.kPCM_PORT,IntakeContstants.kINTAKE_DEPLOY,IntakeContstants.kINTAKE_RETRACT);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void deployIntake() {
    intakeLift.set(DoubleSolenoid.Value.kForward);
  }

  public void retractIntake() {
    intakeLift.set(DoubleSolenoid.Value.kReverse);
  }
}
