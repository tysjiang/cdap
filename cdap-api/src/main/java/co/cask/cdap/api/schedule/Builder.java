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

import java.util.Map;

/**
 * Builder for scheduling.
 */
public interface Builder {

  Builder setDescription(String description);

  Builder setProperties(Map<String, String> properties);

  Builder limitConcurrentRuns(int max);

  Builder delayRun(long delayMillis);

  Builder setTimeRange(int startHour, int endHour);

  Builder setDurationSinceLastRun(long delayMillis);

  void createTimeSchedule(String cronExpression);

  void createPFSTrigger(String datasetName, int numPartitions);
}
