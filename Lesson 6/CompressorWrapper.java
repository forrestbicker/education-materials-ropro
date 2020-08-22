package robotcode.systems;

import config.Config;
import edu.wpi.first.wpilibj.Compressor;

public class CompressorWrapper {
    public static void action(Compressor airCompressor, Config config) {
        if (config.runConstants.RUNNING_PNEUMATICS) {
				airCompressor.start();
			} else if (airCompressor != null) {
				airCompressor.stop();
		}
    }
}