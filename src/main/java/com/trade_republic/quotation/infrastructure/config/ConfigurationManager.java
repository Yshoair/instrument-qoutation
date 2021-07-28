package com.trade_republic.quotation.infrastructure.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Manages system configurations
 */
@Configuration
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
@Getter
public class ConfigurationManager {

  @Value("${trade-republic.partner.websocket.stream.instrument}")
  private String instrumentStream;

  @Value("${trade-republic.partner.websocket.stream.quote}")
  private String quoteStream;

  @Value("${trade-republic.date-time.format}")
  private String dateTimeFormat;

  @Value("${trade-republic.instrument.candlestick.interval}")
  private int candlestickInterval;

}
