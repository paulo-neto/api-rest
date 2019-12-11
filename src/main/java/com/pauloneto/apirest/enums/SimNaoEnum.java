/**
 * 
 */
package com.pauloneto.apirest.enums;
/**
* @author paulo.antonio@fornecedores.sicoob.com.br 
* @version 0.1 - 29 de out de 2019
*/
public enum SimNaoEnum {

	S("Sim"),N("Não");
	
	private String descricao;
	
	private SimNaoEnum(String desc) {
		this.descricao = desc;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
