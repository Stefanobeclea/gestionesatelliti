package it.prova.gestionesatelliti.service;

import java.util.List;

import it.prova.gestionesatelliti.model.Satellite;


public interface SatelliteService {
	public List<Satellite> listAllElements();

	public Satellite caricaSingoloElemento(Long id);
	
	public void aggiorna(Satellite impiegatoInstance);

	public void inserisciNuovo(Satellite impiegatoInstance);

	public void rimuovi(Satellite impiegatoInstance);
	
	public List<Satellite> findByExample(Satellite example);
}
