// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.List;
import java.util.ArrayList;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Xbox;

public class TankDrivePlus extends Command {
  private Drivetrain drivetrain;
  private Xbox controller;
  private Shooter shooter;
  private Intake intake;

  /** Creates a new TankDrive. */
  public TankDrivePlus(Drivetrain drivetrain, Xbox controller, Shooter shooter, Intake intake) {
    this.drivetrain = drivetrain;
    this.shooter = shooter;
    this.intake = intake;
    this.controller = controller;
    addRequirements(drivetrain);
    addRequirements(shooter);
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  public double deadzone(double value) {
    if (value > 0.05) {
      return (value - 0.05) / 0.95;
    } else if (value < -0.05) {
      return (value + 0.05) / 0.95;
    } return 0.0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.setLeftPower((deadzone(controller.getRightStickY()) - deadzone(controller.getRightStickX())) * Constants.drivetrainSpeed);
    drivetrain.setRightPower((deadzone(controller.getRightStickY()) + deadzone(controller.getRightStickX())) * Constants.drivetrainSpeed);
    // drivetrain.setLeftPower((controller.getLeftStickY()));
    // drivetrain.setRightPower((controller.getRightStickY()));
    intake.setPower(controller.getLeftTriggerAxis());
    shooter.setPower(controller.getLeftTriggerAxis());
    double right = controller.getRightTriggerAxis();
    if (right > 0) {
      intake.setPower(-right);
      shooter.setPower(-right);
    }
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
