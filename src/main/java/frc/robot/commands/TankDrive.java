// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Xbox;

public class TankDrive extends Command {
  private Drivetrain drivetrain;
  private Xbox controller;
  /** Creates a new TankDrive. */
  public TankDrive(Drivetrain drivetrain, Xbox controller) {
    this.drivetrain = drivetrain;
    this.controller = controller;
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // drivetrain.setLeftPower((controller.getRightStickY() - controller.getRightStickX()) * 0.35);
    // drivetrain.setRightPower((controller.getRightStickY() + controller.getRightStickX()) * 0.35);
    drivetrain.setLeftPower((controller.getLeftStickY()) * 0.5);
    drivetrain.setRightPower((controller.getRightStickY()) * 0.5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.setLeftPower(0.0);
    drivetrain.setRightPower(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
