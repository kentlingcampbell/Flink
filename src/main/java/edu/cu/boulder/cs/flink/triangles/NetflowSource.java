package edu.cu.boulder.cs.flink.triangles;

import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;
import org.apache.flink.streaming.api.watermark.Watermark;

import java.time.Instant;
import java.util.Random;

/**
 * This creates netflows from a pool of IPs.
 */
public class NetflowSource extends RichParallelSourceFunction<Netflow> {

  /// The number of netflows to create.
  private int numEvents;

  /// How many netflows created.
  private int currentEvent;

  /// How many unique IPs in the pool
  private int numIps;

  /// Used to create the netflows with randomly selected ips
  private Random random;

  /// Time from start of run
  private double time = 0;

  /// The time in seconds between netflows
  private double increment = 0.1;

  /// When the first netflow was created
  private double t1;

//  public int samGeneratedId;
//  public int label;
//  public double timeSeconds;
//  public String parseDate;
//  public String dateTimeString;
//  public String protocol;
//  public String protocolCode;
//  public String sourceIp;
//  public String destIp;
//  public int sourcePort;
//  public int destPort;
//  public String moreFragments;
//  public int countFragments;
//  public double durationSeconds;
//  public long srcPayloadBytes;
//  public long destPayloadBytes;
//  public long srcTotalBytes;
//  public long destTotalBytes;
//  public long firstSeenSrcPacketCount;
//  public long firstSeenDestPacketCount;
//  public int recordForceOut;
// Adds below
//  public int unix_secs;      //Current count of seconds since epoch.
//  public int unix_nsecs;     //Residual nanonseconds since epoch.
//  public int sysuptime;      //Current time in ms since export device booted.
//  public String exaddr;      //The address of the export machine.
//  public int dpkts;          //Packets in the flow.
//  public int doctets;        //Total number of Layer 3 bytes in the packets of the flow.
//  public int first;          //System uptime at start of flow.
  public double timeSeconds;
//  public int last;           //System uptime at the time the last packet of the flow was received.
//  public int engine_type;    //Type of flow-switching engine.
//  public int engine_id;      //Slot number of the flow-switching engine.
//  public String srcaddr;     //Source IP address.
//  public String destaddr;    //Destination IP address.
  public String sourceIp;
  public String destIp;
//  public String nexthop;     //IP address of next hop router.
//  public int input;          //SNMP index of input interface.
//  public int output;         //SNMP index of output interface.
//  public int srcport;        //TCP/UDP source port number or equivalent.
//  public int dstport;        //TCP/UDP destination port number or equivalent.
//  public int prot;           //IP protocol type (e.g. TCP=6; UDP=17)
//  public int tos;            //IP type of service (ToS)
//  public int tcp_flags;      //Cumulative OR of TCP flags.
//  public int src_mask;       //Source address prefix mask bits.
//  public int dst_mask;       //Destination address prefix mask bits.
//  public int src_as;         //Autonomous system number of the source, either origin or peer.
//  public int dst_as;         //Autonomous system number of the destination, either origin or peer.

  /**
   *
   * @param numEvents The number of netflows to produce.
   * @param numIps The number of ips in the pool.
   * @param rate The rate of netflows produced in netflows/s.
   */
//  public NetflowSource(int numEvents, int numIps, double rate)
// Adds below
  public NetflowSource(int timeSeconds, String sourceIp, String destIp)
  {
//    this.numEvents = numEvents;
//    currentEvent = 0;
//    this.numIps = numIps;
//    this.increment = 1 / rate;
    //this.random = new Random();
// Adds below
    this.timeSeconds = timeSeconds;
    currentEvent = 0;
    this.sourceIp = sourceIp;
    this.destIp = destIp;
//    this.increment = 1 / rate;

//    label = 0;
//    parseDate = "parseDate";
//    dateTimeString = "dateTimeString";
//    protocol = "protocol";
//    protocolCode = "protocolCode";
//    sourcePort = 80;
//    destPort = 80;
//    moreFragments = "moreFragments";
//    countFragments = 0;
//    durationSeconds = 0.1;
//    srcPayloadBytes = 10;
//    destPayloadBytes = 10;
//    srcTotalBytes = 15;
//    destTotalBytes = 15;
//    firstSeenSrcPacketCount = 5;
//    firstSeenDestPacketCount =5;
//    recordForceOut = 0;
  }

  @Override
  public void run(SourceContext<Netflow> out) throws Exception
  {
    RuntimeContext context = getRuntimeContext();
    int taskId = context.getIndexOfThisSubtask();

    if (currentEvent == 0) {
      t1 = ((double) Instant.now().toEpochMilli()) / 1000;
      this.random = new Random(taskId);
    }

    while(currentEvent < numEvents)
    {
      long t = Instant.now().toEpochMilli();
      double currentTime = ((double)t) / 1000;
      if (currentEvent % 1000 == 0) {
        double expectedTime = currentEvent * increment;
        double actualTime = currentTime - t1;
        System.out.println("Expected time: " + expectedTime +
                            "Actual time: " + actualTime);
      }

      double diff = currentTime - t1;
      if (diff < currentEvent * increment)
      {
        long numMilliseconds = (long)(currentEvent * increment - diff) * 1000;
        Thread.sleep(numMilliseconds);
      }

      int source = random.nextInt(numIps);
      int dest   = random.nextInt(numIps);
      String sourceIp = "node" + source;
      String destIp = "node" + dest;
      //String sourceIp = "node0";
      //String destIp = "node1";
//      Netflow netflow = new Netflow(currentEvent, label, time,
//                                     parseDate, dateTimeString, protocol, protocolCode,
//                                     sourceIp, destIp, sourcePort, destPort, moreFragments, countFragments,
//                                     durationSeconds, srcPayloadBytes, destPayloadBytes, srcTotalBytes,
//                                     destTotalBytes, firstSeenSrcPacketCount, firstSeenDestPacketCount,
//                                      recordForceOut);
// Adds below
      Netflow netflow = new Netflow(timeSeconds, sourceIp, destIp);

      out.collectWithTimestamp(netflow, t);
      out.emitWatermark(new Watermark(t));
//      currentEvent++;
//      time += increment;
    }
  }

  @Override
  public void cancel()
  {
    this.currentEvent = numEvents;
  }
}
