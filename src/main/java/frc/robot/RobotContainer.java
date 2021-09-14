/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.JoystickButtons;
import frc.robot.Constants.ConsoleConstants;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.libraries.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static final DriveSubsystem m_drive = new DriveSubsystem();
  private final IntakeSubsystem m_intake = new IntakeSubsystem();
  private final HopperSubsystem m_hopper = new HopperSubsystem();
  private final Vision m_vision = new Vision();
  private final ConsoleCommand m_consoleCommand = new ConsoleCommand();

  private final Joystick m_driveStick = new Joystick(0);
  private final JoystickButton m_turnDash = new JoystickButton(m_driveStick, JoystickButtons.kTURN_DASH);
  private final Joystick m_console = new Joystick(1);

  private final AutoLeft m_autoLeft;
  private final AutoMiddle m_autoMiddle;
  private final AutoDefault m_aAutoDefault;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    m_drive.setDefaultCommand(
      new ArcadeDrive(
        m_drive,
        () -> m_driveStick.getY(),
        () -> m_driveStick.getTwist(),
        () -> m_driveStick.getThrottle(),
        () -> m_console.getX()
      )
    );

    m_vision.setDefaultCommand(
      new FwdCameraTilt(m_vision,
        () -> m_console.getY()
      )
    );

    m_aAutoDefault = new AutoDefault();
    m_consoleCommand.setDefaultOption(ConsoleConstants.kPOS_PATTERN_NAME[ConsoleConstants.kPOSITION_DEFAULT_I], m_aAutoDefault);
    m_autoLeft = new AutoLeft(m_drive);
    m_consoleCommand.addOption(ConsoleConstants.kPOS_PATTERN_NAME[ConsoleConstants.kPOSITION_LEFT_I], m_autoLeft);
    m_autoMiddle = new AutoMiddle();
    m_consoleCommand.addOption(ConsoleConstants.kPOS_PATTERN_NAME[ConsoleConstants.kPOSITION_MIDDLE_I], m_autoMiddle);

    // lamda for pov - "() -> m_Console.getPOV(0)"
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    new JoystickButton(m_driveStick, JoystickButtons.kRESET_DRIVE)
      .whenPressed(() -> m_drive.resetDrive());
    
    new JoystickButton(m_driveStick, JoystickButtons.kFWD_VISION_ON)
      .whileHeld(new FwdVisionLightOn(m_vision));
  
    new JoystickButton(m_driveStick, JoystickButtons.kREV_VISION_ON)
      .whileHeld(new RevVisionLightOn(m_vision));

    new JoystickButton(m_driveStick, JoystickButtons.kINTAKE)
      .whileHeld(new IntakeOn(m_intake, m_hopper));

    m_turnDash.whenPressed(new AutoTurn(m_drive.m_turnPIDController, () -> m_drive.getYaw(), () -> m_drive.getDashboardAngle(), m_drive));

  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    // lamda for pov - "() -> m_Console.getPOV(0)"

    //Command autoCommand = m_consoleCommand.getSelected(() -> m_console.getPOV(0), () -> m_console.getPOV(1));
    Command autoCommand = m_consoleCommand.getSelected(() -> m_console.getPOV(0));
    return autoCommand;
  
  }
}