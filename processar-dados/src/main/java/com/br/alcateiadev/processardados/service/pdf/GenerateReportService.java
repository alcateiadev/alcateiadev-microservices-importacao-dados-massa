package com.br.alcateiadev.processardados.service.pdf;

import com.br.alcateiadev.processardados.domain.Venda;
import com.br.alcateiadev.processardados.gateway.repository.VendaRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class GenerateReportService {

    @Autowired
    private VendaRepository vendaRepository;

    public ByteArrayOutputStream execute(){

        List<Venda> vendas = vendaRepository.findVendas();
        List<Object[]> vendasSum = vendaRepository.findVendasSum();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, out);
            document.open();

            generateSoldItens(document, vendas);

            document.add(Chunk.NEWLINE);

            generateItensSoldAdded(document, vendasSum);

        }catch (DocumentException ex){
            System.err.println(ex.getMessage());
        }

        document.close();

        return out;
    }

    private void generateSoldItens(Document document, List<Venda> vendas) throws DocumentException {

        if (vendas == null || vendas.isEmpty()) {
            return;
        }

        Font fontCabecalho = new Font(Font.FontFamily.COURIER, 16, Font.NORMAL);
        Paragraph paragraph = new Paragraph("Lista de itens vendidos", fontCabecalho);
        paragraph.setAlignment(PdfPCell.ALIGN_CENTER);
        document.add(paragraph);

        document.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(6);

        Font fontH1 = new Font(Font.FontFamily.COURIER, 8, Font.NORMAL);
        PdfPCell pcell = new PdfPCell(new Phrase("Nome do Vendedor", fontH1));
        pcell.setBorder(Rectangle.NO_BORDER);
        pcell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(pcell);

        pcell.setPhrase(new Phrase("CPF", fontH1));
        table.addCell(pcell);

        pcell.setPhrase(new Phrase("Qtd Vendida", fontH1));
        table.addCell(pcell);

        pcell.setPhrase(new Phrase("Valor da Venda", fontH1));
        table.addCell(pcell);

        pcell.setPhrase(new Phrase("Nome da Loja", fontH1));
        table.addCell(pcell);

        pcell.setPhrase(new Phrase("Cidade da Loja", fontH1));
        table.addCell(pcell);

        PdfPCell pcellLines = new PdfPCell();
        pcellLines.setBorder(Rectangle.NO_BORDER);

        for (Venda venda : vendas) {
            pcellLines.setPhrase(new Phrase(venda.getVendedorNome(), fontH1));
            table.addCell(pcellLines);

            pcellLines.setPhrase(new Phrase(venda.getVendedorCpf(), fontH1));
            table.addCell(pcellLines);

            pcellLines.setPhrase(new Phrase(venda.getVendaQtd().toString(), fontH1));
            table.addCell(pcellLines);

            pcellLines.setPhrase(new Phrase(venda.getVendaValor().toString(), fontH1));
            table.addCell(pcellLines);

            pcellLines.setPhrase(new Phrase(venda.getLojaNome(), fontH1));
            table.addCell(pcellLines);

            pcellLines.setPhrase(new Phrase(venda.getLojaCidade(), fontH1));
            table.addCell(pcellLines);
        }

        document.add(table);
    }

    private void generateItensSoldAdded(Document document, List<Object[]> vendasSum) throws DocumentException {

        if (vendasSum == null || vendasSum.isEmpty()) {
            return;
        }

        Font fontCabecalho = new Font(Font.FontFamily.COURIER, 16, Font.NORMAL);
        Paragraph paragraph = new Paragraph("Lista de itens vendidos somados", fontCabecalho);
        paragraph.setAlignment(PdfPCell.ALIGN_CENTER);
        document.add(paragraph);

        document.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(4);

        Font fontH1 = new Font(Font.FontFamily.COURIER, 8, Font.NORMAL);
        PdfPCell pcell = new PdfPCell(new Phrase("Nome do Vendedor", fontH1));
        pcell.setBorder(Rectangle.NO_BORDER);
        pcell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(pcell);

        pcell.setPhrase(new Phrase("CPF", fontH1));
        table.addCell(pcell);

        pcell.setPhrase(new Phrase("Qtd Vendida", fontH1));
        table.addCell(pcell);

        pcell.setPhrase(new Phrase("Valor da Venda", fontH1));
        table.addCell(pcell);

        PdfPCell pcellLines = new PdfPCell();
        pcellLines.setBorder(Rectangle.NO_BORDER);

        for (Object[] venda : vendasSum) {
            pcellLines.setPhrase(new Phrase(venda[1].toString(), fontH1));
            table.addCell(pcellLines);

            pcellLines.setPhrase(new Phrase(venda[0].toString(), fontH1));
            table.addCell(pcellLines);

            pcellLines.setPhrase(new Phrase(venda[2].toString(), fontH1));
            table.addCell(pcellLines);

            pcellLines.setPhrase(new Phrase(venda[3].toString(), fontH1));
            table.addCell(pcellLines);

        }

        document.add(table);
    }
}
