// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.DoubleEntry;
import edu.wpi.first.util.datalog.DoubleLogEntry;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.constants.CANConstants;
import frc.robot.constants.DIOConstants;
import frc.robot.constants.IntakeConstants;
import frc.robot.subsystems.SubsystemABC;

public class IntakeWheels extends SubsystemABC {
  private final CANSparkMax intakeWheel;

  private DoubleEntry intakeVoltage;

  /** Creates a new Intake. */
  public IntakeWheels() {
    super();

    intakeWheel = new CANSparkMax(CANConstants.Intake.kIntakeWheels, MotorType.kBrushless);

    setupNetworkTables("intakeWheels");

    intakeVoltage = ntTable.getDoubleTopic("wheels_voltage").getEntry(0);

    setupShuffleboard();
    setupTestCommands();
    seedNetworkTables();
  }

  @Override
  public void setupShuffleboard() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    writePeriodicOutputs();

    SmartDashboard.putNumber("intake bus voltage", intakeWheel.getBusVoltage());
  }

  @Override
  public void writePeriodicOutputs() {

  }

  @Override
  public void setupTestCommands() {

  }

  @Override
  public void seedNetworkTables() {
    setIntakeWheels(0);
    getIntakeWheels();
  }

  // GETTERS
  public double getIntakeWheels() {
    return intakeVoltage.get();
  }

  private DoubleLogEntry intakeVoltageLog = new DoubleLogEntry(log, "/intake/target");

  // SETTERS
  public void setIntakeWheels(double voltage) {
    intakeVoltage.set(voltage);
    intakeVoltageLog.append(voltage);

    intakeWheel.set(voltage);
  }
}
