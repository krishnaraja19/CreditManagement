package com.brixo.sytem.creditmanagement.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.Table;

import org.springframework.stereotype.Service;

import com.brixo.sytem.creditmanagement.model.Application;
import com.brixo.sytem.creditmanagement.model.ApplicationPlanDetails;
import com.brixo.sytem.creditmanagement.model.InvoivePdfWirter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class InvoicePdfWriterService implements InvoivePdfWirter{

	public String pdfWriter(ApplicationPlanDetails planDetails) throws DocumentException, FileNotFoundException {
		// TODO Auto-generated method stub
		Document document = new Document();
		String filename = planDetails.getApplication().getSsn()+planDetails.getApplication().getId()+".pdf";
		PdfWriter.getInstance(document, new FileOutputStream(filename));
		
		
		document.open();
		addTitle(document);
		 
		// customer reference information
		addCustomerReference(document,planDetails.getApplication());
	
		PdfPTable table = new PdfPTable(5);
		addTableHeader(table);
		addRows(table,planDetails);
		
		document.add(table);

		
		document.close();
		return filename;
	}
	
	public static void addTitle(Document layoutDocument) throws DocumentException
	{
		
		Font font = FontFactory.getFont(FontFactory.COURIER_BOLD, 16, BaseColor.BLACK);
		Chunk chunk = new Chunk("BRIXO INVOICE", font);
		Paragraph para1 = new Paragraph(chunk);
		 para1.setAlignment(Paragraph.ALIGN_CENTER);
		 layoutDocument.add(para1);
	}
	
	
	public static void addCustomerReference(Document layoutDocument,Application application) throws DocumentException
	{
		Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		Paragraph name = new Paragraph(new Chunk(application.getFirstName()+" "+application.getLastName(), font));
	    layoutDocument.add(name);
	    layoutDocument.add(new Paragraph(new Chunk(application.getSsn(), font)));
	    layoutDocument.add(new Paragraph(new Chunk(application.getEmail(), font)));
	    layoutDocument.add(new Paragraph(new Chunk("                 ", font)));
	}
	private void addTableHeader(PdfPTable table) {
	    Stream.of("Invoice Id", "Amortization", "Interest", "Invoice fee","Total Amount")
	      .forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell();
	        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        header.setBorderWidth(2);
	        header.setPhrase(new Phrase(columnTitle));
	        table.addCell(header);
	    });
	}
	private void addRows(PdfPTable table,ApplicationPlanDetails planDetails) {
	    table.addCell(""+planDetails.getId());
	    table.addCell(""+planDetails.getAmortization());
	    table.addCell(""+planDetails.getInterest());
	    table.addCell(""+planDetails.getInvoiceFee());
	    table.addCell(""+planDetails.getMonthlyPayableAmount());
	}

	
}
