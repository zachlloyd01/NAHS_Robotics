package org.usfirst.frc.team3694.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;

public class Robot extends IterativeRobot {

	Victor fl = new Victor(0);
	Victor fr = new Victor(2);
	Victor bl = new Victor(1);
	Victor br = new Victor(3);
	Joystick leftStick = new Joystick(0);
	Joystick rightStick = new Joystick(1);
	
	@Override
	public void robotInit() {
		fl.setInverted(true);
		bl.setInverted(true);
	}

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
		while (isEnabled() && isOperatorControl()) {
			fl.set(leftStick.getY());
			bl.set(leftStick.getY());
			fr.set(rightStick.getY());
			br.set(rightStick.getY());
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}

