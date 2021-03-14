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
  BufferedReader csvReader;

  public NetflowSource(String filename)
  {
    //this.csvReader = new BufferedReader(new FileReader(fileToParse));
    this.csvReader = new BufferedReader(new FileReader("testfile.csv"));
  }

  @Override
  public void run(SourceContext <Netflow> out) throws Exception
  {
    RuntimeContext context = getRuntimeContext();
    int taskId = context.getIndexOfThisSubtask();
    this.csvReader = new BufferedReader(new FileReader("testfile.csv"));
    while ((line = csvReader.readLine()) != null){
      String[] data = line.split(DELIMITER);
      Double timeSeconds = Double.parseDouble(data[0]);
      Long t = (Long) timeSeconds ;
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
    this.currentEvent = numEvents;
  }
}
