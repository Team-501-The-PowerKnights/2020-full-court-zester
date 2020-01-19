/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.telemetry;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class TelemetryManager {
   /** Singleton instance of class for all to use **/
   private static TelemetryManager ourInstance;
   /** Name of our subsystem **/
   private final static String myName = TelemetryNames.Telemetry.name;

   private final ArrayList<ITelemetryProvider> providerList;

   public static synchronized void constructInstance() {
      SmartDashboard.putBoolean(TelemetryNames.Telemetry.status, false);

      if (ourInstance != null) {
         throw new IllegalStateException(myName + " already constructed");
      }

      ourInstance = new TelemetryManager();

      SmartDashboard.putBoolean(TelemetryNames.Telemetry.status, true);
   }

   public static TelemetryManager getInstance() {
      if (ourInstance == null) {
         throw new IllegalStateException(myName + " not constructed yet");
      }
      return ourInstance;
   }

   private TelemetryManager() {
      providerList = new ArrayList<ITelemetryProvider>();
   }

   public void addProvider(ITelemetryProvider provider) {
      if (provider != null) {
         providerList.add(provider);
      }
   }

   private int counter = 0;

   // This should get called from robotPeriodic() in robot
   public void sendTelemetry() {
      if (counter >= 10) {
         counter = 0;

         for (ITelemetryProvider provider : providerList) {
            provider.updateTelemetry();
         }
      } else {
         counter++;
      }
   }

}
