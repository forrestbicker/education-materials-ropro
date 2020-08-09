
public class LocalPIDBase {
    protected void calculate() {
    
        enabled = m_enabled;
        if (enabled) {
            double input;

            // Storage for function inputs
            PIDSourceType pidSourceType;
            double P;
            double I;
            double D;
            double feedForward = calculateFeedForward();
            double minimumOutput;
            double maximumOutput;

            // Storage for function input-outputs
            double prevError;
            double error;
            double totalError;

        
            input = m_pidInput.pidGet();

            pidSourceType = m_pidInput.getPIDSourceType();
            P = m_P;
            I = m_I;
            D = m_D;
            minimumOutput = m_minimumOutput;
            maximumOutput = m_maximumOutput;

            prevError = m_prevError;
            error = getContinuousError(m_setpoint - input);
            totalError = m_totalError;

            // Storage for function outputs
            double result;

            if (pidSourceType.equals(PIDSourceType.kRate)) {
                if (P != 0) {
                    totalError = clamp(totalError + error, minimumOutput / P, maximumOutput / P);
                }

                result = P * totalError + D * error + feedForward;
            } 
            else {
                if (I != 0) {
                    
                     totalError = clamp(totalError + error, minimumOutput / I, maximumOutput / I);
                
                }
                
                if (Math.abs(error) < deadband) {
                    result = 0;
                } 
                else {
                    result = P * error + I * totalError + D * (error - prevError) + feedForward;
                }

            }

            result = clamp(result, minimumOutput, maximumOutput);

            
            m_prevError = error;
            m_error = error;
            m_totalError = totalError;
            m_result = result;
        } 
    }
    protected double calculateFeedForward() {
        if (m_pidInput.getPIDSourceType().equals(PIDSourceType.kRate)) {
            return m_F * getSetpoint();
        } else {
            double temp = m_F * getDeltaSetpoint();
            m_prevSetpoint = m_setpoint;
            m_setpointTimer.reset();
            return temp;
        }
    }

}