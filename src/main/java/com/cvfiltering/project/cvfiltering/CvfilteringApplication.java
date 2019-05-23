package com.cvfiltering.project.cvfiltering;

import com.google.common.collect.Lists;
import decisiontree.DecisionTree;
import decisiontree.data.DataSample;
import decisiontree.data.SimpleDataSample;
import decisiontree.feature.Feature;
import decisiontree.label.BooleanLabel;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseBool;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.util.CsvContext;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static decisiontree.feature.PredicateFeature.newFeature;

@Data
@SpringBootApplication
public class CvfilteringApplication {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		SpringApplication.run(CvfilteringApplication.class, args);
		List<DataSample> trainingData = readData(true);
		DecisionTree tree = new DecisionTree();

		List<Feature> features = getFeatures();

		for (Object feature : features) {
			System.out.println("Featuree: " + feature);
		}

		tree.train(trainingData, features);

		// print tree after training
		tree.printTree();

        // read test data
        List<DataSample> testingData = readData(false);
        List<String> predictions = Lists.newArrayList();
        // classify all test data
        for (DataSample dataSample : testingData) {
            predictions.add(dataSample.getValue("id").get() + "," + tree.classify(dataSample).getPrintValue());
            System.out.println(predictions);
        }

        // write predictions to file
        FileWriter fileWriter = new FileWriter(new File("predictions3.csv"));
        fileWriter.append("id,qualified").append("\n");
        for (String prediction : predictions) {
            fileWriter.append(prediction).append("\n");
        }
        fileWriter.flush();
        fileWriter.close();




	}
		private static List<Feature> getFeatures() {
			Feature jobPost1 = newFeature("job_post", "Developer");
			Feature jobPost2 = newFeature("job_post", "DBA");
			Feature jobPost3 = newFeature("job_post", "Q.A");
			Feature qualification1= newFeature("qualification", "Bachelor");
			Feature qualification2= newFeature("qualification", "Master");
			Feature experience0 = newFeature("experience","0");
			Feature experience1 = newFeature("experience","1");
			Feature experience2 = newFeature("experience","2");

			Feature skill1 = newFeature("skill","High");
			Feature skill2 = newFeature("skill","Medium");
			Feature skill3 = newFeature("skill","Low");
			Feature interactivity1 = newFeature("interactivity","High");
			Feature interactivity2 = newFeature("interactivity","Medium");
			Feature interactivity3 = newFeature("interactivity","Low");

			return Arrays.asList(jobPost1, jobPost2, jobPost3,qualification1,qualification2,
					experience0,experience1,experience2,skill1,skill2,skill3,
					interactivity1,interactivity2,interactivity3);
		}

		private static List<DataSample> readData(boolean training) throws IOException {
			List<DataSample> data = Lists.newArrayList();
			String filename = training ? "train.csv" : "test.csv";
			InputStreamReader stream = new InputStreamReader(new FileInputStream(new File("src\\main\\java\\com\\cvfiltering\\project\\cvfiltering/"+filename)));
			try (ICsvListReader listReader = new CsvListReader(stream, CsvPreference.STANDARD_PREFERENCE);) {

				// the header elements are used to map the values to the bean (names must match)
				final String[] header = listReader.getHeader(true);

				List<Object> values;
				while ((values = listReader.read(getProcessors(training))) != null) {
					data.add(SimpleDataSample.newSimpleDataSample("qualified", header, values.toArray()));
				}
			}
//        System.out.println("Main test labels: " + data);
			return data;
		}

		private static CellProcessor[] getProcessors(boolean training) {
			if (training) {
				final CellProcessor[] processors = new CellProcessor[] {
						new Optional(new ParseInt()),
						new Optional(),
						new Optional(),
						new Optional(),
						new Optional(),
						new Optional(new ParseInt()),
						new Optional(),
						new Optional(),
						new Optional(new ParseBooleanLabel())
				};
				return processors;
			} else {
				final CellProcessor[] processors = new CellProcessor[] {
						new Optional(new ParseInt()),
						new Optional(),
						new Optional(),
						new Optional(),
						new Optional(),
						new Optional(new ParseInt()),
						new Optional(),
						new Optional(),
				};
				return processors;
			}
		}

		private static class ParseBooleanLabel extends ParseBool {

			public Object execute(final Object value, final CsvContext context) {
				Boolean parsed = (Boolean)super.execute(value, context);
				return parsed ? BooleanLabel.TRUE_LABEL : BooleanLabel.FALSE_LABEL;
			}

		}

}
