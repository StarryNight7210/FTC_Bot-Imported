// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  private Driveside left;
  private Driveside right;

  public Drivetrain() {
    left = new Driveside(Constants.LEFT_FRONT, Constants.LEFT_BACK);
    right = new Driveside(Constants.RIGHT_FRONT, Constants.RIGHT_BACK);
  }

  public class Driveside {
    TalonSRX lead;
    TalonSRX follower;

    public Driveside(int MotorIDLead, int MotorIDFollower) {
      this.lead = new TalonSRX(MotorIDLead);
      this.follower = new TalonSRX(MotorIDFollower);
      follower.follow(lead);
      lead.setNeutralMode(NeutralMode.Brake);
    }

    public void setPower(double power) {
      lead.set(ControlMode.PercentOutput, power);
      follower.set(ControlMode.PercentOutput, power);
    }

    public void setPower(double front, double back) {
      lead.set(ControlMode.PercentOutput, front);
      follower.set(ControlMode.PercentOutput, back);
    }
  }

  public void setLeftPower(double power) {
    left.setPower(-power);
  }

  public void setRightPower(double power) {
    right.setPower(power);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}