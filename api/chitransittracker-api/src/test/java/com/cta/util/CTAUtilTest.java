package com.cta.util;

import static org.junit.Assert.fail;
import static org.fest.assertions.api.Assertions.*;

import java.net.URISyntaxException;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.junit.Test;

public class CTAUtilTest {
	Logger logger = Logger.getLogger(CTAUtilTest.class);
	
	@Test
	public void testJodaFormattingCTAArrivals(){
		String timestamp = "20150306 00:24:32";
		try{
			DateTime mockDateTime = CTAUtil.parseDateTime(timestamp);
			assertThat(mockDateTime.getYear()).isEqualTo(2015);
			assertThat(mockDateTime.getMonthOfYear()).isEqualTo(3);
			assertThat(mockDateTime.getDayOfMonth()).isEqualTo(6);
			assertThat(mockDateTime.getHourOfDay()).isEqualTo(0);
			assertThat(mockDateTime.getMinuteOfHour()).isEqualTo(24);
			assertThat(mockDateTime.getSecondOfMinute()).isEqualTo(32);
		}catch(Exception e){
			fail("Failed to parse CTA arrivals time");
			logger.debug("Failed to parse CTA arrivals time", e);
		}
	}
	
	@Test
	public void testJodaFormattingCTAAlerts(){
		String timestamp = "20150121";
		try{
			DateTime mockDateTime = CTAUtil.parseDateTime(timestamp);
			assertThat(mockDateTime.getYear()).isEqualTo(2015);
			assertThat(mockDateTime.getMonthOfYear()).isEqualTo(1);
			assertThat(mockDateTime.getDayOfMonth()).isEqualTo(21);
		}catch(Exception e){
			fail("Failed to parse CTA arrivals time");
			logger.debug("Failed to parse CTA arrivals time", e);
		}
	}
	
	@Test
	public void testJodaFormattingCTAAlertsEventStart(){
		String timestamp = "20150126 05:00";
		try{
			DateTime mockDateTime = CTAUtil.parseDateTime(timestamp);
			assertThat(mockDateTime.getYear()).isEqualTo(2015);
			assertThat(mockDateTime.getMonthOfYear()).isEqualTo(1);
			assertThat(mockDateTime.getDayOfMonth()).isEqualTo(26);
			assertThat(mockDateTime.getHourOfDay()).isEqualTo(5);
			assertThat(mockDateTime.getMinuteOfHour()).isEqualTo(0);
		}catch(Exception e){
			fail("Failed to parse CTA arrivals time");
			logger.debug("Failed to parse CTA arrivals time", e);
		}
	}
	
	@Test
	public void testJodaFormattingCTAAlertsEventEnd(){
		String timestamp = "20150323";
		try{
			DateTime mockDateTime = CTAUtil.parseDateTime(timestamp);
			assertThat(mockDateTime.getYear()).isEqualTo(2015);
			assertThat(mockDateTime.getMonthOfYear()).isEqualTo(3);
			assertThat(mockDateTime.getDayOfMonth()).isEqualTo(23);
		}catch(Exception e){
			fail("Failed to parse CTA arrivals time");
			logger.debug("Failed to parse CTA arrivals time", e);
		}
	}
	
	@Test
	public void testCTABaseUrl(){
		String expectedUrl = "http://lapi.transitchicago.com/api/";
		try {
			String builtUrl = CTAUtil.getAPIBase().build().toString();
			assertThat(builtUrl).isEqualTo(expectedUrl);
		} catch (URISyntaxException e) {
			fail("API Base URI build failure");
		}
	}
	
	@Test
	public void testGetTrainLinesURI(){
		String expectedUrl = "http://lapi.transitchicago.com/api/1.0/routes.aspx?type=" + CTAUtil.RAIL;
		assertThat(CTAUtil.getCTARoutesURI("rail").toString()).isEqualTo(expectedUrl);
	}
	
	@Test
	public void testGetTrainStationsURI(){
		String expectedUrl = "http://lapi.transitchicago.com/api/1.0/routes.aspx?type=" + CTAUtil.STATION;
		assertThat(CTAUtil.getCTARoutesURI(CTAUtil.STATION).toString()).isEqualTo(expectedUrl);
	}
	
	@Test
	public void testGetBusesURI(){
		String expectedUrl = "http://lapi.transitchicago.com/api/1.0/routes.aspx?type=" + CTAUtil.BUS;
		assertThat(CTAUtil.getCTARoutesURI(CTAUtil.BUS).toString()).isEqualTo(expectedUrl);
	}
	
	@Test
	public void testGetBusAPIBaseURI(){
		String expectedUrl = "http://www.ctabustracker.com/bustime/api/";
		try {
			assertThat(CTABusUtil.getAPIBase().build().toString()).isEqualTo(expectedUrl);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
