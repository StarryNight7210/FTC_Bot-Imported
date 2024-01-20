// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class VisionC extends Command {
  /** Creates a new VisionC. */

  private PhotonCamera camera;
  private PhotonPipelineResult returned;
  private Drivetrain m_drivetrain;
 
  // stole from photonvision.
 
  private final double CAMERA_HEIGHT_METERS = Units.inchesToMeters(12);
  private final double TARGET_HEIGHT_METERS = Units.feetToMeters(2);

  private final double CAMERA_PITCH_RADIANS = Units.degreesToRadians(5);

  // How far from the target we want to be
  private final double GOAL_RANGE_METERS = Units.feetToMeters(0);



  public VisionC(Drivetrain drivetrain) {
    camera = new PhotonCamera("Left");
      m_drivetrain = drivetrain;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  // @Override
 public void execute() {
   
    returned = camera.getLatestResult();
    
    
  if (returned.hasTargets()){
    List<PhotonTrackedTarget> targets = returned.getTargets();
    PhotonTrackedTarget target = targets.get(0);

    double length = Math.pow(Math.pow(PhotonUtils.calculateDistanceToTargetMeters( CAMERA_HEIGHT_METERS,
    TARGET_HEIGHT_METERS,
    CAMERA_PITCH_RADIANS,
    Units.degreesToRadians(target.getPitch())), 2.0), 0.5);
    System.err.printf("dis = %f",length );
    double true_center = (Math.asin(Units.inchesToMeters(-3.5)/length));
    double test =Math.abs(target.getYaw() - true_center);
    System.out.printf("test = %f \n",test );
    if ( test < 1 && length > GOAL_RANGE_METERS ){

      
        m_drivetrain.setRightPower(-.25);
        m_drivetrain.setLeftPower(-.25);
      
    } 
    else if ( true_center - target.getYaw() > 1  ){
      m_drivetrain.setRightPower(-.05*test);
        m_drivetrain.setLeftPower(.05*test);

    }
    else if ( true_center - target.getYaw() < -.9){
      m_drivetrain.setRightPower(.05*test);
        m_drivetrain.setLeftPower(-.05*test);

    }
    else {
      m_drivetrain.setRightPower(0);
      m_drivetrain.setLeftPower(0);
    }
  

  }
  else {
      m_drivetrain.setRightPower(0);
      m_drivetrain.setLeftPower(0);
    }
 
    
 

      

  //     double true_center = (Math.cos(Units.inchesToMeters(3.5/length)));//
  //     double test =Math.abs(target.getYaw() - true_center);

  //     if ( test >= .01 && test<= .01 ){

  //     if (length > GOAL_RANGE_METERS){
  //       m_drivetrain.setRightPower(.5);
  //       m_drivetrain.setLeftPower(.5);
  //     }
  //    }
          
  //         // List<PhotonTrackedTarget> targets = returned.getTargets();
    
  //         // PhotonTrackedTarget target = targets.get(0);
  //         // m_drivetrain.setLeftPower(-test);
  //         // m_drivetrain.setRightPower(-test);
     
  //     // else {
  //     //   m_drivetrain.setLeftPower(0);
  //     //   m_drivetrain.setRightPower(0);
  //     // }

  //    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
