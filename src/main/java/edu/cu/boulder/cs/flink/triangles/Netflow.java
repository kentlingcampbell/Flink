package edu.cu.boulder.cs.flink.triangles;

/**
Updated to ingest SAL format
 */
public class NetflowV5 {
  public int unix_secs;      //Current count of seconds since epoch.
  public int unix_nsecs;     //Residual nanonseconds since epoch.
  public int sysuptime;      //Current time in ms since export device booted.
  public String exaddr;      //The address of the export machine.
  public int dpkts;          //Packets in the flow.
  public int doctets;        //Total number of Layer 3 bytes in the packets of the flow.
  public int first;          //System uptime at start of flow.
  public int last;           //System uptime at the time the last packet of the flow was received.
  public int engine_type;    //Type of flow-switching engine.
  public int engine_id;      //Slot number of the flow-switching engine.
  public String srcaddr;     //Source IP address.
  public String destaddr;    //Destination IP address.
  public String nexthop;     //IP address of next hop router.
  public int input;          //SNMP index of input interface.
  public int output;         //SNMP index of output interface.
  public int srcport;        //TCP/UDP source port number or equivalent.
  public int dstport;        //TCP/UDP destination port number or equivalent.
  public int prot;           //IP protocol type (e.g. TCP=6; UDP=17)
  public int tos;            //IP type of service (ToS)
  public int tcp_flags;      //Cumulative OR of TCP flags.
  public int src_mask;       //Source address prefix mask bits.
  public int dst_mask;       //Destination address prefix mask bits.
  public int src_as;         //Autonomous system number of the source, either origin or peer.
  public int dst_as;         //Autonomous system number of the destination, either origin or peer.

  public NetflowV5(int label,
                 int unix_secs,
                 int unix_nsecs,
                 int sysuptime,
                 String exaddr,
                 int dpkts,
                 int doctets,
                 int first,
                 int last,
                 int engine_type,
                 int engine_id,
                 String srcaddr,
                 String destaddr,
                 String nexthop,
                 int input,
                 int output,
                 int srcport,
                 int dstport,
                 int prot,
                 int tos,
                 int tcp_flags,
                 int src_mask,
                 int dst_mask,
                 int src_as,
                 int dst_as)
  {
    this.unix_secs = unix_secs;
    this.unix_nsecs = unix_nsecs;
    this.sysuptime = sysuptime;
    this.exaddr = exaddr;
    this.dpkts = dpkts;
    this.doctets = doctets;
    this.first = first;
    this.last = last;
    this.engine_type = engine_type;
    this.engine_id = engine_id;
    this.srcaddr = srcaddr;
    this.destaddr = destaddr;
    this.nexthop = nexthop;
    this.input = input;
    this.output = output;
    this.srcport = srcport;
    this.dstport = dstport;
    this.prot = prot;
    this.tos = tos;
    this.tcp_flags = tcp_flags;
    this.src_mask = src_mask;
    this.dst_mask = dst_mask;
    this.src_as = src_as;
    this.dst_as = dst_as;
  }

  /**
   * Converts the netflow to a string.  This is mostly for debugging.
   * I only print the time, source ip and dest ip because those are the
   * fields that matter for the temporal triangle query.
   */
  public String toString()
  {
    return first + ", " + srcaddr + ", " + destaddr;
  }
}
