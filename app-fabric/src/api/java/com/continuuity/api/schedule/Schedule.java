package com.continuuity.api.schedule;

/**
 * Defines the schedule to run a program. Cron-based scheduling is supported for the schedule.
 */
public interface Schedule {

  /**
   * @return Name of the schedule.
   */
  String getName();

  /**
   * @return Schedule description.
   */
  String getDescription();

  /**
   * @return cronExpression for the schedule.
   */
  String getCronEntry();

  /**
   * @return Action for the schedule.
   */
  Action getAction();

  /**
   * Defines the ScheduleAction.
   */
  enum Action {START, STOP};
}
