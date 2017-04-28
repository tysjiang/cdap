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

import co.cask.cdap.api.schedule.Configurer;
import co.cask.cdap.internal.app.runtime.schedule.constraint.ConcurrencyConstraint;
import co.cask.cdap.internal.app.runtime.schedule.constraint.Constraint;
import co.cask.cdap.internal.app.runtime.schedule.constraint.DelayConstraint;
import co.cask.cdap.internal.app.runtime.schedule.constraint.DurationSinceLastRunConstraint;
import co.cask.cdap.internal.app.runtime.schedule.constraint.TimeRangeConstraint;
import co.cask.cdap.internal.app.runtime.schedule.trigger.PartitionTrigger;
import co.cask.cdap.internal.app.runtime.schedule.trigger.TimeTrigger;
import co.cask.cdap.proto.id.ProgramId;
import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * The default implementation of {@link Configurer}.
 */
public class DefaultConfigurer implements Configurer {

  private final String name;
  private final ProgramId programId;
  private String description;
  private Map<String, String> properties;
  private List<Constraint> constraints;

  public DefaultConfigurer(String name, ProgramId programId) {
    this.name = name;
    this.description = "";
    this.programId = programId;
    this.constraints = new ArrayList<>();
  }

  @Override
  public Configurer setDescription(String description) {
    this.description = description;
    return this;
  }

  @Override
  public Configurer setProperties(Map<String, String> properties) {
    this.properties = properties;
    return this;
  }

  @Override
  public Configurer limitConcurrentRuns(int max) {
    if (max < 1) {
      throw new IllegalArgumentException("max concurrent runs must be at least 1.");
    }
    constraints.add(new ConcurrencyConstraint(max));
    return this;
  }

  @Override
  public Configurer delayRun(long delayMillis) {
    // TODO: disallow from being called multiple times?
    constraints.add(new DelayConstraint(delayMillis));
    return this;
  }

  @Override
  public Configurer setTimeRange(int startHour, int endHour) {
    constraints.add(new TimeRangeConstraint(startHour, endHour));
    return this;
  }

  @Override
  public Configurer setDurationSinceLastRun(long delayMillis) {
    constraints.add(new DurationSinceLastRunConstraint(delayMillis));
    return this;
  }

  @Override
  public void triggerByTime(String cronExpression) {
    setSchedule(new ProgramSchedule(name, description, programId, properties,
                                    new TimeTrigger(cronExpression), constraints));
  }

  @Override
  public void triggerOnPartitions(String datasetName, int numPartitions) {
    setSchedule(new ProgramSchedule(name, description, programId, properties,
                                    new PartitionTrigger(programId.getNamespaceId().dataset(datasetName),
                                                         numPartitions),
                                    constraints));
  }

  private void setSchedule(ProgramSchedule schedule) {
  }
}
