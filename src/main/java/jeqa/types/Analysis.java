

/* First created by JCasGen Wed Jan 25 19:24:52 JST 2017 */
package jeqa.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 解析結果
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * XML source: src/main/resources/desc/ts/typeSystem.xml
 * @generated */
public class Analysis extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Analysis.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Analysis() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Analysis(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Analysis(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Analysis(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: analysisResult

  /** getter for analysisResult - gets 解析結果
   * @generated
   * @return value of the feature 
   */
  public String getAnalysisResult() {
    if (Analysis_Type.featOkTst && ((Analysis_Type)jcasType).casFeat_analysisResult == null)
      jcasType.jcas.throwFeatMissing("analysisResult", "jeqa.types.Analysis");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Analysis_Type)jcasType).casFeatCode_analysisResult);}
    
  /** setter for analysisResult - sets 解析結果 
   * @generated
   * @param v value to set into the feature 
   */
  public void setAnalysisResult(String v) {
    if (Analysis_Type.featOkTst && ((Analysis_Type)jcasType).casFeat_analysisResult == null)
      jcasType.jcas.throwFeatMissing("analysisResult", "jeqa.types.Analysis");
    jcasType.ll_cas.ll_setStringValue(addr, ((Analysis_Type)jcasType).casFeatCode_analysisResult, v);}    
  }

    