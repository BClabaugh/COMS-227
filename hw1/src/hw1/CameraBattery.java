package hw1;

/**
 * This class controls a camera battery simulation
 * @author Blake_Clabaugh
 *
 */
public class CameraBattery {
	
	
	/**
	 * amount of charger setting
	 */
	public static final int NUM_CHARGER_SETTINGS = 4;
	/**
	 * how fast the battery is charged
	 */
	public static final double CHARGE_RATE = 2.0; 
	/**
	 * default power consump
	 */
	public static final double DEFAULT_CAMERA_POWER_CONSUMPTION = 1.0; 
	
	
	
	/**
	 * creates is variable to see if its being used
	 */
	private double isBeingUsed = 0.0;
	/**
	 * holds value of how charged the battery is
	 */
	private double batteryCharge;
	/**
	 * how much charge the battery can hold
	 */
	private double batteryCapacity;
	/**
	 * sets initial setting for where the charger is at
	 */
	private int chargerSetting = 0;
	/**
	 * holds how much charge the camera has
	 */
	private double cameraCharge;
	/**
	 * total amount of charge that is drained from the battery
	 */
	private double totalDrain;
	/**
	 * makes constant equal to default power consumption
	 */
	private double newPowerConsump = DEFAULT_CAMERA_POWER_CONSUMPTION;
	
	
	/**
	 * initially creates the camera battery object
	 * @param batteryStartingCharge sets batteries starting charges
	 * @param batteryCapacity sets the total capacity of the battery
	 */
	public CameraBattery(double batteryStartingCharge, double batteryCapacity) {
		batteryCharge = Math.min(batteryStartingCharge, batteryCapacity);
		this.batteryCapacity = batteryCapacity;
		
	}
	/**
	 * function changes the setting of how fast the charger charges
	 */
	public void buttonPress() {
		chargerSetting += 1;
		chargerSetting %= NUM_CHARGER_SETTINGS;
	}
	
	/**
	 * charges the camera and from the camera battery object
	 * @param minutes passes through "theoretically" an amount of time the battery is on the charger
	 * @return s the amount of charge the camera has
	 */
	public double cameraCharge(double minutes) {
		 double chargeOutput = Math.min(minutes * CHARGE_RATE, batteryCapacity - batteryCharge)* isBeingUsed;
		 batteryCharge += chargeOutput * isBeingUsed;
		 cameraCharge = batteryCharge * isBeingUsed;
		 return chargeOutput;
	}
	
	/**
	 * drains the battery depending on how long the camera is used 
	 * @param minutes passes through how long the camera gets used as a value
	 * @return s the amount that was drained
	 */
	public double drain(double minutes) {
		double amountDrained = Math.min(minutes * newPowerConsump, batteryCharge);
		batteryCharge -= amountDrained * isBeingUsed;
		cameraCharge = batteryCharge * isBeingUsed;
		totalDrain += amountDrained * isBeingUsed;
		return amountDrained * isBeingUsed;
	}
	
	/**
	 * charges the battery object
	 * @param minutes passes through how long the battery charges
	 * @return s how much the charger charged the battery
	 */
	public double externalCharge(double minutes) {
		 double chargeOutput1 = Math.min(minutes * CHARGE_RATE * chargerSetting, batteryCapacity - batteryCharge);
		 batteryCharge += Math.min(minutes * CHARGE_RATE * chargerSetting, batteryCapacity - batteryCharge);
		 return chargeOutput1;
	}
	
	/**
	 * resets the batteries charge
	 */
	public void resetBatteryMonitor() {
		totalDrain = 0.0;
	}
	
	/**
	 * getter for battery capacity
	 * @return s the amount of capacity the battery has
	 */
	public double getBatteryCapacity() {
		return batteryCapacity;
	}
	
	/**
	 * getter for how charged the battery is
	 * @return s how charged the battery is
	 */
	public double getBatteryCharge() {
		return batteryCharge;
	}
	
	/**
	 * getter for how much charge the camera gets
	 * @return s how value on how charged it is
	 */
	public double getCameraCharge() {
		return cameraCharge;
	}
	
	/**
	 * getter for how much power the camera consumed
	 * @return s the amount consumed
	 */
	public double getCameraPowerConsumption() {
		return newPowerConsump;
	}
	
	/**
	 * getter for what setting the charger is at
	 * @return setting of charger
	 */
	public int getChargerSetting() {
		return chargerSetting;
	}
	
	/**
	 * getter for total amount drained
	 * @return s total amount drained
	 */
	public double getTotalDrain() {
		return totalDrain;
	}
	
	/**
	 * tells me the battery is not in the camera
	 */
	public void moveBatteryExternal() {
		isBeingUsed = 0.0;
	}
	
	/**
	 * tells me the battery is in the camera
	 */
	public void moveBatteryCamera() {
		isBeingUsed = 1.0;
	}
	
	/**
	 * tells me the battery is not in the camera
	 */
	public void removeBattery() {
		isBeingUsed = 0.0;
		cameraCharge = 0.0;
	}
	
	/**
	 * switches the amount the camera consumes
	 * @param cameraPowerConsumption puts into this class
	 */
	public void setCameraPowerConsumption(double cameraPowerConsumption) {
		newPowerConsump = cameraPowerConsumption;
	}
	
	
	
	
}

