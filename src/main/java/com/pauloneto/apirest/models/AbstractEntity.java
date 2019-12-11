/**
 * 
 */
package com.pauloneto.apirest.models;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
* @author paulo.antonio@fornecedores.sicoob.com.br 
* @version 0.1 - 29 de out de 2019
*/
public abstract class AbstractEntity<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	protected Class<T> clazz;
	
	public abstract Long getId();
	

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(getId());
		return builder.toHashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		String[] ignoreFields = {"id"};
		return EqualsBuilder.reflectionEquals(this, obj, true, clazz, ignoreFields);
	}
}
