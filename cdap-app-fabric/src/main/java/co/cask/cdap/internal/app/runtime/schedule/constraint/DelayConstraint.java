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

package co.cask.cdap.internal.app.runtime.schedule.constraint;

import co.cask.cdap.internal.app.runtime.schedule.ProgramSchedule;

/**
 * A constraint which requires a certain amount of delay pass after the trigger is fired, before executing the program.
 */
public class DelayConstraint extends Constraint {

  private final long millisAfterTrigger;

  public DelayConstraint(long millisAfterTrigger) {
    this.millisAfterTrigger = millisAfterTrigger;
  }

  @Override
  public Result check(ProgramSchedule schedule, ConstraintContext context) {
    return Result.SATISFIED;
  }
}
