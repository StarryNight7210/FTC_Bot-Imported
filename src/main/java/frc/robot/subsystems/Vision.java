// this code doesn't use multable cams
package frc.robot.subsystems;

import java.util.ArrayList;
import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Vision extends SubsystemBase {

  private PhotonCamera camera;
  private PhotonPipelineResult returned;

 
  // stole from photonvision.
 
  private final double CAMERA_HEIGHT_METERS = Units.inchesToMeters(12);
  private final double TARGET_HEIGHT_METERS = Units.feetToMeters(34);
  private final double CAMERA_PITCH_RADIANS = Units.degreesToRadians(5);


  public Vision(){
    
    camera = new PhotonCamera("Left");
    returned = camera.getLatestResult();


  }

  public List<PhotonTrackedTarget> getTargetsList(){
    returned = camera.getLatestResult();
     if (returned.hasTargets() ){
    var targets = returned.getTargets();
    return targets;}
    return new ArrayList<PhotonTrackedTarget>() ;
  
  }

  public int getTagIndex(int april) {
    returned = camera.getLatestResult();
    int hold = -1;
    if (returned.hasTargets() ){

      var targets = returned.getTargets();
      for (int i = 0; i < targets.size(); i++) {
        if (april == targets.get(i).getFiducialId()) hold = i;

      }
    return hold ;
  }
  return -1 ;
}

  public double getTargetsMeters(int Target){
    returned = camera.getLatestResult();
    if (returned.hasTargets() ){
      // fix later
    List<PhotonTrackedTarget> targets = returned.getTargets();
    PhotonTrackedTarget target = targets.get(Target);

    double length = Math.pow(Math.pow(PhotonUtils.calculateDistanceToTargetMeters( CAMERA_HEIGHT_METERS,
    TARGET_HEIGHT_METERS,
    CAMERA_PITCH_RADIANS,
    Units.degreesToRadians(target.getPitch())), 2.0), 0.5);
    return length;




  }
  return 0.0;
}

  public double getYaw(int Target){
    returned = camera.getLatestResult();
    if (returned.hasTargets() ){
      //fix later
      List<PhotonTrackedTarget> targets = returned.getTargets();
      PhotonTrackedTarget target = targets.get(Target);

      return target.getYaw();

    }
    return 0.0;
  }

  public double getPitch(int Target){
    if (returned.hasTargets() ){
      //fix later
      List<PhotonTrackedTarget> targets = returned.getTargets();
      PhotonTrackedTarget target = targets.get(Target);

      return target.getPitch();

    }
    return 0.0;
  }

  public double getSkew(int Target){
    if (returned.hasTargets() ){
      //fix later
      List<PhotonTrackedTarget> targets = returned.getTargets();
      PhotonTrackedTarget target = targets.get(Target);

      return target.getSkew();

    }
    return 0.0;
  }

  public double getArea(int Target){
    if (returned.hasTargets() ){
      //fix later
      List<PhotonTrackedTarget> targets = returned.getTargets();
      PhotonTrackedTarget target = targets.get(Target);

      return target.getArea();

    }
    return 0.0;
  }
  public double getAmbiguity(int Target){
    if (returned.hasTargets() ){
      //fix later
      List<PhotonTrackedTarget> targets = returned.getTargets();
      PhotonTrackedTarget target = targets.get(Target);

      return target.getPoseAmbiguity();

    }
    return 0.0;
  }


  public double getTrueCenter(int Target){
    // you have to change for each bot
    double true_center = (Math.asin(Units.inchesToMeters(-3.5)/getTargetsMeters(Target)));

    
    return true_center;
  }




  






}
    
        





     /*    
        int id = tar.getFiducialId();
        decoder.add(id);




      }
       this code is trying to make to list of the targets and what target is the index.
      */







/*

    This code is to print out the values of photon for target one 
     PhotonTrackedTarget target = targets.get(0);

    double yaw = target.getYaw();
    double pitch = target.getPitch();
    double area = target.getArea();
    double skew = target.getSkew();

    System.out.printf("f% yaw /n , %f pitch /n ,%f  area /n,%f skew /n ",yaw,pitch,area,skew);

*/
