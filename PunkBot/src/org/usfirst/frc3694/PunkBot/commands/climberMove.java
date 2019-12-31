package org.usfirst.frc3694.PunkBot.commands;

import org.usfirst.frc3694.PunkBot.Robot;

import org.usfirst.frc3694.PunkBot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class climberMove extends Command {

    public climberMove() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	RobotMap.climberClimbMotor.set(Robot.oi.shootStick.getY());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.climberClimbMotor.set(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
