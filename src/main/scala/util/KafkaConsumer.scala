package util

package de.htwg.scala.kafka

import java.util
import java.util.Properties
import org.apache.kafka.clients.consumer.KafkaConsumer

import java.time.Duration
import scala.jdk.CollectionConverters._

object KafkaConsumer extends App {

  val  props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")
  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("group.id", "something")

  val consumer = new KafkaConsumer[String, String](props)
  val TOPIC="test"

  consumer.subscribe(util.Collections.singletonList(TOPIC))

//  while(true){
//    println("Polling..")
//    val records = consumer.poll(Duration)
//    for (record <- records.asScala){
//      println(record)
//    }
//  }
}

