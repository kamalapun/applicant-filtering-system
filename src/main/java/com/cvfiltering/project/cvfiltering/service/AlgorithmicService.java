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

    public List<String> algorithmicCompution(InputDataDTO inputDataDTO) throws IOException {
//

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
        List<DataSample> testingData = readTestData(inputDataDTO);

//        List<String> predictions = Lists.newArrayList();

        Map<String,Boolean> predicationMap = new HashMap<>();

        // classify all test data
        for (DataSample dataSample : testingData) {
           String emailAddress = (String) dataSample.getValue("email_address").get();
            boolean isQualified = Integer.parseInt(tree.classify(dataSample).getPrintValue()) == 1;
            if(predicationMap.containsKey(emailAddress)) {
                boolean isPreviousResultQualified = predicationMap.get(emailAddress);
                predicationMap.put(emailAddress, isQualified && isPreviousResultQualified);
            }else {
                predicationMap.put(emailAddress, isQualified);
            }
        }

        List<String> returnValue = new ArrayList<>();
        Set<Map.Entry<String, Boolean>> entrySet = predicationMap.entrySet();

        for (Map.Entry<String, Boolean> entry:entrySet){
            if(entry.getValue()) {
                returnValue.add(entry.getKey());
            }
        }
        // all go rythm
        // return returnValue;

        return null;

        // write predictions to file




    }



    private static List<DataSample> readData(boolean training) throws IOException {
        List<DataSample> data = Lists.newArrayList();
        //for training
        if (training == true) {
            String filename = "train.csv";
            InputStreamReader stream = new InputStreamReader(new FileInputStream(new File("src/main/java/com/cvfiltering/project/cvfiltering/service" + filename)));
            try (ICsvListReader listReader = new CsvListReader(stream, CsvPreference.STANDARD_PREFERENCE);) {

                // the header elements are used to map the values to the bean (names must match)
                final String[] header = listReader.getHeader(true);

                List<Object> values;
                while ((values = listReader.read(getProcessors(training))) != null) {
                    //                System.out.println(String.format("lineNo=%s, rowNo=%s, data=%s", listReader.getLineNumber(), listReader.getRowNumber(), values));
                    data.add(SimpleDataSample.newSimpleDataSample("qualified", header, values.toArray()));
                }
            }
            return data;
        } else {
            return null;
        }
    }

    private static List<DataSample> readTestData(InputDataDTO inputDataDTO) {
        List<String> jobpost = inputDataDTO.getJobPost();
        List<String> qualification = inputDataDTO.getQualification();
        List<Integer> experience = inputDataDTO.getExperience();
        List<String> skill = inputDataDTO.getSkill();
        List<String> interactivity = inputDataDTO.getInteractivity();

        return createTestDataSample(inputDataDTO);
    }

    private static List<DataSample> createTestDataSample(InputDataDTO inputDataDTO) {
        String filename = "train.csv";
        File file = new File("src/main/java/com/cvfiltering/project/cvfiltering/service" + filename);
        List<DataSample> dataSampleList = new ArrayList<>();
        try {

            BufferedReader br = new BufferedReader(new FileReader(file));
            String[] header = {"id", "job_post", "qualification", "experience", "skill", "interactivity"};

            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                String[] records = line.split(",");
                String[] sampleDataValue = new String[5];
                for (String jobpost : inputDataDTO.getJobPost()) {
                    sampleDataValue[0] = records[0];
                    sampleDataValue[1] = jobpost;
                    sampleDataValue[2] = records[2];
                    sampleDataValue[3] = records[3];
                    sampleDataValue[4] = records[4];
                    sampleDataValue[5] = records[5];
                }
                dataSampleList.add(SimpleDataSample.newSimpleDataSample("qualified", header, sampleDataValue));
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataSampleList;
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