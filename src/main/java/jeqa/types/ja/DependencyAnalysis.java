

/* First created by JCasGen Wed Jan 25 19:24:52 JST 2017 */
package jeqa.types.ja;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSList;
import jeqa.types.Analysis;


/** CaboChaやKNPの結果を保持する
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * XML source: src/main/resources/desc/ts/typeSystem.xml
 * @generated */
public class DependencyAnalysis extends Analysis {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(DependencyAnalysis.class);
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
  protected DependencyAnalysis() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public DependencyAnalysis(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public DependencyAnalysis(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public DependencyAnalysis(JCas jcas, int begin, int end) {
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
  //* Feature: analysisResultTreeFormat

  /** getter for analysisResultTreeFormat - gets ツリー形式の解析結果
   * @generated
   * @return value of the feature 
   */
  public String getAnalysisResultTreeFormat() {
    if (DependencyAnalysis_Type.featOkTst && ((DependencyAnalysis_Type)jcasType).casFeat_analysisResultTreeFormat == null)
      jcasType.jcas.throwFeatMissing("analysisResultTreeFormat", "jeqa.types.ja.DependencyAnalysis");
    return jcasType.ll_cas.ll_getStringValue(addr, ((DependencyAnalysis_Type)jcasType).casFeatCode_analysisResultTreeFormat);}
    
  /** setter for analysisResultTreeFormat - sets ツリー形式の解析結果 
   * @generated
   * @param v value to set into the feature 
   */
  public void setAnalysisResultTreeFormat(String v) {
    if (DependencyAnalysis_Type.featOkTst && ((DependencyAnalysis_Type)jcasType).casFeat_analysisResultTreeFormat == null)
      jcasType.jcas.throwFeatMissing("analysisResultTreeFormat", "jeqa.types.ja.DependencyAnalysis");
    jcasType.ll_cas.ll_setStringValue(addr, ((DependencyAnalysis_Type)jcasType).casFeatCode_analysisResultTreeFormat, v);}    
   
    
  //*--------------*
  //* Feature: bunsetsuList

  /** getter for bunsetsuList - gets 文節のリスト
   * @generated
   * @return value of the feature 
   */
  public FSList getBunsetsuList() {
    if (DependencyAnalysis_Type.featOkTst && ((DependencyAnalysis_Type)jcasType).casFeat_bunsetsuList == null)
      jcasType.jcas.throwFeatMissing("bunsetsuList", "jeqa.types.ja.DependencyAnalysis");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DependencyAnalysis_Type)jcasType).casFeatCode_bunsetsuList)));}
    
  /** setter for bunsetsuList - sets 文節のリスト 
   * @generated
   * @param v value to set into the feature 
   */
  public void setBunsetsuList(FSList v) {
    if (DependencyAnalysis_Type.featOkTst && ((DependencyAnalysis_Type)jcasType).casFeat_bunsetsuList == null)
      jcasType.jcas.throwFeatMissing("bunsetsuList", "jeqa.types.ja.DependencyAnalysis");
    jcasType.ll_cas.ll_setRefValue(addr, ((DependencyAnalysis_Type)jcasType).casFeatCode_bunsetsuList, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    