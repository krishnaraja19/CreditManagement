package com.brixo.sytem.creditmanagement.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger LOGGER = LoggerFactory.getLogger(InvoicePdfWriterService.class);

	public String pdfWriter(ApplicationPlanDetails planDetails) throws DocumentException, FileNotFoundException {
		// TODO Auto-generated method stub
		LOGGER.info("Calling PDF Writer ");
		Document document = new Document();
		String filename = planDetails.getApplication().getSsn()+planDetails.getApplication().getId()+".pdf";
		PdfWriter.getInstance(document, new FileOutputStream(filename));
		
		
		document.open();
		//Adding title to pdf
		addTitle(document);
		 
		// Adding  customer reference information to pdf
		addCustomerReference(document,planDetails.getApplication());
		
		// Adding  invoice table information to pdf
		PdfPTable table = new PdfPTable(5);
		addTableHeader(table);
		addRows(table,planDetails);
		document.add(table);
		
		// Closing  invoice table information to pdf		
		document.close();
		return filename;
	}
	//creating invoice Heading
	public static void addTitle(Document layoutDocument) throws DocumentException
	{
		LOGGER.info("Writing PDF Debtor details ");
		Font font = FontFactory.getFont(FontFactory.COURIER_BOLD, 16, BaseColor.BLACK);
		Chunk chunk = new Chunk("BRIXO INVOICE", font);
		Paragraph para1 = new Paragraph(chunk);
		 para1.setAlignment(Paragraph.ALIGN_CENTER);
		 layoutDocument.add(para1);
	}
	//creating debtor details
	
	public static void addCustomerReference(Document layoutDocument,Application application) throws DocumentException
	{
		LOGGER.info("Writing PDF Debtor details ");
		Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		Paragraph name = new Paragraph(new Chunk(application.getFirstName()+" "+application.getLastName(), font));
	    layoutDocument.add(name);
	    layoutDocument.add(new Paragraph(new Chunk(application.getSsn(), font)));
	    layoutDocument.add(new Paragraph(new Chunk(application.getEmail(), font)));
	    layoutDocument.add(new Paragraph(new Chunk("                 ", font)));
	}
	//creating table header
	private void addTableHeader(PdfPTable table) {
		LOGGER.info("Writing PDF table header ");
	    Stream.of("Invoice Id", "Amortization", "Interest", "Invoice fee","Total Amount")
	      .forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell();
	        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        header.setBorderWidth(2);
	        header.setPhrase(new Phrase(columnTitle));
	        table.addCell(header);
	    });
	}
	//creating rows
	private void addRows(PdfPTable table,ApplicationPlanDetails planDetails) {
		LOGGER.info("Writing PDF table rows ");
	    table.addCell(""+planDetails.getId());
	    table.addCell(""+planDetails.getAmortization());
	    table.addCell(""+planDetails.getInterest());
	    table.addCell(""+planDetails.getInvoiceFee());
	    table.addCell(""+planDetails.getMonthlyPayableAmount());
	}

	
}
