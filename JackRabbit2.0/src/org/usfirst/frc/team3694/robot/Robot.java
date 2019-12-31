package org.usfirst.frc.team3694.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
z
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	final String TankDrive = "TankDrive";
	final String ArcadeDrive = "ArcadeDrive";
	final String rightArcade = "rightArcade";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
	
	Victor frontLeft = new Victor(0);
	Victor backLeft = new Victor(1);
	Victor frontRight = new Victor(2);
	Victor backRight = new Victor(3);
	
	Joystick leftStick = new Joystick(0);
	Joystick rightStick = new Joystick(1);
	Joystick manStick = new Joystick(2);

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("TankDrive", TankDrive);
		chooser.addObject("Left-Handed ArcadeDrive", ArcadeDrive);
		chooser.addObject("Right-Handed ArcadeDrive", rightArcade);
		SmartDashboard.putData("Auto choices", chooser);
		autoSelected = chooser.getSelected();
		backRight.setInverted(true);
		frontRight.setInverted(true);
	}


	@Override
	public void autonomousInit() {
	
	}


	@Override
	public void autonomousPeriodic() {
	
	}

	@Override
	public void teleopPeriodic() {
		while (isOperatorControl() && isEnabled()) {
			//tankdrive commands
			double tankLeftCommand = leftStick.getY();
			double tankRightCommand = rightStick.getY();
			
			//right-handed arcadedrive commands
			double rightArcadeTurnCommand = rightStick.getX();
			double rightArcadeSpeedCommand = rightStick.getY();
			
			//left-handed arcadedrive commands
			double leftArcadeTurnCommand = leftStick.getX();
			double leftArcadeSpeedCommand = leftStick.getY(); 
		
					tankDrive(tankRightCommand, tankLeftCommand);
		
			}
		}
	

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
	public void tankDrive(double tankLeft, double tankRight) {
		double left = tankLeft;
		double right = tankRight;
	
		frontRight.set(right);
		frontLeft.set(left);
		backRight.set(right);
		backLeft.set(left);
	}
	public void rightArcadeDrive(double rightArcadeTurn, double rightArcadeSpeed){
		double rightArcadeLeft = rightArcadeSpeed + rightArcadeTurn;
		double rightArcadeRight = rightArcadeSpeed - rightArcadeTurn;
		
		backRight.set(rightArcadeRight); //set motor to joystick
		frontRight.set(rightArcadeRight); //set motor to joystick
		backLeft.set(rightArcadeLeft); //set motor to joystick
		frontLeft.set(rightArcadeLeft); //set motor to joystick
	}
	public void arcadeDrive(double leftArcadeTurn, double leftArcadeSpeed) {
		double leftArcadeLeft = leftArcadeSpeed + leftArcadeTurn;
		double leftArcadeRight = leftArcadeSpeed - leftArcadeTurn;
		
		backRight.set(leftArcadeRight); //set motor to joystick
		frontRight.set(leftArcadeRight); //set motor to joystick
		backLeft.set(leftArcadeLeft); //set motor to joystick
		frontLeft.set(leftArcadeLeft); //set motor to joystick
	}
}

