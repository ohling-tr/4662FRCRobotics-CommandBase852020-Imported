// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CommonConstants;
import frc.robot.Constants.HopperConstants;

public class HopperSubsystem extends SubsystemBase {
  /** Creates a new HopperSubsystem. */

  private DoubleSolenoid m_beltFrameLift;

  public HopperSubsystem() {
    m_beltFrameLift = new DoubleSolenoid(CommonConstants.kPCM_PORT,HopperConstants.kBELT_FRAME_FWD,HopperConstants.kBELT_FRAME_REV);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void liftBeltFrame() {
    m_beltFrameLift.set(DoubleSolenoid.Value.kForward);
  }

  public void dropBeltFrame() {
    m_beltFrameLift.set(DoubleSolenoid.Value.kReverse);
  }
}
