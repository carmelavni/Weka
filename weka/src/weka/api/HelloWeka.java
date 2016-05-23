package weka.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;
import weka.filters.unsupervised.attribute.Standardize;
import weka.classifiers.Evaluation;
import weka.classifiers.lazy.IBk;

public class HelloWeka {

	public static void main(String[] args) throws Exception{

		Instances test;
		Instances train;
		 // Read all the instances in the file (ARFF, CSV, XRFF, ...)
		test = new Instances(new BufferedReader(new FileReader("C:\\files\\test.arff")));
		train = new Instances(new BufferedReader(new FileReader("C:\\files\\train.arff")));
		 // Make the last attribute be the class
		System.out.println("pre-processing"); 
		//Creating the KNN
		KnnClassifier knn = new KnnClassifier(train, test);
		knn.setCrossValidate(true);
		knn.setClassIndex(test.numAttributes() -1);
		String res;
		if((res = knn.createEvaluation()) !=null)
			System.out.println(res);
		else 
		{
			System.out.println("processing..."); 
			knn.crossValidateModel( 10, new Random(100));
			System.out.println("done"); 
			knn.showResults();
		}	  
		//double[][] met=eval.confusionMatrix();
		//System.out.println(met.toString());

	}
}

