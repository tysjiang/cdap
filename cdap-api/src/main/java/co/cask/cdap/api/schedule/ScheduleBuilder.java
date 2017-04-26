/*
 * Copyright © 2017 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package co.cask.cdap.api.schedule;

import co.cask.cdap.api.schedule.constraints.ConcurrencyConstraint;
import co.cask.cdap.api.schedule.constraints.Constraint;
import co.cask.cdap.api.schedule.constraints.DelayConstraint;
import co.cask.cdap.api.schedule.constraints.DurationSinceLastRunConstraint;
import co.cask.cdap.api.schedule.constraints.TimeRangeConstraint;
import co.cask.cdap.api.schedule.trigger.PFSTrigger;
import co.cask.cdap.api.schedule.trigger.TimeTrigger;
import co.cask.cdap.api.schedule.trigger.Trigger;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ScheduleBuilder {

  public static Builder builder(String name) {
    return new Builder(name);
  }

  public static class Builder {
    private final String name;
    private String description;
    private List<Constraint> constraints;

    private Builder(String name) {
      this.name = name;
      this.description = "";
      this.constraints = new ArrayList<>();
    }

    public Builder setDescription(String description) {
      this.description = description;
      return this;
    }

    public Builder setMaxConcurrentRuns(int max) {
      if (max < 1) {
        throw new IllegalArgumentException("max concurrent runs must be at least 1.");
      }
      constraints.add(new ConcurrencyConstraint(max));
      return this;
    }

    // fields that pertain more to data-triggered schedules
    public Builder setDelayMillis(long delayMillis) {
      // TODO: disallow from being called multiple times?
      constraints.add(new DelayConstraint(delayMillis));
      return this;
    }

    public Builder setTimeRange(int startHour, int endHour) {
      constraints.add(new TimeRangeConstraint(startHour, endHour));
      return this;
    }

    public Builder setDurationSinceLastRun(long delayMillis) {
      constraints.add(new DurationSinceLastRunConstraint(delayMillis));
      return this;
    }

    public ProgramSchedule createTimeSchedule(String cronExpression) {
      return new ProgramSchedule(name, description, new TimeTrigger(cronExpression), constraints);
    }

    public ProgramSchedule createPFSTrigger(String datasetName) {
      return new ProgramSchedule(name, description, new PFSTrigger(datasetName), constraints);
    }

    public ProgramSchedule build(Trigger trigger) {
      return new ProgramSchedule(name, description, trigger, constraints);
    }
  }
}
