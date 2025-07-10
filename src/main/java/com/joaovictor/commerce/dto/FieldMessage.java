package com.joaovictor.commerce.dto;

//Classe que vai ser objeto salvo na classe ValidationError
public class FieldMessage {
	private String fieldName;//Campo que causou erro
	private String message;// Mensagem do erro na classe DTO
	public FieldMessage(String fieldName, String message) {
		this.fieldName = fieldName;
		this.message = message;
	}
	public String getFieldName() {
		return fieldName;
	}
	public String getMessage() {
		return message;
	}
	
	
}
