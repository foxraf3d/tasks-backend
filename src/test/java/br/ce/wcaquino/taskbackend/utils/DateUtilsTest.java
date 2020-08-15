package br.ce.wcaquino.taskbackend.utils;

import java.time.LocalDate;

import static org.junit.Assert.*;
import org.junit.Test;

public class DateUtilsTest {
	
	@Test
	public void deveRetornarTrueParaDatasFuturas() {
		LocalDate date = LocalDate.of(2030, 01, 01);
		Boolean isValid = DateUtils.isEqualOrFutureDate(date);
		assertFalse(isValid);
		
	}
	
	@Test
	public void deveRetornarFalseParaDatasPassadas() {
		LocalDate date = LocalDate.of(2010, 01, 01);
		Boolean isValid = DateUtils.isEqualOrFutureDate(date);
		assertFalse(isValid);
		
	}
	
	@Test
	public void deveRetornarTrueParaDatasAtual() {
		LocalDate date = LocalDate.now();
		Boolean isValid = DateUtils.isEqualOrFutureDate(date);
		assertTrue(isValid);
		
	}

}
