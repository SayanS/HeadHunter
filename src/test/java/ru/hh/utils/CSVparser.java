package ru.hh.utils;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public final class CSVparser {

    private static <T> void writeCSVData(List<T> emps) throws IOException {
        ICsvBeanWriter beanWriter = null;
        StringWriter writer = new StringWriter();
        try{
            beanWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);
            final String[] header = new String[]{"id","name","role","salary"};
            final CellProcessor[] processors = getProcessors();

            // write the header
            beanWriter.writeHeader(header);

            //write the beans data
            for(T emp : emps){
                beanWriter.write(emp, header, processors);
            }
        }finally{
            if( beanWriter != null ) {
                beanWriter.close();
            }
        }
        System.out.println("CSV Data\n"+writer.toString());
    }

    private static <T> List<T> readCSVToBean() throws IOException {
        ICsvBeanReader beanReader = null;
        List<T> emps = new ArrayList<T>();
        try {
            beanReader = new CsvBeanReader(new FileReader("employees.csv"),
                    CsvPreference.STANDARD_PREFERENCE);

            // the name mapping provide the basis for bean setters
            final String[] nameMapping = new String[]{"id","name","role","salary"};
            //just read the header, so that it don't get mapped to Employee object
            final String[] header = beanReader.getHeader(true);
            final CellProcessor[] processors = getProcessors();

            T tmp;

            while ((tmp = beanReader.read(T.class, nameMapping,
                    processors)) != null) {
                emps.add(tmp);
            }

        } finally {
            if (beanReader != null) {
                beanReader.close();
            }
        }
        return emps;
    }

    private static CellProcessor[] getProcessors() {
        final CellProcessor[] processors = new CellProcessor[] {
                new UniqueHashCode(), // ID (must be unique)
                new NotNull(), // Name
                new Optional(), // Role
                new NotNull() // Salary
        };
        return processors;
    }

}
