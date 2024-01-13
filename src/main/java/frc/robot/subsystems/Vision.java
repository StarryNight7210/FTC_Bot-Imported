/* 
package frc.robot.subsystems;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;

public class Vision {
    
    public Vision(){
    PhotonCamera camera = new PhotonCamera("photonvision");
    var returned = camera.getLatestResult();    

    double test = .5;
    if (returned.hasTargets()){
      List<PhotonTrackedTarget> targets = returned.getTargets();


      PhotonTrackedTarget target = targets.get(0);


      if ( target.getArea() > test ) {
            Drivetrain.setPower(-0.1);
      }

      else if (target.getArea() < test){
        Drivetrain.setPower(0.1);

      }

      else{
        System.out.println("I did a thing");

        

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
