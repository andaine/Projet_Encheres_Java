package fr.eni.ecole.enchere.exception;


import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Administrator
 *
 * Cette classe permet de recenser l'ensemble des erreurs (par leur code) pouvant survenir lors d'un traitement
 * quel que soit la couche à l'origine.
 */
public class BusinessException extends Exception {
	private static final long serialVersionUID = 1L;
	private List<String> listeMessage;
	
	
	public BusinessException() {
		this.listeMessage = new ArrayList<>();
	}


	public List<String> getListeMessage() {
		return listeMessage;
	}
	
	public void addMessage(String message) {
		this.listeMessage.add(message);
	}

}