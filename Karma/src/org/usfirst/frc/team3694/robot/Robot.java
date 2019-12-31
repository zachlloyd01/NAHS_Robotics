package org.usfirst.frc.team3694.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
	Joystick driveStick = new Joystick(0); //Middle JoyStick
	Joystick manStick = new Joystick(1); //Right JoyStick
	//JoystickButton climbbutt = new JoystickButton(driveStick, 1);
	JoystickButton reverse1 = new JoystickButton(driveStick, 1);
	JoystickButton normal1 = new JoystickButton(driveStick, 3);
	Timer autoTimer = new Timer();
	Victor frontLeft = new Victor(0);
	Victor frontRight = new Victor(2);
	Victor backLeft = new Victor(1);
	Victor backRight = new Victor(3);
	Spark Climb = new Spark(4);
	
	//Gyro gyro;
	//static final double Kp = 0.09;	V
	@Override
	public void robotInit() {
		chooser.addDefault("Go past the line", defaultAuto);
		chooser.addObject("Attempt the gear", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
		//CameraServer.getInstance().startAutomaticCapture();
	}
	
	@Override
	public void autonomousInit() {
		autoSelected = chooser.getSelected();
		 autoSelected = SmartDashboard.getString("Auto Selector",
		 defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
		autoTimer.reset();
		autoTimer.start();
	}

	@Override
	public void autonomousPeriodic() {
		while (isAutonomous() && isEnabled()) {			
			switch (autoSelected) {
				case customAuto:
					if(autoTimer.get() < 1.0) {
						arcadeDrive(-0.4, -0.1);
					}
					else {
						arcadeDrive(0, 0);
						
					}
					break;
			
				case defaultAuto:
					default:
						if(autoTimer.get() < 2.0 ) {
							arcadeDrive(1.0, -0.1);
						} 
						else{
							arcadeDrive(0, 0);
							
							}
			}
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() { //joystick control time
		while (isOperatorControl() && isEnabled()) {
			double speedCommand = driveStick.getY(); //Y value for making the chassis move. Essentially front back
			double turnCommand = driveStick.getX(); //X value for turning the robot chassis side side
			
			//chassis.tankDrive(driveStick, shootStick);
			
		Climb.set(manStick.getY()*0.9); //climb using manStick
				if(driveStick.getTrigger() == true){
					arcadeDrive(-speedCommand, -turnCommand);
				}else{
					arcadeDrive(speedCommand, -turnCommand); //drive command
				}
			}
		
			Timer.delay(0.01); //Update Time
		}
	@Override
	public void testPeriodic() {//test command
	}
	
	public void arcadeDrive (double speed, double turn) { //arcade drive command
		double left = speed + turn; //left turn
		double right= speed - turn; //right turn
		
		backLeft.set(-left); //backleft motor control
		frontLeft.set(-left); //frontleft motor control
		frontRight.set(right); //front right motor control
		backRight.set(right); //backright motor
	}
}