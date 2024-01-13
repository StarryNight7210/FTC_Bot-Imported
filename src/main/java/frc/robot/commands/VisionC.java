// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class VisionC extends Command {
  /** Creates a new VisionC. */

  private PhotonCamera camera;
  private PhotonPipelineResult returned;
  private double test ;
  private Drivetrain drivetrain;
  public VisionC() {
    test = .5;
    PhotonCamera camera = new PhotonCamera("photonvision");
    returned = camera.getLatestResult(); 
    this.drivetrain = drivetrain;

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

     if (returned.hasTargets()){
          List<PhotonTrackedTarget> targets = returned.getTargets();
    
    
          PhotonTrackedTarget target = targets.get(0);
    
    
          if ( target.getArea() > test ) {
                drivetrain.setLeftPower(-0.3);
                drivetrain.setRightPower(-0.3);
          }
    
          else if (target.getArea() < test){
            drivetrain.setLeftPower(0.3);
            drivetrain.setRightPower(0.3);
    
          }
    
          else{
            System.out.println("I did a thing");
    
            
    
          }
        }


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
