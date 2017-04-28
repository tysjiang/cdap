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

import co.cask.cdap.internal.app.runtime.schedule.constraint.Constraint;
import co.cask.cdap.internal.app.runtime.schedule.trigger.Trigger;
import co.cask.cdap.proto.id.ProgramId;

import java.util.List;
import java.util.Map;

/**
 * A schedule for a program.
 */
public class ProgramSchedule {
  private final String name;
  private final String description;

  private final ProgramId programId;
  private final Map<String, String> properties;

  private final Trigger trigger;
  private final List<Constraint> constraints;

  public ProgramSchedule(String name, String description,
                         ProgramId programId, Map<String, String> properties,
                         Trigger trigger, List<Constraint> constraints) {
    this.name = name;
    this.description = description;
    this.programId = programId;
    this.properties = properties;
    this.trigger = trigger;
    this.constraints = constraints;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public ProgramId getProgramId() {
    return programId;
  }

  public Map<String, String> getProperties() {
    return properties;
  }

  public Trigger getTrigger() {
    return trigger;
  }

  public List<Constraint> getConstraints() {
    return constraints;
  }
}
