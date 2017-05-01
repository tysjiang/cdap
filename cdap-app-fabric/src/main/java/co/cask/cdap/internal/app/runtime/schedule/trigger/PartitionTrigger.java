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

package co.cask.cdap.internal.app.runtime.schedule.trigger;


import co.cask.cdap.proto.id.DatasetId;
import com.google.common.base.Objects;

/**
 * A Trigger that schedules a ProgramSchedule, when a certain number of partitions are added to a PartitionedFileSet.
 */
public class PartitionTrigger extends Trigger {
  private final DatasetId datasetId;
  private final int numPartitions;

  public PartitionTrigger(DatasetId datasetId, int numPartitions) {
    this.datasetId = datasetId;
    this.numPartitions = numPartitions;
  }

  public DatasetId getDatasetId() {
    return datasetId;
  }

  public int getNumPartitions() {
    return numPartitions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PartitionTrigger that = (PartitionTrigger) o;
    return numPartitions == that.numPartitions &&
      Objects.equal(datasetId, that.datasetId);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(datasetId, numPartitions);
  }

  @Override
  public String toString() {
    return "PartitionTrigger{" +
      "datasetId=" + datasetId +
      ", numPartitions=" + numPartitions +
      '}';
  }
}
