package org.usfirst.frc.team3694.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {
	Joystick leftStick = new Joystick(0);
	Joystick rightStick = new Joystick(1);
	Joystick manStick = new Joystick(2);
	
	
	Victor frontRight = new Victor(0);
	Victor backRight = new Victor(1);
	Victor frontLeft = new Victor(2);
	Victor backLeft = new Victor(3);
	Victor roller = new Victor(4);
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		roller.setInverted(true);
		CameraServer.getInstance().startAutomaticCapture();
	}

	@Override
	public void autonomousInit() {

	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {

	}
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		while (isOperatorControl() && isEnabled()) {
		double leftmotorCommand = leftStick.getY();
		double rightmotorCommand = rightStick.getY();
		
		tankDrive(leftmotorCommand, rightmotorCommand);
		roller.set(manStick.getY());
		}
	}
	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
	public void tankDrive(double leftmotor, double rightmotor) { //tankDrive command
		double left = leftmotor; //set left motor command
		double right = rightmotor; //set right motor command
		
		backRight.set(right); //set motor to joystick
		frontRight.set(right); //set motor to joystick
		backLeft.set(left); //set motor to joystick
		frontLeft.set(left); //set motor to joystick
	}
}

