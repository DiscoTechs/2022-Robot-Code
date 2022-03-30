 package frc.robot;

public class RobotMap {
    
    // CAN IDs
    public static int LEFT_MOTOR = 10;
    public static int LEFT_FOLLOWER = 11;

    public static int RIGHT_MOTOR = 20;
    public static int RIGHT_FOLLOWER = 21;

    public static int INTAKE_ARM = 55;
    public static int SPINNER = 56;

    public static int ELEVATOR = 40;
    public static int WINCH = 41;

    // USB Controllers
    public static int LEFT_STICK = 0;
    public static int RIGHT_STICK = 1;
    public static int GAMEPAD = 2;
    public static int DRIVEPAD = 3;

    // ATTACK3 
    public static int ATTACK_X = 0;
    public static int ATTACK_Y = 1;
    public static int ATTACK_Z = 2;

    // XBOX
    public static int XBX_L_X = 0;
    public static int XBX_L_Y = 1;
    public static int XBX_L_TRIG = 2;
    public static int XBX_R_TRIG = 3;
    public static int XBX_R_X = 4;
    public static int XBX_R_Y = 5;

    public static int XBX_A = 1;
    public static int XBX_B = 2;
    public static int XBX_C = 3;
    public static int XBX_D = 4;
    public static int LEFT_BUMPER = 5;
    public static int RIGHT_BUMPER = 6;

    // Map logical controls to physical descriptions
    public static int SPEED_AXIS = XBX_L_Y;
    public static int ROTATE_AXIS = XBX_R_X;

    public static int SPINNER_OUT = XBX_L_TRIG;
    public static int SPINNER_IN = XBX_R_TRIG;

    // DIO Channels
    public static int ARM_TOP_LIMIT = 0;
    public static int ARM_BOTTOM_LIMIT = 1;


}
