package edu.cu.boulder.cs.flink.triangles;

/**
 * This class represents a netflow.  The fields came from the VAST Challenge
 * 2013: Mini-Challenge 3 dataset and the format they used.
 * http://vacommunity.org/VAST+Challenge+2013%3A+Mini-Challenge+3
 * There are different formats for netflows, version 5 and version 9
 * are the most popular.  To represent those formats, likely another
 * class is needed.
 *
 * The samGeneratedId and the label fields came from the Streaming
 * Analytics Machine (github/elgood/SAM).  These don't come from
 * the netflow representation but are used by SAM internally.  I added
 * them here to compare directly with the SAM implementation.
 // Updated to ingest SAL format
 */
public class Netflow {
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
  public int first;          //System uptime at start of flow.
//  public int last;           //System uptime at the time the last packet of the flow was received.
//  public int engine_type;    //Type of flow-switching engine.
//  public int engine_id;      //Slot number of the flow-switching engine.
  public String srcaddr;     //Source IP address.
  public String destaddr;    //Destination IP address.
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

  public Netflow(//int samGeneratedId,
//                 int label,
//                 double timeSeconds,
//                 String parseDate,
//                 String dateTimeString,
//                 String protocol,
//                 String protocolCode,
//                 String sourceIp,
//                 String destIp,
//                 int sourcePort,
//                 int destPort,
//                 String moreFragments,
//                 int countFragments,
//                 double durationSeconds,
//                 long srcPayloadBytes,
//                 long destPayloadBytes,
//                 long srcTotalBytes,
//                 long destTotalBytes,
//                 long firstSeenSrcPacketCount,
//                 long firstSeenDestPacketCount,
//                 int recordForceOut,
// Adds below
//                 int unix_secs,
//                 int unix_nsecs,
//                 int sysuptime,
//                 String exaddr,
//                 int dpkts,
//                 int doctets,
                 int first,
//                 int last,
//                 int engine_type,
//                 int engine_id,
                 String srcaddr,
                 String destaddr
//                 String nexthop,
//                 int input,
//                 int output,
//                 int srcport,
//                 int dstport,
//                 int prot,
//                 int tos,
//                 int tcp_flags,
//                 int src_mask,
//                 int dst_mask,
//                 int src_as,
//                 int dst_as
                )
  {
//    this.label = label;;
//    this.timeSeconds = timeSeconds;
//    this.parseDate = parseDate;
//    this.dateTimeString = dateTimeString;
//    this.protocol = protocol;
//    this.protocolCode = protocolCode;
//    this.sourceIp = sourceIp;
//    this.destIp = destIp;
//    this.sourcePort = sourcePort;
//    this.destPort = destPort;
//    this.moreFragments = moreFragments;
//    this.countFragments = countFragments;
//    this.durationSeconds = durationSeconds;
//    this.srcPayloadBytes = srcPayloadBytes;
//    this.destPayloadBytes = destPayloadBytes;
//    this.srcTotalBytes = srcTotalBytes;
//    this.destTotalBytes = destTotalBytes;
//    this.firstSeenSrcPacketCount = firstSeenSrcPacketCount;
//    this.firstSeenDestPacketCount = firstSeenDestPacketCount;
//    this.recordForceOut = recordForceOut;
// Adds below
//    this.unix_secs = unix_secs;
//    this.unix_nsecs = unix_nsecs;
//    this.sysuptime = sysuptime;
//    this.exaddr = exaddr;
//    this.dpkts = dpkts;
//    this.doctets = doctets;
    this.first = first;
//    this.last = last;
//    this.engine_type = engine_type;
//    this.engine_id = engine_id;
    this.srcaddr = srcaddr;
    this.destaddr = destaddr;
//    this.nexthop = nexthop;
//    this.input = input;
//    this.output = output;
//    this.srcport = srcport;
//    this.dstport = dstport;
//    this.prot = prot;
//    this.tos = tos;
//    this.tcp_flags = tcp_flags;
//    this.src_mask = src_mask;
//    this.dst_mask = dst_mask;
//    this.src_as = src_as;
//    this.dst_as = dst_as;
  }

  /**
   * Converts the netflow to a string.  This is mostly for debugging.
   * I only print the time, source ip and dest ip because those are the
   * fields that matter for the temporal triangle query.
   */
  public String toString()
  {
//    return timeSeconds + ", " + sourceIp + ", " + destIp;
// Adds below
    return first + ", " + srcaddr + ", " + destaddr;
  }
}
