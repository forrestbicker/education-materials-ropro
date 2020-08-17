float KpAim = -0.1f;
float KpDistance = -0.1f;
float min_aim_command = 0.05f;

std::shared_ptr<NetworkTable> table = NetworkTable::GetTable("limelight");
float tx = table->GetNumber("tx");
float ty = table->GetNumber("ty");

if (joystick->GetRawButton(9))
{
        float heading_error = -tx;
        float distance_error = -ty;
        float steering_adjust = 0.0f;

        if (tx > 1.0)
        {
                steering_adjust = KpAim*heading_error - min_aim_command;
        }
        else if (tx < 1.0)
        {
                steering_adjust = KpAim*heading_error + min_aim_command;
        }

        float distance_adjust = KpDistance * distance_error;

        left_command += steering_adjust + distance_adjust;
        right_command -= steering_adjust + distance_adjust;
}