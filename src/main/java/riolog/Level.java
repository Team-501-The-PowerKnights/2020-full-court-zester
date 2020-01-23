/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package riolog;


/**
 * 
 **/
public enum Level
{

   OFF( ch.qos.logback.classic.Level.OFF ),
   ERROR( ch.qos.logback.classic.Level.ERROR ),
   WARN( ch.qos.logback.classic.Level.WARN ),
   INFO( ch.qos.logback.classic.Level.INFO ),
   DEBUG( ch.qos.logback.classic.Level.DEBUG ),
   TRACE( ch.qos.logback.classic.Level.TRACE );

   final ch.qos.logback.classic.Level level;


   private Level( ch.qos.logback.classic.Level level )
   {
      this.level = level;
   }

}
