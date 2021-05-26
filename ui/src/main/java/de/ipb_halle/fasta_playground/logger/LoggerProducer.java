package de.ipb_halle.fasta_playground.logger;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/*
 * Code from https://stackoverflow.com/a/21092326
 */
public class LoggerProducer {
	@Produces
	public Logger getLogger(InjectionPoint p) {
		return Logger.getLogger(p.getClass().getCanonicalName());
	}
}