package com.example.services.reservationService.service.reservation

import com.lowagie.text.*
import com.lowagie.text.pdf.PdfWriter
import java.awt.Color
import java.io.FileOutputStream


/***
 * Funkcja create tworzy plik pdf
 * i zapisuje go w tej ścieżce!!!
 */
class PdfGenerator {
    companion object {

        private val lorenIpsum1 = """Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo 
        |ligula eget dolor. Aenean massa. 
        |Cum sociis natoque penatibus et magnis dis
        |parturient montes, nascetur ridiculus mus. 
            
        |Donec quam felis, ultricies nec, pellentesque eu, 
        |pretium quis, sem. Nulla consequat massa quis enim. 
        |Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu.
        |In enim justo, rhoncus ut, imperdiet a, venenatis vitae, 
        |justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. 
        |Cras dapibus. Vivamus elementum semper nisi. 
        |Aenean vulputate eleifend tellus.""".trimMargin()

        private const val title = "Twój bilet"

        fun create(){
            val pdfOutputFile = FileOutputStream("ticket.pdf")
            val myPDFDoc = Document()
            val pdfWriter = PdfWriter.getInstance(myPDFDoc, pdfOutputFile)

            myPDFDoc.apply {
                addTitle("Ticket")
                open()
                val titleFont = Font(Font.COURIER, 20f, Font.BOLDITALIC, Color.BLACK)
                add(
                    Paragraph(title, titleFont).apply {
                        alignment = Element.ALIGN_CENTER
                    })

                add(Paragraph(Chunk.NEWLINE))
                add(Paragraph(lorenIpsum1))
                close()
            }
            pdfWriter.close()
        }
    }
}