package weka.api;

import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.J48;
import weka.core.Instances;
public class KnnClassifier extends Classifier {
	private IBk ibk;
	private Instances test;
	private Instances train;
	private Evaluation eval;
	
	/**
	 * Default construct, by default k=5
	 */
	public KnnClassifier(Instances test, Instances train){
		//setting the classifier attribute
		this.test=test;
		this.train=train;
		ibk = new IBk(5);
	}
	/**
	 * Construct
	 * @param k the k nearest neighbors 
	 */
	public KnnClassifier(int k, Instances test, Instances train){
		//setting the classifier attribute
		this.test=test;
		this.train=train;
		ibk = new IBk(k);
	}


	@Override
	public void buildClassifier(Instances arg0) throws Exception {
		// TODO Auto-generated method stub
		ibk.buildClassifier(arg0);
	}
	/**
	 * Set the cross validation option
	 * @param bool the option (true or false)
	 */
	public void setCrossValidate (boolean bool) {
		ibk.setCrossValidate(bool);
	}
	/**
	 * set the class index in test and train
	 * @param index - the class index
	 */
	public void setClassIndex(int index){ //TODO: check if needed here
		test.setClassIndex(index);
		train.setClassIndex(index);
	}
	/**
	 * Creates the Evaluation
	 * @return null if the evaluation created successfully, else returns the error message
	 */
	public String createEvaluation(){
		String result=null;
		if( train != null)
		{
			try {
				eval= new Evaluation(train);
			} catch (Exception e) {
				result="throw exception"+e.getMessage();
			} 
		}
		else
			result="train dataset is null";
		
		return result;
	}
	/**
	 * Runs the cross-validation on the evaluation
	 * @param numOfFolds - the number of folds for the cross-validation
	 * @param random - random number generator for randomization
	 */
	public void crossValidateModel(int numOfFolds, Random random){
		 if(numOfFolds > 0)
		 {
			 try {
				eval.crossValidateModel(ibk,test,numOfFolds,random);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	}
	/**
	 * Shows the results of the evaluation if the evaluation exist
	 */
	public void showResults(){
		if(eval != null)
		  System.out.println(eval.toSummaryString("\n Result:\n", true));
	}
}
