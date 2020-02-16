package jingjs;

import java.io.StringReader;
import java.io.IOException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import com.thaiopensource.validate.Schema;
import com.thaiopensource.validate.SchemaReader;
import com.thaiopensource.validate.rng.SAXSchemaReader;
import com.thaiopensource.validate.ValidationDriver;
import com.thaiopensource.util.PropertyMap;
import de.inetsoftware.jwebassembly.api.annotation.Export;

public class App {

    public static String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
        + "<addressBook>"
        + "<card>"    
        + "<name>John Smith</name>"
        + "<email>js@example.com</email>"
        + "</card>"
        + "<card>"
        + "<name>Fred Bloggs</name>"
        + "<email>fb@example.net</email>"
        + "</card>"
        + "</addressBook>";

    public static String rng = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
        + "<grammar "
        + "xmlns=\"http://relaxng.org/ns/structure/1.0\""
        + "xmlns:a=\"http://relaxng.org/ns/compatibility/annotations/1.0\""
        + "datatypeLibrary=\"http://www.w3.org/2001/XMLSchema-datatypes\">"
        + "<start>"
        + "<element name=\"addressBook\" xmlns=\"http://relaxng.org/ns/structure/1.0\">"
        + "<zeroOrMore>"
        + "<element name=\"card\">"
        + "<element name=\"name\">"
        + "<text/>"
        + "</element>"
        + "<element name=\"email\">"
        + "<text/>"
        + "</element>"
        + "</element>"
        + "</zeroOrMore>"
        + "</element>"
        + "</start>"
        + "</grammar>";

    @Export
    public static void main(String[] args) {
        boolean flag = true;
        try {
            validate(xml, rng);
        } catch (SAXException e) {
            System.out.println(e);
        } catch (IOException e) {
            flag = false;
        }
    }

    public static void validate(String xmlData, String rngData) throws SAXException, IOException {
        SchemaReader schemaReader = SAXSchemaReader.getInstance();
        ValidationDriver vd = new ValidationDriver(PropertyMap.EMPTY, PropertyMap.EMPTY, schemaReader);
        vd.loadSchema(new InputSource(new StringReader(rngData)));
        vd.validate(new InputSource(new StringReader(xmlData)));
    }
}
