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

package co.cask.cdap.api.schedule.constraints;

import co.cask.cdap.proto.Notification;
import co.cask.cdap.proto.ProgramRunStatus;
import co.cask.cdap.proto.RunRecord;

import java.util.List;
import javax.annotation.Nullable;

/**
 *
 */
public final class ConstraintCheckContext {
  private final List<RunRecord> history;
  private final Notification notification;

  public ConstraintCheckContext(List<RunRecord> history, Notification notification) {
    this.history = history;
    this.notification = notification;
  }

  public List<RunRecord> getProgramRuns(@Nullable ProgramRunStatus status) {
    return history;
  }

  public List<RunRecord> getProgramRuns(@Nullable ProgramRunStatus status, long startTime, long endTime, int limit) {
    return history;
  }

  public Notification getNotification() {
    return notification;
  }
}
