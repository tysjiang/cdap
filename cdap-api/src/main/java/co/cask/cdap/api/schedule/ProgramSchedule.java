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

import co.cask.cdap.api.schedule.constraints.Constraint;
import co.cask.cdap.api.schedule.trigger.Trigger;

import java.util.List;

/**
 * A schedule for a program, defined by its Trigger as well
 */
public class ProgramSchedule {
  private final String name;
  private final String description;

  private final Trigger trigger;
  private final List<Constraint> constraints;

  public ProgramSchedule(String name, String description, Trigger trigger, List<Constraint> constraints) {
    this.name = name;
    this.description = description;
    this.trigger = trigger;
    this.constraints = constraints;
  }
}
