
package com.verisign.iot.discovery.utils;

import com.verisign.iot.discovery.domain.Fqdn;
import org.junit.Assert;
import org.junit.After;

import org.junit.Before;
import org.junit.Test;

/**
 * @author pmaresca
 */
public class ValidatorUtilTest {

	public ValidatorUtilTest () {
	}


	@Before
	public void setUp () {
	}


	@After
	public void tearDown () {
	}


	@Test
	public void validFqdn () {
		Fqdn validName = new Fqdn( "", "iot.end-points.verisigninc.com" );
		validName = new Fqdn( "", "Service\\032Test.iot.end-points.verisigninc.com" );
		validName = new Fqdn( "", "Service\\ Test.iot.end-points.verisigninc.com" );
		validName = new Fqdn( "", "service.blahhh" );
		validName = new Fqdn( "", "123\\ service.iot.end-points.verisigninc.com" );
		validName = new Fqdn( "", "123\\ service.iot.end-points.verisigninc.com" );
	}


	@Test
	public void invalidFqdn () {
		Fqdn invalidName = null;
		try {
			invalidName = new Fqdn( "", "yup'ok.iot.end-points.verisigninc.com" );
			Assert.fail( "Expected a FQDN validation failure " + invalidName );
			invalidName = new Fqdn( "", "yup\\ok.i$ot.end-points.verisigninc.com" );
			Assert.fail( "Expected a FQDN validation failure " + invalidName );
			invalidName = new Fqdn( "", "yup-ok.i&ot.end-points.verisigninc.com" );
			Assert.fail( "Expected a FQDN validation failure " + invalidName );
		}
		catch ( Exception e ) {
			Assert.assertTrue( true );
		}
	}


	@Test
	public void dnsSdFqdn () {
		Fqdn invalidName = null;
		try {
			invalidName = new Fqdn( "", "Service\\032Test.iot.end-points.verisigninc.com" );
			String query = invalidName.fqdnWithPrefix( "_bip" );
			Assert.assertTrue( query.contains( "\"" ) );
		}
		catch ( Exception e ) {
			Assert.fail( "Expected a FQDN validatio failure " + invalidName );
		}
	}

	@Test
	public void validPort(){
		org.junit.Assert.assertTrue( ValidatorUtil.isValidPort( "0" ) );
		org.junit.Assert.assertTrue( ValidatorUtil.isValidPort( "1" ) );
		org.junit.Assert.assertTrue( ValidatorUtil.isValidPort( "23" ) );
		org.junit.Assert.assertTrue( ValidatorUtil.isValidPort( "456" ) );
		org.junit.Assert.assertTrue( ValidatorUtil.isValidPort( "568" ) );
		org.junit.Assert.assertTrue( ValidatorUtil.isValidPort( "1568" ) );
		org.junit.Assert.assertTrue( ValidatorUtil.isValidPort( "35688" ) );
		org.junit.Assert.assertTrue( ValidatorUtil.isValidPort( "65534" ) );
		org.junit.Assert.assertTrue( ValidatorUtil.isValidPort( "65535" ) );
	}

	@Test
	public void invalidPort(){
		org.junit.Assert.assertTrue( !ValidatorUtil.isValidPort( "-1" ) );
		org.junit.Assert.assertTrue( !ValidatorUtil.isValidPort( "-13" ) );
		org.junit.Assert.assertTrue( !ValidatorUtil.isValidPort( "-178" ) );
		org.junit.Assert.assertTrue( !ValidatorUtil.isValidPort( "-1789" ) );
		org.junit.Assert.assertTrue( !ValidatorUtil.isValidPort( "1789000000" ) );
		org.junit.Assert.assertTrue( !ValidatorUtil.isValidPort( "65536" ) );
		org.junit.Assert.assertTrue( !ValidatorUtil.isValidPort( "65537" ) );
		org.junit.Assert.assertTrue( !ValidatorUtil.isValidPort( "23394012341029313123412340234908423984102934" ) );
		org.junit.Assert.assertTrue( !ValidatorUtil.isValidPort( "askdjfaosidufaasdfajksldjofia" ) );
		org.junit.Assert.assertTrue( !ValidatorUtil.isValidPort( "" ) );
		org.junit.Assert.assertTrue( !ValidatorUtil.isValidPort( null ) );
		org.junit.Assert.assertTrue( !ValidatorUtil.isValidPort( "            " ) );
	}
}
