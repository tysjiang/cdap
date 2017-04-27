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

package co.cask.cdap.internal.app.runtime.schedule;

import co.cask.cdap.api.schedule.Builder;
import co.cask.cdap.internal.app.runtime.schedule.constraint.Constraint;
import co.cask.cdap.api.schedule.trigger.PFSTrigger;
import co.cask.cdap.api.schedule.trigger.TimeTrigger;
import co.cask.cdap.internal.app.runtime.schedule.constraint.ConcurrencyConstraint;
import co.cask.cdap.internal.app.runtime.schedule.constraint.DelayConstraint;
import co.cask.cdap.internal.app.runtime.schedule.constraint.DurationSinceLastRunConstraint;
import co.cask.cdap.internal.app.runtime.schedule.constraint.TimeRangeConstraint;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class DefaultBuilder implements Builder {

  private final String name;
  private String description;
  private List<Constraint> constraints;

  private DefaultBuilder(String name) {
    this.name = name;
    this.description = "";
    this.constraints = new ArrayList<>();
  }

  @Override
  public Builder setDescription(String description) {
    this.description = description;
    return this;
  }

  @Override
  public Builder limitConcurrentRuns(int max) {
    if (max < 1) {
      throw new IllegalArgumentException("max concurrent runs must be at least 1.");
    }
    constraints.add(new ConcurrencyConstraint(max));
    return this;
  }

  @Override
  public Builder delayRun(long delayMillis) {
    // TODO: disallow from being called multiple times?
    constraints.add(new DelayConstraint(delayMillis));
    return this;
  }

  @Override
  public Builder setTimeRange(int startHour, int endHour) {
    constraints.add(new TimeRangeConstraint(startHour, endHour));
    return this;
  }

  @Override
  public Builder setDurationSinceLastRun(long delayMillis) {
    constraints.add(new DurationSinceLastRunConstraint(delayMillis));
    return this;
  }

  @Override
  public void createTimeSchedule(String cronExpression) {
    // TODO: associate the schedule with the program
    new ProgramSchedule(name, description, new TimeTrigger(cronExpression), constraints);
  }

  @Override
  public void createPFSTrigger(String datasetName) {
    // TODO: associate the schedule with the program
    new ProgramSchedule(name, description, new PFSTrigger(datasetName), constraints);
  }
}
