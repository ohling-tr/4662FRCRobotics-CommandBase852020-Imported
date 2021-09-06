// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.libraries;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.ConsoleConstants;

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


//import static edu.wpi.first.wpilibj.util.ErrorMessages.requireNonNullParam;

import java.util.LinkedHashMap;
import java.util.Map;
//import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntSupplier;
import static edu.wpi.first.wpilibj.util.ErrorMessages.requireNonNullParam;


/**
 * The {@link SendableChooser} class is a useful tool for presenting a selection of options to the
 * {@link SmartDashboard}.
 *
 * <p>For instance, you may wish to be able to select between multiple autonomous modes. You can do
 * this by putting every possible Command you want to run as an autonomous into a {@link
 * SendableChooser} and then put it into the {@link SmartDashboard} to have a list of options appear
 * on the laptop. Once autonomous starts, simply ask the {@link SendableChooser} what the selected
 * value is.
 *
 * @param <V> The type of the values to be stored
 */
public class ConsoleCommand {

  
  /** The key for the default value. */
  //private static final String DEFAULT = "default";
  /** The key for the selected option. */
  //private static final String SELECTED = "selected";
  /** The key for the active option. */
  //private static final String ACTIVE = "active";
  /** The key for the option array. */
  //private static final String OPTIONS = "options";
  /** The key for the instance number. */
  //private static final String INSTANCE = ".instance";
  /** A map linking strings to the objects the represent. */
  
  private final Map<String, Command> m_map = new LinkedHashMap<>();

  private String m_defaultChoice = "";
  
  /** Creates a new ConsoleCommand. */
  public ConsoleCommand() {

  }

  /**
   * Adds the given object to the list of options. On the {@link SmartDashboard} on the desktop, the
   * object will appear as the given name.
   *
   * @param name the name of the option
   * @param object the option
   */
  public void addOption(String name, Command command) {
    m_map.put(name, command);
  }

  public void setDefaultOption(String name, Command object) {
    requireNonNullParam(name, "name", "setDefaultOption");

    m_defaultChoice = name;
    addOption(name, object);
  }
  /*
  * given the position and pattern settings find a matching name
  * if the settings are out of bounds use the default
  * and hope someone defined a default command
  */
  //public String getPatternName(IntSupplier position, IntSupplier pattern) {
  public String getPatternName(IntSupplier position) {

    int iPosition = position.getAsInt() / ConsoleConstants.kPOV_INTERVAL;
    //int iPattern = pattern.getAsInt() / ConsoleCommandConstants.kPOV_INTERVAL;
    String patternName = m_defaultChoice;

    if (iPosition < ConsoleConstants.kPOS_PATTERN_NAME.length) {
      //if (iPattern < ConsoleCommandConstants.kPOS_PATTERN_NAME[iPosition].length) {
        //patternName = ConsoleCommandConstants.kPOS_PATTERN_NAME[iPosition][iPattern];
      //}
      patternName = ConsoleConstants.kPOS_PATTERN_NAME[iPosition];
    }
    SmartDashboard.putString("Auto Name", patternName);
    return patternName;

  }

  /**
   * Returns the selected option. If there is none selected, it will return the default. If there is
   * none selected and no default, then it will return {@code null}.
   *
   * @return the option selected
   */
  //public Command getSelected(IntSupplier position, IntSupplier pattern) {
  public Command getSelected(IntSupplier position) {

    Command autoCommand = null;
    //String sPatternName = getPatternName(position, pattern);
    String sPatternName = getPatternName(position);
    autoCommand = m_map.get(sPatternName);
    Boolean bIsCommandFound = autoCommand != null;
    SmartDashboard.putBoolean("Auto Found", bIsCommandFound);
    if (!bIsCommandFound) {
      autoCommand = m_map.get(m_defaultChoice);
    }
    return autoCommand;
  
  }

}