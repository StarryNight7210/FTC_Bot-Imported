// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.List;


import frc.robot.subsystems.Vision;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;


public class VisionC1 extends Command {
  /** Creates a new VisionC. */

  
  private Drivetrain m_drivetrain;
  private Vision m_vision ; 
  private int index ;
  
 
 

  // How far from the target we want to be
  private final double GOAL_RANGE_METERS = Units.feetToMeters(1);



  public VisionC1(Drivetrain drivetrain,Vision vision) {

    m_drivetrain=drivetrain;
    m_vision = vision;
    index = -1;

    // Use addRequirements() here to declare subsystem dependencies.
    
    addRequirements(drivetrain);
    addRequirements(vision);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  // @Override
 public void execute() {
  
  int tag_id = 1;
  int index = m_vision.getTagIndex(tag_id);
  System.out.println("index is "+index);
  
  





    
  if (index != -1){ 
    System.out.println("I am so great"); 
    double cent = m_vision.getTrueCenter(index);

    double dif = Math.abs(cent - m_vision.getYaw(index));
    double length =  m_vision.getTargetsMeters(index);
    
    if ( dif < 1 && length > GOAL_RANGE_METERS ){

      
        m_drivetrain.setRightPower(-.25);
        m_drivetrain.setLeftPower(-.25);
      
    } 
    else if ( cent - m_vision.getYaw(index) > 1  ){
      m_drivetrain.setRightPower(-.05*dif);
        m_drivetrain.setLeftPower(.05*dif);

    }
    else if ( cent - m_vision.getYaw(index) < -.9){
      m_drivetrain.setRightPower(.05*dif);
        m_drivetrain.setLeftPower(-.05*dif);

    }
    else {
      m_drivetrain.setRightPower(0);
      m_drivetrain.setLeftPower(0);
    }
  

  

  
  }
  else{
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
