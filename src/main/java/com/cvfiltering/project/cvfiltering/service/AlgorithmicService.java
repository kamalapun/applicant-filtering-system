package com.cvfiltering.project.cvfiltering.service;

import com.cvfiltering.project.cvfiltering.entity.InputDataDTO;
import com.google.common.collect.Lists;
import decisiontree.DecisionTree;
import decisiontree.data.DataSample;
import decisiontree.data.SimpleDataSample;
import decisiontree.feature.Feature;
import decisiontree.label.BooleanLabel;
import org.springframework.stereotype.Service;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseBool;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.util.CsvContext;

import java.io.*;
import java.util.*;

import static decisiontree.feature.PredicateFeature.newFeature;

@Service
public class AlgorithmicService {

    public String algorithmicCompution(InputDataDTO inputDataDTO) throws IOException {
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
        DataSample inputDataSample = createTestDataSample(inputDataDTO);

        return tree.classify(inputDataSample).getPrintValue();
    }



    private static List<DataSample> readData(boolean training) throws IOException {
        List<DataSample> data = Lists.newArrayList();
        //for training
        if (training == true) {
            String filename = "train.csv";
            InputStreamReader stream = new InputStreamReader(new FileInputStream(new File("src/main/java/com/cvfiltering/project/cvfiltering/" + filename)));
            try (ICsvListReader listReader = new CsvListReader(stream, CsvPreference.STANDARD_PREFERENCE);) {

                // the header elements are used to map the values to the bean (names must match)
                final String[] header = listReader.getHeader(true);

                List<Object> values;
                while ((values = listReader.read(getProcessors(training))) != null) {
                    data.add(SimpleDataSample.newSimpleDataSample("qualified", header, values.toArray()));
                }
            }
            return data;
        } else {
            return null;
        }
    }

    private static DataSample createTestDataSample(InputDataDTO inputDataDTO) {
        String[] header = {"job_post", "qualification", "experience", "skill", "interactivity"};

        String[] sampleDataValue = new String[5];

        sampleDataValue[0] = inputDataDTO.getJobPost();
        sampleDataValue[1] = inputDataDTO.getQualification();
        sampleDataValue[2] = String.valueOf(inputDataDTO.getExperience());
        sampleDataValue[3] = inputDataDTO.getSkill();
        sampleDataValue[4] = inputDataDTO.getInteractivity();

        return SimpleDataSample.newSimpleDataSample("qualified", header, sampleDataValue);
    }



    private static List<Feature> getFeatures() {
        Feature jobPost1 = newFeature("job_post", "Developer");
        Feature jobPost2 = newFeature("job_post", "DBA");
        Feature jobPost3 = newFeature("job_post", "Q.A");
        Feature qualification1 = newFeature("qualification", "Bachelor");
        Feature qualification2 = newFeature("qualification", "Master");
        Feature experience0 = newFeature("experience", "0");
        Feature experience1 = newFeature("experience", "1");
        Feature experience2 = newFeature("experience", "2");

        Feature skill1 = newFeature("skill", "High");
        Feature skill2 = newFeature("skill", "Medium");
        Feature skill3 = newFeature("skill", "Low");
        Feature interactivity1 = newFeature("interactivity", "High");
        Feature interactivity2 = newFeature("interactivity", "Medium");
        Feature interactivity3 = newFeature("interactivity", "Low");

        return Arrays.asList(jobPost1, jobPost2, jobPost3, qualification1, qualification2,
                experience0, experience1, experience2, skill1, skill2, skill3,
                interactivity1, interactivity2, interactivity3);
    }

    private static CellProcessor[] getProcessors(boolean training) {
        if (training) {
            final CellProcessor[] processors = new CellProcessor[]{
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
            final CellProcessor[] processors = new CellProcessor[]{
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
                Boolean parsed = (Boolean) super.execute(value, context);
                return parsed ? BooleanLabel.TRUE_LABEL : BooleanLabel.FALSE_LABEL;
            }

      }

}