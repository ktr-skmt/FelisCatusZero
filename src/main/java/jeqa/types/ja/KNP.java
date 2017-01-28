

/* First created by JCasGen Wed Jan 25 19:24:52 JST 2017 */
package jeqa.types.ja;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** KNPによる解析結果
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * XML source: src/main/resources/desc/ts/typeSystem.xml
 * @generated */
public class KNP extends DependencyAnalysis {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(KNP.class);
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
  protected KNP() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public KNP(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public KNP(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public KNP(JCas jcas, int begin, int end) {
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
  //* Feature: analysisResultCase

  /** getter for analysisResultCase - gets KNPの格解析結果
   * @generated
   * @return value of the feature 
   */
  public String getAnalysisResultCase() {
    if (KNP_Type.featOkTst && ((KNP_Type)jcasType).casFeat_analysisResultCase == null)
      jcasType.jcas.throwFeatMissing("analysisResultCase", "jeqa.types.ja.KNP");
    return jcasType.ll_cas.ll_getStringValue(addr, ((KNP_Type)jcasType).casFeatCode_analysisResultCase);}
    
  /** setter for analysisResultCase - sets KNPの格解析結果 
   * @generated
   * @param v value to set into the feature 
   */
  public void setAnalysisResultCase(String v) {
    if (KNP_Type.featOkTst && ((KNP_Type)jcasType).casFeat_analysisResultCase == null)
      jcasType.jcas.throwFeatMissing("analysisResultCase", "jeqa.types.ja.KNP");
    jcasType.ll_cas.ll_setStringValue(addr, ((KNP_Type)jcasType).casFeatCode_analysisResultCase, v);}    
   
    
  //*--------------*
  //* Feature: analysisResultReference

  /** getter for analysisResultReference - gets KNPの照応解析結果
   * @generated
   * @return value of the feature 
   */
  public String getAnalysisResultReference() {
    if (KNP_Type.featOkTst && ((KNP_Type)jcasType).casFeat_analysisResultReference == null)
      jcasType.jcas.throwFeatMissing("analysisResultReference", "jeqa.types.ja.KNP");
    return jcasType.ll_cas.ll_getStringValue(addr, ((KNP_Type)jcasType).casFeatCode_analysisResultReference);}
    
  /** setter for analysisResultReference - sets KNPの照応解析結果 
   * @generated
   * @param v value to set into the feature 
   */
  public void setAnalysisResultReference(String v) {
    if (KNP_Type.featOkTst && ((KNP_Type)jcasType).casFeat_analysisResultReference == null)
      jcasType.jcas.throwFeatMissing("analysisResultReference", "jeqa.types.ja.KNP");
    jcasType.ll_cas.ll_setStringValue(addr, ((KNP_Type)jcasType).casFeatCode_analysisResultReference, v);}    
  }

    