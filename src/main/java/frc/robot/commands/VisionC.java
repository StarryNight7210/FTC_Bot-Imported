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
  private double test;
  private Drivetrain m_drivetrain;

  public VisionC(Drivetrain drivetrain) {
    this.test = .5;
    camera = new PhotonCamera("Arducam_OV9281_USB_Camera (1)");
    m_drivetrain = drivetrain;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    PhotonPipelineResult returned = camera.getLatestResult(); 
    
     if (returned.hasTargets()){
          
          List<PhotonTrackedTarget> targets = returned.getTargets();
    
          PhotonTrackedTarget target = targets.get(0);
          m_drivetrain.setLeftPower(-test);
          m_drivetrain.setRightPower(-test);
     }
      else {
        m_drivetrain.setLeftPower(0);
        m_drivetrain.setRightPower(0);



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
