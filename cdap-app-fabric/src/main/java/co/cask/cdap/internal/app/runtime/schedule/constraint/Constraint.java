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

import javax.annotation.Nullable;

/**
 * A constraint that is checked before executing a schedule.
 */
public abstract class Constraint {

  public abstract Result check(ProgramSchedule schedule, ConstraintContext context);

  static class Result {
    public static final Result SATISFIED = new Result(true, null);

    private final boolean satisfied;
    private final Long millisBeforeNextRetry;

    Result(boolean satisfied) {
      this(satisfied, null);
    }

    Result(boolean satisfied, @Nullable Long millisBeforeNextRetry) {
      this.satisfied = satisfied;
      this.millisBeforeNextRetry = millisBeforeNextRetry;
    }
  }
}
