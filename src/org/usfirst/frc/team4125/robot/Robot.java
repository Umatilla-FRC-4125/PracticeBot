package org.usfirst.frc.team4125.robot;
//test thing v2

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.vision.USBCamera;

public class Robot extends SampleRobot {
	Talon tal0, tal1, tal2, tal3, shooter;
    Joystick joy1;
    //Compressor c;
    Solenoid s1, s2;
    Spark intake;
    boolean toggle;
    boolean button;
    boolean shooterToggle;
    boolean shooterButton;
    
    public Robot() {
    	tal0 = new Talon(0);
    	tal1 = new Talon(1);
    	tal2 = new Talon(2);
    	tal3 = new Talon(3);
    	shooter = new Talon(5);
    	joy1 = new Joystick(0);
    	s1 = new Solenoid(0);
    	s2 = new Solenoid(1);
    	//c = new Compressor(0);
    	intake = new Spark(4);
    	
    }

    public void teleopInit(){
    	tal0.set(0);
    	tal1.set(0);
    	tal2.set(0);
    	tal3.set(0);
    	shooter.set(0);
    	toggle = false;
    	shooterToggle = false;
    }
    public void operatorControl() {
    	tal0.setInverted(true);
    	tal1.setInverted(true);
        while (isOperatorControl() && isEnabled()) {
        	//c.setClosedLoopControl(true);
        	//intakeSwitch = false;
        	//Shifters
        	if(joy1.getRawButton(1) == true  ){
        		s1.set(true);
        		s2.set(false);
        	}else if(joy1.getRawButton(2) == true ){
        		s1.set(false);
        		s2.set(true);}
        	//intake on and off
        	if (toggle && joy1.getRawButton(3)) {  // Only execute once per Button push
        		  toggle = false;  // Prevents this section of code from being called again until the Button is released and re-pressed
        		  if (button) {  // Decide which way to set the motor this time through (or use this as a motor value instead)
        		    button= false;
        		    intake.set(1);
        		  } else {
        		    button= true;
        		    intake.set(0);
        		  }
        		} else if(joy1.getRawButton(3) == false) { 
        		    toggle = true; // Button has been released, so this allows a re-press to activate the code above.
        		}
        	//shooter on and off
//        	if(joy1.getRawButton(4) == true){
//        		shooter.set(1);
//        	}else{
//        		shooter.set(0);
//        	}
        	if (shooterToggle && joy1.getRawButton(4)) {  // Only execute once per Button push
      		  shooterToggle = false;  // Prevents this section of code from being called again until the Button is released and re-pressed
      		  if (shooterButton) {  // Decide which way to set the motor this time through (or use this as a motor value instead)
      		    shooterButton= false;
      		    shooter.set(-.6);
      		  } else {
      		    shooterButton= true;
      		    shooter.set(0);
      		  }
      		} else if(joy1.getRawButton(4) == false) { 
      		    shooterToggle = true; // Button has been released, so this allows a re-press to activate the code above.
      		}
        	//drive System
            if(joy1.getRawAxis(0) > 0.1){
            	tal0.set(joy1.getRawAxis(1) - joy1.getRawAxis(0));
            	tal1.set(joy1.getRawAxis(1) - joy1.getRawAxis(0));
            	tal2.set(joy1.getRawAxis(1) + joy1.getRawAxis(0));
            	tal3.set(joy1.getRawAxis(1) + joy1.getRawAxis(0));
        	}else if(joy1.getRawAxis(0) < -0.1){
            	tal0.set(joy1.getRawAxis(1) - joy1.getRawAxis(0));
            	tal1.set(joy1.getRawAxis(1) - joy1.getRawAxis(0));
            	tal2.set(joy1.getRawAxis(1) + joy1.getRawAxis(0));
            	tal3.set(joy1.getRawAxis(1) + joy1.getRawAxis(0));
        	}else if(joy1.getRawAxis(1) > 0.1 || joy1.getRawAxis(1) < -0.1){
        		tal0.set(joy1.getRawAxis(1));
        		tal1.set(joy1.getRawAxis(1));
        		tal2.set(joy1.getRawAxis(1));
        		tal3.set(joy1.getRawAxis(1));
        	}else {
        		tal0.set(0);
        		tal1.set(0);
        		tal2.set(0);
        		tal3.set(0);}
        }
    }
    
    public void autonomousInit(){
    }

	public void autonomous(){
	}
}

