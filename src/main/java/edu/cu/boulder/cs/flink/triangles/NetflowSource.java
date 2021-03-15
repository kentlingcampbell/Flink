package edu.cu.boulder.cs.flink.triangles;

import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;
import org.apache.flink.streaming.api.watermark.Watermark;

import java.time.Instant;
import java.util.Random;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NetflowSource extends RichParallelSourceFunction <Netflow> {
//  BufferedReader csvReader;

  public NetflowSource(String filename)
  {
    try {
      this.csvReader = new BufferedReader(new FileReader(filename));
    } catch (java.io.IOException e) {

      System.out.println("Oh no");
    }
    //this.csvReader = new BufferedReader(new FileReader("/data/converted/test.csv"));
  }

  @Override
  public void run(SourceContext <Netflow> out) throws Exception
  {
    BufferedReader csvReader;
    RuntimeContext context = getRuntimeContext();
    int taskId = context.getIndexOfThisSubtask();
    //this.csvReader = new BufferedReader(new FileReader("/data/converted/test.csv"));
    String line = "";
    while ((line = csvReader.readLine()) != null) {
      String[] data = line.split(",");
      Long timeSeconds = Long.parseLong(data[0]);
      Long t = timeSeconds;
      String sourceIp = data[10];
      String destIp = data[11];
      //System.out.println(data[0] + ' ' + data[10] + ' ' + data[11]);
      Netflow netflow = new Netflow(timeSeconds, sourceIp, destIp);
      out.collectWithTimestamp(netflow, t);
      out.emitWatermark(new Watermark(t));
    }
  }

  @Override
  public void cancel()
  {
//    this.currentEvent = numEvents;
  }
}
