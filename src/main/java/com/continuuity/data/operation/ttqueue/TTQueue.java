package com.continuuity.data.operation.ttqueue;

import org.apache.hadoop.hbase.util.Bytes;

import com.continuuity.data.operation.ttqueue.QueueAdmin.QueueMeta;
import com.continuuity.data.table.ReadPointer;

/**
 * A Transactional Tabular Queue interface.
 *
 * See <pre>https://wiki.continuuity.com/display/PROD/Transactional+Tabular+Queues</pre>
 * for more information about TTQueue semantics.
 */
public interface TTQueue {

  public static final byte [] QUEUE_NAME_PREFIX = Bytes.toBytes("queue://");
  public static final byte [] STREAM_NAME_PREFIX = Bytes.toBytes("stream://");
  
  /**
   * Inserts an entry into the tail of the queue using the specified write
   * version.
   * @param data the data to be inserted into the queue
   * @param writeVersion
   * @return return code, and if success, the unique entryId of the queue entry
   */
  public EnqueueResult enqueue(byte [] data, long writeVersion);

  /**
   * Invalidates an entry that was enqueued into the queue.  This is used only
   * as part of a transaction rollback.
   * @param entryPointer entry id and shard id of enqueued entry to invalidate
   * @param writeVersion version entry was written with and version invalidated
   *                     entry will be written with
   */
  public void invalidate(QueueEntryPointer entryPointer, long writeVersion);

  /**
   * Attempts to mark and return an entry from the queue for the specified
   * consumer from the specified group, according to the specified configuration
   * and read pointer.
   * @param consumer
   * @param config
   * @param readPointer
   */
  public DequeueResult dequeue(QueueConsumer consumer, QueueConfig config,
      ReadPointer readPointer);

  /**
   * Acknowledges a previously dequeue'd queue entry.  Returns true if consumer
   * that is acknowledging is allowed to do so, false if not.
   * @param entryPointer
   * @param consumer
   */
  public boolean ack(QueueEntryPointer entryPointer, QueueConsumer consumer);

  /**
   * Finalizes an ack.
   * @param entryPointer
   * @param consumer
   */
  public boolean finalize(QueueEntryPointer entryPointer,
      QueueConsumer consumer);

  /**
   * Unacknowledges a previously acknowledge ack.
   * @param entryPointer
   * @param consumer
   */
  boolean unack(QueueEntryPointer entryPointer, QueueConsumer consumer);

  /**
   * Generates and returns a unique group id for this queue.
   * 
   * Note: uniqueness only guaranteed if you always use this call to generate
   * groups ids.
   * 
   * @return a unique group id for this queue
   */
  public long getGroupID();
  
  /**
   * Gets the meta information for this queue.  This includes all meta
   * data available without walking the entire queue.
   * @return global meta information for this queue and its groups
   */
  public QueueMeta getQueueMeta();
}
