package it.prova.gestionesatelliti.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import it.prova.gestionesatelliti.model.Satellite;

public class UtilityForm {

	public static boolean validateBean(Satellite satelliteToBeValidated) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(satelliteToBeValidated.getDenominazione())
				|| StringUtils.isBlank(satelliteToBeValidated.getCodice())
				|| satelliteToBeValidated.getStato() == null
				|| satelliteToBeValidated.getDataLancio() == null){
			return false;
		}
		return true;
	}
	

	public static Date parseDateArrivoFromString(String dataLancioStringParam) {
		if (StringUtils.isBlank(dataLancioStringParam))
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dataLancioStringParam);
		} catch (ParseException e) {
			return null;
		}
	}
}
