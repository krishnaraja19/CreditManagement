package com.brixo.sytem.creditmanagement.model;

import java.io.FileNotFoundException;

import com.itextpdf.text.DocumentException;

@FunctionalInterface
public interface InvoivePdfWirter {
	public String pdfWriter(ApplicationPlanDetails planDetails) throws DocumentException, FileNotFoundException;
}
